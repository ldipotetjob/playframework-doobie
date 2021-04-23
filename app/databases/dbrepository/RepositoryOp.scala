package databases.dbrepository

import scala.concurrent.Future

trait RepositoryOp[A] {
  def findByPattern(pattern:String): Future[Option[A]]
  def all(): Future[Either[Throwable,Seq[A]]]
}
