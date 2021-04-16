package controllers

import databases.model.FootballMatch
import services.TFootballDataServices

import scala.concurrent.{ExecutionContext, Future}
import databases.model.ImplicitConversion.matchGameFormat

import javax.inject.Inject

class FootballLeagueController@Inject()(cc: GetControllerComponents, services: TFootballDataServices)(
  implicit ec: ExecutionContext)
  extends GetBaseController(cc)  with ContentNegotiation{

  def footballMatches = GetAction.async{ implicit request =>

    /**
     * Si queremos logear los headers:
     *  ref.: https://docs.konghq.com/hub/kong-inc/oauth2/#upstream-headers
     *  request.headers.headers.map(x=>logger.warn(x._1 +" value " + x._2))
     */
    val headers:String = request.body.toString
    val dataResultsSeq:Future[Seq[FootballMatch]] = services.leagueGameServices
    dataResultsSeq.map( secgames =>
      proccessContentNegotiation[FootballMatch](secgames)
    )
  }

}
