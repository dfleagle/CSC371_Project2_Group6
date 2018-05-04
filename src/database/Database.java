package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database class establishes a connection to the database.
 * @author Denny Fleagle
 *
 */
public class Database
{
	private Connection conn = null;
	public static final String DB_LOCATION = "jdbc:mysql://...Link to your database server...";
    public static final String LOGIN_NAME = "login_username";
    public static final String PASSWORD = "login_password";

    /**
     * Class constructor 
     */
	public Database()
	{
		activateJDBC();
	}
	
	/**
	 * activate the JDBC connection to the database. 
	 * Establishes a connection to the desired database
	 * @return true if connected false otherwise
	 */
	private boolean activateJDBC()
	{
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * @return conn The connection to the database
	 */
	public Connection getConnection()
	{
		return conn;
	}
	
	/**
	 * allow us to identify if connection has been made
	 * @return true if connected false otherwise
	 */
	public boolean hasConnection()
	{
		if(conn == null)
		{
			return false;
		}
		
		return true;
	}
}
