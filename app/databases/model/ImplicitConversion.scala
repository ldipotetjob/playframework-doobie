package databases.model

import play.api.libs.json.{Format, Json}

object ImplicitConversion {
  implicit val matchGameFormat: Format[FootballMatch] = Json.format[FootballMatch]
  implicit val insertedValue: Format[InsertedValue] = Json.format[InsertedValue]
}
