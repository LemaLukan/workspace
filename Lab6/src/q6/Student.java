package q6;

public class Student extends Person {
	protected String myIdNum; // Student Id Number
	protected double myGPA; // grade point average
	// constructor
	public Student(String name, int age, String gender,
	String idNum, double gpa)
	{
	// use the super class' constructor
	super(name, age, gender);
	// initialize what's new to Student
	myIdNum = idNum;
	myGPA = gpa;
	}
	
	public String getIdNum()
	{
		return myIdNum;
	}
	public double getGPA()
	{
		return myGPA;
	}
	public void setIdNum(String id)
	{
		myIdNum = id;
	}
	public void setGPA(double gpa)
	{
		myGPA = gpa;
	}
}
