import com.google.inject.AbstractModule
import databases.dbconnection.{ConnConf, TConnConf}
import databases.dbrepository.{RepositoryOp, RepositoryOpImpl}
import databases.model.FootballMatch
import services.{DataServices, TFootballDataServices}

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[TFootballDataServices]).to(classOf[DataServices]).asEagerSingleton()
    bind(classOf[TConnConf]).to(classOf[ConnConf]).asEagerSingleton()
  }
}
