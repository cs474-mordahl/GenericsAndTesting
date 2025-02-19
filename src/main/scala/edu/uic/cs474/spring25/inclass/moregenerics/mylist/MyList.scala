package secret.spring25.inclass.moregenerics.mylist

/** A simple list trait to provide common list operations.
  *
  * @tparam T
  *   The type of item in the list.
  */
sealed trait MyList[+T]:

  /** Adds an element to the beginning of this list.
    *
    * @param elem
    *   The element to prepend.
    * @return
    *   A new list, representing the old list with `elem` added to the front and
    *   `this` at the end.
    */
  def prepend[U >: T](elem: U): MyList[U] = elem :: this

  /** A right-associative operator, equivalent to `prepend`.
    *
    * @param elem
    *   The element to prepend to the list.
    * @return
    *   A new list with `elem` as the head and `this` as the tail.
    */
  def ::[U >: T](elem: U): MyList[U] = NonEmptyList(elem, this)

  /** Adds an element to the end of a list.
    *
    * @param elem
    *   The element to append to the list.
    * @return
    *   A list identical to `this` but with `elem` at the end.
    */
  def append[U >: T](elem: U): MyList[U] =
    // (1, 2, 3).append(4) = (1, 2, 3, 4)
    this match // This is pattern matching, a very powerful Scala utility.
      case NonEmptyList(head, tail) => head :: tail.append(elem)
      case EmptyList                => elem :: EmptyList

  /** Gets the tail element of a list.
    *
    * @return
    *   `None` if the list is empty, `Some(x)` otherwise, where `x: T` is the
    *   last element of the list.
    */
  def tailElement(): Option[T] = this match
    case NonEmptyList(h1, EmptyList) => Some(h1)
    case NonEmptyList(_, tail)       => tail.tailElement()
    case EmptyList                   => None

  /** Reverses a list.
    *
    * @example
    *   {{{
    * val x = 1 :: 2 :: 3 :: EmptyList
    * x.reverse // 3 :: 2 :: 1 :: EmptyList
    *   }}}
    *
    * @return
    *   `this` but reversed.
    */
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

  /** Transforms a `MyList[T]` to a `MyList[U]` by applying `f` to each element.
    *
    * @tparam U
    *   The type of item contained in the new `MyList`.
    * @param f
    *   The function to apply to each element of `this`.
    * @return
    *   A new `MyList` that is the result of applying `f` to each element in
    *   `this`.
    *
    * @example
    *   {{{
    * val x = 1 :: 2 :: 3 :: EmptyList
    * x.map(_ * 2) // 2 :: 4 :: 6 :: EmptyList
    *   }}}
    *
    * @example
    *   {{{
    * val y = 1 :: 2 :: 3 :: EmptyList
    * y.map(_.toString) // "1" :: "2" :: "3" :: EmptyList
    *   }}}
    */
  def map[U](f: T => U): MyList[U] = this match
    case NonEmptyList(head, tail) => NonEmptyList(f(head), tail.map(f))
    case EmptyList                => EmptyList

  /** Takes a predicate, and returns a sublist of `this` with only the elements
    * where the predicate is true.
    *
    * @param pred
    *   A function of type `T => Boolean`.
    * @return
    *   A new list, containing only the elements of `this` that the predicate
    *   returns `true` for.
    *
    * @example
    *   {{{
    * val x = 1 :: 2 :: 3 :: EmptyList
    * x.filter(_ % 2 == 0) // 2 :: EmptyList
    *   }}}
    */
  def filter(pred: T => Boolean): MyList[T] = this match
    case NonEmptyList(head, tail) =>
      if pred(head) then NonEmptyList(head, tail.filter(pred))
      else tail.filter(pred)
    case EmptyList => EmptyList

  /** @return
    *   The length of the list as an [[scala.Int Int]].
    */
  def length(): Int = this match
    case NonEmptyList(head, tail) => 1 + tail.length()
    case EmptyList                => 0

  /** @tparam U
    *   The type of the aggregator; the final aggregator is the return value.
    * @param aggregator
    *   The initial value to use.
    * @param combiner
    *   A function that combines the aggregator with elements of the `MyList` to
    *   produce a new aggregator.
    * @return
    *   The final state of the aggregator.
    *
    * @example
    *   {{{
    *  val x = 1 :: 2 :: 3 :: EmptyList
    *  x.foldLeft[Int](0)(_ + _)
    *  /* Does the following steps:
    *     0 + 1 = 1
    *     1 + 2 = 3
    *     3 + 3 = 6
    *     returns 6
    *  */
    *   }}}
    *
    * @example
    *   {{{
    *  val y = 1 :: 2 :: 3 :: EmptyList
    *  y.foldLeft[String]("")(_ + _.toString)
    *  /* Does the following steps:
    *     "" + "1"   = "1"
    *     "1" + "2"  = "12"
    *     "12" + "3" = "123"
    *     returns "123"
    *  */
    *   }}}
    */
  def foldLeft[U](aggregator: U)(combiner: (U, T) => U): U =
    // format: off
    /**
      * Summation with foldLeft:
        (1, 2, 3)
        aggregator: 0
        combiner: _ + _
        0 + 1 = 1
        1 + 2 = 3
        3 + 3 = 6
      */
    // format: on
    this match
      case NonEmptyList(head, tail) =>
        tail.foldLeft(combiner(aggregator, head))(combiner)
      case EmptyList => aggregator

  /** Like [[foldLeft]], but starts from the right side of the list.
    *
    * @param aggregator
    *   The initial value to combine with the rightmost element of `this`/
    * @param combiner
    *   The function that combines values in `MyList`.
    * @return
    *   The final state of the aggregator.
    *
    * @example
    *   {{{
    * val x = 1 :: 2 :: 3 :: EmptyList
    * // The following rebuilds the list.
    * val y = x.foldRight(EmptyList)((u, t) => t :: u)
    * y // 1 :: 2 :: 3 :: EmptyList
    *
    * // The following reverses the list.
    * val z = x.foldRight(EmptyList)((u, t) => u.append(t))
    * z // 3 :: 2 :: 1 :: EmptyList
    *   }}}
    */
  def foldRight[U](aggregator: U)(combiner: (U, T) => U): U = this match
    case NonEmptyList(head, tail) =>
      val rev = this.reverse()
      rev.foldLeft(aggregator)(combiner)
    case EmptyList => aggregator
end MyList

/** A non-empty list.
  *
  * @tparam T
  *   The type of item in the list.
  * @param head
  *   The first element of the list.
  * @param tail
  *   The [[edu.uic.cs474.spring25.inclass.moregenerics.mylist.MyList MyList]]
  *   that represents the end of the list.
  */
final case class NonEmptyList[+T](head: T, tail: MyList[T]) extends MyList[T]

/** An empty list.
  */
case object EmptyList extends MyList[Nothing]
