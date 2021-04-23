package config

import com.typesafe.config.{Config, ConfigFactory}
import config.Utilities.getConfig
import databases.ConfigurationError
import databases.dbconnection.DBConfig

import scala.util.{Failure, Success, Try}

trait ConfigurationBroker {
    def getDBConf: Either[ConfigurationError, DBConfig] = {
        Try {
            val cf: Config = ConfigFactory.load()
            val url: String = cf.getString("postgres.url")
            val driv: String = cf.getString("postgres.driver")
            val pass: String = cf.getString("postgres.password")
            val usr: String = cf.getString("postgres.username")
            DBConfig(url, driv, usr, pass)
        } match {
          case Success(dbbconf) => Right(dbbconf)
          case Failure(exception) => Left( ConfigurationError("ErrorConf",
              s"Error reading configuration info. Details: ${exception.getMessage}",
              exception))
        }
      }
   }
