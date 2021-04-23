import com.typesafe.scalalogging.Logger
import org.slf4j.{LoggerFactory, Marker, MarkerFactory}
import play.api.http.{HttpErrorHandler, Status}
import play.api.libs.json.Json
import play.api.mvc.Results._
import play.api.mvc._
import databases.ConfigurationError
import javax.inject.Singleton
import scala.concurrent._
/** ref: https://github.com/playframework/playframework/issues/7019 */

@Singleton
class ErrorHandler extends  HttpErrorHandler with Status {

  val marker:Marker = MarkerFactory.getMarker("CRITICAL_ERR")

  val logger:Logger = Logger(LoggerFactory.getLogger(this.getClass))


  override def onClientError(request: RequestHeader,
                             statusCode: Int,
                             message: String): Future[Result] = {
    logger.debug(
      s"onClientError: statusCode = $statusCode, uri = ${request.uri}, message = $message")

    Future.successful {
      val result = statusCode match {
        case BAD_REQUEST =>
          BadRequest(Json.obj("status" ->BadRequest.header.status, "message" -> s"Url ${request.uri} with malformed syntax"))
        case FORBIDDEN =>
          Forbidden(Json.obj("status" ->Forbidden.header.status, "message" -> s"Url ${request.uri} with malformed syntax"))
        case NOT_FOUND =>
          NotFound(Json.obj("status" ->NotFound.header.status, "message" -> s"Url ${request.uri} Not found"))
        case clientError if statusCode >= 400 && statusCode < 500 =>
          Results.Status(statusCode).apply(Json.obj("status" ->statusCode, "message" -> s"Unknown error in this request: ${request.uri}"))
          NotFound(Json.obj("status" ->statusCode, "message" -> s"Url ${request.uri} Not found"))
        case nonClientError =>
          val msg =
            s"onClientError invoked with non client error status code $statusCode: $message"
          throw new IllegalArgumentException(msg)
      }
      result
    }
  }
  def onServerError(request: RequestHeader, exception: Throwable) = {
    Future.successful(
      exception match {
        case exconf:ConfigurationError =>
            InternalServerError(Json.obj(
            "status" ->InternalServerError.header.status,
            "message" ->s"Database configuration error: ${exconf.message}"
          ))
        case _ => InternalServerError(
          Json.obj("status" ->InternalServerError.header.status,
            "message" ->s"Unknown error: ${exception.getMessage}")
        )
      }
    )
  }
}