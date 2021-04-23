package databases.dbconnection
//class HConnection (dbconnection: TConnConf)

import akka.actor.ActorSystem
import cats.effect._
import doobie._
import doobie.hikari._
import play.libs.concurrent.CustomExecutionContext

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

class GetExecutionContext @Inject()(actorSystem: ActorSystem)
  extends CustomExecutionContext(actorSystem, "repository.dispatcher")

@Singleton
class HConnection @Inject()(dbconf: TConnConf)/*extends IOApp*/ {

  // Resource yielding a transactor configured with a bounded connect EC and an unbounded
  // transaction EC. Everything will be closed and shut down cleanly after use.

  implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  def transactor: Either[Throwable,Resource[IO, HikariTransactor[IO]]] =
    dbconf.dbConfig match {
      case Right(config) => Right{
        for {
          ce <- ExecutionContexts.fixedThreadPool[IO] (25)// our connect EC
          be <- Blocker[IO] // our blocking EC
          xa <- HikariTransactor.newHikariTransactor[IO](
            config.driver, //"org.postgresql.Driver"
            config.url, //"jdbc:postgresql://localhost:5432/football"
            config.usr, //"postgres"
            config.passw, //"postgres"
            ce, // await connection here
            be  // execute JDBC operations here
          )
        } yield xa
      }
      case Left(fail) => Left(fail)
    }
}