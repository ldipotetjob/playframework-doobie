package services

import databases.dbrepository.RepositoryOpImpl
import databases.model.{FootballMatch, InsertedValue}
import doobie.util.fragment.Fragment

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class DataServices @Inject()(repository:RepositoryOpImpl) extends TFootballDataServices {
  def leagueGameServices(pattern: Fragment): Future[Either[Throwable,Seq[FootballMatch]]]= repository.all(pattern)
  def leagueGameServicesWithDoobie: Future[Seq[FootballMatch]] = ???

 def insertMatch : Future[Either[Throwable,Seq[InsertedValue]]] =  repository.insert
}
