// UsingChainedExceptions.java
// Demonstrating chained exceptions.


public class UsingChainedExceptions_4{

   public static void main( String args[] )
   {
      try { 
         method1(); // call method1
     }

      // catch Exceptions thrown from method1
     catch ( Exception exception ) { 
        exception.printStackTrace();
     }
   }

   // call method2; throw exceptions back to main
   public static void method1() throws Exception
   {
      try { 
         method2(); // call method2
      }

      // catch Exception thrown from method2
      catch ( Exception exception ) {
         throw new Exception( "Exception thrown in method1", exception );
      }
   }

   // call method3; throw exceptions back to method1
   public static void method2() throws Exception
   {
      try { 
         method3(); // call method3
      }

      // catch Exception thrown from method3
      catch ( Exception exception ) {
    	  //,,,,,,,
         throw new Exception( "Exception thrown in method2", exception );
      }
   }

   // throw Exception back to method2
   public static void method3() throws Exception
   {
	   System.out.println("hi");
      throw new Exception( "Exception thrown in method3" );
   }

} // end class Using Exceptions
