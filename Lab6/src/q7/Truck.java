package q7;

public class Truck extends Vehicle {
	private double load;
	private int tow;
	public Truck(String name, int cylin, Person owner, double load, int tow) {
		super(name, cylin, owner);
		this.load = load;
		this.tow = tow;
	}
	public double getLoad()
	{
		return load;
	}
	public int getTow()
	{
		return tow;
	}
	public void setLoad(double load)
	{
		this.load = load;
	}
	public void setTow(int tow)
	{
		this.tow = tow;
	}
	public String toString()
	{
		return super.toString() +", load of " + load + ", with towing ability of " + tow;
	}
}
