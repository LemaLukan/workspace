package operations;

import java.awt.*;	
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import databaseInterface.MainRun;
import databaseData.EntryNode;
import databaseData.PictureNode;
/**
 * 
 * @author Stephen Nusko
 * Used to display all the entries contained in MainRun class.
 */
public class BrowseEntries {
	
	JFrame userFrame = new JFrame();																			// Sets up a new JFrame.
	Container container = userFrame.getContentPane();
	
	JPanel panel = new JPanel();
	JLayeredPane lpane = new JLayeredPane();
	
	JLabel title = new JLabel("");
	JLabel address = new JLabel("");
	JLabel taxRollNumberLabel = new JLabel("Tax Roll #;");
	JLabel taxRollNumber = new JLabel("");
	JLabel city = new JLabel("");
	JTextArea Description = new JTextArea();
	Images currentImage;
	
	JButton next = new JButton("Next");
	JButton reset = new JButton("Reset");
	JButton prev = new JButton("Previous");
	JButton picNext = new JButton("Next Picture");
	JButton picPrev = new JButton("Prev Picture");
	JButton picReset = new JButton("Reset Pictures");
	JButton edit = new JButton("Edit");
	JButton remove = new JButton("Remove");
	JButton removePic = new JButton("remove Picture");
	
	Dimension dim;
	String describeText;
	PictureNode currentPicture;
	EntryNode currentNode;
	double widthBounds;
	double heightBounds;
	int fontSize;
	int pixelSize;
	int pixelWidth;
	double imagePlacement;
	
	/**
	 * Default constructor.
	 */
	public BrowseEntries() {
		
		Description = makeLabelStyle(Description);
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		JScrollPane scroller = new JScrollPane(Description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension((int)dim.getWidth()/2,(int)dim.getHeight()/4));
		
		pixelSize = findPixels();
		pixelWidth = findPixelWidth();
		
		userFrame.setSize((int)dim.getWidth(),((int)dim.getHeight()-25));											// Sets the size of the UserFrame.
		userFrame.setLocation(0, 0);										// Sets the location of the UserFrame.
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// Tell the program to exit when the user is done.
		userFrame.setLayout(new BorderLayout());
		
		fontSize = (int)(dim.getWidth()-dim.getHeight())/30;
		
		if (MainRun.isEmpty() != true) {		
			MainRun.resetEntryData();
			currentNode = MainRun.getNextItem();
			currentNode.resetPicture();
			city.setText(""+currentNode.getCity());
			title.setText(""+currentNode.getTitle());
			address.setText(""+currentNode.getGreenNumber()+" "+currentNode.getStreet());
			if (currentNode.getTaxRoll() > 0) {
				taxRollNumber.setText(""+currentNode.getTaxRoll());
				}
			else {
				taxRollNumber.setText(".                   "+currentNode.getTaxRoll()+"                   .");
			}
			currentPicture = currentNode.getNextImage();
			Description.setEditable(true);
			Description.setText(createParagraph(currentPicture.getDescription()));
			Description.setEditable(false);
			Description.setCaretPosition(0);
			currentImage = new Images(currentPicture, dim);	
		}
		else {
			JOptionPane.showMessageDialog(null, "No Entries currently in the database.");
		}
		lpane.setBounds(0, 0, (int)dim.getWidth(),((int)dim.getHeight()-25));
		
		reset.addActionListener(new Reset());
		next.addActionListener(new Next());
		prev.addActionListener(new Prev());
		picNext.addActionListener(new PicNext());
		picPrev.addActionListener(new PicPrev());
		picReset.addActionListener(new PicReset());
		
		reset.setBounds(0, 950, 100, 50);
		next.setBounds(150, 950, 100, 50);
		
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 6;
		c1.gridy = 0;
		c1.gridwidth = 5;
		c1.anchor = GridBagConstraints.PAGE_START;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		title.setFont(new Font("Dialog", 21, fontSize+2));
		panel.add(title, c1);	
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 17;
		c3.anchor = GridBagConstraints.CENTER;
		address.setFont(new Font("Dialog", 1, fontSize));
		panel.add(address, c3);
		
		if (currentNode.getTaxRoll() != 0) {
			GridBagConstraints c13 = new GridBagConstraints();
			c13.gridx = 0;
			c13.gridy = 15;
			c13.anchor = GridBagConstraints.CENTER;
			taxRollNumberLabel.setFont(new Font("Dialog", 1, fontSize));
			panel.add(taxRollNumberLabel, c13);
		}
		
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 0;
		c4.gridy = 16;
		c4.anchor = GridBagConstraints.CENTER;
		taxRollNumber.setFont(new Font("Dialog", 1, fontSize));
		panel.add(taxRollNumber,c4);
		
		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 0;
		c11.gridy = 18;
		c11.anchor = GridBagConstraints.CENTER;
		city.setFont(new Font("Dialog", 1, fontSize));
		panel.add(city, c11);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 5;
		c7.gridy = 20;
		c7.weighty = 1.0;
		c7.gridwidth = 9;
		c7.gridheight = 10;
		panel.add(scroller, c7);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 8;
		c5.gridy = 35;
		c5.weightx = 1.0;
		c5.weighty = 1.0;
		panel.add(reset, c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 18;
		c6.gridy = 35;
		c6.insets = new Insets(0,0,0,100);
		panel.add(next, c6);
		
		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 0;
		c8.gridy = 35;
		c8.gridwidth = 2;
		panel.add(prev, c8);
		
		GridBagConstraints c9 = new GridBagConstraints();
		c9.gridx = 9;
		c9.gridy = 19;
		c9.insets = new Insets(pixelSize, 0, 0, pixelWidth);
		panel.add(picNext, c9);
		
		GridBagConstraints c10 = new GridBagConstraints();
		c10.gridx = 7;
		c10.gridy = 19;
		c10.insets = new Insets(pixelSize, pixelWidth, 0, 0);
		panel.add(picPrev, c10);
		
		GridBagConstraints c12 = new GridBagConstraints();
		c12.gridx = 8;
		c12.gridy = 19;
		c12.insets = new Insets(pixelSize, 0, 0, 0);
		panel.add(picReset, c12);
		
		panel.setBounds(0, 0, 750, 750);
		
		widthBounds = dim.getWidth()/2.5;
		heightBounds = dim.getHeight()/2.5;
		
		imagePlacement = dim.getWidth()/3.35;
       
		currentImage.setBounds((int)imagePlacement,75,(int)widthBounds,(int)heightBounds);
        currentImage.setOpaque(true);
        
        panel.setBounds(0,0,(int)dim.getWidth(),((int)dim.getHeight()-75));
        panel.setOpaque(true);
        
		lpane.add(panel);
		lpane.add(currentImage,0);
		
		container.add(lpane);
		
		userFrame.setVisible(true);
	}
	/**
	 * Constructor that allows the user to edit the current entry.
	 * @param showNow The EntryNode to be shown, and allow edits.
	 */
	public BrowseEntries(EntryNode showNow) {
		
		Description = makeLabelStyle(Description);
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		JScrollPane scroller = new JScrollPane(Description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension((int)dim.getWidth()/2,(int)dim.getHeight()/4));
		
		pixelSize = findPixels();
		pixelWidth = findPixelWidth();
		
		userFrame.setSize((int)dim.getWidth(),((int)dim.getHeight()-25));											// Sets the size of the UserFrame.
		userFrame.setLocation(0, 0);										// Sets the location of the UserFrame.
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// Tell the program to exit when the user is done.
		userFrame.setLayout(new BorderLayout());
		
		fontSize = (int)(dim.getWidth()-dim.getHeight())/30;
		
		currentNode = showNow;
		currentNode.resetPicture();
		city.setText(""+currentNode.getCity());
		title.setText(""+currentNode.getTitle());
		address.setText(""+currentNode.getGreenNumber()+" "+currentNode.getStreet());
		if (currentNode.getTaxRoll() > 0) {
			taxRollNumber.setText(""+currentNode.getTaxRoll());
			}
		else {
			taxRollNumber.setText(".                   "+currentNode.getTaxRoll()+"                   .");
		}
		currentPicture = currentNode.getNextImage();
		Description.setEditable(true);
		Description.setText(createParagraph(currentPicture.getDescription()));
		Description.setEditable(false);
		Description.setCaretPosition(0);
		currentImage = new Images(currentPicture, dim);	
		
		
		lpane.setBounds(0, 0, (int)dim.getWidth(),((int)dim.getHeight()-25));
		
		edit.addActionListener(new Edit());
		remove.addActionListener(new Remove());
		removePic.addActionListener(new RemovePic());
		picNext.addActionListener(new PicNext());
		picPrev.addActionListener(new PicPrev());
		picReset.addActionListener(new PicReset());
		
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 6;
		c1.gridy = 0;
		c1.gridwidth = 5;
		c1.anchor = GridBagConstraints.PAGE_START;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		title.setFont(new Font("Dialog", 21, fontSize+2));
		panel.add(title, c1);	
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 17;
		c3.anchor = GridBagConstraints.CENTER;
		address.setFont(new Font("Dialog", 1, fontSize));
		panel.add(address, c3);
		
		if (currentNode.getTaxRoll() != 0) {
			GridBagConstraints c13 = new GridBagConstraints();
			c13.gridx = 0;
			c13.gridy = 15;
			c13.anchor = GridBagConstraints.CENTER;
			taxRollNumberLabel.setFont(new Font("Dialog", 1, fontSize));
			panel.add(taxRollNumberLabel, c13);
		}

		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 0;
		c4.gridy = 16;
		c4.anchor = GridBagConstraints.CENTER;
		taxRollNumber.setFont(new Font("Dialog", 1, fontSize));
		panel.add(taxRollNumber,c4);
		
		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 0;
		c11.gridy = 18;
		c11.anchor = GridBagConstraints.CENTER;
		city.setFont(new Font("Dialog", 1, fontSize));
		panel.add(city, c11);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 5;
		c7.gridy = 20;
		c7.weighty = 1.0;
		c7.gridwidth = 9;
		c7.gridheight = 10;
		panel.add(scroller, c7);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 8;
		c5.gridy = 35;
		c5.weightx = 1.0;
		c5.weighty = 1.0;
		panel.add(edit, c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 18;
		c6.gridy = 35;
		c6.insets = new Insets(0,0,0,100);
		panel.add(remove, c6);
		
		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 0;
		c8.gridy = 35;
		c8.gridwidth = 2;
		panel.add(removePic, c8);
		
		GridBagConstraints c9 = new GridBagConstraints();
		c9.gridx = 9;
		c9.gridy = 19;
		c9.insets = new Insets(pixelSize, 0, 0, pixelWidth);
		panel.add(picNext, c9);
		
		GridBagConstraints c10 = new GridBagConstraints();
		c10.gridx = 7;
		c10.gridy = 19;
		c10.insets = new Insets(pixelSize, pixelWidth, 0, 0);
		panel.add(picPrev, c10);
		
		GridBagConstraints c12 = new GridBagConstraints();
		c12.gridx = 8;
		c12.gridy = 19;
		c12.insets = new Insets(pixelSize, 0, 0, 0);
		panel.add(picReset, c12);
		
		panel.setBounds(0, 0, 750, 750);
		
		widthBounds = dim.getWidth()/2.5;
		heightBounds = dim.getHeight()/2.5;
		
		imagePlacement = dim.getWidth()/3.35;
       
		currentImage.setBounds((int)imagePlacement,75,(int)widthBounds,(int)heightBounds);
        currentImage.setOpaque(true);
        
        panel.setBounds(0,0,(int)dim.getWidth(),((int)dim.getHeight()-75));
        panel.setOpaque(true);
        
		lpane.add(panel);
		lpane.add(currentImage,0);
		
		container.add(lpane);
		
		userFrame.setVisible(true);
	}

	/**
	 * Constructor to display the current entry without allowing any editting.
	 * @param Browse The EntryNode to be displayed.
	 * @param tobedifferent A String to allow the program to know no editting allowed.
	 */
	public BrowseEntries(EntryNode Browse, String tobedifferent) {
		
		Description = makeLabelStyle(Description);
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		JScrollPane scroller = new JScrollPane(Description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setPreferredSize(new Dimension((int)dim.getWidth()/2,(int)dim.getHeight()/4));
		
		pixelSize = findPixels();
		pixelWidth = findPixelWidth();
		
		userFrame.setSize((int)dim.getWidth(),((int)dim.getHeight()-25));											// Sets the size of the UserFrame.
		userFrame.setLocation(0, 0);										// Sets the location of the UserFrame.
		userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// Tell the program to exit when the user is done.
		userFrame.setLayout(new BorderLayout());
		
		fontSize = (int)(dim.getWidth()-dim.getHeight())/30;
		currentNode = MainRun.findNode(Browse);
		currentNode.resetPicture();
		city.setText(""+currentNode.getCity());
		title.setText(""+currentNode.getTitle());
		address.setText(""+currentNode.getGreenNumber()+" "+currentNode.getStreet());
		if (currentNode.getTaxRoll() > 0) {
			taxRollNumber.setText(""+currentNode.getTaxRoll());
			}
		else {
			taxRollNumber.setText(".                   "+currentNode.getTaxRoll()+"                   .");
		}
		currentPicture = currentNode.getNextImage();
		Description.setEditable(true);
		Description.setText(createParagraph(currentPicture.getDescription()));
		Description.setEditable(false);
		Description.setCaretPosition(0);
		currentImage = new Images(currentPicture, dim);	
		
		
		lpane.setBounds(0, 0, (int)dim.getWidth(),((int)dim.getHeight()-25));
		
		reset.addActionListener(new Reset());
		next.addActionListener(new Next());
		prev.addActionListener(new Prev());
		picNext.addActionListener(new PicNext());
		picPrev.addActionListener(new PicPrev());
		picReset.addActionListener(new PicReset());
		
		reset.setBounds(0, 950, 100, 50);
		next.setBounds(150, 950, 100, 50);
		
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 6;
		c1.gridy = 0;
		c1.gridwidth = 5;
		c1.anchor = GridBagConstraints.PAGE_START;
		c1.weightx = 1.0;
		c1.weighty = 1.0;
		title.setFont(new Font("Dialog", 21, fontSize+2));
		panel.add(title, c1);	
		
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 17;
		c3.anchor = GridBagConstraints.CENTER;
		address.setFont(new Font("Dialog", 1, fontSize));
		panel.add(address, c3);
		
		if (currentNode.getTaxRoll() != 0) {
			GridBagConstraints c13 = new GridBagConstraints();
			c13.gridx = 0;
			c13.gridy = 15;
			c13.anchor = GridBagConstraints.CENTER;
			taxRollNumberLabel.setFont(new Font("Dialog", 1, fontSize));
			panel.add(taxRollNumberLabel, c13);
		}

		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 0;
		c4.gridy = 16;
		c4.anchor = GridBagConstraints.CENTER;
		taxRollNumber.setFont(new Font("Dialog", 1, fontSize));
		panel.add(taxRollNumber,c4);
		
		GridBagConstraints c11 = new GridBagConstraints();
		c11.gridx = 0;
		c11.gridy = 18;
		c11.anchor = GridBagConstraints.CENTER;
		city.setFont(new Font("Dialog", 1, fontSize));
		panel.add(city, c11);
		
		GridBagConstraints c7 = new GridBagConstraints();
		c7.gridx = 5;
		c7.gridy = 20;
		c7.weighty = 1.0;
		c7.gridwidth = 9;
		c7.gridheight = 10;
		panel.add(scroller, c7);
		
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 8;
		c5.gridy = 35;
		c5.weightx = 1.0;
		c5.weighty = 1.0;
		panel.add(reset, c5);
		
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 18;
		c6.gridy = 35;
		c6.insets = new Insets(0,0,0,100);
		panel.add(next, c6);
		
		GridBagConstraints c8 = new GridBagConstraints();
		c8.gridx = 0;
		c8.gridy = 35;
		c8.gridwidth = 2;
		panel.add(prev, c8);
		
		GridBagConstraints c9 = new GridBagConstraints();
		c9.gridx = 9;
		c9.gridy = 19;
		c9.insets = new Insets(pixelSize, 0, 0, pixelWidth);
		panel.add(picNext, c9);
		
		GridBagConstraints c10 = new GridBagConstraints();
		c10.gridx = 7;
		c10.gridy = 19;
		c10.insets = new Insets(pixelSize, pixelWidth, 0, 0);
		panel.add(picPrev, c10);
		
		GridBagConstraints c12 = new GridBagConstraints();
		c12.gridx = 8;
		c12.gridy = 19;
		c12.insets = new Insets(pixelSize, 0, 0, 0);
		panel.add(picReset, c12);
		
		panel.setBounds(0, 0, 750, 750);
		
		widthBounds = dim.getWidth()/2.5;
		heightBounds = dim.getHeight()/2.5;
		
		imagePlacement = dim.getWidth()/3.35;
       
		currentImage.setBounds((int)imagePlacement,75,(int)widthBounds,(int)heightBounds);
        currentImage.setOpaque(true);
        
        panel.setBounds(0,0,(int)dim.getWidth(),((int)dim.getHeight()-75));
        panel.setOpaque(true);
        
		lpane.add(panel);
		lpane.add(currentImage,0);
		
		container.add(lpane);
		
		userFrame.setVisible(true);
	}
	// Edit allows users to edit the current EntryNode.
	private class Edit implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			userFrame.dispose();
			AddingEntries editGUI = new AddingEntries(currentNode, currentPicture);
		}
	}
	// Remove completely removes the current entry.
	private class Remove implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int response = JOptionPane.showConfirmDialog(null, "Do you want to delete the entry?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
			   	MainRun.deleteEntryNode(currentNode);
			   	userFrame.dispose();
			}
		}
	}
	// RemovePic completely removes the current picture.
	private class RemovePic implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int response = JOptionPane.showConfirmDialog(null, "Do you want to delete the currentPicture?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				MainRun.deletePicture(currentNode, currentPicture);
				currentPicture = currentNode.getNextImage();
			    Description.setEditable(true);
				Description.setText(createParagraph(currentPicture.getDescription()));
				Description.setEditable(false);
				Description.setCaretPosition(0);	
				currentImage.setPictureNode(currentPicture);
			}
		}
	}
	// Next returns the next EntryNode to display.
	private class Next implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			if (MainRun.isEmpty() == false) {
				currentNode = MainRun.getNextItem();
				title.setText(""+currentNode.getTitle());
				city.setText(""+currentNode.getCity());
				address.setText(""+currentNode.getGreenNumber()+" "+currentNode.getStreet());
				if (currentNode.getTaxRoll() > 0) {
				taxRollNumber.setText(""+currentNode.getTaxRoll());
				}
				else {
					taxRollNumber.setText(".                   "+currentNode.getTaxRoll()+"                   .");
				}
			    currentNode.resetPicture();
				currentPicture = currentNode.getNextImage();
			    Description.setEditable(true);
				Description.setText(createParagraph(currentPicture.getDescription()));
				Description.setEditable(false);
				Description.setCaretPosition(0);	
				currentImage.setPictureNode(currentPicture);
			}
		}
	}
	// Prev returns the previous EntryNode to display.
	private class Prev implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			if (MainRun.isEmpty() == false) {
				currentNode = MainRun.getPrevItem();
				title.setText(""+currentNode.getTitle());
				city.setText(""+currentNode.getCity());
				address.setText(""+currentNode.getGreenNumber()+" "+currentNode.getStreet());
				if (currentNode.getTaxRoll() > 0) {
					taxRollNumber.setText(""+currentNode.getTaxRoll());
					}
				else {
					taxRollNumber.setText(".                   "+currentNode.getTaxRoll()+"                   .");
				}       
			    currentNode.resetPicture();
				currentPicture = currentNode.getNextImage();	
			    Description.setEditable(true);
				Description.setText(createParagraph(currentPicture.getDescription()));
				Description.setEditable(false);
				Description.setCaretPosition(0);				
				currentImage.setPictureNode(currentPicture);
			}
		}
	}
	// Reset returns the list to its first item.
	private class Reset implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			MainRun.resetEntryData();
			currentNode = MainRun.getNextItem();
			title.setText(""+currentNode.getTitle());
			city.setText(""+currentNode.getCity());
			address.setText(""+currentNode.getGreenNumber()+" "+currentNode.getStreet());
			if (currentNode.getTaxRoll() > 0) {
				taxRollNumber.setText(""+currentNode.getTaxRoll());
				}
			else {
				taxRollNumber.setText(".                   "+currentNode.getTaxRoll()+"                   .");
			}
			currentNode.resetPicture();
			currentPicture = currentNode.getNextImage();
			Description.setEditable(true);
			Description.setText(createParagraph(currentPicture.getDescription()));
			Description.setEditable(false);
			Description.setCaretPosition(0);
			currentImage.setPictureNode(currentPicture);
		}
	}
	// PicNext returns the next image for the current EntryNode.
	private class PicNext implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			currentPicture = currentNode.getNextImage();
			Description.setEditable(true);
			Description.setText(createParagraph(currentPicture.getDescription()));
			Description.setEditable(false);
			Description.setCaretPosition(0);
			currentImage.setPictureNode(currentPicture);
		}
	}
	// PicPrev returns the previous image for the current EntryNode.
	private class PicPrev implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			currentPicture = currentNode.getPrevImage();
			Description.setEditable(true);
			Description.setText(createParagraph(currentPicture.getDescription()));
			Description.setEditable(false);
			Description.setCaretPosition(0);
			currentImage.setPictureNode(currentPicture);
		}
	}
	// PicReset returns the image to the first for the current EntryNode.
	private class PicReset implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			currentNode.resetPicture();
			currentPicture = currentNode.getNextImage();
			Description.setEditable(true);
			Description.setText(createParagraph(currentPicture.getDescription()));
			Description.setEditable(false);
			Description.setCaretPosition(0);
			currentImage.setPictureNode(currentPicture);
		}
	}
	/**
	 * Figures out the height placement of the picture buttons.
	 * @return Returns an int equal to the required top inset.
	 */
	private int findPixels() {
		int pixels = 0;
		for (int pixelCounter = 0; pixelCounter < dim.getHeight(); pixelCounter = pixelCounter + 100) {
			pixels = pixels + 33;
		}
		return pixels;
	}
	/**
	 * Figures out the required width placement of the picture buttons.
	 * @return Returns an int equal to the required left and right inset.
	 */
	private int findPixelWidth() {
		int pixels = 0;
		for (int i = 0; i < dim.getWidth(); i = i + 100) {
			pixels = pixels + 22;
		}
		return pixels;
	}
	/**
	 * Creates a paragraph of multiple lines of the inputed String.
	 * @param newString The String to be converted into a multiple line paragraph.
	 * @return Returns the new String if it was big enough with multiple lines, otherwise as it was.
	 */
	public String createParagraph(String newString) {
		String finished = "";
		char checkP = '.';
		char checkC = ',';
		char checkS = ' ';
		char checkSemi = ';';
		char checkFull = ':';
		double limit = (((dim.getWidth()/3.5)*1.2)/4.2);
		if (newString.length() > (int)limit) {
			int startCounter = 0;
			int endCounter = 0;
			int startCountDown = 0;
			int start2CountDown = 0;
			boolean spaceUsed = false;
			while (endCounter < newString.length()) {
				endCounter = endCounter + (int)limit;
				if (endCounter <= newString.length()) {
					while (startCountDown < endCounter) {
						startCountDown++;
					}
					while (checkP != newString.charAt(endCounter) && endCounter > startCounter) {
						endCounter--;
					}
					if (endCounter > startCounter) {
						endCounter++;
						endCounter++;
						finished = finished + newString.substring(startCounter, endCounter) +"\n";
						startCounter = endCounter;
					}
					else {
						while (start2CountDown < startCountDown) {
							start2CountDown++;
						}
						endCounter = startCountDown;
						while (checkC != newString.charAt(endCounter) || checkSemi != newString.charAt(endCounter)|| checkFull != newString.charAt(endCounter) ) {
							if (endCounter > startCounter) {
								endCounter--;
							}
							else {
								break;
							}
						}
						if (endCounter <= startCounter) {
							endCounter = start2CountDown;
							while (checkS != newString.charAt(endCounter)) {
								endCounter--;
							}
						}
						endCounter++;
						finished = finished + newString.substring(startCounter, endCounter) +"\n";	
						startCounter = endCounter;
					}
				}
				else {
					finished = finished + newString.substring(startCounter, newString.length());
				}
			}
		}
		else {
			finished = newString;
		}
		return finished;
	}
	/**
	 * Changes all the settings to make a textArea appear like a label.
	 * @param textArea The text area to be converted.
	 * @return Returns a JTextArea formatted to look like a label.
	 */
	public static JTextArea makeLabelStyle(JTextArea textArea) {   
		if (textArea == null) {
			return null;   
		}
	    textArea.setEditable(false);   
	    textArea.setCursor(null);   
	    textArea.setOpaque(false);   
	    textArea.setFocusable(false);   
	    return textArea;   
	}  
}