package q12;

interface A2 {   
	  void print();
	}
	 
	class C {}
	 
	class B2 extends C implements A2 {  
	  public void print() { }
	}
	 
	public class Test2 {
	  public static void main(String[] args) {
	    B2 b = new B2();
	    if (b instanceof A2)
	      System.out.println("b is an instance of A");
	    if (b instanceof C)
	      System.out.println("b is an instance of C");
	  }
	}
