package edu.uic.cs474.spring25.inclass.moregenerics.myTree

sealed trait MyTree[+T]
case class Node[+T](value: T, left: MyTree[T], right: MyTree[T])
    extends MyTree[T]
case object Leaf extends MyTree[Nothing]
