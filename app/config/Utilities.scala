package config

import com.typesafe.config.Config
import databases.{ConfigurationError, FailureTrait}

import scala.util.{Failure, Success, Try}

object Utilities {
  def getConfig[T <: Config, R] (config: T)(block: T => R): Either[FailureTrait, R] = {
    Try(block(config)) match {
      case Success(expectedValue) => Right(expectedValue)
      case Failure(fail) => Left(ConfigurationError(
        "conf_error", s"Error reading configuration info. Details: ${fail.getMessage}", fail)
      )
    }
  }
}
