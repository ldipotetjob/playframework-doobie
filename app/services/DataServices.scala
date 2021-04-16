package services

import databases.dbrepository.RepositoryOpImpl
import databases.model.FootballMatch

import javax.inject.Inject
import scala.concurrent.Future

class DataServices @Inject()(repository: RepositoryOpImpl) extends TFootballDataServices {
  def leagueGameServices: Future[Seq[FootballMatch]] = repository.all()
}
