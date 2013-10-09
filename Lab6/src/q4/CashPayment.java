package q4;

public class CashPayment extends Payment {
	CashPayment()
	{
		super();
	}
	CashPayment(double pay)
	{
		super(pay);
	}
	public String paymentDetails()
	{
		return "there has been " + getPayment() + " worth of cash payments";
	}
}
