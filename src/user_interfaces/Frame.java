package user_interfaces;

import javax.swing.JFrame;

/**
 * All frame classes should implement this interface for consistency 
 * standardizes the way displays are constructed
 * @author Denny Fleagle
 *
 */
public interface Frame
{
	/**
	 * all frames should set their desired size...
	 * 
	 * use getframeWidth() and getframeHeight() 
	 * which should be inherited from UI_setup abstract class
	 */
	public void setFrameSize();
	
	/**
	 * Classes must initiate the construction of a display
	 */
	public void buildFrame();
	
	/**
	 * allows us to get the jpanel within the panel object
	 * @return jpanel the actual panel to be added to the window
	 */
	public JFrame getFrame();
}
