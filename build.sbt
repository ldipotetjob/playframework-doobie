lazy val doobieVersion = "1.0.0-RC1"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """playframework-doobie""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "org.tpolecat" %% "doobie-core"      % doobieVersion,
      "org.tpolecat" %% "doobie-hikari"    % doobieVersion,
      "org.tpolecat" %% "doobie-postgres"  % doobieVersion,
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
    )
  )
TwirlKeys.templateFormats += ("csv" -> "databases.formats.CsvFormat")

javaOptions += "-XX:+UseG1GC"