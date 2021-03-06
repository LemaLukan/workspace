package a4;

import javax.swing.*;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class OutputFrame extends JFrame implements Observer {
	private JLabel aLabel;
	private JTextArea myArea;
	public OutputFrame(String s){
		super(s);
		setLayout(new FlowLayout());
		aLabel = new JLabel("The " + s + "s are as follows:");
		add(aLabel);
		myArea = new JTextArea(10, 20);
		add(myArea);

	}

	public void insert(Object o){
		myArea.append(o.toString() + "\n");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.insert(arg1);
	}
}

