package services

import databases.dbrepository.RepositoryOpImpl
import databases.model.FootballMatch

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class DataServices @Inject()(repository:RepositoryOpImpl) extends TFootballDataServices {
  def leagueGameServices: Future[Either[Throwable,Seq[FootballMatch]]]= repository.all()
  def leagueGameServicesWithDoobie: Future[Seq[FootballMatch]] = ???
}
