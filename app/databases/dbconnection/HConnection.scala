package databases.dbconnection
//class HConnection (dbconnection: TConnConf)

import cats.effect._
import doobie._
import doobie.hikari._

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext
@Singleton
class HConnection @Inject() (dbconnection: TConnConf)/*extends IOApp*/ {

  // Resource yielding a transactor configured with a bounded connect EC and an unbounded
  // transaction EC. Everything will be closed and shut down cleanly after use.

  implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  val transactor: Resource[IO, HikariTransactor[IO]] =
    for {
      ce <- ExecutionContexts.fixedThreadPool[IO](32) // our connect EC
      be <- Blocker[IO]    // our blocking EC
      xa <- HikariTransactor.newHikariTransactor[IO](
        "org.postgresql.Driver",                        // driver classname
        "jdbc:postgresql://localhost:5432/football",   // connect URL
        "postgres",                                   // username
        "postgres",                                     // password
        ce,                                     // await connection here
        be                                      // execute JDBC operations here
      )
    } yield xa

/*
  def run(args: List[String]): IO[ExitCode] =
    transactor.use { xa =>
      // Construct and run your server here!
      for {
        n <- sql"select 42".query[Int].unique.transact(xa)
        _ <- IO(println(n))
      } yield ExitCode.Success

    }
*/
}