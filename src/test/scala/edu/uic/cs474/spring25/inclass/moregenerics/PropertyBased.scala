package edu.uic.cs474.spring25.inclass.moregenerics

import munit.ScalaCheckSuite
import org.scalacheck.Prop.*

class IntegerSuite extends ScalaCheckSuite:
  /** Checks that addition is commutative for lots of generated i and js.
    */
  property("addition is commutative"):
    forAll: (i: Int, j: Int) =>
      assertEquals(i + j, j + i)

  /** Determines that subtraction is not commutative.
    */
  property("subtraction is not commutative".fail):
    forAll: (i: Int, j: Int) =>
      assertEquals(i - j, j - i)

end IntegerSuite
