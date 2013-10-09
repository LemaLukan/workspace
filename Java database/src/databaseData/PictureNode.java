package databaseData;

import java.io.Serializable;

// PictureNode() creates a node that contains a file path to an image, and a string describing
// the image.
/**
 * contains and String holding a file path, and a description of the picture at that location. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class PictureNode implements Comparable, Serializable {
	String pictureURL;		// holds the picture path
	String description;		// holds the description of the image.
	/**
	 * Default constructor
	 */
	public PictureNode() {
		pictureURL = "";
		description = "";
	}
	/**
	 * Constructor taking the file path and description of the picture.
	 * @param picUrl Used to store the file path of the picture.
	 * @param described Used to describe the picture stored in picUrl.
	 */
	public PictureNode(String picUrl, String described) {
		pictureURL = picUrl;
		description = described;
	}
	/**
	 * @return Returns a String representing the file path to the picture.
	 */
	public String getPictureURL() {
		return pictureURL;
	}
	/**
	 * @return Returns a String representing a description of the picture stored.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param newURL Sets the file path for the picture to the inputed {@link String}.
	 */
	public void setPictureURL(String newURL) {
		pictureURL = newURL;
	}
	/**
	 * @param newDescription Sets the description of the picture to the inputed {@link String}.
	 */
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	// Compares the file paths to see if they are the same file.
	/**
	 * Used to compare one PictureNode to another.
	 * @param otherObject Should be a PictureNode.
	 */
	public int compareTo(Object otherObject) {
		return pictureURL.compareTo(((PictureNode)otherObject).pictureURL);
	}
}
