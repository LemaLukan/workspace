package q1;

public class Test {
	public static void main(String args[])
	{
		Person p = new Person("fred", 1992);
		Student s = new Student("bob", 1989, "Computer Science");
		Instructor i = new Instructor("Dr. annie", 1970, 30000);
		
		System.out.println("person get name: " + p.getName() + " person year: " +
				p.getYear() + " person to String: " + p.toString());
		

		System.out.println("Student get name: " + s.getName() + " Student year: " +
				s.getYear() + " get major: " + s.getMajor() + " Student to String: " + s.toString());
		

		System.out.println("Instructor get name: " +i.getName() + " Instructor year: " +
				i.getYear() + " get salery: " + i.getSalery() + " Instructor to String: " + i.toString());
	}
}
