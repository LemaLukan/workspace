package operations;

import java.awt.*;	
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import databaseData.EntryNode;
import databaseData.PictureNode;
import databaseData.UnsortedList;
import databaseInterface.MainRun;

/**
 * Adding entries allows new entries to be added to the database. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class AddingEntries {
	
	final JFileChooser fc = new JFileChooser();
	File readFile;
	UnsortedList pictureList = new UnsortedList();
	String titleInfo;
	long greenNumberInfo;
	long taxRollNumberInfo;
	String locationInfo;
	String pictureInfo;
	String descriptionInfo;
	String cityInfo;
	// Creates the JFrame, JLabels, JTextFields, and JButtons.
	JFrame userFrame = new JFrame();																			// Sets up a new JFrame.
	Container userPane = userFrame.getContentPane();													// Sets userPane to the container inside userFrame.
	JLabel title = new JLabel("Title:");						// Setting up title display text.
	JTextField titleText = new JTextField("");					// Setting up titleText display text.
	JLabel greenNumber = new JLabel("Green Number:");		// Setting up greenNumber display text.
	JTextField greenNumberText = new JTextField("");					// Setting up titleText display text.
	JLabel taxRoll = new JLabel("Tax Roll Number:");								// Setting up taxRoll display text.
	JTextField taxRollText = new JTextField("");					// Setting up titleText display text.
	JLabel location = new JLabel("Street:");	// Setting up location display text.
	JTextField locationText = new JTextField("");					// Setting up titleText display text.
	JLabel city = new JLabel("City: ");
	JTextField cityText = new JTextField("");
	JTextField pictureUrl = new JTextField("");
	JButton save = new JButton("Save");				// Setting up sortByYear display text.
	JButton done = new JButton("close");
	JButton pictureNav = new JButton("Select picture File.");
	JLabel descriptionLabel = new JLabel("Description: ");
	JTextArea description = new JTextArea();
	JButton savePicDescription = new JButton("Add the picture and description.");
	JLabel blank = new JLabel();
	EntryNode currentNode;
	PictureNode currentPicture;
	
	JButton saveEdit = new JButton("Save changes to description and picture.");
	File file;
	// Default contructor: Starts the class to its default settings.		
	/**
	 * default constructor.
	 */
	public AddingEntries()
	{
		userFrame.setSize(600, 600);											// Sets the size of the UserFrame.
		userFrame.setLocation(250, 150);										// Sets the location of the UserFrame.
		
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// Tell the program to exit when the user is done.
		
		userPane.setLayout(new GridLayout(9, 2));							// Sets the userPane to 7 rows, and 2 cols.
		
		save.addActionListener(new Save());
		done.addActionListener(new Close());
		pictureNav.addActionListener(new NavAndSave());
		savePicDescription.addActionListener(new NavAndSave());
		
		JScrollPane scroller = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
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
		userPane.add(pictureUrl);
		userPane.add(pictureNav);
		userPane.add(descriptionLabel);
		userPane.add(scroller);
		userPane.add(blank);
		userPane.add(savePicDescription);
		userPane.add(save);
		userPane.add(done);
		
		userFrame.setVisible(true);											// Makes the UserFrame visible.
	}
	/**
	 * Constructor to allow the editing of the current EntryNode.
	 * @param editNow the current EntryNode being edited.
	 * @param picNode the current PictureNode being edited.
	 */
	public AddingEntries(EntryNode editNow, PictureNode picNode)
	{
		userFrame.setSize(600, 600);											// Sets the size of the UserFrame.
		userFrame.setLocation(250, 150);										// Sets the location of the UserFrame.
		
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// Tell the program to exit when the user is done.
		
		userPane.setLayout(new GridLayout(9, 2));							// Sets the userPane to 7 rows, and 2 cols.
		
		currentNode = editNow;
		pictureList = currentNode.getPictureList();
		PictureNode listPic = picNode;
		currentPicture = (PictureNode)pictureList.getNextItem();
		while (listPic.compareTo(currentPicture) != 0) {
			currentPicture = (PictureNode)pictureList.getNextItem();
		}
		
		save.addActionListener(new SaveEdit());
		done.addActionListener(new Close());
		saveEdit.addActionListener(new SaveChanges());
		pictureNav.addActionListener(new NavAndSave());
		savePicDescription.addActionListener(new NavAndSave());
		
		JScrollPane scroller = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		userPane.add(title);											// Adds title to the userPane.
		titleText.setText(""+currentNode.getTitle());
		userPane.add(titleText);									// Adds titleText to the userPane.
		userPane.add(greenNumber);												// Adds greenNumber to the userPane.
		greenNumberText.setText(""+currentNode.getGreenNumber());
		userPane.add(greenNumberText);
		userPane.add(taxRoll);											// Adds taxRoll to the userPane.
		taxRollText.setText(""+currentNode.getTaxRoll());
		userPane.add(taxRollText);
		userPane.add(location);											// Adds location to the userPane.
		locationText.setText(""+currentNode.getStreet());
		userPane.add(locationText);
		userPane.add(city);
		cityText.setText(""+currentNode.getCity());
		userPane.add(cityText);
		pictureUrl.setText(currentPicture.getPictureURL());
		userPane.add(pictureUrl);
		userPane.add(pictureNav);
		userPane.add(descriptionLabel);
		description.setText(currentPicture.getDescription());
		userPane.add(scroller);
		userPane.add(saveEdit);
		userPane.add(savePicDescription);
		userPane.add(save);
		userPane.add(done);
		
		userFrame.setVisible(true);											// Makes the UserFrame visible.
	}
	
	// SaveChanges allows changes to the current PictureNode
	private class SaveChanges implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				titleInfo = titleText.getText();
				if (file != null) {
					readFile = new File(file.getAbsolutePath());
				}
				else {
					readFile = new File(pictureUrl.getText());
				}
				if (titleInfo != null || titleInfo != "") {
			       	pictureInfo = pictureUrl.getText();
			       	BufferedImage img = ImageIO.read(readFile);
			       	boolean newDirectory = (new File("Pictures/"+titleInfo+"/").mkdirs());
			       	File outFile = new File("Pictures/"+titleInfo+"/Pictures Entries"+MainRun.pictureID()+".jpg");
					ImageIO.write(img, "jpg", outFile);				
					pictureInfo = outFile.getPath();
					boolean deletePic = (new File(currentPicture.getPictureURL()).delete());
					currentPicture.setPictureURL(pictureInfo);
					currentPicture.setDescription(description.getText());
					file = null;
					JOptionPane.showMessageDialog(userFrame, "Picture and description saved."); 
				}
				else {
					JOptionPane.showMessageDialog(userFrame, "Please enter a title.");
				}
		    } 
		    catch (IOException e) {
		    	JOptionPane.showMessageDialog(userFrame, "File was not there.");
		    }
		}
	}
	// SaveEdit Saves all the edits done to the EntryNode.
	private class SaveEdit implements ActionListener
	{				
		public void actionPerformed(ActionEvent event)				// The actionPerformed() method to gather the inputed data.
		{
			
			titleInfo = titleText.getText();
			locationInfo = locationText.getText();
			cityInfo = cityText.getText();
			
			MainRun.removeEntryNode(currentNode);
			try {
				greenNumberInfo = Long.parseLong(greenNumberText.getText());
				taxRollNumberInfo = Long.parseLong(taxRollText.getText());
				if (greenNumberInfo < 0 || taxRollNumberInfo < 0) {
					JOptionPane.showMessageDialog(userFrame, "Green Number, and Tax Roll Number must be higher then 0.");
				}
				
				EntryNode currentNode = new EntryNode(titleInfo, greenNumberInfo, taxRollNumberInfo, locationInfo, cityInfo, pictureList);
				MainRun.addEntryData(currentNode);
				userFrame.dispose();
				BrowseEntries finishedGUI = new BrowseEntries(currentNode);
				JOptionPane.showMessageDialog(userFrame, "Edit Saved.");
			}
			catch (NumberFormatException error) {
				JOptionPane.showMessageDialog(userFrame, "An error occured, please try inputing a number only in the ta.");
			}
			
		}
	}
	// Saves the new EntryNode into the database.
	private class Save implements ActionListener
	{				
		public void actionPerformed(ActionEvent event)				// The actionPerformed() method to gather the inputed data.
		{
			titleInfo = titleText.getText();
			locationInfo = locationText.getText();
			cityInfo = cityText.getText();
			try {
				greenNumberInfo = Long.parseLong(greenNumberText.getText());
				taxRollNumberInfo = Long.parseLong(taxRollText.getText());
				if (greenNumberInfo < 0 || taxRollNumberInfo < 0) {
					JOptionPane.showMessageDialog(userFrame, "Green Number, and Tax Roll Number must be higher then 0.");
				}
				
				EntryNode currentNode = new EntryNode(titleInfo, greenNumberInfo, taxRollNumberInfo, locationInfo, cityInfo, pictureList);
				MainRun.addEntryData(currentNode);
				pictureList = new UnsortedList();			// Resets to empty pictures and description list.
				titleInfo = "";
				greenNumberInfo = 0;
				taxRollNumberInfo = 0;
				locationInfo = "";
				pictureInfo = "";
				descriptionInfo = "";
				cityInfo = "";
				JOptionPane.showMessageDialog(userFrame, "Entry Saved.");
			}
			catch (NumberFormatException error) {
				JOptionPane.showMessageDialog(userFrame, "An error occured, please try inputing a number only.");
			}
		}
	}
	// Close closes the window if the user is done.
	private class Close implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int response = JOptionPane.showConfirmDialog(null, "Have you saved?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				userFrame.dispose();
			}
		}
	}
	// NavAndSave saves the current PictureNode into the EntryNode.
	private class NavAndSave implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand() == "Select picture File.") { 
				int returnVal = fc.showOpenDialog(userPane);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            file = fc.getSelectedFile();
		        	pictureUrl.setText(file.getAbsolutePath());         
		        }
		    }
			else if (event.getActionCommand() == "Add the picture and description.") {
				try {
					titleInfo = titleText.getText();
					if (file != null) {
						readFile = new File(file.getAbsolutePath());
					}
					else {
						readFile = new File(pictureUrl.getText());
					}
					if (titleInfo != null || titleInfo != "") {
				       	pictureInfo = pictureUrl.getText();
				       	BufferedImage img = ImageIO.read(readFile);
				       	boolean newDirectory = (new File("Pictures/"+titleInfo+"/").mkdirs());
				       	
							File outFile = new File("Pictures/"+titleInfo+"/Pictures Entries"+MainRun.pictureID()+".jpg");
							ImageIO.write(img, "jpg", outFile);				
							pictureInfo = outFile.getPath();
							PictureNode pictureNode = new PictureNode(pictureInfo, description.getText());
							pictureList.insert(pictureNode);
							file = null;
							JOptionPane.showMessageDialog(userFrame, "Picture and description saved.");
					}
					else {
						JOptionPane.showMessageDialog(userFrame, "Please enter a title.");
					}
			    } 
			    catch (IOException e) {
			    	JOptionPane.showMessageDialog(userFrame, "File was not there.");
			    }
			} 
		}
	}
}