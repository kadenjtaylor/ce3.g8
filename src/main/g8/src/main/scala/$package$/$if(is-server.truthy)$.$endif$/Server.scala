package $package$

import cats.effect._
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.syntax._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.ember.server.EmberServerBuilder
import scala.concurrent.ExecutionContext.global

$if(logging.truthy)$
// Logging imports
import org.typelevel.log4cats.SelfAwareStructuredLogger
import org.typelevel.log4cats.LoggerFactory
import dev.kaden.util.Logging.logging
$endif$

object Server extends IOApp {

  $if(logging.truthy)$
  val logger: SelfAwareStructuredLogger[IO] = LoggerFactory[IO].getLogger
  $endif$

  val helloWorldService = HttpRoutes
    .of[IO] { case GET -> Root / "hello" / name =>
      HelloWorld.say(name).flatMap(Ok(_))
    }
    .orNotFound

  def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder
      .default[IO]
      .withHttpApp(helloWorldService)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
}
