package edu.uic.cs474.spring25.inclass.moregenerics.mylist

class MyListsTests extends munit.FunSuite:
  test("MyList allows you to prepend elements."):
    // (1, 2, 3)
    val x = 1 :: 2 :: 3 :: EmptyList
    val y = x.prepend(4)
    assertEquals(y, 4 :: x, "MyList failed to prepend an element.")

  test("MyList returns the tail element."):
    val x = "a" :: "b" :: "c" :: EmptyList
    assertEquals(x.tailElement(), Some("c"))
    assertEquals(EmptyList.tailElement(), None)

  test("MyList allows mapping over its elements."):
    val x = 1 :: 2 :: 3 :: EmptyList
    assertEquals(
      x.map(_ * 2),
      2 :: 4 :: 6 :: EmptyList,
      "Can double a list of ints"
    )
    assertEquals(
      EmptyList.map(x => x),
      EmptyList,
      "Mapping over an empty list is an empty list."
    )

  test("Reverse (1, 2, 3, 4)"):
    val x = 1 :: 2 :: 3 :: 4 :: EmptyList
    val y = 4 :: 3 :: 2 :: 1 :: EmptyList
    assertEquals(x.reverse(), y)

end MyListsTests
