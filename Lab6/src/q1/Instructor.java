package q1;

public class Instructor extends Person{
	private int salery;
	public Instructor(String name, int year, int salery)
	{
		super(name, year);
		this.salery = salery;
	}
	
	public int getSalery()
	{
		return salery;
	}
	public void setSalery(int salery)
	{
		this.salery = salery;
	}
	public String toString()
	{
		return "Name: " + getName() + " Born: " + getYear() + " Salery: " + salery;
	}
}
