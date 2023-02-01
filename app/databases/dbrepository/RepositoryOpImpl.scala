package databases.dbrepository

import databases.dbconnection.HConnection
import databases.model.{FootballMatch, InsertedValue}
import doobie.implicits._
import doobie.util.fragment.Fragment

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future
import cats.effect.unsafe.implicits.global

/**
 * A trivial implementation for the Post Repository.
 *
 * A custom execution context is used here to establish that blocking operations should be
 * executed in a different thread than Play's ExecutionContext, which is used for CPU bound tasks
 * such as rendering.
 */
@Singleton
class RepositoryOpImpl @Inject()(dbConnection: HConnection) extends RepositoryOp[FootballMatch] {
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

  def insert: Future[Either[Throwable, Seq[InsertedValue]]] = {
    dbConnection.transactor match {
      case Right(transactor) => transactor.use { xa =>
        for {
          //n <- sql"select leagueid,season,audience from footballgame where leagueid='PRML'".query[FootballMatch].to[Seq].transact(xa)
          n <- sql"""INSERT INTO footballgame (leagueid,season,audience,dategame,playermin) values ('PREMIER222','2020/2021',200,'2022-10-19 10:23:54', '{{neymar2,"323/,3232"},{cesfab2,"323/,3232323/,3232"},{ronaldo2,"323/,3232"}}')""".update.run.transact(xa)
        } yield InsertedValue(n)::Nil

      }.attempt.unsafeToFuture()
      case Left(fail) => Future.successful(Left(fail))
    }
  }
}