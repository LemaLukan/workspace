package databaseInterface;


import java.awt.*;	
import java.awt.event.*;
import javax.swing.*;

import operations.*;

// MainMenuGUI creates the main menu for users to select future operations.
/**
 * Creates the Main menu allowing options to reach the rest of the program. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class MainMenuGUI {

	
	// Creates the JFrame, JLabels, JTextFields, and JButtons.
	JFrame userFrame = new JFrame();									// Sets up a new JFrame.
	Container userPane = userFrame.getContentPane();					// Sets userPane to the container inside userFrame.
	JButton addNewEntry = new JButton("Add New Entry");					// Setting up addNewEntry display text.
	JButton removeEntry = new JButton("Edit or Remove Entry");			// Setting up removeEntry display text.
	JButton browseSearch = new JButton("Browse Search");			// Setting up sortByGreenNumber display text.
	JButton displayEntries = new JButton("Browse Entries");				// Setting up Browse Entries display text.
	JLabel copyRight = new JLabel(" Program designed by Stephen Nusko 2011. All copyright reserved.");
	private final String pw = "Password";
	// Default constructor: Starts the class to its default settings.		
	public MainMenuGUI()
	{
		userFrame.setSize(400, 300);									// Sets the size of the UserFrame.
		userFrame.setLocation(250, 150);								// Sets the location of the GUI at that point on the screen.
		userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Tell the program to exit when the user is done.
		userPane.setLayout(new GridLayout(5, 1));						// Sets the userPane to 7 rows, and 2 cols.
		
		// Adding action listeners to the buttons.
		addNewEntry.addActionListener(new AddNewEntries());
		removeEntry.addActionListener(new RemoveEntries());
		browseSearch.addActionListener(new BrowseSearch());
		displayEntries.addActionListener(new Display());
		
		// adding the buttons to the userPane.
		userPane.add(addNewEntry);										// Adds addNewEntry to the userPane.
		userPane.add(removeEntry);										// Adds removeEntry to the userPane.
		userPane.add(browseSearch);								// Adds sortByGreenNumber to the userPane.
		userPane.add(displayEntries);									// Adds displayEntries to the userPane.
		userPane.add(copyRight);
		userFrame.setVisible(true);										// Makes the UserFrame visible.
	}
	
	// Display starts the GUI to Browse the Entries.
	/**
	 * @Display Launches the window for browsing entries.
	 */
	private class Display implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			BrowseEntries browseGUI = new BrowseEntries();
		}
	}
	// AddNewEntries starts the GUI to add Entries.
	/**
	 * @AddNewEntries Launches the window that is used to add new entries.
	 */
	private class AddNewEntries implements ActionListener
	{				
		public void actionPerformed(ActionEvent event) {
			String input = JOptionPane.showInputDialog("Enter the password:");
			if (input.compareTo(pw) == 0) {
				AddingEntries addGUI  = new AddingEntries();
			}
			else {
				JOptionPane.showMessageDialog(userFrame, "Password was incorrect.");
			}
		}
	}
	
	// RemoveEntries starts the GUI to remove Entries.
	/**
	 * @RemoveEntries Launches the window to search and will then proceed to launch the removal window. 
	 */
	private class RemoveEntries implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			String input = JOptionPane.showInputDialog("Enter the password:");
			if (input.compareTo(pw) == 0) {
				Searches search = new Searches();
			}
			else {
				JOptionPane.showMessageDialog(userFrame, "Password was incorrect.");
			}				
		}
	}
	// The following for starts the GUI to search the records.
	/**
	 * @BrowseSearch Launches a window to search the entries.
	 */
	private class BrowseSearch implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			if (MainRun.isEmpty()) {
				JOptionPane.showMessageDialog(userFrame, "No entries in the database.");
			}
			else {
				Searches search = new Searches("needed");	// a string is needed to call the correct constructor.
			}
		}
	}
	
}
