package edu.uic.cs474.spring25.inclass.troubleshooting

import munit.ScalaCheckSuite
import org.scalacheck.Prop.*

class IntegerSuite extends ScalaCheckSuite:

  property("addition is commutative"):
    forAll: (n1: Int, n2: Int) =>
      n1 + n2 == n2 + n1

  property("0 is the identity of addition"):
    forAll: (n: Int) =>
      n + 0 == n

  property("subtraction is commutative"):
    forAll: (n1: Int, n2: Int) =>
      n1 - n2 == n2 - n1

  property("subtration is associative"):
    forAll: (n1: Int, n2: Int, n3: Int) =>
      (n1 - n2) - n3 == n1 - (n2 - n3)

end IntegerSuite
