package edu.uic.cs474.spring25.inclass.troubleshooting

sealed trait MyList[+T]:
  def prepend[T](elem: T): MyList[T] = ???
  def append[T](elem: T): MyList[T]  = ???
  def tailElement(): T               = ???
  def reverse(): MyList[T]           = ???
  def map()                          = ???
  def filter()                       = ???
  def foldLeft()                     = ???
  def foldRight()                    = ???
end MyList

final case class NonEmptyList[+T](head: T, tail: MyList[T]) extends MyList[T]
final case object EmptyList extends MyList[Nothing]
