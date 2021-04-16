package databases.dbrepository
import akka.actor.ActorSystem
import databases.model.{FootballMatch, Insertable}
import javax.inject.{Inject, Singleton}
import play.api.libs.concurrent.CustomExecutionContext

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
class RepositoryOpImpl @Inject()(implicit ec: GetExecutionContext) extends RepositoryOp[FootballMatch]{
  //type A = FootballMatch
  def findByPattern(pattern: String): Future[Option[FootballMatch]] = ???
  def all(): Future[Seq[FootballMatch]] =
    Future(
      Seq(
        FootballMatch(league = "PRML", season = "2019/2020", audience = 160),
        FootballMatch(league = "CHAMPIONS", season = "2019/2020", audience = 12)
      )
    )
}
