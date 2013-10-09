//Lab 2
//Debugging Problem (b)

//The program prompts the user to ask a question then
//the application answers accordingly.

//1. You are not allowed to rewrite the whole program.
//2. After finishing debugging, comment the code.
//3. You may need to reference the Java API page (http://java.sun.com/j2se/1.4.2/docs/api/) for explainations of some 
// of the methods and classes used in the program.
//4. You will be asked questions when your lab instructor/GA evaulates your work.

import javax.swing.*;

public class CompTalk {

		public static void main( String args[] )
		{
			int inputNum; //moved to start.
		 do {
		      // read in the input from the user
		      String input = JOptionPane.showInputDialog(
		         "Enter 1 to Ask me how I am feeling today \n" +
		         "Enter 2 to ask me who I am \n" +
		         "Enter 3 to make me count from 1 to 9 and then 11 to 15 \n" +
		         "Enter 4 to terminate" );
		

		      // convert input string to an int
		      inputNum = Integer.parseInt( input );
		

		      // determine output to display
		      switch ( inputNum ) { // changed to inputNum
		

		         case 1:
		

		            // generate a random number between 1 - 4
		            int random = 1 + ( int ) ( Math.random() * 4 ); // added ;
		

		            switch ( random ) { // removed bool check added {
		

		               case 1:
		                  JOptionPane.showMessageDialog( null,
		                     "I feel fine ", "Answer Box",
		                     JOptionPane.INFORMATION_MESSAGE );
		                  break;
		

		               case 2:
		                  JOptionPane.showMessageDialog( null,
		                     "I've been better ", "Answer Box",
		                     JOptionPane.INFORMATION_MESSAGE );
		                  break;
		

		               case 3:
		                  JOptionPane.showMessageDialog( null,
		                     "I feel horrible ", "Answer Box",
		                     JOptionPane.INFORMATION_MESSAGE );
		                  break;
		

		               case 4:
		                  JOptionPane.showMessageDialog( null,
		                     "I feel terrific ", "Answer Box",
		                     JOptionPane.INFORMATION_MESSAGE );
		                  break; //added break.
		

		            } // end switch statement
		            break; // added break;

		         case 2:
		            JOptionPane.showMessageDialog( null,
		               "I am Sam, the computer man", "Answer Box",
		               JOptionPane.INFORMATION_MESSAGE );
		            break;
		

		         case 3:
		            String num = ""; // added ""
		            for (int i = 1; i <= 15; i++ ) { // added in and ; removed ;
		               // will skip printing 10
		               if (i == 10 )
		               {
		                  continue;
		               }
		               num = num + " " + Integer.toString(i); //added num
		               JOptionPane.showMessageDialog( null, num, "Answer Box",
		               JOptionPane.INFORMATION_MESSAGE );
		            }
		            break;
		

		         case 4:
		            break;
		

		         default:
		            String error = "" + input + " is not a valid entry";
		            JOptionPane.showMessageDialog( null, error, "Error in input data",
		               JOptionPane.INFORMATION_MESSAGE );
		            break;

		      } // end switch structure
		
		 } while ( inputNum != 4);

		   System.exit( 0 );
		

		} // end main
}// end class CompTalk