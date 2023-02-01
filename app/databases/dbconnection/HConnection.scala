package databases.dbconnection
//class HConnection (dbconnection: TConnConf)

import cats.effect._
import cats.effect.unsafe.implicits.global
import cats.implicits._
import doobie._
import doobie.implicits._
import doobie.hikari._

import javax.inject.{Inject, Singleton}

@Singleton
class HConnection @Inject()(dbconf: TConnConf)/*extends IOApp*/ {

  // Resource yielding a transactor configured with a bounded connect EC and an unbounded
  // transaction EC. Everything will be closed and shut down cleanly after use.

  //implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  def transactor: Either[Throwable,Resource[IO, HikariTransactor[IO]]] = {
    /** Traversing monad */
    dbconf.dbConfig.map { config =>
      for {
        ce <- ExecutionContexts.fixedThreadPool[IO](32) // our connect EC
        xa <- HikariTransactor.newHikariTransactor[IO](
          "org.postgresql.Driver",                        // driver classname
          "jdbc:postgresql://localhost:5432/football",   // connect URL
          "postgres",                                   // username
          "postgres",                                     // password
          ce                                      // await connection here
        )
      } yield xa
    }
  }

}