package q2;

public class Test {
	public static void main(String args[])
	{
		Employee e = new Employee("fred", 30000);
		Manager m = new Manager("bob", 50000, "clothing");
		Executive E = new Executive("alfred", 100000, "sales");
		
		System.out.println("Employee get name: " + e.getName() + " Employee year: " +
				e.getSalary() + " Employee to String: " + e.toString());
		
		System.out.println("Manager get name: " + m.getName() + " Manager year: " +
				m.getSalary() + " manager department: " + m.getDepart() + " Manager to String: " + m.toString());
		
		System.out.println("Executive get name: " + E.getName() + " Executive year: " +
				E.getSalary() + " Executive department: " + E.getDepart() + " Executive to String: " + E.toString());
		
	}
}
