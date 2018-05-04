package user_interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.UIManager;
/**
 * The UI setup class is designed to provide a unified look and feel for 
 * all UI's that utilize it's functionality
 * @author Denny Fleagle
 *
 */
public abstract class UI_setup
{
	private int frameWidth;		//The width of the frame being built
	private int frameHeight;	//The height of the frame being built
	private int screenWidth;	//The user's screen width
	private int screenHeight;	//The user's screen height
   
	/**
	 * Constructor to setup the global user interface default values
	 */
	public UI_setup()
	{
		windowSize();
		popupDefaults();

	}

	/**
	 * Establish the window size of the users computer
	 */
	private void windowSize()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.frameWidth = (int) (screenSize.getWidth()*.83);
		this.frameHeight = (int) (screenSize.getHeight()*.8);
		this.screenWidth = (int) (screenSize.getWidth());
		this.screenHeight = (int) (screenSize.getHeight());
		
	}

	/**
	 * Setup global values for the UIManager 
	 */
	private void popupDefaults()
	{
		UIManager.put("Button.font", new Font("Arial", Font.BOLD, (int)(frameHeight * .03)));
		UIManager.put("TextField.font", new Font("Courier", Font.BOLD, (int)(frameHeight * .03)));
		Font oldLabelFont = UIManager.getFont("Label.font");
	    UIManager.put("Label.font", oldLabelFont.deriveFont(Font.PLAIN, (int)(frameHeight * .02)));
	}
	
	/**
	 * get the width of the user's screen 
	 * @return width
	 */
	public int getScreenWidth()
	{
		return screenWidth;
	}
	
	/**
	 * get the height of the user's screen
	 * @return height
	 */
	public int getScreenHeight()
	{
		return screenHeight;
	}
	
	/**
	 * get the width of the user's screen 
	 * @return width
	 */
	public int getFrameWidth()
	{
		return frameWidth;
	}
	
	/**
	 * get the height of the user's screen
	 * @return height
	 */
	public int getFrameHeight()
	{
		return frameHeight;
	}
		
}
