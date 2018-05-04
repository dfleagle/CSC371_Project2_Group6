package user_interfaces;

public class Security extends UI_setup
{
	private boolean userLoggedIn;
	
	public Security()
	{
		userLoggedIn = false;
	}
	
	public void setUserLoggedIn(boolean tof)
	{
		userLoggedIn = tof;
	}
	
	public boolean isUserLoggedIn()
	{
		return userLoggedIn;
	}
}
