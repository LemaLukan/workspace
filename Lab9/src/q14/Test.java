package q14;

public class Test {
    public static void main(String[] args) {
          System.out.println("Sum is " + xMethod(5));
    }
   
    public static int xMethod(int n) {
          if (n == 1)
                      return 1;
          else
                      return n + xMethod(n - 1);
    }
}