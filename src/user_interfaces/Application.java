package user_interfaces;

import java.sql.Connection;

import database.Database;

/**
 * Application class acts as the parent for the DB application. 
 * It will hold the connection that should be used globally. Moreover, 
 * it extends the UI_setup so that every class will share the same user
 * experience settings. 
 * @author Denny Fleagle
 *
 */
public abstract class Application extends UI_setup
{
	protected Connection conn;		//global access to database connection 
	
	/**
	 * class constructor
	 */
	public Application()
	{
		//Instantiate a connection to the database 
		Database db = new Database();
		
		//Get the connection to the database
		conn = db.getConnection();
	}

}
