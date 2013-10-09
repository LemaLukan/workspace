package q15;

class Base {
    
    private int i=0;
    protected int j=1;
    public int k=2;
    public String toString() {
           return "i="+i+"   "+"j="+j+"   "+"k="+k+"\n";
    }
}

class Sub extends Base {
    private int i=3;
    protected int j=4;
    public int k=5;
    protected int m=10;
   
    public Sub(){
           super.j=m;
    }
    public String toString() {
           return "i="+i+"   "+"j="+j+"   "+"k="+k+"   "+"m="+m+"\n";
    }
}

class Testing {
    public static void main(String []str){
           Base b= new Base();
           Sub s= new Sub();

           System.out.println("b.j="+b.j);
           System.out.println("b.k="+b.k);
          
           System.out.println("\n\n");
           System.out.println("s.j="+s.j);
           System.out.println("s.k="+s.k);
           System.out.println("\n\n");
          
           b=s;

           System.out.println(" after b=s\n"+"b.j="+b.j+" ");
           System.out.println("b.k="+b.k);
    }
}