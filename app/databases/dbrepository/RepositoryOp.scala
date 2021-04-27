package databases.dbrepository

import doobie.util.fragment.Fragment

import scala.concurrent.Future

trait RepositoryOp[A] {
  def findByPattern(pattern:String): Future[Option[A]]
  def all(pattern: Fragment): Future[Either[Throwable,Seq[A]]]
}
