package q3;

public class Test {

	static Rental[] r = new Rental[4];
	public static void main(String args)
	{
		r[0] = new Rental(new Action("PG", "10434", "kill bill"), 1345);
		r[1] = new Rental(new Drama("E", "54375", "Queen Mary"), 6542);
		r[2] = new Rental(new Comedy("R", "76521", "jonny english"), 5472);
		r[3] = new Rental(new Movie("pg-13", "75213", "Flowers"), 8423);
		r[2].daysLate(4);
		r[0].daysLate(3);
		System.out.println("total fees owed = " + lateFeesOwed());
	}
	
	public static double lateFeesOwed()
	{
		double sum = 0.0;
		for (int i = 0; i < 4; i ++)
		{
			sum += r[i].lateFees();
		}
		return sum;
	}
}
