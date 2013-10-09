package operations;

import java.io.*;
import databaseData.*;
import databaseInterface.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * ReadWrite is designed to write out an object to the text file designated in its constructor. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class ReadWriteTextFile {
	private String fileName;
	/**
	 * Default constructor.
	 */
	public ReadWriteTextFile() {
		fileName = "default.bin";
	}
	/**
	 * Constructor sets fileName to input.
	 * @param newFileName The file name to be outputed.
	 */
	public ReadWriteTextFile(String newFileName) {
		fileName = newFileName;
	}
	/**
	 * Saves the SortedList from the class MainRun to the file name in the constructor.
	 */
	public void SaveEntries() {
		
		ObjectOutputStream objectOut;
		try {
			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
			MainRun.resetEntryData();
			objectOut.writeObject(MainRun.getEntryData()); // Write object
			objectOut.close(); // Close the output stream
		}
		catch (FileNotFoundException error) {
			
		} 
		catch (IOException e) {
			
		}
	}
	/**
	 * Reads the file stored in the constructor returning it as a SortedList.
	 * @return Returns a sortedList read from the file name inputed into the constructor.
	 */
	public SortedList ReadEntries() {
	    ObjectInputStream objectIn = null;
		SortedList readList = new SortedList();
	    try {// Read from the stream until we hit the end
	    	objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
	    	readList = (SortedList)objectIn.readObject();
	    	readList.resetList();
	    	objectIn.close();
	    }	    		
	    catch (IOException e) {
		} 
	    catch (ClassNotFoundException e) {
		}
	    return readList;
	}
}