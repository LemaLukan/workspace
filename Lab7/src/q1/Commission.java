package q1;

public class Commission extends Hourly {
	double totalSales, comRate;
	public Commission(String eName, String eAddress, String ePhone,
			String socSecNumber, double rate, double comRate) {
		super(eName, eAddress, ePhone, socSecNumber, rate);
		this.comRate = comRate;
		totalSales = 0;
	}
	public void addSales(double totalSales)
	{
		this.totalSales += totalSales;
	}
	public double pay()
	{
	  double payment = super.pay() * (1 + comRate);

	  totalSales = 0;

	  return payment;
	}
	
	public String toString()
	{
		return super.toString() + ", Total sales: " + totalSales;
	}
}
