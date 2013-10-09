package databaseData;

import java.io.Serializable;

// class List                                                             //
// Provides routines needed for implementing a generic list               //
//------------------------------------------------------------------------//
/**
 * class List
 * Provides routines needed for implementing a generic list. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public abstract class List implements Serializable
{
  protected Comparable[] listItems;
  protected int numItems;
  protected int currentPos;
  protected boolean nextWasUsed;
  protected boolean prevWasUsed;
//------------------------------------------------------------------------//
// List()                                                                 //
// Default Constructor. Creates an empty list of size 100                 //
//------------------------------------------------------------------------//
  /**
   * Default Constructor, creates an empty list of 100.
   */
  public List()
  {
    numItems = 0;
    listItems = new Comparable[100];
    currentPos = 0;
    nextWasUsed = false;
    prevWasUsed = false;
  }

//------------------------------------------------------------------------//
// List(int)                                                              //
// Constructor. Creates an empty list of the specified size               //
//------------------------------------------------------------------------//
  /**
   * Constructor. Creates an empty list of the specified size.
   * @param maxItem Used to specify the size of the list.
   */
  public List(int maxItem)
  {
    numItems = 0;
    listItems = new Comparable[maxItem];
    currentPos = 0;
    nextWasUsed = false;
    prevWasUsed = false;
  }

//------------------------------------------------------------------------//
// boolean isFull()                                                       //
// Returns true if the list is full...otherwise false                     //
//------------------------------------------------------------------------//
  /**
   * @return Returns true if the list is full otherwise doubles the current list size.
   */
  public boolean isFull()
  {
	if (listItems.length == numItems) {
		listItems = doubleList();
	}
    return (listItems.length == numItems);
  }

//------------------------------------------------------------------------//
// boolean isEmpty                                                        //
// Returns true if the list is empty...otherwise false                    //
//------------------------------------------------------------------------//
  /**
   * @return Returns true if the list has no items otherwise false.
   */
  public boolean isEmpty()
  {
    return (numItems == 0);
  }

//------------------------------------------------------------------------//
// int length()                                                           //
// Returns the number of items in the list                                //
//------------------------------------------------------------------------//
  /**
   * Returns Returns the number of items on the list.
   */
  public int length()
  {
    return numItems;
  }

//------------------------------------------------------------------------//
// void resetList()                                                       //
// Sets the position of the list to the beginning for use with the        //
// getNextItem() function                                                 //
//------------------------------------------------------------------------//
  /**
   * resets the list counter to its starting position.
   */
  public void resetList()
  {
    currentPos = 0;
    prevWasUsed = false;
    nextWasUsed = false;
  }
  
  /**
   * Causes the list to jump to the chosen location
   * @param pos jumps the location to the specified location
   */
  public void jumpTo(int pos) {
	  currentPos = pos;
  }
//------------------------------------------------------------------------//
// Comparable getNextItem()                                               //
// Returns the item pointed to by currentPos...increments currentPos when //
// done                                                                   //
//------------------------------------------------------------------------//
  /**
   * @return Returns the next item moving forward on the list.
   */
  public Comparable getNextItem()
  {
	Comparable next;
    if (prevWasUsed == true) {
    	if (currentPos == numItems -1) {
    		currentPos = 0;
    	}
    	else {
    		currentPos = currentPos+1;
    	}
    	next = listItems[currentPos];
    	prevWasUsed = false;
    }
    else {
    	next = listItems[currentPos];
    }
    if (currentPos == numItems -1) {
    	currentPos = 0;
    }
    else {
      currentPos++;
    }
    nextWasUsed = true;
    return next;
  }
//------------------------------------------------------------------------//
//Comparable getPrevItem()                                               //
//Returns the item pointed to by currentPos...decreases currentPos when //
//done                                                                   //
//------------------------------------------------------------------------//
  /**
   * @return Returns the previous item moving backward on the list.
   */
  public Comparable getPrevItem() {
	  if (nextWasUsed == true) {
		  if (currentPos == 0) {
			  currentPos = numItems -1;
		  }
		  else {
			  currentPos = currentPos-1;
		  }
		  nextWasUsed = false;
	  }
	  if (currentPos == 0) {
		  currentPos = numItems -1;
	  }
	  else {
		  currentPos--;
	  }
	  Comparable prev = listItems[currentPos];
	  prevWasUsed = true;
	  return prev;
  }
  /** 
   * @return Returns a list double the length of the original with the same entries transfered over.
   */
  public Comparable[] doubleList() {
	  int currentLength = listItems.length;
	  Comparable newList[] = new Comparable[currentLength*2];
	  for (int i = 0; i < listItems.length; i++) {
		  newList[i] = listItems[i];
	  }
	  return newList;
  }
//------------------------------------------------------------------------//
// void delete(Comparable)                                                //
// Removes the specified item from the list                               //
//------------------------------------------------------------------------//
  /**
   * abstract delete, used to delete an item from the list.
   */
  public abstract void delete(Comparable item);

//------------------------------------------------------------------------//
// void insert(Comparable)                                                //
// Inserts the specified item into the list                               //
//------------------------------------------------------------------------//
  /**
   * abstract insert, used to add an item to the list.
   */
  public abstract void insert(Comparable item);

//------------------------------------------------------------------------//
// isThere(Comparable)                                                    //
// Returns true if the specified item is in the list otherwise returns    //
// false                                                                  //
//------------------------------------------------------------------------//
  /**
   * abstract isThere, used to check if the item is on the list.
   */
  public abstract boolean isThere(Comparable item);
}
