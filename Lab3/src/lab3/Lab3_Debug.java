package lab3;
//Debugging the following program Problem

//be sure to comment your changes

//what does this program do?

import javax.swing.*;

public class Lab3_Debug {

   public static void main(String str[]) {     
  

	 String students = JOptionPane.showInputDialog( 
     	 "Please enter the number of students you wish to average" ); 
     

	 int students2 = Integer.parseInt( students ); 

	 int NumOfStudents = students2 ; 
	 int counter = 1;  
 	  

   	 double total = 0, previousNumber = 0, newMaximum = 0, newMinimum = 0; 
     

	 while ( students2 != 0 ) {    
	         String si = JOptionPane.showInputDialog( "Please enter a grade" ); 
	         double givenNum = Double.parseDouble( si );
	         total = total + givenNum; 
	                    

	         if ( counter == 1 ) {
	            previousNumber = givenNum; 
	            newMinimum = givenNum; 
	         }
	                     

	         if ( newMaximum < givenNum )
	            newMaximum = Math.max( previousNumber, givenNum );  
	                  

	                
	         if ( newMinimum > givenNum )
	            newMinimum = Math.min( previousNumber, givenNum ); 
	                       

	         students2--; 
	         counter++; 
	         previousNumber = givenNum;         
	 }  
   

 	 double average1 = average( total, NumOfStudents ); 
       

	      JOptionPane.showMessageDialog(null,  "The maximum: " + newMaximum + 
	         "\nThe Average: " + average1 + " \nThe minimum: " + newMinimum);
		  

		  System.exit(0);
	   }    


public static double average( double x, double y )
{
   return x / y; 
}
      

public double maximum( double x, double y )
{
   return Math.max( x, y ); 
}
 

public double minimum( double x, float y )
{
   return Math.min( x, y );
}
}