package lab3;

public class Date {
	String month;
	int date, year;
	public Date(){
		date = 1;
		month = "January";
		year = 13;
	}
	public Date (int newdate, String newmonth, int newyear)
	{
		date = newdate;
		month = newmonth;
		year = newyear;
	}
	public String toString()
	{
		return month + " " + date + ", " + year;
	}
	public boolean LessThan(Date compare)
	{
		if (compare.year > year)
		{
			return false;
		}
		else if (compare.month.compareTo(month) > 0)
		{
			return false;
		}
		else if (compare.date >= date)
		{
			return false;
		}
		return true;
	}
}
