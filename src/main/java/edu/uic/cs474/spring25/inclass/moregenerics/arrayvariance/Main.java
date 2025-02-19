package edu.uic.cs474.spring25.inclass.moregenerics.arrayvariance;

/**
 * Demonstrates the problem with covariant mutable arrays in Java.
 */
class Main {
	public static void main(String[] args) {
		Dolphin[] dolphins = { new Dolphin("Joe"), new Dolphin("Elijah"), new Dolphin("Jane") };
		System.out.println(dolphins[0].name());
		dolphins[0] = new Dolphin("Austin");
		System.out.println(dolphins[0].name());

		Animal[] animals = dolphins;
		// This allows us to add a Cheetah to a Dolphin array!
		animals[0] = new Cheetah("Dash");
		System.out.println(dolphins[0]);
	}
}
