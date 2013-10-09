package databaseData;

import java.io.Serializable;
//------------------------------------------------------------------------//
// class SortedList                                                       //
// Implements a sorted list                                               //
//------------------------------------------------------------------------//
/**
 * SortedList implements a sorted list. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class SortedList extends List implements Serializable
{
	long TotalPictures;
//------------------------------------------------------------------------//
// SortedList()                                                           //
// Defalut Constructor. Calls parent class                                //
//------------------------------------------------------------------------//
	/**
	 * default constructor, calls the parent class.
	 */
  public SortedList()
  {
    super();
    TotalPictures = 0;
  }

//------------------------------------------------------------------------//
//  public SortedList(int)                                                //
//  Constructor. Calls corresponding constructor in parent class          //
//------------------------------------------------------------------------//
  /**
   * constructor. calls the corresponding constructor in the parent class.
   * @param maxItems sets the list to the inputed number.
   */
  public SortedList(int maxItems)
  {
    super(maxItems);
    TotalPictures = 0;
  }

//------------------------------------------------------------------------//
// boolean isThere(Comparable)                                            //
// Returns true if the specified item is in the list...otherwise false    //
//------------------------------------------------------------------------//
  /**
   * @param item The item to compare.
   * @return returns true if the specified item is in the list, otherwise returns false.
   */
  public boolean isThere(Comparable item)
  {
    int first = 0;
    int last = numItems-1;
    int middle;
    boolean found = false;
    while (last >= first && !found)
    {
      middle = (first + last)/2;
      if (item.compareTo(listItems[middle])==0)
        found = true;
      else if (item.compareTo(listItems[middle])<0)
        last = middle -1;
      else
        first = middle + 1;
    }
    return found;
  }
  /**
   * Finds the index of the item.
   * @param item The item to compare.
   * @return Returns an int of the index if found otherwise returns -1;
   */
  public int returnIndex(Comparable item) {
	  int first = 0;
	    int last = numItems-1;
	    int middle = 0;
	    boolean found = false;
	    while (last >= first && !found)
	    {
	      middle = (first + last)/2;
	      if (item.compareTo(listItems[middle])==0)
	        found = true;
	      else if (item.compareTo(listItems[middle])<0)
	        last = middle -1;
	      else
	        first = middle + 1;
	    }
	    if (found == false) {
	    	middle = -1;
	    }
	    return middle;
  }

//------------------------------------------------------------------------//
// void delete(Comparable)                                                //
// Removes the specified item from the list                               //
//------------------------------------------------------------------------//
  /**
   * Deletes the item chosen.
   * @param item The item to delete.
   */
  public void delete(Comparable item)
  {
    int index = 0;
    boolean found = false;
    while (index < numItems && !found)
    {
      if (listItems[index].compareTo(item) == 0)
        found = true;
      else
        index++;
    }
    if (found)
    {
      for (int count = index; count < numItems - 1;count++)
        listItems[count] = listItems[count+1];
      numItems--;
    }
  }

//------------------------------------------------------------------------//
// void insert(Comparable)                                                //
// Inserts the specified item into the list                               //
//------------------------------------------------------------------------//
  /**
   * Adds the item chosen. If the list is full will double the size of the list.
   * @param item The item to be added.
   */
  public void insert(Comparable item)
  {
    if (!isFull())
    {
      int index = numItems - 1;
      while (index >= 0 && (item.compareTo(listItems[index]) < 0))
      {
        listItems[index+1] = listItems[index];
        index--;
      }
      listItems[index+1] = item;
      numItems++;
    }
  }
  /**
   * a unique ID to keep the item separate.
   * @return Returns a long variable one higher then the last time called.
   */
  public long pictureID() {
	  TotalPictures++;
	  return TotalPictures;
  }
}