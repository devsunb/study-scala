scalaVersion := "2.12.6" // Also supports 2.11.x

val http4sVersion = "0.18.17"

// Only necessary for SNAPSHOT releases
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.slf4j" % "slf4j-simple" % "1.6.4"
)

scalacOptions ++= Seq("-Ypartial-unification")
