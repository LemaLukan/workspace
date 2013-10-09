package databaseData;

import java.io.Serializable;
//------------------------------------------------------------------------//
// class SortedList                                                       //
// Implements a unsorted list                                             //
//------------------------------------------------------------------------//
/**
 * Implements an unsorted list 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class UnsortedList extends List implements Serializable
{
	int TotalPictures;
//------------------------------------------------------------------------//
// SortedList()                                                           //
// Defalut Constructor. Calls parent class                                //
//------------------------------------------------------------------------//
	/**
	 * default constructor. Calls parent class.
	 */
  public UnsortedList()
  {
    super();
    TotalPictures = 0;
  }

//------------------------------------------------------------------------//
//  public SortedList(int)                                                //
//  Constructor. Calls corresponding constructor in parent class          //
//------------------------------------------------------------------------//
  /**
   * Constructor, calls the corresponding constructor in the parent class.
   */
  public UnsortedList(int maxItems)
  {
    super(maxItems);
    TotalPictures = 0;
  }

//------------------------------------------------------------------------//
// boolean isThere(Comparable)                                            //
// Returns true if the specified item is in the list...otherwise false    //
//------------------------------------------------------------------------//
  /**
   * @param item The item to be looked for.
   * @return Returns true if the item is found, false otherwise.
   */
  public boolean isThere(Comparable item)
  {
    int first = 0;
    boolean found = false;
    while (first < numItems && !found)
    {
    	if (item.compareTo(listItems[first]) == 0) {
    		found = true;
    	}
      else {
    	  first++;
      }
    }
    return found;
  }

//------------------------------------------------------------------------//
// void delete(Comparable)                                                //
// Removes the specified item from the list                               //
//------------------------------------------------------------------------//
  /**
   * @param item The item to be deleted.
   * Deletes the item from the list.
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
   * @param item The item to be added.
   * Adds the item to the list, if the list is full, it will double the size.
   */
  public void insert(Comparable item)
  {
    if (!isFull())
    {
      listItems[numItems] = item;
      numItems++;
    }
  }
  /**
   * @return Returns a unique ID number one more then the last time it was called.
   */
  public int pictureID() {
	  TotalPictures++;
	  return TotalPictures;
  }
}
