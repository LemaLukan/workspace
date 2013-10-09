package assignment1;

public class MyDate {
	private int year;
	private int day;
	private int month;
	private static String[] months = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; 
	MyDate(String date) throws Exception
	{
		try 
		{
			year = Integer.parseInt(date.substring(6, 10));
		}
		catch (NumberFormatException e)
		{
			throw new Exception("year is not a number");
		}
		try 
		{
			month = Integer.parseInt(date.substring(3, 5));
		}
		catch (NumberFormatException e)
		{
			throw new Exception("month is not a number");
		}
		try 
		{
			day = Integer.parseInt(date.substring(0, 2));
		}
		catch (NumberFormatException e)
		{
			throw new Exception("day is not a number");
		}
		if (this.getMonth() > 12 || this.getMonth() < 1)
		{
			throw new Exception("Month is not within 01 and 12.");
		}
		
	}
	public int getYear()
	{
		return year;
	}
	public int getDay()
	{
		return day;
	}
	public int getMonth()
	{
		return month;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public void setDay(int day)
	{
		this.day = day;
	}
	public void setMonth(int month)
	{
		this.month = month;
	}
	public boolean lessThan(MyDate anotherDate)
	{
		if (this.getYear() != anotherDate.getYear())
		{
			return this.getYear() < anotherDate.getYear();
		} 
		else if (this.getMonth() != anotherDate.getMonth())
		{
			return this.getMonth() < anotherDate.getMonth();
		}
		else if (this.getDay() != anotherDate.getDay())
		{
			return this.getDay() < anotherDate.getDay();
		}
		else
		{
			return false;
		}
	}
	public String toString()
	{
		String correct;
		correct = String.format("%s %d, '%02d", this.getMonthName(this.getMonth()-1), this.getDay(), this.getYear()%100);
		return correct;
	}
	private String getMonthName(int index)
	{
		return months[index];
	}
}
