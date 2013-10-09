package operations;

import databaseData.PictureNode;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
// Images() creates a panel containing one image and the current computer's screen size that 
// is inputed when the constructor is called.
/**
 * Images extends a JPanel, but with a picture displayed in the center of the current screen. 
 * @author Stephen Nusko
 * @version Beta 1.0 2011
 */
public class Images extends JPanel {  

Image img;					// The image
Dimension currentScreen;	// the screen size of the computer
PictureNode picture;		// Picture node stores the file path and description of the picture
double width;
double height;
double screenWidth;
int ScaleTwoCounter;
double ScaleOneNormal;
double ScaleTwoNormal;
int ScaleOneCounter;
MediaTracker mTracker;
	/**
	 * Default constructor.
	 */
	public Images() {
		super();
		currentScreen = null;
		picture = null;
		img = null;
	}
	/**
	 * Constructor, Calls super class and adds picture and screenSize.
	 * @param input Used to pass the PictureNode to be displayed.
	 * @param screenSize Used to pass the current computer screen size.
	 */
	public Images(PictureNode input, Dimension screenSize) {
		super();
		currentScreen = screenSize;
		picture = input;
		img = getToolkit().getImage(picture.getPictureURL());
		calcScale();
	}
	/**
	 * Sets the PictureNode to be displayed to the new input.
	 * @param input The PictureNode the panel should be updated to display.
	 */
	public void setPictureNode(PictureNode input) {
		picture = input;
		img = getToolkit().getImage(picture.getPictureURL());
		calcScale();
		repaint();
	}
	/**
	 * Used to calculate the required size of the image scaling needed.
	 */
    public void calcScale() {
    	mTracker = new MediaTracker(this);
		mTracker.addImage(img,1);
		try {
			mTracker.waitForID(1);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = img.getWidth(null);
		height = img.getHeight(null);
    	ScaleTwoCounter = 0;
		for (int size = 0; size <= width; size = size + 600) {
			ScaleTwoCounter++;
		}
		ScaleOneNormal = 4.75;
		for (int widthSize  = 100; widthSize < width; widthSize = widthSize + 100) {
			ScaleOneNormal = ScaleOneNormal - 0.125;
		}
		ScaleOneCounter = 0;
		for (int size = 0; size <= height; size = size + 600) {
			ScaleOneCounter++;
		}
		ScaleTwoNormal = 4.454;
		for (int heightSize  = 0; heightSize < height; heightSize = heightSize + 50) {
			if (heightSize < 2000) {
				ScaleTwoNormal = (ScaleTwoNormal - .13636);
				
			}
			else {
				ScaleTwoNormal = (ScaleTwoNormal - .083);	
			}
		}
    }
    /**
     * Used to scale the picture to the required size, and paint it in the middle of the screen.
     */
	public void paintComponent(Graphics g) {  
		super.paintComponent(g);
		Graphics2D ourGraphics = (Graphics2D) g;
		if (width > height) {
			ourGraphics.drawImage(img, AffineTransform.getScaleInstance((currentScreen.getWidth()/width)/2.5,(currentScreen.getWidth()/width)/2.5), null);
		}
		else {
			ourGraphics.drawImage(img, AffineTransform.getScaleInstance((currentScreen.getWidth()/width)/4.5,(currentScreen.getWidth()/width)/4.5), null);
		}
	}  
}