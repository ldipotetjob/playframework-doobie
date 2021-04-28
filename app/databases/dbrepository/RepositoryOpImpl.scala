package databases.dbrepository

import akka.actor.ActorSystem
import databases.dbconnection.HConnection
import databases.model.FootballMatch
import doobie.implicits._
import doobie.util.fragment.Fragment
import play.libs.concurrent.CustomExecutionContext

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future


class GetExecutionContext @Inject()(actorSystem: ActorSystem)
  extends CustomExecutionContext(actorSystem, "repository.dispatcher")
/**
 * A trivial implementation for the Post Repository.
 *
 * A custom execution context is used here to establish that blocking operations should be
 * executed in a different thread than Play's ExecutionContext, which is used for CPU bound tasks
 * such as rendering.
 */
@Singleton
class RepositoryOpImpl @Inject()(dbConnection: HConnection)(implicit ec: GetExecutionContext) extends RepositoryOp[FootballMatch] {
  //type A = FootballMatch
  def findByPattern (pattern: String): Future[Option[FootballMatch]] = ???
  def all(pattern: Fragment) : Future[Either[Throwable, Seq[FootballMatch]]] = {
    dbConnection.transactor match {
        case Right(transactor) => transactor.use { xa =>
          for {
            //n <- sql"select leagueid,season,audience from footballgame where leagueid='PRML'".query[FootballMatch].to[Seq].transact(xa)
             n <- (fr"""select leagueid,season,audience from footballgame """ ++ pattern).query[FootballMatch].to[Seq].transact(xa)
          } yield n
        }.attempt.unsafeToFuture()
        case Left(fail) => Future.successful(Left(fail))
      }
    }
  }