package edu.uic.cs474.spring25.inclass.moregenerics.arrayvariance;

/**
 * A basic Animal class.
 */
class Animal {
	/**
	 * The name of the Animal.
	 */
	private String name;

	/**
	 * Getter for name.
	 */
	public String name() {
		return this.name;
	}

  /**
	 * Constructs an Animal with a given name.
	 * 
	 * @param name The name of the animal.
	 */
	public Animal(String name) {
		this.name = name;
	}
}
