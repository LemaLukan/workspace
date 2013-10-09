import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

/* This program solves the generalized Towers of Hanoi (toh) problem
   according to the requirements of TME 1 B (Comp 272).

   The representation of the towers (poles) is not as transparent as
   it should be and it is to be simplified--as time allows. At present,
   the configuration of the rings on the screen is recorded by two
   arrays:

   integer array ringAt[3][12], whose i,k entry records the serial
   number of the ring at screen position i,k (ring serial numbers
   range from 0 through 11, -1 stands for an empty ring position),

   boolean array marked[3][12], whose i,k entry is true, iff there
   is a ring in position i,k of the screen, and it is displayed in
   black, that is, the ring is marked as "selected".

   The main job of updating the display as the rings move is done by
   method refresh(), that checks the difference between the ring
   configuration reached at the present state of the computations and
   the ring configuration currently showing on the screen, and makes
   the necessary updates on the screen and in ringAt[][] and
   marked[][].

   Records of the current off-screen configuration (the one that has
   been reached by the computations but perhaps not yet shown on the
   screen) are kept by three Pole objects and the Pole class itself
   through its static variables. Each Pole object has

   * a boolean array present[12], whose i'th entry is true iff ring i is
     present at the pole, and the Pole class itself has the static

   * boolean array selected[12], whose i'th entry is true iff the user
     has selected ring i by clicking the Button of ring i, and

   * Pole array poleOfRing[12], whose i'th entry points to the Pole
     on which ring i currently resides. A null entry indicates that
     ring i is not on any of the Poles.

 (Too complex? Yes. I have not had time to clean up the program's
  logic and data structures to make them more educationally palatable.) 
*/



public class toh extends Applet implements ActionListener
{
/**************************************************************
              I N S T A N C E  V A R I A B L E S
***************************************************************/
    private static String FILLER =
    "                                                 ";

    private String

        Command []=   {"Delete","Reset","Stop","Go"}, 
        nameOfPole []=   {"West","Center","East"}; 

    private Font    f = new Font("Courier", Font.PLAIN, 7),
                  fff = new Font("Monospaced", Font.BOLD, 24);

    private Pole activePole;     // null if no pole is active

    private Pole pole[];         // West, Center, and East poles


    private Panel   panNorth,    // holds the 3 Pole buttons on top
                    panSouth,    // holds the 4 command buttons at bottom
                    panPole[],   // a panel for each pole[i]
                    panWCE[][];  // 12 panels in each panPole[i] (GridLayout)

    private int ringAt[][];      // serial numbers of rings currently shown
                                 // on screen in panWCE[][]. Ring-numbers go
                                 // from 0 to 11; -1 if panWCE[i][k] is empty
    private boolean marked[][];  // true iff ring at panWCE[i][k] is selected                      

    private tohThread tt=null;   // thread that gives the recursive solution

    private Button  commandButt[]; // the 4 command buttons at the bottom

    private int     appletWidth=630, appletHeight=380;

    private Color   background = new Color(192, 192, 192),
                    lightbackg = new Color(230, 230, 230),
                    darkbackg  = new Color(160, 160, 160),
                    green      = new Color(0, 180, 0);
/**************************************************************/
/**************************************************************/

  public void init()
  { int i, k;

    Pole.init(this); // initialize the Pole class: create panels
                     // representing the 12 rings of a tallest tower 
                     // as coded in the sample screen of TME 1 B

    setLayout( new BorderLayout(0,35) );
    setBackground ( background );

/**************************************************************
  Initialize panPole[3], and panWCE[3][12] and add them to the
  applet; and initialize ringAt[][] and marked[][] on the way. 
***************************************************************/
    pole     = new Pole[3];
    panPole  = new Panel[3];
    panWCE   = new Panel[3][12];
    ringAt   = new int[3][12];
    marked   = new boolean[3][12];
    
    for (i=0; i<3; i++)
    { pole[i] = new  Pole (nameOfPole[i], i);
      panPole[i] = new Panel();
      panPole[i].setLayout( new GridLayout (12, 1) );
      panPole[i].setBackground(background);
      for (k = 0; k < 12; k++ )
      {
       panWCE[i][k]=new Panel();
       panWCE[i][k].setLayout( new BorderLayout(0,0));
       panWCE[i][k].setFont(f);
       panWCE[i][k].setBackground(background);
       panWCE[i][k].add(new Label(FILLER, Label.CENTER),BorderLayout.CENTER);
       panPole[i].add( panWCE[i][k] );
       ringAt[i][k] = -1;
       marked[i][k] = false;
      }

      panPole[i].setBackground(background);
      add( panPole[i], nameOfPole[i] );
    }

   /**************************************************************
     Implement the North panel, panNorth, with the Pole buttons.
     The button of Pole i is referenced by pole[i].butt
    **************************************************************/

     panNorth = new Panel();
     panNorth.setLayout( new GridLayout (1, 3) );

     for (i = 0; i < 3; i++ )  panNorth.add ( pole[i].butt );

     add( panNorth, BorderLayout.NORTH );
     
   /**************************************************************
     Implement the South panel, panSouth, with 4 command buttons
    **************************************************************/

     panSouth = new Panel();
     panSouth.setLayout( new GridLayout (1, 4) );

     commandButt = new Button[ 4 ] ;

      for (i = 0; i < 4 ; i++ )
      {
         commandButt[ i ] = makeButton( Command[ i ], lightbackg, fff );
         panSouth.add( commandButt[ i ] );
      }
     add( panSouth, BorderLayout.SOUTH );
    handleReset();  // pressing the Reset button has the same effect
  }
/***************************************************************/
  public void start()
  {
      setSize(appletWidth,appletHeight);
      validate();
  }

/***************************************************************/

  public void finishTOH()
  // Chores to do when the tohThread finished executing
  {
    commandButt[3].setBackground(darkbackg);
    tt=null;
  }

/***************************************************************/

  public void setActivePole(Pole p) { activePole = p ; } 

/***************************************************************/
  
  private Button makeButton (String label, Color fcolor, Font font)
  {
		Button		b = new Button(label);
        b.setForeground(fcolor);
		b.setFont(font);
        b.addActionListener(this);
		return b;
  }

/***************************************************************/

  void handleDelete()
  { 
   if (tt == null)  
   { 
       if (activePole != null)
       { for (int i=0; i<12; i++)
            if ( (Pole.selected[i]) && (Pole.poleOfRing[i]==activePole) )
               activePole.deleteRing(i);

         activePole.deactivate();
       }
       reDraw();
   }
  }

  void handleReset()
  {  
   if (tt == null)
   {    
    int i;
    for ( i = 0; i<3; i++ )  pole[i].deactivate();
    for ( i = 0; i<Command.length; i++ )
         commandButt[i].setBackground(darkbackg);
    
    for ( i = 0; i<12; i++)
    {  Pole.selected[i]=false;    // user has not selected any of the rings
       Pole.poleOfRing[i] = null; // none of the 12 rings has a Pole yet
    }

    pole[0].putRingsToPole(0);
    pole[1].putRingsToPole(12);   // all 12 rings go to the Center Pole
    pole[2].putRingsToPole(0);
    reDraw();
   }
  }

/***************************************************************/

  void handleStop()
     {  if ( tt != null )
        {
          tt.myStop();
          commandButt[2].setBackground(Color.blue);
          commandButt[3].setBackground(darkbackg);
        }
        return;
     }

/***************************************************************/

 void handleGo()
     {  if (( tt == null ) && ( activePole == null) )
        {
          commandButt[3].setBackground(Color.blue);
          commandButt[2].setBackground(darkbackg);
          tt = new tohThread( this, pole );
          tt.setPriority(2);
          tt.start();
          reDraw();
          return;
        }
     }

/***************************************************************/

  void handlePoleButton(int i)  // value of i is 0, or 1, or 2
  {
   if (tt == null)
   {    
    if (activePole!=null)
      {
          Pole.transferRings( activePole, pole[i] );
          activePole.deactivate();
      }
     else pole[i].activate();
     reDraw();
   }
  }

/***************************************************************/

  void handleRingButton(Button source)
  {
   if (tt == null)
   {    
    for (int i=0; i<12; i++)
      if ((source == Pole.butRings[i]) && (Pole.poleOfRing[i].active))
         Pole.toggleSelect(i);
     
    reDraw();
   }
  }

/***************************************************************/

 public  void actionPerformed ( ActionEvent e )
 {
   Button source =(Button) e.getSource() ;

   for (int i=0; i<3; i++)   // a pole buttons has been pressed ?
   if (source == pole[i].butt) {  handlePoleButton(i); return ; }        

   if       ( source == commandButt[0] ) {  handleDelete(); return ; }
   else if  ( source == commandButt[1] ) {  handleReset() ; return ; }
   else if  ( source == commandButt[2] ) {  handleStop()  ; return ; }
   else if  ( source == commandButt[3] ) {  handleGo ()   ; return ; }
   // the remaining possibilty is that a ring button has been pressed
   handleRingButton(source); 
 }

/***************************************************************/
  public void eraseWCE( int i, int k)
  { //  blank out position i, k of panWCE[][] 
    panWCE[i][k].removeAll();
    panWCE[i][k].add(new Label(FILLER, Label.CENTER),BorderLayout.CENTER);
    panWCE[i][k].validate();
  }
/***************************************************************/

  public void refresh()
  // Adjust the rings on the poles to show their current position.
  // Record the new status of the rings in ringAt[][] and marked[][]
  { int i, k;

    for (i=0; i<3; i++)
    {
      int missing = 12 - pole[i].nRings;

      for ( k = 0; k < missing; k++ )
      if (ringAt[i][k] >= 0) // ring position that should be empty on the
                             // next screen but currently holding a ring 
      {
        ringAt[i][k] = -1;
        marked[i][k] = false;
        eraseWCE(i,k);
      }

      //  This segment checks the position of each ring, k, on a pole
      //  (the position in which the ring should be on the next screen)
      //  and puts ring k in that position--unless it is there already

      for ( k = 0; k < 12; k++ )
      if ( pole[i].present[k] )  
      { 
        if ( ( ringAt[i][missing] != k ) ||
             ( marked[i][missing] != Pole.selected[k] ) )
        {    // then put the correct ring in this position of the screen
          if (Pole.selected[k]) Pole.butRings[k].setBackground(Color.black);
              else
          Pole.butRings[k].setBackground( ((k % 2) == 0) ?  Color.red : green );
          if (tt == null) eraseWCE(i,missing); // if solution not in progress
          panWCE[i][missing].removeAll();
          panWCE[i][missing].add( Pole.panRings[k], BorderLayout.CENTER );
          ringAt[i][missing] = k;
          marked[i][missing] = Pole.selected[k];
          panWCE[i][missing].validate();
        }        // if ( ( ringAt...
          ++missing;                // increment row counter
      }          // if ( ( pole...
    }            // for ( i=0 ...
  }              // method refresh


  public void reDraw()
  {
    
    refresh();
    validate();
    repaint();
  }

}                // toh class
