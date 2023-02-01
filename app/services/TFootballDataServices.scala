package services

import databases.model.{FootballMatch, InsertedValue}
import doobie.util.fragment.Fragment

import scala.concurrent.Future

trait TFootballDataServices {
  def leagueGameServices(pattern: Fragment): Future[Either[Throwable,Seq[FootballMatch]]]
  def leagueGameServicesWithDoobie: Future[Seq[FootballMatch]]
  def insertMatch(): Future[Either[Throwable,Seq[InsertedValue]]]
}