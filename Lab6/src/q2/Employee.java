package q2;

public class Employee {
	private String name;
	private int salary;
	Employee(String name, int salary)
	{
		this.name = name;
		this.salary = salary;
	}
	public String getName()
	{
		return name;
	}
	public int getSalary()
	{
		return salary;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setSalary(int sal)
	{
		salary = sal;
	}
	public String toString()
	{
		return "Employee: " + name + " makes " + salary;
	}
}
