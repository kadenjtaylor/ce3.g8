package $package$

import cats.effect.IOApp
import cats.effect.IO

$if(logging.truthy)$
// Logging imports
import org.typelevel.log4cats.SelfAwareStructuredLogger
import org.typelevel.log4cats.LoggerFactory
import dev.kaden.util.Logging.logging
$endif$

object Main extends IOApp.Simple {

  $if(logging.truthy)$
  val logger: SelfAwareStructuredLogger[IO] = LoggerFactory[IO].getLogger
  $endif$

  // This is your new "main"!
  def run: IO[Unit] = for {
    $if(logging.truthy)$
    _ <- logger.info("Running the main method!")
    $endif$
    greeting <- HelloWorld.say("Bob")
    _ <- IO.println(greeting)
  } yield ()
}
