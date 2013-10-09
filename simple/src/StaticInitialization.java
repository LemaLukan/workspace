// StaticInitialization.java
// Specifying initial values in a class definition.

class Bowl {
  Bowl(int marker) {
    System.out.println("Bowl(" + marker + ")");
  }
  void f(int marker) {
    System.out.println("f(" + marker + ")");
  }
}

class Table {
  static Bowl b1 = new Bowl(1);

  static int i=9;
  Table() {
	  System.out.println("hi");
  }
  {
	  b2.f(1);
  }
  
  void f2(int marker) {
    System.out.println("f2(" + marker + ")");
  }
  
  static {
	  System.out.println("Table()");
  }
  static Bowl b2 = new Bowl(2);
  
  public static Bowl main() { 
	  i = 100;
	  return new Bowl(444);
  }
}

class Cupboard {
  Bowl b3 = new Bowl(3);
  
  static Bowl b4 = new Bowl(4);
  
  Cupboard() {
    System.out.println("Cupboard()");
    b4.f(2);
  }
  void f3(int marker) {
    System.out.println("f3(" + marker + ")");
  }
  static Bowl b5 = new Bowl(5);
}

public class StaticInitialization {
    public static void main(String[] args) {
		    System.out.println("Creating new Cupboard() in main");
		    new Cupboard();
		    System.out.println("Creating another new Cupboard() in main");
		    new Cupboard();
		    t3.f3(1);
		    new Table().f2(Table.i);
		    Table.main();
		    //   t2.f2(1);
		 //  t3.f3(1);
		   // Table.i=10;  
   }
  
    //static Table t2 = new Table();
    static Cupboard t3 = new Cupboard();
} ///:~


