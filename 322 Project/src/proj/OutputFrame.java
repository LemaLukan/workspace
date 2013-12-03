package proj;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.*;
public abstract class OutputFrame extends JFrame implements Observer{
	final int WIDTH = 200, HEIGHT = 300;
	private JTextArea outputArea;
	public OutputFrame(){
		outputArea = new JTextArea(20, 30);
		add(outputArea);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}
	public void displayResult(String s){
//		System.out.println(s);
		outputArea.setText(s);
	}
}
