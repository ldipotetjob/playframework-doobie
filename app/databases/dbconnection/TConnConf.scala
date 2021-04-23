package databases.dbconnection

import databases.ConfigurationError

trait TConnConf {
  def dbConfig: Either[ConfigurationError,DBConfig]
}