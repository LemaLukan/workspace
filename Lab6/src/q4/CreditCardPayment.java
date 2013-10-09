package q4;

public class CreditCardPayment extends Payment {
	CreditCardPayment()
	{
		super();
	}
	CreditCardPayment(double pay)
	{
		super(pay);
	}
	
	public String paymentDetails()
	{
		return "There has been " + getPayment() + " worth of credit card payments";
	}
}
