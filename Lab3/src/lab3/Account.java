package lab3;

public class Account {
	String name, address, email, phone, SIN, accID;
	double balance;
	public Account(String sin, String Nname, String Naddress)
	{
		SIN = sin;
		accID = ""+((int) (Math.random()*1000))+SIN;
		name = Nname;
		address = Naddress;
	}
	public double getbal()
	{
		return balance;
	}
	public void setName(String Nname)
	{
		name = Nname;
	}
	public void setAddress(String NAddress)
	{
		address = NAddress;
	}
	public void setBal(double NBalance)
	{
		balance = NBalance;
	}
}
