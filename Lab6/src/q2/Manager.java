package q2;

public class Manager extends Employee {
	private String department;
	Manager(String name, int salary, String depart)
	{
		super(name, salary);
		department = depart;
	}
	public String getDepart()
	{
		return department;
	}
	public void setDepart(String depart)
	{
		department = depart;
	}
	public String toString()
	{
		return "Manager: " + getName() + " makes " + getSalary() + " works in " + getDepart();
	}
}
