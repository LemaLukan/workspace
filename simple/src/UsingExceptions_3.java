// UsingExceptions.java
// Demonstrating getMessage and printStackTrace from class Exception.


public class UsingExceptions_3
{

   public static void main( String args[] )
   {
      try { 
         method1(); // call method1 
      }

      // catch Exceptions thrown from method1
      catch ( Exception exception ) { 
         System.err.println( exception.getMessage() + "\n" );
         exception.printStackTrace();

         // obtain the stack-trace information
         StackTraceElement[] traceElements = exception.getStackTrace();
         
         System.out.println(  "\nStack trace from getStackTrace:" );
         System.out.println( "Class\t\tFile\t\t\tLine\tMethod" );

         // loop through traceElements to get exception description
         for ( int i = 0; i < traceElements.length; i++ ) {
            StackTraceElement currentElement = traceElements[ i ];
            System.out.print( currentElement.getClassName() + "\t" );
            System.out.print( currentElement.getFileName() + "\t" );
            System.out.print( currentElement.getLineNumber() + "\t" );
            System.out.print( currentElement.getMethodName() + "\n" );

         } // end for block

      } // end catch

   } // end method main

   // call method2; throw exceptions back to main
   public static void method1() throws Exception  // how about throws ArithmeticException
   {
      method2();
   }

   // call method3; throw exceptions back to method1
   public static void method2()  throws Exception 
 	  // throws RuntimeException
   {
      method3();
   }

   // throw Exception back to method2
   public static void method3() throws  Exception
   {
   	  throw new Exception( "Exception thrown in method3" );
   }

} // end class Using Exceptions

