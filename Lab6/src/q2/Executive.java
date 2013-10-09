package q2;

public class Executive extends Manager {
	public Executive(String name, int salary, String department)
	{
		super(name, salary, department);
	}
	public String toString()
	{
		return "Executive: " + getName() + " makes " + getSalary() + " works in " + getDepart();
	}
}
