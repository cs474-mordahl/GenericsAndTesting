package edu.uic.cs474.spring25.inclass.moregenerics

/* MyList is covariant w.r.t. T, meaning that a MyList[B] is a subtype of
 * MyList[A] if B is a subtype of A */
sealed trait MyList[+T]:
  def prepend[U >: T](elem: U): MyList[U] = elem :: this
  def ::[U >: T](elem: U): MyList[U]      = NonEmptyList(elem, this)
  def append[U >: T](elem: U): MyList[U]  = ???
  def tailElement(): Option[T] = this match
    case NonEmptyList(h1, EmptyList) => Some(h1)
    case NonEmptyList(_, tail)       => tail.tailElement()
    case EmptyList                   => None
  def reverse(): MyList[T] =
    /** Example: (1, 2, 3, 4)
      * head = 1, tail = (2, 3, 4)
      * | head = 2, tail = (3, 4)
      *   | head = 3, tail = (4)
      *     | head = 4, tail = EmptyList
      *       | EmptyList.reverse().append(4)
      *         | EmptyList.append(4)
      *         > 4
      *       > 4
      *     > 4
      *   > (4, 3)
      * > (4, 3, 2)
      * (4, 3, 2, 1)
      */
    this match
      case NonEmptyList(head, tail) =>
        tail.reverse().append(head)
      case EmptyList => EmptyList
  def map[U](f: T => U): MyList[U] = this match
    case NonEmptyList(head, tail) => NonEmptyList(f(head), tail.map(f))
    case EmptyList                => EmptyList

  def filter(pred: T => Boolean): MyList[T] = this match
    case NonEmptyList(head, tail) =>
      if pred(head) then NonEmptyList(head, tail.filter(pred))
      else tail.filter(pred)
    case EmptyList => EmptyList

  def foldLeft()  = ???
  def foldRight() = ???
end MyList

final case class NonEmptyList[+T](head: T, tail: MyList[T]) extends MyList[T]
final case object EmptyList extends MyList[Nothing]
