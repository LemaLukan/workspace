package q7;

public class Test {
	public static void main (String args[])
	{
		Vehicle v = new Vehicle("GM", 4, new Person("bob"));
		Truck t = new Truck("Ford", 8, new Person("fred"), 12.2, 4);
		System.out.println(v);
		System.out.println(t);
		System.out.println("are they owned by the same person? " + v.equals(t));
	}
}
