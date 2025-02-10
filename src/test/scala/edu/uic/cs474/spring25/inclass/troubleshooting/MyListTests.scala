package edu.uic.cs474.spring25.inclass.troubleshooting

class MyListsTests extends munit.FunSuite:
  test("MyList allows you to prepend elements."):
    // (1, 2, 3)
    val x = NonEmptyList[Int](1, NonEmptyList(2, NonEmptyList(3, EmptyList)))
    val y = x.prepend(4)
    assertEquals(
      y,
      NonEmptyList(
        4,
        NonEmptyList(1, NonEmptyList(2, NonEmptyList(3, EmptyList)))
      ),
      "MyList failed to prepend an element."
    )
end MyListsTests
