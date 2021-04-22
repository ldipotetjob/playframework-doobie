package config

import com.typesafe.config.ConfigFactory
import config.Utilities.getConfig
import databases.ConfigurationError
import databases.dbconnection.DBConfig

trait ConfigurationBroker {

  def getDBConf: DBConfig = {
    val postgresConf = getConfig{ConfigFactory.load()} { config =>
        DBConfig(
          config.getString("postgres.url"),
          config.getString("postgres.driver"),
          config.getString("postgres.usr"),
          config.getString("postgres.password")
        )
    }
    postgresConf match {
      case Right(postgresConf) => postgresConf
      case Left(fail) => fail match {
        case error: ConfigurationError =>
          throw new ConfigurationError(error.errorType,error.message,error.e)
        /** For compiler reason */
        case _: Exception =>
          throw new ConfigurationError("conf_error","Uknown Error")
      }
    }
  }
}