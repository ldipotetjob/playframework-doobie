import com.google.inject.AbstractModule
import databases.dbrepository.{RepositoryOp, RepositoryOpImpl}
import services.{DataServices, TFootballDataServices}

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[TFootballDataServices]).to(classOf[DataServices]).asEagerSingleton()
  }
}
