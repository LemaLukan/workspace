package q4;

public class Payment {
	private double pay;
	Payment()
	{
	}
	Payment(double p)
	{
		pay = p;
	}
	public double getPayment()
	{
		return pay;
	}
	public void setPayment(double p)
	{
		pay = p;
	}
	
	public String paymentDetails()
	{
		return "there has been " + pay + " worth of payments";
	}
}
