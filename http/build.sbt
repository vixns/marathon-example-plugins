name := "http-plugin"

version := "1.0"

resolvers += "Mesosphere Public Repo" at "http://downloads.mesosphere.io/maven"

libraryDependencies ++= Seq(
  "mesosphere.marathon" %% "plugin-interface" % "1.4.1" % "provided",
  "log4j" % "log4j" % "1.2.17" % "provided",
  "org.slf4j" % "slf4j-api" % "1.7.12" % "provided"
)

