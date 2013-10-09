package operations;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import databaseData.EntryNode;
import databaseData.UnsortedList;
import databaseInterface.MainRun;

/**
 * This class is used to show the interface to search the SortedList in the MainRun class. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class Searches {
	// Creates the JFrame, JLabels, JTextFields, and JButtons.
	JFrame userFrame = new JFrame();																			// Sets up a new JFrame.
	Container userPane = userFrame.getContentPane();													// Sets userPane to the container inside userFrame.
	JLabel title = new JLabel("Search By Title:");						// Setting up title display text.
	JTextField titleText = new JTextField("");					// Setting up titleText display text.
	JLabel greenNumber = new JLabel("Search By Green Number:");		// Setting up greenNumber display text.
	JTextField greenNumberText = new JTextField("");					// Setting up titleText display text.
	JLabel taxRoll = new JLabel("Search By Tax Roll Number:");								// Setting up taxRoll display text.
	JTextField taxRollText = new JTextField("");					// Setting up titleText display text.
	JLabel location = new JLabel("Search By Street:");	// Setting up location display text.
	JTextField locationText = new JTextField("");					// Setting up titleText display text.
	JButton done = new JButton("Search");				// Setting up sortByYear display text.
	JLabel city = new JLabel("City:");
	JTextField cityText = new JTextField("");
	
	JFrame resultFrame = new JFrame();
	Container resultPane = resultFrame.getContentPane();
	JButton next = new JButton("Next result");
	JButton show = new JButton("View Entry");
	JButton removeResult = new JButton("Remove Entry");
	JLabel titleResult = new JLabel("");
	JLabel address = new JLabel("");
	JLabel taxRollNumber = new JLabel("");
	JLabel blank = new JLabel("");
	
	String titleInfo;
	long greenNumberInfo;
	long taxRollNumberInfo;
	String locationInfo;
	String cityInfo;
	EntryNode currentNode;
	EntryNode foundNode;
	UnsortedList searchResults;
	UnsortedList pictureList = new UnsortedList();
	
	boolean editOrNo;
	// Default constructor: Starts the class to its default settings.	
	/**
	 * Default constructor. Used to allow searching of the database that lets you edit your findings.
	 */
	public Searches()
	{
		userFrame.setSize(400, 300);											// Sets the size of the UserFrame.
		userFrame.setLocation(250, 150);										// Sets the location of the UserFrame.
		userPane.setLayout(new GridLayout(6, 2));							// Sets the userPane to 7 rows, and 2 cols.
		//title.addActionListener(new AddNewEntries());
		done.addActionListener(new Search());
		
		userPane.add(title);											// Adds title to the userPane.
		userPane.add(titleText);									// Adds titleText to the userPane.
		userPane.add(greenNumber);												// Adds greenNumber to the userPane.
		userPane.add(greenNumberText);
		userPane.add(taxRoll);											// Adds taxRoll to the userPane.
		userPane.add(taxRollText);
		userPane.add(location);											// Adds location to the userPane.
		userPane.add(locationText);
		userPane.add(city);
		userPane.add(cityText);
		userPane.add(done);
		
		userFrame.setVisible(true);											// Makes the UserFrame visible.
	}
	/**
	 * constructor of the class that does not allow editing after a match has been found
	 * @param tobedifferent This can be any String as it is only to allow the program to know the results should not offer any options to edit.
	 */
	public Searches(String tobedifferent) {
		userFrame.setSize(400, 300);											// Sets the size of the UserFrame.
		userFrame.setLocation(250, 150);										// Sets the location of the UserFrame.
		userPane.setLayout(new GridLayout(6, 2));							// Sets the userPane to 7 rows, and 2 cols.
		//title.addActionListener(new AddNewEntries());
		done.addActionListener(new SearchBrowse());
		
		userPane.add(title);											// Adds title to the userPane.
		userPane.add(titleText);									// Adds titleText to the userPane.
		userPane.add(greenNumber);												// Adds greenNumber to the userPane.
		userPane.add(greenNumberText);
		userPane.add(taxRoll);											// Adds taxRoll to the userPane.
		userPane.add(taxRollText);
		userPane.add(location);											// Adds location to the userPane.
		userPane.add(locationText);
		userPane.add(city);
		userPane.add(cityText);
		userPane.add(done);
		
		userFrame.setVisible(true);											// Makes the UserFrame visible.
	}
	/**
	 * calls a window to display the UnsortedList searchResults, displaying any matching results.
	 */
	private void searchResults() {
		
		resultFrame.setSize(400, 300);											// Sets the size of the resultFrame.
		resultFrame.setLocation(250, 150);										// Sets the location of the resultFrame.
		resultPane.setLayout(new GridLayout(3, 2));							// Sets the resultPane to 7 rows, and 2 cols.
		
		next.addActionListener(new nextResult());
		show.addActionListener(new showResult());
		
		currentNode = (EntryNode)searchResults.getNextItem();
		titleResult.setText(""+currentNode.getTitle());
		address.setText("Address: "+currentNode.getGreenNumber()+" "+currentNode.getStreet()+", \n"+currentNode.getCity());
		taxRollNumber.setText("Tax Roll: "+currentNode.getTaxRoll());
		
		resultPane.add(titleResult);											// Adds titleResult to the resultPane.
		resultPane.add(taxRollNumber);											// Adds taxRoll to the resultPane.
		resultPane.add(address);											// Adds location to the resultPane.
		if (editOrNo) {
			removeResult.addActionListener(new removeResult());
			resultPane.add(removeResult);
		}
		else {
			resultPane.add(blank);
		}
		resultPane.add(show);
		resultPane.add(next);
		
		resultFrame.setVisible(true);											// Makes the resultFrame visible.
	}
	/**
	 * Searches by the different information provided.
	 */
	private void searches() {
		if (taxRollNumberInfo != 0) {
			currentNode = MainRun.findNodeTaxNumber(taxRollNumberInfo);
			userFrame.dispose();
			if (currentNode != null) {
				if (editOrNo == true) {
					BrowseEntries foundGUI = new BrowseEntries(currentNode);
				}
				else {
					BrowseEntries foundGUI = new BrowseEntries(currentNode, "");
				}
			}
		}
		else if (greenNumberInfo != 0 && locationInfo.length() != 0 && cityInfo.length() != 0) {
			currentNode = MainRun.findNodeAddressAndCity(greenNumberInfo, locationInfo, cityInfo);
			userFrame.dispose();
			if (currentNode != null) {
				if (editOrNo == true) {
					BrowseEntries foundGUI = new BrowseEntries(currentNode);
				}
				else {
					BrowseEntries foundGUI = new BrowseEntries(currentNode, "");
				}
			}
		}
		else if (greenNumberInfo != 0 && locationInfo.length() != 0) {
			searchResults = MainRun.findNodeAddress(greenNumberInfo, locationInfo);
			JOptionPane.showMessageDialog(userFrame, ""+searchResults.length()+" matches were found.");
			if (searchResults.isEmpty() == false) {
				searchResults();
				userFrame.dispose();
			}
		}
		else if (locationInfo.length() != 0 && cityInfo.length() != 0) {
			searchResults = MainRun.findNodeStreetAndCity(cityInfo, locationInfo);
			JOptionPane.showMessageDialog(userFrame, ""+searchResults.length()+" matches were found.");
			if (searchResults.isEmpty() == false) {
				searchResults();
				userFrame.dispose();
			}
		}
		else if (locationInfo.length() != 0){
			searchResults = MainRun.findNodeStreet(locationInfo);
			JOptionPane.showMessageDialog(userFrame, ""+searchResults.length()+" matches were found.");
			if (searchResults.isEmpty() == false) {
				searchResults();
				userFrame.dispose();
			}
		}
		else if (cityInfo.length() != 0) {
			searchResults = MainRun.findNodeCity(cityInfo);
			JOptionPane.showMessageDialog(userFrame, ""+searchResults.length()+" matches were found.");
			if (searchResults.isEmpty() == false) {
				searchResults();
				userFrame.dispose();
			}
		}
		else if (titleInfo.length() != 0) {
			searchResults = MainRun.findNodeTitle(titleInfo);
			JOptionPane.showMessageDialog(userFrame, ""+searchResults.length()+" matches were found.");
			if (searchResults.isEmpty() == false) {
				searchResults();
				userFrame.dispose();
			}
		}
		else if (greenNumberInfo != 0) {
			searchResults = MainRun.findNodeGreenNumber(greenNumberInfo);
			JOptionPane.showMessageDialog(userFrame, ""+searchResults.length()+" matches were found.");
			if (searchResults.isEmpty() == false) {
				searchResults();
				userFrame.dispose();
			}
		}
	}
	
	// private classes below allow the buttons to function.
	
	// Remove result will allow an entry be removed if its causing a problem.
	private class removeResult implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int response = JOptionPane.showConfirmDialog(null, "Do you want to delete the entry?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				MainRun.removeEntryNode(currentNode);
				searchResults.delete(currentNode);
				if (searchResults.isEmpty() != true) {
				currentNode = (EntryNode)searchResults.getNextItem();
				titleResult.setText(""+currentNode.getTitle());
				address.setText("Address: "+currentNode.getGreenNumber()+" "+currentNode.getStreet()+", \n"+currentNode.getCity());
				taxRollNumber.setText("Tax Roll: "+currentNode.getTaxRoll());
				}
				else {
					resultFrame.dispose();
				}
			}
		}
	}
	// showResult starts the correct browseEntries window.
	private class showResult implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (editOrNo == true) {
				BrowseEntries foundGUI = new BrowseEntries(currentNode);
			}
			else {
				BrowseEntries foundGUI = new BrowseEntries(currentNode, "");
			}
		}
	}
	// nextResult shows the next possible result the search turned up.
	private class nextResult implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			currentNode = (EntryNode)searchResults.getNextItem();
			titleResult.setText(""+currentNode.getTitle());
			address.setText("Address: "+currentNode.getGreenNumber()+" "+currentNode.getStreet()+", \n"+currentNode.getCity());
			taxRollNumber.setText("Tax Roll: "+currentNode.getTaxRoll());
		}
	}
	// Search gathers the information and starts the search method, while allowing editing of the results.
	private class Search implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			titleInfo = titleText.getText();
			locationInfo = locationText.getText();
			cityInfo = cityText.getText();
			try {
				taxRollNumberInfo = Integer.parseInt(taxRollText.getText());
				if (taxRollNumberInfo < 0) {
					JOptionPane.showMessageDialog(userFrame, "A Tax Roll Number must be higher then 0.");
				}
			}
			catch (NumberFormatException error) {
				taxRollNumberInfo = 0;
			}
			try {
				greenNumberInfo = Integer.parseInt(greenNumberText.getText());
				if (greenNumberInfo < 0) {
					JOptionPane.showMessageDialog(userFrame, "A Green Number must be higher then 0.");
				}
			}
			catch (NumberFormatException error) {
				greenNumberInfo = 0;
			}
			editOrNo = true;
			searches();
		}
	}
	// SearchBrowse gathers the information and starts the search method, while not allowing people to edit the results.
	private class SearchBrowse implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			titleInfo = titleText.getText();
			locationInfo = locationText.getText();
			cityInfo = cityText.getText();
			try {
				taxRollNumberInfo = Integer.parseInt(taxRollText.getText());
				if (taxRollNumberInfo < 0) {
					JOptionPane.showMessageDialog(userFrame, "A Tax Roll Number must be higher then 0.");
				}
			}
			catch (NumberFormatException error) {
				taxRollNumberInfo = 0;
			}
			try {
				greenNumberInfo = Integer.parseInt(greenNumberText.getText());
				if (greenNumberInfo < 0) {
					JOptionPane.showMessageDialog(userFrame, "A Green Number must be higher then 0.");
				}
			}
			catch (NumberFormatException error) {
				greenNumberInfo = 0;
			}
			editOrNo = false;
			searches();
		}
	}
}