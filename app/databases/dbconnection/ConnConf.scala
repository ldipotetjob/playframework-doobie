package databases.dbconnection
import config.ConfigurationBroker
import databases.ConfigurationError
case class DBConfig(url: String, driver: String, usr: String, passw: String)

class ConnConf extends TConnConf with ConfigurationBroker {
 def dbConfig: Either[ConfigurationError,DBConfig] = getDBConf
}