lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """playframework-doobie""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.3",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "org.tpolecat" %% "doobie-core"      % "0.12.1",
      "org.tpolecat" %% "doobie-hikari"    % "0.12.1",
      "org.tpolecat" %% "doobie-postgres"  % "0.12.1",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
    )
  )
TwirlKeys.templateFormats += ("csv" -> "databases.formats.CsvFormat")