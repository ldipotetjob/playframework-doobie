package databases

sealed trait FailureTrait extends Exception{
  val errorType: String
}

//for wrapped exception implemented in funcional way
case class ConnectionError(errorType: String="",message:String="",e: Throwable=null) extends FailureTrait
case class ExecuteStatementError(errorType: String="",message:String="",e: Throwable=null) extends FailureTrait

//exception that can  stop the API
case class ConnException(errorType: String="",message: String="", e: Throwable=null) extends FailureTrait //extends Exception(message: String, e: Throwable)
case class ConfigurationError(errorType: String="",message: String="", e: Throwable=null) extends FailureTrait