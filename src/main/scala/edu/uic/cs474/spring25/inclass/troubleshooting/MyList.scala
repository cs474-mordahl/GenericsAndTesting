package edu.uic.cs474.spring25.inclass.troubleshooting

sealed trait MyList[+T]:
  def prepend()     = ???
  def append()      = ???
  def tailElement() = ???
  def reverse()     = ???
  def map()         = ???
  def filter()      = ???
  def foldLeft()    = ???
  def foldRight()   = ???
end MyList

final case class NonEmptyList[+T](head: T, tail: MyList[T]) extends MyList[T]
final case object EmptyList extends MyList[Nothing]

// MyList[Nothing] <: MyList[Int]

// COVARIANCE
// If A <: B, and T is covariant, then T[A] <: T[B]

// CONTRAVARIANCE
// If A <: B, and T is contravariant, then T[A] >: T[B]

trait Function[-A, +B]:
  def apply(a: A): B = ???

val plusOne: Function[Int, Int] = new Function[Int, Int]:
  override def apply(a: Int): Int = a + 1

trait A:
  val name: String
case class B(name: String) extends A:
  def setName(name: String): B = B(name)

case class C(name: String) extends A:
  def setName(name: String): C = C(name)

def transform[T, U](elem: T, f: Function[T, U]): U = f(elem)

@main
def main() =
  val b: B = B("Pichu")
  println(b.name)
  val s: String = transform(
    b,
    new Function[B, String]:
      override def apply(a: B): String = a.name
  )
  val operateOnA: Function[A, String] = new Function[A, String]:
    override def apply(a: A): String = a.name

  transform(b, operateOnA)
end main
