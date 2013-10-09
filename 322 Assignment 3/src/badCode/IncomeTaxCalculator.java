package badCode;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class IncomeTaxCalculator {

	private Calendar cal;
	private SimpleDateFormat sdf;
	private MyDate aDate;
	public IncomeTaxCalculator() {
		this.sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.cal = Calendar.getInstance();
	}
	public Calendar getCalendar()
	{
		return this.cal;
	}
	public SimpleDateFormat getSimpleDateFormat()
	{
		return this.sdf;
	}
	public MyDate getDate()
	{
		return aDate;
	}
	public void setDate(MyDate a)
	{
		this.aDate = a;
	}
	public abstract String createDateAddress(String address, String province);
	public abstract double computeTax(double income);
	public abstract String createBodyLetter(double taxPayable, String province, String name, String gender);

}
