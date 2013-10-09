package q4;

public class Test {
	public static void main(String args[])
	{
		CashPayment cp1 = new CashPayment(30.4);
		CashPayment cp2 = new CashPayment(20.0);
		CreditCardPayment ccp1 = new CreditCardPayment(200.5);
		CreditCardPayment ccp2 = new CreditCardPayment(5000.2);	
		System.out.println("cashpayment1 to string = " + cp1.paymentDetails());

		System.out.println("cashpayment2 to string = " + cp2.paymentDetails());

		System.out.println("creditcardPayment1 to string = " + ccp1.paymentDetails());

		System.out.println("creditcardPayment2 to string = " + ccp2.paymentDetails());
	}
}
