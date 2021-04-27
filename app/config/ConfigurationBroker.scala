package config

import com.typesafe.config.ConfigFactory
import config.Utilities.getConfig
import databases.FailureTrait
import databases.dbconnection.DBConfig

/** trait 2 get config file for connection*/
trait ConfigurationBroker {
    def getDBConf: Either[FailureTrait, DBConfig] =
      getConfig(ConfigFactory.load()){
        cf =>
          val url: String = cf.getString("postgres.url")
          val driv: String = cf.getString("postgres.driver")
          val pass: String = cf.getString("postgres.password")
          val usr: String = cf.getString("postgres.username")
          DBConfig(url, driv, usr, pass)
      }
}

