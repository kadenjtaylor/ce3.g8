package $package$

import cats.effect._
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.syntax._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.ember.server.EmberServerBuilder
import scala.concurrent.ExecutionContext.global
import Sorting._

// Needed to generate encoders/decoders from case classes
import io.circe.generic.auto._
import org.http4s.circe.CirceEntityCodec._

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
    .of[IO] {
      case GET -> Root / "hello" / name =>
        for {
          $if(logging.truthy)$
          _        <- logger.debug(s"Received param \$name")
          $endif$
          greeting <- HelloWorld.say(name)
          response <- Ok(greeting)
        } yield response
      case req @ POST -> Root / "sort" =>
        for {
          sortReq      <- req.as[SortRequest]
          $if(logging.truthy)$
          _            <- logger.debug(s"Received request \$sortReq")
          $endif$
          sortResponse <- Sorting.sortingEndpoint(sortReq)
          response     <- Ok(sortResponse)
        } yield response
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
