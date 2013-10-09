package databaseInterface;

import java.io.File;

import operations.ReadWriteTextFile;
import databaseData.*;

// MainRun starts up the GUI for the database, and creates the Sorted List.
/**
 * MainRun Starts the program and contains the list of entries, and methods to operate on the list. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011 (First release)
 * @version Beta 1.0.1 2011 (Corrected display problems for 1400x900 screens, added remove entry onto search return, and fixed a bug that crashed the createParagraph() method)
 */
public class MainRun {
	private static SortedList entryData;
	protected static ReadWriteTextFile readFile;
	
	/**
	 * getEntryData returns the SortedList that stores all the entry data.
	 * @return Returns a SortedList with the current Data that was loaded at program start up.
	 */
	public static SortedList getEntryData() {
		return entryData;
	}
	/**
	 * AddEntryData will add and then save the list to object.bin
	 * @param input Used to pass the entryNode to enter into the SortedList.
	 */
	public static void addEntryData(EntryNode input) {
		entryData.insert(input);
		readFile.SaveEntries();	
	}
	/**
	 * entryDataLength will retrive the length of the data list.
	 * @return returns an int equal to the number of items on the list.
	 */
	public static int entryDataLength() {
		return entryData.length();
	}
	/**
	 * ResetEntryData will reset the list to its original condition.
	 */
	public static void resetEntryData() {
		entryData.resetList();
	}
	/**
	 * getNextItem will retrieve the next item on the list.
	 * @return returns an EntryNode that is next on the list.
	 */
	public static EntryNode getNextItem() {
		return (EntryNode)entryData.getNextItem();
	}
	/**
	 * getPrevItem will retrieve the previous item on the list.
	 * @return returns an EntryNode that is previous on the list.
	 */
	public static EntryNode getPrevItem() {
		return (EntryNode)entryData.getPrevItem();
	}
	/**
	 * isEmpty checks if the list has any data in it.
	 * @return returns a boolean true if empty false otherwise.
	 */
	public static boolean isEmpty(){
		return entryData.isEmpty();
	}
	/**
	 * PictureID is used to get a currently unused number for use as a picture ID
	 * @return returns a long one more then the last time called.
	 */
	public static long pictureID() {
		return entryData.pictureID();
	}
	/**
	 * deleteEntryNode will find the EntryNode deleteing all pictures from the hard drive then removes it from the list
	 * @param input Removes the EntryNode object passed from the list, as well as any files saves with it.
	 */
	public static void deleteEntryNode(EntryNode input) {
		input.deletePictureList();
		if (new File("Pictures/"+input.getTitle()).list().length == 0) {
			boolean deleteFolder = (new File("Pictures/"+input.getTitle()).delete());
		}
		entryData.delete(input);
		readFile.SaveEntries();	
	}
	/**
	 * removeEntryNode is used when a full delete of photos is unneeded or harmful
	 * @param input Removes the EntryNode object passed from the list, leaving any files intact.
	 */
	public static void removeEntryNode(EntryNode input) {
		entryData.delete(input);
		readFile.SaveEntries();
	}
	/**
	 * deletePicture will delete the selected picture from the hard drive and the entryNode and saves
	 * the results to objects.bin
	 * @param input1 The EntryNode containing the photo to be deleted.
	 * @param input2 The PictureNode to be deleted.
	 */
	public static void deletePicture(EntryNode input1, PictureNode input2) {
		EntryNode current = input1;
		PictureNode deletePic = input2;
		current.deletePicture(deletePic);
		readFile.SaveEntries();	
	}
	/**
	 * findNode checks to see if the EntryNode is there and then returns it if true.
	 * @param input the entryNode to be looked for.
	 * @return Returns an EntryNode if true otherwise will return null.
	 */
	public static EntryNode findNode(EntryNode input) {
		int jumpTo = entryData.returnIndex(input);
		if (jumpTo != -1) {
			entryData.jumpTo(jumpTo);
			return (EntryNode)entryData.getNextItem();
		}
		return null;
	}
	/**
	 * findNodeTaxNumber checks to see if the EntryNode is there and then returns it if true.
	 * @param taxNumber A long variable of the tax Roll number of the entry you are looking for.
	 * @return Returns an EntryNode if found otherwise will return null.
	 */	
	public static EntryNode findNodeTaxNumber(long taxNumber) {
		EntryNode currentNode;
		entryData.resetList();
		for (int i = 0; i < entryData.length(); i++) {
			currentNode = (EntryNode)entryData.getNextItem();
			if (taxNumber == currentNode.getTaxRoll()) {
				return currentNode;
			}
		}		
		return null;		
	}
	/**
	 * findNodeAddressAndCity checks to see if the EntryNode is there and then returns it if true.
	 * @param greenNumber A long variable of the green number of the entry you are looking for.
	 * @param street The street of the entry you are looking for.
	 * @param city The City of the entry you are looking for.
	 * @return Returns an EntryNode if found otherwise will return null.
	 */
	public static EntryNode findNodeAddressAndCity(long greenNumber, String street, String city) {
		EntryNode currentNode;
		entryData.resetList();
		for (int i = 0; i < entryData.length(); i++) {
			currentNode = (EntryNode)entryData.getNextItem();
			if (greenNumber == currentNode.getGreenNumber() && street.toLowerCase().compareTo(currentNode.getStreet().toLowerCase()) == 0 && city.toLowerCase().compareTo(currentNode.getCity().toLowerCase())== 0) {
				return currentNode;
			}
		}	
		return null;
	}
	/**
	 * findNodeAddress checks to see if any entry matches the inputed values.
	 * @param greenNumber A long variable of the green number of the entry you are looking for.
	 * @param street The street of the entry you are looking for.
	 * @return Returns an UnsortedList either containing all matches or empty if none were found.
	 */
	public static UnsortedList findNodeAddress(long greenNumber, String street) {
		UnsortedList results = new UnsortedList();
		EntryNode currentNode;
		entryData.resetList();
		for (int i = 0; i < entryData.length(); i++) {
			currentNode = (EntryNode)entryData.getNextItem();
			if (greenNumber == currentNode.getGreenNumber() && street.toLowerCase().compareTo(currentNode.getStreet().toLowerCase()) == 0) {
				results.insert(currentNode);
			}
		}	
		return results;
	}/**
	 * findNodeStreetAndCity checks to see if any entry matches the inputed values.
	 * @param city The city of the entry you are looking for.
	 * @param street The street of the entry you are looking for.
	 * @return Returns an UnsortedList either containing all matches or empty if none were found.
	 */
	public static UnsortedList findNodeStreetAndCity(String city, String street) {
		UnsortedList results = new UnsortedList();
		EntryNode currentNode;
		entryData.resetList();
		for (int i = 0; i < entryData.length(); i++) {
			currentNode = (EntryNode)entryData.getNextItem();
			if (currentNode.getCity().toLowerCase().compareTo(city.toLowerCase()) == 0 && currentNode.getStreet().toLowerCase().compareTo(street.toLowerCase()) == 0) {
				results.insert(currentNode);
			}
		}
		return results;
	}
	/**
	 * findNodeCity checks to see if any entry matches the inputed value.
	 * @param city The city of the entry you are looking for.
	 * @return Returns an UnsortedList either containing all matches or empty if none were found.
	 */
	public static UnsortedList findNodeCity(String city) {
		UnsortedList results = new UnsortedList();
		EntryNode currentNode;
		entryData.resetList();
		for (int i = 0; i < entryData.length(); i++) {
			currentNode = (EntryNode)entryData.getNextItem();
			if (currentNode.getCity().toLowerCase().compareTo(city.toLowerCase()) == 0) {
				results.insert(currentNode);
			}
		}
		return results;
	}
	/**
	 * findNodeStreet checks to see if any entry matches the inputed value.
	 * @param Street The street of the entry you are looking for.
	 * @return Returns an UnsortedList either containing all matches or empty if none were found.
	 */
	public static UnsortedList findNodeStreet(String Street) {
		UnsortedList results = new UnsortedList();
		EntryNode currentNode;
		entryData.resetList();
		for (int i = 0; i < entryData.length(); i++) {
			currentNode = (EntryNode)entryData.getNextItem();
			if (currentNode.getStreet().toLowerCase().compareTo(Street.toLowerCase()) == 0) {
				results.insert(currentNode);
			}
		}
		return results;
	}
	/**
	 * findNodeTitle checks to see if any entry matches the inputed value.
	 * @param title The title of the entry you are looking for.
	 * @return Returns an UnsortedList either containing all matches or empty if none were found.
	 */
	public static UnsortedList findNodeTitle(String title) {
		UnsortedList results = new UnsortedList();
		EntryNode currentNode;
		entryData.resetList();
		for (int i = 0; i < entryData.length(); i++) {
			currentNode = (EntryNode)entryData.getNextItem();
			if (currentNode.getTitle().toLowerCase().compareTo(title.toLowerCase()) == 0) {
				results.insert(currentNode);
			}
		}
		return results;
	}
	/**
	 * findNodeGreenNumber checks to see if any entry matches the inputed value.
	 * @param greenNumber A long variable of the greenNumber of the entry you are looking for.
	 * @return Returns an UnsortedList either containing all matches or empty if none were found.
	 */
	public static UnsortedList findNodeGreenNumber(long greenNumber) {
		UnsortedList results = new UnsortedList();
		EntryNode currentNode;
		entryData.resetList();
		for (int i = 0; i < entryData.length(); i++) {
			currentNode = (EntryNode)entryData.getNextItem();
			if (currentNode.getGreenNumber() == greenNumber) {
				results.insert(currentNode);
			}
		}
		return results;
	}
	/**
	 * Starts the Database program.
	 */
	public static void main (String[] args) {
		readFile = new ReadWriteTextFile("objects.bin");	// Reads the Saved SortedList if any.
		entryData = readFile.ReadEntries();
		final MainMenuGUI menuStart = new MainMenuGUI();
	}	
}	
