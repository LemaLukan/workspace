package badCode;

import java.util.StringTokenizer;

public abstract class  MyDate {
	private int day;
	private int month;
	private int year;

	public MyDate(String inputString){
		StringTokenizer tokenizeDate = new StringTokenizer(inputString, "/");
		this.day = Integer.parseInt(tokenizeDate.nextToken());
		this.month = Integer.parseInt(tokenizeDate.nextToken());
		this.year = Integer.parseInt(tokenizeDate.nextToken());
	}
	
	public int getDay()
	{
		return this.day;
	}
	public int getMonth()
	{
		return this.month;
	}
	public int getYear()
	{
		return this.year;
	}
	
	// returns the date formated to the local standard.
	public abstract String toString();
}
