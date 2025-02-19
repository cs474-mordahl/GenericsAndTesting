import edu.uic.cs474.spring25.inclass.moregenerics.mylist.*

// A list with 2 elements (equivalent to 1, 2)
val c = 1 :: 2 :: 3 :: -6 :: EmptyList

// Associativity
// 1 + 2 + 3 = (1 + 2) + 3 = 1 + (2 + 3)
// 1 - 2 - 3 = (1 - 2) - 3 =/= 1 - (2 - 3)
// 1 :: 2 :: 3 :: EmptyList = 1 :: (2 :: (3 :: EmptyList))
// 1 ++ 2 ++ 3 ++ EmptyList = ((1 ++ 2) ++ 3) ++ EmptyList
// Demonstrate that prepend adds to the beginning of the list.
c.prepend(4)

c.prepend(4).length()
EmptyList.length()

EmptyList.append(1)
(1 :: 2 :: EmptyList).append(0)
c.append(9)
c.foldLeft("")(_ + _.toString)
c.foldRight[MyList[?]](EmptyList)((u, t) => u.append(t))
c.map(_ :: EmptyList)

/** */
