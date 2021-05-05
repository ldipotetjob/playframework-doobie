package controllers

import databases.ConfigurationError
import databases.model.{FootballMatch, Parseable}
import databases.model.ImplicitConversion.matchGameFormat
import doobie.implicits.toSqlInterpolator
import play.api.libs.json.{Json, Writes}
import play.api.mvc.{AnyContent, Request, Result}
import services.TFootballDataServices

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag

class FootballLeagueController@Inject()(cc: GetControllerComponents, services: TFootballDataServices)(
  implicit ec: ExecutionContext)
  extends GetBaseController(cc)  with ContentNegotiation{

  def processEitherCollection[A <: Parseable](eitherCollection: Either[Throwable, Seq[A]])
                                             (implicit request: Request[AnyContent],
                                              tag: ClassTag[A],
                                              writeable: Writes[A]): Result = eitherCollection match {
    case Left(exception) => {
      exception match {
        case ex: ConfigurationError => InternalServerError(Json.obj("status" ->InternalServerError.header.status, "message" -> s"Error ${ex.message} with malformed syntax"))
        case _ => BadRequest(Json.obj("status" ->BadRequest.header.status, "message" -> s"Error ${exception.getMessage} with malformed syntax"))
      }
    }
    case Right(seq) => proccessContentNegotiation[A](seq)
  }
  def footballMatches(pattern: String) = GetAction.async { implicit request =>

    /**
     * Si queremos logear los headers:
     * ref.: https://docs.konghq.com/hub/kong-inc/oauth2/#upstream-headers
     * request.headers.headers.map(x=>logger.warn(x._1 +" value " + x._2))
     */
    val dataResultsSeq: Future[Either[Throwable, Seq[FootballMatch]]] = services.leagueGameServices(fr"where leagueid=$pattern")
    dataResultsSeq.map( secgames => processEitherCollection(secgames))
  }
}