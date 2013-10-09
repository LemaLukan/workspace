package q6;

public class UniversityStudent extends Student {
	private String major;
	private int year;
	public UniversityStudent(String name, int age, String gender, String idNum,
			double gpa, String major, int year) {
		super(name, age, gender, idNum, gpa);
		this.major = major;
		this.year = year;
	}
	public String getMajor()
	{
		return major;
	}
	public int getYear()
	{
		return year;
	}
	public void setMajor(String maj)
	{
		major = maj;
	}
	public void setYear(int year)
	{
		this.year = year;
	}
	public String toString()
	{
		return super.toString() + ", id " + super.getIdNum() + ", GPA " + super.getGPA() + ", major " + getMajor() + ", year " + getYear();
	}
}
