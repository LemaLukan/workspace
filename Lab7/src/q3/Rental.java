package q3;

public class Rental {
	Movie movie;
	int custId, dayslate;
	public Rental(Movie m, int Id)
	{
		movie = m;
		custId = Id;
	}
	
	public void daysLate(int day)
	{
		dayslate = day;
	}
	public double lateFees()
	{
		return movie.calcLateFees(dayslate);
	}
}
