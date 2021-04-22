package databases.dbrepository

import akka.actor.ActorSystem
import cats.effect.{ExitCode, IO}
import databases.dbconnection.HConnection
import databases.model.FootballMatch
import doobie.implicits._
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
class RepositoryOpImpl @Inject()(implicit ec: GetExecutionContext, db: HConnection) extends RepositoryOp[FootballMatch] {
  //type A = FootballMatch
  def findByPattern (pattern: String): Future[Option[FootballMatch]] = ???

  def all1(): Future[Either[Throwable, Seq[FootballMatch]]] =
  {
    Future {
      try {
        Right {
        //  "ywyw".toInt
          Seq(
            FootballMatch(league = "PRML", season = "2019/2020", audience = 160),
            FootballMatch(league = "CHAMPIONS", season = "2019/2020", audience = 12)
          )
        }
      } catch {
        case ex: NumberFormatException => Left(ex)
      }
    }
  }

  override def allWithDoobie () = ???

  def run (args: List[String]): IO[ExitCode] =
    db.transactor.use { xa =>
      // Construct and run your server here!
      for {
        n <- sql"select 42".query[Int].unique.transact(xa)
        _ <- IO(println(n))
      } yield ExitCode.Success
    }

  def all() : Future[Either[Throwable, Seq[FootballMatch]]] = {
    db.transactor.use { xa =>
      // Construct and run your server here!
      for {
        n <- sql"select leagueid,season,audience from footballgame".query[FootballMatch].to[Seq].transact(xa)
      } yield n
    }.attempt.unsafeToFuture()
  }
}