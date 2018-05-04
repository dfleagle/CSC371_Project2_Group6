package runnable_class;

import user_interfaces.StdWindow;

/**
 * Runner class that will initialize the program
 * @author Denny Fleagle
 *
 */
public class Runner
{
	/**
	 * static run method
	 * First we need to create a std window and pass in a panel to 
	 * be displayed 
	 * @param args
	 */
	public static void main(String[] args)
	{
		StdWindow.buildStdWindow();
	}
}
