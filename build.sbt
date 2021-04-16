lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """playframework-doobie""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.3",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
    )
  )
TwirlKeys.templateFormats += ("csv" -> "databases.formats.CsvFormat")