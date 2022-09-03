package $package$

import cats.effect.IO
import weaver._

object HelloWorldSuite extends SimpleIOSuite {

  // Easy to test stuff with no side effects...
  pureTest("Ensure 1 + 1 = 2") {
    expect(1 + 1 == 2)
  }

  // ... and just as easy to test effectful code
  test("make sure IO computes the right result") {
    for {
      greeting <- HelloWorld.say("nurse")
    } yield expect(greeting == "Hello nurse")
  }
}
