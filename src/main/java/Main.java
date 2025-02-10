class Main {
	public static void main(String[] args) {
		Dolphin[] dolphins = { new Dolphin("Joe"), new Dolphin("Elijah"), new Dolphin("Jane") };
		System.out.println(dolphins[0].name());
		dolphins[0] = new Dolphin("Austin");
		System.out.println(dolphins[0].name());

		Animal[] animals = dolphins;
		animals[0] = new Cheetah("Dash");
		System.out.println(dolphins[0]);
	}
}
