package databaseData;

import java.io.File;
import java.io.Serializable;

/**
 * EntryNode stores all required information needed for the database.
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class EntryNode implements Comparable, Serializable {
	String title;
	long greenNumber;
	long taxRollNumber;
	String address;
	String city;
	UnsortedList pictureURL;
	PictureNode current;
	String POBox = "";
	String postalCode = "";
	String country = "";
	String province = "";
	String roadPostFix = "";
	String age = "";
	String type = "";
	String owners = "";
	String orignalOwners = "";
	String orignalLocation = "";
	String history = "";
	String year = "";
	String style = "";
	SortedList SortedExtraList = new SortedList();
	UnsortedList UnsortedExtraList = new UnsortedList();
	
	/**
	 * default constructor.
	 */
	public EntryNode() {
		title = "";
		greenNumber = 0;
		taxRollNumber = 0;
		address = "";
		city = "";
		pictureURL = new UnsortedList();
	}
	/**
	 * Version beta 1.0 used to selectively fill in the class variables.
	 * @param newTitle Sets the title of the entry to the inputed String.
	 * @param newGreenNumber Sets the green number to the inputed long.
	 * @param newTaxRollNumber Sets the tax Roll number to the inputed long.
	 * @param newAddress Sets the street address to the inputed String.
	 * @param newCity sets the city to the inputed String.
	 * @param newPictureURL sets the Picture list to the inputed UnsortedList.
	 */
	public EntryNode(String newTitle, long newGreenNumber, long newTaxRollNumber, String newAddress, String newCity, UnsortedList newPictureURL) {
		title = newTitle;
		greenNumber = newGreenNumber;
		taxRollNumber = newTaxRollNumber;
		address = newAddress;
		city = newCity;
		pictureURL = newPictureURL;
	}
	/**
	 * The long version constructor currently un-used as of beta 1.0.
	 * @param newTitle Sets the title of the entry to the inputed String.
	 * @param newGreenNumber Sets the green number to the inputed long.
	 * @param newTaxRollNumber Sets the tax Roll number to the inputed long.
	 * @param newAddress Sets the street address to the inputed String.
	 * @param newCity sets the city to the inputed String.
	 * @param newPictureURL sets the Picture list to the inputed UnsortedList.
	 * @param newPOBox Sets the POBox to the String.
	 * @param newPostalCode Sets the postal code to the String.
	 * @param newCountry Sets the country to the String.v
	 * @param newProvince Sets the province to the String.
	 * @param newRoadPostFix Sets the road post-fix to the String.
	 * @param newAge Sets the age to the String.
	 * @param newType Sets the type to the String.
	 * @param newOwners Sets the owners to the String.
	 * @param newOrignOwners Sets the orignOwners to the String.
	 * @param newOrignLocation Sets the orignLocation to the String.
	 * @param newHistory Sets the history to the String.
	 * @param newYear Sets the year to the String.
	 * @param newStyle Sets the style to the String.
	 * @param newSortedExtraList Sets the extra sorted list to the inputed SortedList.
	 * @param newUnsortedExtraList Sets the extra unsorted list to the inputed UnsortedList.
	 */
	public EntryNode(String newTitle, long newGreenNumber, long newTaxRollNumber, String newAddress, String newCity, UnsortedList newPictureURL, 
					String newPOBox, String newPostalCode,	String newCountry, String newProvince, String newRoadPostFix, String newAge,
					String newType, String newOwners, String newOrignOwners, String newOrignLocation, String newHistory, String newYear,
					String newStyle, SortedList newSortedExtraList,	UnsortedList newUnsortedExtraList) {
		title = newTitle;
		greenNumber = newGreenNumber;
		taxRollNumber = newTaxRollNumber;
		address = newAddress;
		city = newCity;
		pictureURL = newPictureURL;
		POBox = newPOBox;
		postalCode = newPostalCode;
		country = newCountry;
		province = newProvince;
		roadPostFix = newRoadPostFix;
		age = newAge;
		type = newType;
		owners = newOwners;
		orignalOwners = newOrignOwners;
		orignalLocation = newOrignLocation;
		history = newHistory;
		year = newYear;
		style = newStyle;
		SortedExtraList = newSortedExtraList;
		UnsortedExtraList = newUnsortedExtraList;
	}
	/**
	 * used to compare one EntryNode to another.
	 * @param other Should be an EntryNode to be compared with..
	 */
	public int compareTo(Object other){
		if (greenNumber == ((EntryNode)other).greenNumber && taxRollNumber == ((EntryNode)other).taxRollNumber ) {
			return 0;
		}
		String thisObject = title +" "+address;
		String otherObject = ((EntryNode)other).title + " "+ ((EntryNode)other).address;
		return thisObject.compareTo(otherObject);
	}
	/**
	 * @return Returns the title as a String.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return Returns the street as a String
	 */
	public String getStreet() {
		return address;
	}
	/**
	 * @return Returns the green number as a long.
	 */
	public long getGreenNumber() {
		return greenNumber;
	}
	/**
	 * @return Returns the tax roll as a long.
	 */
	public long getTaxRoll() {
		return taxRollNumber;
	}
	/**
	 * @return Returns the city as a String
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param newTitle Sets the title to the inputed String.
	 */
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	/**
	 * @param newStreet Sets the street to the inputed String.
	 */
	public void setStreet(String newStreet) {
		address = newStreet;
	}
	/**
	 * @param newGreenNumber Sets the green number to the inputed long.
	 */
	public void setGreenNumber(long newGreenNumber) {
		greenNumber = newGreenNumber;
	}
	/**
	 * @param newTaxRoll Sets the tax Roll to the inputed long.
	 */
	public void setTaxRoll(long newTaxRoll) {
		taxRollNumber = newTaxRoll;
	}
	/**
	 * @param newCity Sets the city to the inputed String.
	 */
	public void setCity(String newCity) {
		city = newCity;
	}
	/**
	 * Gets the next PictureNode.
	 * @return Returns the next PictureNode to display.
	 */
	public PictureNode getNextImage() {
		current = (PictureNode)pictureURL.getNextItem();
		return current;
	}
	/**
	 * Gets the previous PictureNode.
	 * @return Returns the previous PictureNode to display.
	 */
	public PictureNode getPrevImage() {
		current = (PictureNode)pictureURL.getPrevItem();
		return current;
	}
	/**
	 * Places the counter to the start of the list.
	 */
	public void resetPicture() {
		pictureURL.resetList();
	}
	/**
	 * Deletes the selected PictureNode
	 * @param input Deletes the input from the picture list.
	 */
	public void deletePicture(PictureNode input) {
		boolean deleteSuccess = (new File(input.getPictureURL()).delete());
		pictureURL.delete(input);
		pictureURL.resetList();
	}
	/**
	 * Deletes the whole picture list.
	 */
	public void deletePictureList() {
		PictureNode currentPicture;
		boolean deleteSuccess;
		for (int i = 0; i < pictureURL.length(); i++) {
			currentPicture = (PictureNode)pictureURL.getNextItem();
			deleteSuccess = (new File(currentPicture.getPictureURL()).delete());
		}
	}
	/**
	 * @return Returns an UnsortedList containing everything inside the picture list.
	 */
	public UnsortedList getPictureList() {
		return pictureURL;
	}
	
	
	// These are future observers if needed to be activated Add java doc comments if used..
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String newPostal) {
		postalCode = newPostal;
	}
	public String getPOBox() {
		return POBox;
	}
	public void setPOBox(String newPO) {
		POBox = newPO;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String newCountry) {
		country = newCountry;
	}
	public String getProvince() {
		return province;
	}
	public String getRoadPostFix() {
		return roadPostFix;
	}
	public void setRoadPostFix(String newRoadPost) {
		roadPostFix = newRoadPost;
	}
	public String getAge() {
		return age;
	}
	public String getType() {
		return type;
	}
	public String getOwners() {
		return owners;
	}
	public String getOrignalOwners() {
		return orignalOwners;
	}
	public String getOrignalLocation() {
		return orignalLocation;
	}
	public String getHistory() {
		return history;
	}
	public String getYear() {
		return year;
	}
	public String getStyle() {
		return style;
	}
	public SortedList getSortedExtraList() {
		return SortedExtraList;
	}
	public UnsortedList getUnsortedExtraList() {
		return UnsortedExtraList;
	}
	public void setAge(String input) { 
		age = input;
	}
	public void setType(String input) { 
		type = input;
	}
	public void setOwners(String input) {
		owners = input;
	}
	public void setOrignalOwners(String input) { 
		orignalOwners = input;
	}
	public void setOrignalLocation(String input) { 
		orignalLocation = input;
	}
	public void setHistory(String input) {
		history = input;
	}
	public void setYear(String input) { 
		year = input;
	}
	public void setStyle(String input) { 
		style = input;
	}
	public void setSortedExtraList(SortedList input) {
		SortedExtraList = input;
	}
	public void setUnsortedExtraList(UnsortedList input) {
		UnsortedExtraList = input;
	}
}