package services

import databases.model.FootballMatch
import scala.concurrent.Future

trait TFootballDataServices {
  def leagueGameServices:Future[Seq[FootballMatch]]
}