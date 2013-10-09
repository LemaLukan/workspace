import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;


public class tohThread extends Thread
{
	static int goThrough = 0;
  Pole pole[];
  toh applit;
  boolean amAlive;

  public tohThread ( toh applit, Pole[] pole )
  {
    this.pole = pole;   this.applit = applit;   amAlive = false; 
  }

  public void run()
  {
    amAlive = true;
    int k = pole[0].nRings + pole[1].nRings + pole[2].nRings;

    genToh( k, 1 );

    applit.finishTOH();
    return;
  }


/*
 ******************************************************************
 File: tohThread.java
 ******************************************************************
 Purpose: The tohThread will gather the current state of the poles,
 and the applit that called it. When the applit's go button is pressed
 genToh will be called and will solve any starting arrangement of rings
 of the tower of hanoi problem. It must be called with the total 
 amount of rings it should solve for and the destination pole. The
 input for the destination pole must be a 0, 1, or 2. 
 ******************************************************************
 Student ID number: 2961247.
 Author: Stephen Thomas Kyte Nusko.
 Date: Febuary 14th 2011.
 Version 1.1
 ******************************************************************
 */
 /**
  * Begins solving the Towers of Hanoi from any possible starting location.
  * Must be called with the total amount of rings and the index number of the final
  * location the program should finish at.
  
  * @param n The total number of rings that are currently on the poles.
  
  * @param target And index of the pole that the method will solve too, must be a 0, 1 or 2.
  
 */
	void genToh ( int n, int target )
	{
		// If N=0 or amAlive is false, then return. (The base cases.) 
		if ((n==0) || (!amAlive)) return;   // base cases
		else
		{
			// Otherwise set m to the pole with the biggest of N rings through the use of indexOfPoleWithNthRing(n) method.
			int m = indexOfPoleWithNthRing(n);
			// Set k to 3 - m - target.
			int k = 3-m-target;
			// If k is equal or between 0 or 2 proceed. Otherwise go to step 10
			if (k <= 2 && k >= 0)
			{
				// Call genToh with n-1 and k as the target.
				genToh(n-1, k);
				// if amAlive is true proceed.
				if (amAlive)
				{
					// Move the current m ring to the current target with the method moveTopRing(m, target).
					moveTopRing(m,target);
					// Display the current arrangement of poles and pause the program for 1 second with the method displayPoles().
					displayPoles();
					// Call genToh with n-1, and target as the target.
					genToh(n-1, target);
				}
			}
			// If at step 4 k was not in the range of 0 to 2 proceed from here.
			else
			{
				// If k was 3 or more call genToh with n-1 and 0 as the target
				if (k >= 3)
				{	
					genToh(n-1, 0);		
				}
				else
				{
					// If k was less then 0 call genToh with n-1 and 2.
					genToh(n-1, 2);
				}
			}

		}
	}





  void displayPoles()
  {
    // Task: Send a message to the toh-applet to display the current 
    // board configuration; and then suspend the execution of (this)
    // tohThread for 1000 milliseconds.

    applit.refresh();
    try { Thread.sleep(1000); } catch (Exception e)  { }

  }



  public void myStop() { amAlive = false; }


  public void moveTopRing (int f, int t)
  {
    // Task: Move the topmost ring of Pole f from Pole f to Pole t,
    // where f and t are pole indexes.
    // Assumptions:  0 <= f <= 2  and  0 <= t <= 2.
    // Assumptions:  When moveTopRing starts to execute, then:
    //               * Pole f has at least 1 ring.
    //               * Pole t either has no rings at all, or its top
    //                 ring is larger than the top ring of Pole f.

  
    Pole fromPole=pole[f];
    Pole toPole=pole[t];
    int k=-1;
    while (Pole.poleOfRing[++k] != fromPole);
    Pole.moveRing(k,fromPole,toPole);
  }





  public int indexOfPoleWithNthRing( int N )
  {
    // Task: Return the index of the Pole on which the largest of
    //       the smallest N rings on board resides.
    // Assumption: the total number of rings on board is atleast N.

    int k=-1, i=0, indx; 

    Pole x;  // Pole having the largest of the smallest N rings

    // find Pole x
    while (i<N)
    { if (Pole.poleOfRing[++k] != null) i++; }
    x = Pole.poleOfRing[k];
    for (indx=0; pole[indx] != x; indx++) ;
    return indx;
  }


}
