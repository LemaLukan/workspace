package q2;

public class Test {

	public static void main(String args[])
	{
		Action a = new Action("PG", "10434", "kill bill");
		Drama d = new Drama("E", "54375", "Queen Mary");
		Comedy c = new Comedy("R", "76521", "jonny english");
		Movie m = new Movie("pg-13", "10434", "kill bill");
		
		System.out.println(a);
		System.out.println(d);
		System.out.println(c);
		System.out.println(m);
		System.out.println("late fee for 2 days comedy $" + c.calcLateFees(2));
		System.out.println("Does kill bill equal queen Mary? " + a.equals(d));
		System.out.println("Does action kill bill equal movie kill bill? " + a.equals(m));
	}
}
	
