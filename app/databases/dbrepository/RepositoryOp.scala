package databases.dbrepository

import databases.model.InsertedValue
import doobie.util.fragment.Fragment

import scala.concurrent.Future

trait RepositoryOp[A] {
  def findByPattern(pattern:String): Future[Option[A]]
  def all(pattern: Fragment): Future[Either[Throwable,Seq[A]]]
  def insert: Future[Either[Throwable,Seq[InsertedValue]]]
}
