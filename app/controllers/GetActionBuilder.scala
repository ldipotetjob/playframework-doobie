package controllers

import databases.model.FootballMatch
import databases.model.ImplicitConversion._
import play.api.Logging
import play.api.http.FileMimeTypes
import play.api.i18n.{Langs, MessagesApi}
import play.api.libs.json.{JsValue, Json, Reads}
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

/**
 * Action builder for simple Request.
 *
 * We only process request with content-type
 * Content-type specifications: https://tools.ietf.org/html/rfc7231#section-3.1.1.5
 * So here only is processed request
 *
 */
//TODO => implement robust solution with (sealed class ==> pattern matching)
class GetActionBuilder@Inject()(parser: BodyParsers.Default)(implicit ec: ExecutionContext)
  extends ActionBuilderImpl(parser) with Logging {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    //request.contentType match {
    request.contentType.fold{Some("text/plain")}{content=>Some(content)} match {
      case Some("text/plain") => logger.info("Simple HttpResquest")
        logger.info("Correct Content-type in Header: text/plain")
        block(request) recover { case reqerror: Exception =>
          //we are dealing here with and unhandled exception
          logger.error("Unexpected error processing HttpRequest",reqerror)
          Results.InternalServerError
        }
      case Some(_) => Future.successful {
        val ownContentType:String = request.contentType.getOrElse("Unespecified Content-Type")
        logger.error(s"WRONG Content-Type in Header, must be:text/plain and it is: $ownContentType")
        Results.Forbidden(
          Json.obj("status" ->"KO", "message" -> s"Content-Type is: $ownContentType and must be: text/plain ")
        )
      }
      /*
    case None => Future.successful {
      logger.error("Bad request, doesn't has Content-Type")
      //the client has sent a request without Content-Type so we build a Response Code 400
      Results.BadRequest(Json.obj("status" ->"KO", "message" -> "Bad request, doesn't has Content-Type"))
    }*/
    }
  }
}

/**
 * Action builder for POST Request.
 *
 * Only treated POST request with application/json
 *
 * Fine grain error granularity in which:
 *
 * - we throw Forbidden if we have a content-type but it isn't an application/json .
 * - we throw BadRequest if the request doesn't has content-type
 *
 * Status-Code Definitions : http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
 *
 * Remember: user-id & password should be as entity header
 * https://stackoverflow.com/questions/36617948/play-2-4-actionbuilder-actionfunction-bodyparsers-and-json?rq=1
 */

class JsonActionBuilder@Inject()(parser: BodyParsers.Default)(implicit ec: ExecutionContext)
  extends ActionBuilderImpl(parser)  with Logging {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {

    request.contentType match {

      case Some("application/json") => logger.info("Simple HttpResquest")
        logger.info("REQUEST: Json HttpRequest/ Json/Application")

        jsonBody(request.body) match {

          /**
           * we don't want to track the error in case of POST with a wrong json
           * and you want to consider only some specific Model add some many Cases as
           * model you neeed
           */

          case Some(js) if jsonValidator[FootballMatch](js).isSuccess => {
            //jsValue.as[com.ldg.model.Match] Validate de Json
            block(request) recover { case reqerror: Exception =>
              //we are dealing here with and unhandled exception
              logger.error("Unexpected error  processing HttpRequest", reqerror)
              Results.
                InternalServerError(
                  Json.obj("status" ->Results.InternalServerError.header.status, "message" -> "Unexpected error  processing HttpRequest")
                )
            }
          }
          case _ => Future.successful {
            //the client has sent a request without Content-Type so we build a Response Code 400
            logger.error("Malformed Json or Json Not Allowed")
            Results.BadRequest(Json.obj("status" -> Results.BadRequest.header.status, "message" -> "Malformed Json or Json Not Allowed"))
          }

        }

      case Some(_) => Future.successful {
        val ownContentType: String = request.contentType.getOrElse("Unespecified Content-Type")
        logger.error(s"WRONG Content-Type in Header, must be: application/json and it is: $ownContentType")
        Results.Forbidden(Json.obj("status" -> Results.Forbidden.header.status, "message" -> s"Content-Type is: $ownContentType and must be: application/json "))
      }
      case None => Future.successful {
        logger.error("Bad request, doesn't has Content-Type")
        //the client has sent a request without Content-Type so we build a Response Code 400
        Results.BadRequest(Json.obj("status" -> Results.BadRequest.header.status, "message" -> "Bad request, doesn't has Content-Type"))
      }
    }
  }

  def jsonValidator[A](optJsvalue:JsValue)(implicit redable: Reads[A]): Try[A] =  {
    Try(
      //can deal an IOException
      optJsvalue.as[A]
    )
  }

  def jsonBody[A](body: A)= body match {
    case jsvalue: JsValue => Some(jsvalue)
    case anycontent: AnyContent => anycontent.asJson
    case _ => None
  }

}

case class GetControllerComponents @Inject()(
   getActionBuilder: GetActionBuilder,
   jsonActionBuilder: JsonActionBuilder,
   messagesApi: MessagesApi,
   parsers: PlayBodyParsers,
   actionBuilder: DefaultActionBuilder,
   langs: Langs,
   fileMimeTypes: FileMimeTypes,
   executionContext: scala.concurrent.ExecutionContext)
   extends ControllerComponents


//extends BuilderComponents
class GetBaseController @Inject()(gcc: GetControllerComponents)
  extends BaseController {
  override protected def controllerComponents: ControllerComponents = gcc
  def GetAction: GetActionBuilder = gcc.getActionBuilder
}