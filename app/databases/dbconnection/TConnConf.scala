package databases.dbconnection

import databases.FailureTrait

trait TConnConf {
  def dbConfig: Either[FailureTrait,DBConfig]
}