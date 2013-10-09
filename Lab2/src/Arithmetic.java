import javax.swing.JOptionPane; // moved up top
    
public class Arithmetic {


    public static void main( String args[] )
    {
        String firstNumber, secondNumber, thirdNumber;
        int num1, num2, num3, sum, product, average; // added num1

        firstNumber = JOptionPane.showInputDialog( "Enter first integer:" ); // put ; and O removed =

        secondNumber =JOptionPane.showInputDialog( "Enter second integer:" ); // added ; removed =

        thirdNumber = JOptionPane.showInputDialog( "Enter third integer:" ); // added ; and " removed =

        num1 = Integer.parseInt( firstNumber ); // removed =
        num2 = Integer.parseInt( secondNumber ); // removed =
        num3 = Integer.parseInt( thirdNumber ); // removed =

        sum = num1 + num2 + num3;
        product = num1 * num2 * num3;
        average = ( num1 + num2 + num3 ) / 3;
        JOptionPane.showMessageDialog( null, "The sum is " + sum +
                "\nThe product is " + product + "\nThe average is " + average,
                "Results", JOptionPane.PLAIN_MESSAGE ); // added .
    }

} // end class Arithmetic
