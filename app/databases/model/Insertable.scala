package databases.model

sealed trait Insertable
sealed trait Parseable
case class FootballMatch(league: String, season: String, audience: Int)
  extends Insertable with Parseable

case class InsertedValue(amount: Int)
  extends Insertable with Parseable