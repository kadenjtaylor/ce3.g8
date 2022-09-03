package $package$

import cats.effect.IO

object HelloWorld {

  def say(name: String): IO[String] = IO.delay(s"Hello \$name")
}
