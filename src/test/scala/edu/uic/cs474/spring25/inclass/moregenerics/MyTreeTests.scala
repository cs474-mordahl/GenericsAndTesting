package edu.uic.cs474.spring25.inclass.moregenerics.myTree

import munit.ScalaCheckSuite
import org.scalacheck.Prop.*
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Gen
class MyTreeTests extends ScalaCheckSuite:

  def endsInLeafs[T](s: MyTree[T]): Boolean =
    s match
      case Node(value, left, right) =>
        endsInLeafs(left) && endsInLeafs(right)
      case Leaf => true
  end endsInLeafs

  given [T](using Arbitrary[T]): Arbitrary[MyTree[T]] =
    Arbitrary:
      // Generates Leaves using Gen.const.
      def leafGenerator: Gen[MyTree[T]] = Gen.const(Leaf)

      // Generates nodes, recursively calls treeGenerator.
      def nodeGenerator: Gen[MyTree[T]] = Gen.sized: size =>
        for
          v         <- arbitrary[T]
          leftTree  <- Gen.sized((z: Int) => Gen.resize(z / 2, treeGenerator))
          rightTree <- Gen.sized((z: Int) => Gen.resize(z / 2, treeGenerator))
        yield Node(v, leftTree, rightTree)

      // This is the generator we will use to generate trees.
      def treeGenerator: Gen[MyTree[T]] = Gen.sized(size =>
        if size <= 0 then leafGenerator
        else
          // 50/50 chance of generating a node or a leaf
          // Gen.oneOf(nodeGenerator, leafGenerator)

          // Bias the generator to create larger trees.
          Gen.frequency(9 -> nodeGenerator, 1 -> leafGenerator)
      )

      treeGenerator

  property("Every tree terminates in a leaf node"):

    forAll: (t: MyTree[Int]) =>
      println(t.length)
      assert(endsInLeafs(t))

    forAll: (t: MyTree[String]) =>
      println(t.length)
      assert(endsInLeafs(t))
end MyTreeTests
