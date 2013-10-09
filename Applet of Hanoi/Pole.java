import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class Pole
{
/**************************************************************
              I N S T A N C E  V A R I A B L E S
***************************************************************/
    public String nom;  // name of the Pole (West, Center, or East)

    public Button butt; // button of this Pole--labelled by nom

    public int index;  // index of the Pole, 0 for West Pole, 1
                       // for Center Pole, 2 for East Pole

    public boolean active, // true if the button of this Pole
                           // has been pressed (blue in color)
                present[]; // present[i] is true iff ring i is
                           // present at this Pole

    public int nRings;     // number of rings at this Pole

/*...................static variables.........................*/

    public static boolean selected[]; // selected[i] is true if ring i
                                      // has been selected by the user

    public static  Pole poleOfRing[]; // poleOfRing[i] gives the Pole at
                                      // which ring i currently resides
                                      // (null if ring i is deleted)

    public static toh apple;       // handle for the Applet that
                                   // will display this Pole

    public static Panel panRings [] ; // the panels for the 12 rings 

    public static Button butRings[];  // 12 buttons for the 12 rings

    private static Label padLeft[],  padRight[]; // paddings for the
                                      // buttons in the 12 ring panels

    private static Color
                          background = new Color(192, 192, 192),
                          lightbackg = new Color(230, 230, 230),
                          darkbackg = new Color(160, 160, 160),
                          green     = new Color(0, 180, 0);
                                      // Color.green is too bright

    static Font   f   = new Font("Courier", Font.PLAIN, 7),
                  ff  = new Font("Dialog", Font.PLAIN, 10),
                  fff = new Font("Monospaced", Font.BOLD, 24);

/**************************************************************
***************************************************************/



public Pole (String nom, int index)
{  this.nom = nom;
   this.index = index;

   butt = makeButton( nom + " Pole", lightbackg, fff );

   present = new boolean[12];
   this.putRingsToPole(0);
}

public static void init( toh applit)
{
   // store away the Applet's handle
     apple = applit;

   /**************************************************************
     Make 12 left/right paddings for rings in padLeft[]/padRight[]
     Make 12 Buttons representing the rings, butRings[]
     Make 12 ring panels, panRings
    **************************************************************/

      String str = "";   // null string
      padLeft = new Label[12];
      padRight = new Label[12];
      butRings = new Button[12];
      panRings = new Panel[12];
      selected= new boolean[12];
      poleOfRing = new Pole [12];


      for (int i = 0; i < 12; i++ )
      {
        padLeft[11-i] = new Label( str, Label.CENTER);
        padLeft[11-i].setFont(f);
        padRight[11-i] = new Label( str, Label.CENTER);
        padRight[11-i].setFont(f);
        str=str+"  ";

        butRings[i] = makeButton ( Integer.toString(i+1), Color.white, ff);
      }

      for (int i = 0; i < 12; i++ )
      {
        panRings[i] = makeRing(i);
        selected[i] = false;
      }

}

public void putRingsToPole(int numRings)
/************************************************************
 Put rings 0 to (numRings-1) on this Pole
************************************************************/
{
  active = false; // Pole cannot be active here--but just in case

   for (int i=0; i<12; i++)
    if (i < numRings)
      {  present[i] = true ;
         poleOfRing[i] = this;
      }
    else present[i] = false ;
   nRings = numRings;
}


private static Button makeButton (String label, Color fcolor, Font font)
/************************************************************
 The parameters are: label for the button, foreground color,
 and font for the label. Add the toh Applet as ActionListener.
************************************************************/
  {
		Button		b = new Button(label);
        b.setForeground(fcolor);
		b.setFont(font);
        b.addActionListener( (ActionListener) apple );
		return b;
  }

private static Panel makeRing( int k ) // k goes  0...11, NOT 1...12
/************************************************************
 Make one ring panel with the ring button placed in center
 of the panel. The label of the ring button is k.
************************************************************/
  {
        Panel p = new Panel();
        p.setLayout( new BorderLayout(0,0));
        p.setBackground(background);

        p.add( butRings[k],  BorderLayout.CENTER);

        if (k!=11)
        { p.add( padLeft[k+1], BorderLayout.WEST);
          p.add( padRight[k+1], BorderLayout.EAST);
        }
        return p;
  }

public void deleteRing( int r )
{
     present[r]=false;
     nRings--;
     selected[r]=false;
     poleOfRing[r]=null;
}

public static void moveRing (int r, Pole fromPole, Pole toPole)
{
     fromPole.present[r]=false;
     toPole.present[r]=true;
     fromPole.nRings--;
     toPole.nRings++;
     poleOfRing[r]=toPole;
}



public static void transferRings ( Pole fromPole, Pole toPole)
{
     for ( int i=0; i<12; i++)
     if ( (fromPole.present[i]) && (selected[i]) )
     { moveRing(i, fromPole, toPole);
       selected[i] = false ;
     }
}

public void activate()
{
   active = true;
   butt.setBackground(Color.blue);
   apple.setActivePole(this);
}

public void deactivate()
{
   active = false;
   butt.setBackground(darkbackg);
   apple.setActivePole(null);
}

public static void toggleSelect( int r ) { selected[r]=!selected[r]; }

}
