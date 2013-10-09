//Lab 2
//Debugging Problem (a)

// The program is about converting temperature from Fahrenheit to Celsius and vice versa.
// 1. You are not allowed to rewrite the whole program.
// 2. After finishing debugging, comment the code.
// 3. You may need to reference the Java API page (http://java.sun.com/j2se/1.4.2/docs/api/) for explainations of some 
//    of the methods and classes used in the program.
// 4. You will be asked questions when your lab instructor/GA evaulates your work.

import javax.swing.JOptionPane;

public class Temperature {

		public static void main( String args[] )
		{
		   int option;
		   // changed to double.
		   double degree1;
		   double celsius1;
		   double fahrenheit1;   
		       

		   String result;
		   String degree;
		   String fahrenheit;
		   String input;
		   String celsius;    
		  

		   option = 0; 
		

		   while ( option != 3 ) 
		   {	// added { and lower case w
		   

		      input = JOptionPane.showInputDialog(
		         " 1 for Fahrenheit to Celsius\n" +
		         " 2 for Celsius to Fahrenheit\n 3 to quit:" );
		  

		      option = Integer.parseInt( input ); // changed to int
		       
		      if (option == 1) { // added if
		      degree = 
		         JOptionPane.showInputDialog( "Enter thr degree in Fahrenheit: " );
		      

		      degree1 = Double.parseDouble( degree ); 
		  

		      celsius1 = ( degree1 - 32 ) * 5 / 9; 
		  

		      result = "The temp in Celsius is " + celsius1; 
		  

		      JOptionPane.showMessageDialog( null, result, "Result",
		         JOptionPane.INFORMATION_MESSAGE );
		       
		      } else if ( option == 2 ) { // added { and else
		  

		         degree = JOptionPane.showInputDialog( "Enter degree in Celsius: " );
		      

		         degree1 = Double.parseDouble( degree ); 
		  

		         fahrenheit1= ( degree1 * 9 / 5 ) + 32;
		  

		         result = "The temp in Fahrenheit is " + fahrenheit1;
		  

		         JOptionPane.showMessageDialog( null, result, "Result",
		            JOptionPane.INFORMATION_MESSAGE );
		      }

		   } // end while loop
		      System.exit( 0 ); // moved down.
    
		   
		} // end method Main
} // end class Temperature
