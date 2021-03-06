package a4;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.* ;

import javax.swing.*;

public class MyFrame extends JFrame implements ActionListener {
	private JLabel aLabel, bLabel;
	private JTextField area1, area2;
	private ObservedName watchedNames;
	private ObservedAge watchedAge;
	public MyFrame(OutputFrame nameFrame, OutputFrame ageFrame){
		super("User Input Area");
		
		watchedNames = new ObservedName();
		watchedNames.addObserver(nameFrame);
		watchedAge = new ObservedAge();
		watchedAge.addObserver(ageFrame);
		setLayout(new FlowLayout());
		aLabel = new JLabel("What is your name?");
		add(aLabel);
		area1  = new JTextField( 10 );
		area1.addActionListener(this);
		add(area1);
		bLabel = new JLabel("How old are you?");
		add(bLabel);
		area2  = new JTextField( 10 );
		area2.addActionListener(this);
		add(area2);	
	}
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == area1){
			watchedNames.addName(area1.getText());
			area1.setText("");
		} else {			 
			watchedAge.addAge(Integer.parseInt(area2.getText()));
			area2.setText("");
		}
	}	
	public static void main(String a[]){	
		MyFrame aFrame;
		OutputFrame nameFrame, ageFrame;

		nameFrame = new OutputFrame("name");
		nameFrame. setSize(275,200);
		nameFrame. setVisible(true);
		ageFrame = new OutputFrame("age");
		ageFrame. setSize(275,200);
		ageFrame. setVisible(true);
		aFrame = new MyFrame(nameFrame, ageFrame);
		aFrame. setSize(300, 100);
		aFrame. setVisible(true);
	}
}

