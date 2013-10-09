package q7;

public class Vehicle {
	private String manName;
	private int numCylin;
	Person owner;
	public Vehicle (String name, int cylin, Person owner)
	{
		manName = name;
		numCylin = cylin;
		this.owner = owner;
	}
	
	public String getManufacturer()
	{
		return manName;
	}
	public int GetCylinders()
	{
		return numCylin;
	}
	public void setManufacturer(String man)
	{
		manName = man;
	}
	public void setCylinders(int cyl)
	{
		numCylin = cyl;
	}
	public boolean equals(Object o)
	{
		Vehicle t = (Vehicle)o;
		return owner.equals(t.owner);
	}
	public String toString()
	{
		return "Manufactured by " + manName + ", number of Cylinders " + numCylin + ", owned by " + owner.toString();
	}
}
