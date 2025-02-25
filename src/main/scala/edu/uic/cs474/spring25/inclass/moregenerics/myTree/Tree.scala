package edu.uic.cs474.spring25.inclass.moregenerics.myTree

sealed trait MyTree[+T]:
  def length: Int = this match
    case Node(value, left, right) => left.length + right.length
    case Leaf                     => 1

case class Node[+T](value: T, left: MyTree[T], right: MyTree[T])
    extends MyTree[T]
case object Leaf extends MyTree[Nothing]
