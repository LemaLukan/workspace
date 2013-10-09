
public class Test {
	public static void main (String args[])
	{
		My ob1 = new My();
		ob1.met1();
		ob1.met2();
		ob1.met3();
		

		My ob2 = new MyP();
		ob2.met1();
		ob2.met2();
		ob2.met3();
		
		My ob3 = new MyPP();
		ob3.met1();
		ob3.met2();
		ob3.met3();
		
		MyP ob4 = new MyPP();
		ob1.met2();
	}
}

class My {
	public int v1;
	public int v2;
	My()
	{
		v1 = 1;
		v2 = 2;
	}
	public void met1()
	{
		System.out.println( "met1 My " + v1);
	}
	public void met2()
	{
		System.out.println( "met2 My " + v2);
	}
	public void met3()
	{
		System.out.println( "met3 My " + v2);
		this.met1();
	}
}
class MyP extends My {
	public int v2;
	MyP() {
		v1 = 11;
		v2 = 22;
	}
	public void met2() {
		System.out.println("met2 MyP " + v2);
		this.met1();
	}
}
class MyPP extends MyP {
	public int v1;
	MyPP() {
		v1 = 111;
	}
	public void met3() {
		System.out.println("met3 MyPP " + v2);
		super.met1();
	}
}