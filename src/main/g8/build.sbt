$if(dockerize.truthy)$enablePlugins(JavaAppPackaging)$endif$

ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file(".")).settings(
  name := "$name;format="norm"$",
  libraryDependencies ++= Seq(
    // "core" module - IO, IOApp, schedulers
    // This pulls in the kernel and std modules automatically.
    "org.typelevel" %% "cats-effect" % "3.3.12",
    // concurrency abstractions and primitives (Concurrent, Sync, Async etc.)
    "org.typelevel" %% "cats-effect-kernel" % "3.3.12",
    // standard "effect" library (Queues, Console, Random etc.)
    "org.typelevel" %% "cats-effect-std" % "3.3.12"
    $if(testlib-use-cats-effect-testing-specs2.truthy)$,
    "org.typelevel" %% "cats-effect-testing-specs2" % "1.4.0" % Test
    $endif$
    $if(testlib-use-munit-cats-effect-3.truthy)$,
    "org.typelevel" %% "munit-cats-effect-3" % "1.0.7" % Test
    $endif$
  ),
  $if(is-server.truthy)$
  libraryDependencies ++= Seq(
    "org.http4s" %% "http4s-dsl"          % "0.23.15",
    "org.http4s" %% "http4s-ember-server" % "0.23.15",
    "org.http4s" %% "http4s-ember-client" % "0.23.15",
    "org.http4s" %% "http4s-circe"        % "0.23.15"
  ),
  libraryDependencies ++= Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % "0.14.1"),
  $endif$
  $if(logging.truthy)$
  libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.6",
    "org.typelevel" %% "log4cats-slf4j"  % "2.4.0"
  ),
  $endif$
  $if(dockerize.truthy)$dockerExposedPorts ++= Seq(9000, 9001)$endif$
)
