package controllers

import databases.model.FootballMatch
import databases.model.ImplicitConversion.matchGameFormat
import play.api.libs.json.Json
import play.api.mvc.{AnyContent, Request, Result}
import services.TFootballDataServices
import databases.ConfigurationError
import play.api.mvc.Results.InternalServerError

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class FootballLeagueController@Inject()(cc: GetControllerComponents, services: TFootballDataServices)(
  implicit ec: ExecutionContext)
  extends GetBaseController(cc)  with ContentNegotiation{

  def processEitherCollection[A](eitherCollection: Either[Throwable, Seq[FootballMatch]])(implicit request: Request[AnyContent]): Result = eitherCollection match {
    case Left(exception) => {
      exception match {
        case ex: ConfigurationError => InternalServerError(Json.obj("status" ->InternalServerError.header.status, "message" -> s"Error ${ex.message} with malformed syntax"))
        case _ => BadRequest(Json.obj("status" ->BadRequest.header.status, "message" -> s"Error ${exception.getMessage} with malformed syntax"))
      }
    }
    case Right(seq) => proccessContentNegotiation[FootballMatch](seq)
  }
  def footballMatches = GetAction.async { implicit request =>

    /**
     * Si queremos logear los headers:
     * ref.: https://docs.konghq.com/hub/kong-inc/oauth2/#upstream-headers
     * request.headers.headers.map(x=>logger.warn(x._1 +" value " + x._2))
     */
    val dataResultsSeq: Future[Either[Throwable, Seq[FootballMatch]]] = services.leagueGameServices
    dataResultsSeq.map( secgames => processEitherCollection(secgames))
  }

}
