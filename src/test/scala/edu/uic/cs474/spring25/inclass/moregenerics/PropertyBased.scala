package edu.uic.cs474.spring25.inclass.moregenerics

import munit.ScalaCheckSuite
import org.scalacheck.Prop.*

class IntegerSuite extends ScalaCheckSuite:
  /** Checks that addition is commutative for lots of generated i and js.
    */
  property("addition is commutative"):
    forAll: (i: String, j: String) =>
      println(s"Generated $i and $j")
      assertEquals((i + j).length(), (j + i).length())

  /** Determines that subtraction is not commutative.
    */
  property("subtraction is not commutative".fail):
    forAll: (i: Int, j: Int) =>
      assertEquals(i - j, j - i)

end IntegerSuite
