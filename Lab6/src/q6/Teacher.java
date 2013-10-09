package q6;

public class Teacher extends Person {
	private String subject;
	private int salary;
	public Teacher(String name, int age, String gender, String sub, int sal) {
		super(name, age, gender);
		subject = sub;
		salary = sal;
	}
	public String getSubject()
	{
		return subject;
	}
	public int getSalary()
	{
		return salary;
	}
	public void setSubject(String sub)
	{
		subject = sub;
	}
	public void setSalary(int sal)
	{
		salary = sal;
	}
	
	public String toString()
	{
		return super.toString() + ", subject " + getSubject() + ", salary " + getSalary();
	}
}
