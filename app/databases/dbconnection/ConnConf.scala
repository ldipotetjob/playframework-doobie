package databases.dbconnection
import config.ConfigurationBroker
case class DBConfig(url: String, driver: String, usr: String, passw: String)

class ConnConf extends TConnConf with ConfigurationBroker {
 def dbConfig: DBConfig = getDBConf
}