package badCode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculateTax extends JFrame implements ActionListener {
	private static final long serialVersionUID = -7883256782877805978L;
	private JLabel promptName;
	private JLabel promptProvince;
	private JLabel promptAddress;
	private JLabel promptIncome;
	private JTextField inputName;
	private JTextField inputProvince;
	private JTextField inputAddress;
	private JTextArea outputArea;
	private JTextField inputIncome;
	
	private JRadioButton maleButton;
	private JRadioButton femaleButton;
	String gender;
	private ActionListener myActionHandler;
	
	
	public CalculateTax(){
		super("Calculate Tax");
		setLayout(new FlowLayout());
		/* myActionHandler handles the event of selecting a radio button */
		myActionHandler = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = ((JRadioButton) e.getSource()).getText();
			}
		};
		
	    promptName = new JLabel("Enter Name");
	    add(promptName);	    
	    inputName = new JTextField(20);
	    add(inputName);
	    promptAddress = new JLabel("Enter Address");
	    add(promptAddress);
	    inputAddress = new JTextField(20);
	    add(inputAddress);
	    promptProvince = new JLabel("Enter Province");  
	    add(promptProvince); 
	    inputProvince = new JTextField(20);
	    add(inputProvince);
	    promptIncome = new JLabel("Enter Income"); 
	    add(promptIncome);
	    inputIncome = new JTextField(20);
	    add(inputIncome);
	    inputIncome.addActionListener(this);
	    outputArea = new JTextArea(20, 30);
	    add(outputArea);
	    maleButton = new JRadioButton("male", false);
	    femaleButton = new JRadioButton("female", false);
	    add(maleButton);
	    add(femaleButton);
	    maleButton.addActionListener(myActionHandler);
	    femaleButton.addActionListener(myActionHandler);
	    setSize(400,550);
		setVisible(true);
	}
	
	
	private void displayLetter(String name, String gender, 
			                   String address, String province, 
			                   double income){	
		double taxPayable;
		MyDate today;
		IncomeTaxCalculator a;
		
		
		if (province.equals("Quebec"))
		{
			a = new FrenchIncomeTaxCalculator();
		} 
		else
		{
			a = new EnglishIncomeTaxCalculator();
		}
		
		
		
		taxPayable = a.computeTax(income);
		String output = "";
		output += a.createDateAddress(address, province);
		output += a.createBodyLetter(taxPayable, province, name, gender);
		outputArea.append(output);
	}
	
	public void actionPerformed(ActionEvent evt){
		String province;
		double income;
		String name;
		String address;
		outputArea.setText(""); // reset the area, who wants multiple letters.
		name = inputName.getText();
		address = inputAddress.getText();
		province = inputProvince.getText();
		income = Double.parseDouble(inputIncome.getText());
		displayLetter(name, gender, address, province, income);
		inputName.setText("");
		inputAddress.setText("");
		inputProvince.setText("");
		inputIncome.setText("");
	}
	
	public static void main(String a[]){
		@SuppressWarnings("unused")
		CalculateTax newFrame = new CalculateTax();
	}

}
