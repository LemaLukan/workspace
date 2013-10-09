
// Tests interface Payable.

public class PayableInterfaceTest 
{
   public static void main( String args[] )
   {
      // create six-element Payable array
      Payable payableObjects[] = new Payable[ 6 ];
      
      // populate array with objects that implement Payable
      payableObjects[ 0 ] = new Invoice( "01234", "seat", 2, 375.00 );
      payableObjects[ 1 ] = new Invoice( "56789", "tire", 4, 79.95 );
      payableObjects[ 2 ] = 
         new SalariedEmployee( "John", "Smith", "111-11-1111", 800.00 );
      payableObjects[ 3 ] = new HourlyEmployee("bob", "Book", "134-66-8994", 16.75, 40.0);
         /* create an HourlyEmployee object */
      payableObjects[ 4 ] = new CommissionEmployee("sue", "jones", "333-33-3333", 10000, .06);
         /* create a CommissionEmployee object */
      payableObjects[ 5 ] = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300);
         /* create a BasePlusCommissionEmployee object */

      System.out.println("Invoices and Employees processed polymorphically:\n"); 

      // generically process each element in array payableObjects
      for ( Payable currentPayable : payableObjects )
      {
         // output currentPayable and its appropriate payment amount
         System.out.printf( "%s \n", currentPayable.toString() ); 
            
         /* write code to determine whether currentPayable is a 
            BasePlusCommissionEmployee object */
         {
            /* write code to give a raise */
            /* write code to ouput results of the raise */
         } // end if

         System.out.printf( "%s: $%,.2f\n\n",
            "payment due", currentPayable.getPaymentAmount() ); 
      } // end for
   } // end main
} // end class PayableInterfaceTest


