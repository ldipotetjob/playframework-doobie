package services

import databases.model.FootballMatch

import scala.concurrent.Future

trait TFootballDataServices {
  def leagueGameServices: Future[Either[Throwable,Seq[FootballMatch]]]
  def leagueGameServicesWithDoobie: Future[Seq[FootballMatch]]
}