package database;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import javax.imageio.ImageIO;
/**
 * Inserts values into database tables
 * @author Adam Standke
 *
 */
public class InsertValues 
{
	private Connection conn;
	private static final String DB_LOCATION = "jdbc:mysql://db.cs.ship.edu:3306/csc371-10";
    private static final String LOGIN_NAME = "csc371-10";
    private static final String PASSWORD = "Password10";
    
    /**
     * Runner for inserting values
     * @param args
     */
	public static void main(String[] args)
    {
		new CreateTables();
    }
	
	
	/**
	 * Class constructor
	 */
	public InsertValues() 
	{
		activateJDBC();
		insertAbility();
		insertLocation();
		insertModerator();
		insertManager();
		insertPlayer();
		insertContainer();
		insertArmor();
		insertWeapon(); 
		insertCreature(); 
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Ability table
	 */
	private void insertAbility()
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into ABILITY values(?,?,?,?,?,?,?)");
			stmt.setInt(1, 100);
			stmt.setTime(2, null); 
			stmt.setTime(3, null);
			stmt.setTime(4, Time.valueOf("15:00:00"));
			stmt.setInt(5, 1);
			stmt.setString(6, "Orcs");
			stmt.setString(7, "Invisibility");
			stmt.executeUpdate(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Location table
	 */
	private void insertLocation()
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into LOCATION values(?,?,?,?,?)");
			stmt.setInt(1, 87);
			stmt.setInt(2, 24); 
			stmt.setDouble(3, 300.00);
			stmt.setString(4, "Dungeon Holding Cell");
			stmt.setString(5, "Head north from Current Position");
			stmt.executeUpdate(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Moderator table
	 */
	private void insertModerator()
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into MODERATOR values(?,?)");
			stmt.setString(1, "kingman249");
			stmt.setString(2, "donkey@gmail.com"); 
			stmt.executeUpdate(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Manager table
	 */
	private void insertManager()
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into MANAGER values(?,?,?)");
			stmt.setString(1, "bossman676");
			stmt.setString(2, "kingman249");
			stmt.setString(3, "idiot@gmail.com");
			stmt.executeUpdate(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Player table
	 */
	private void insertPlayer()
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into PLAYER values(?,?,?,?,?)");
			stmt.setString(1, "DaveBong22");
			stmt.setString(2, "kingman249");
			stmt.setString(3, "bossman676");
			stmt.setString(4, "ILoveCheating");
			stmt.setString(5, "badbreath@gmail.com");
			stmt.executeUpdate(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Container table
	 */
	private void insertContainer()
	{
			
		try {
			Object p = ImageIO.read(new File("Images/solution_db.png"));
			PreparedStatement stmt = conn.prepareStatement("insert into CONTAINER values(?,?,?,?,?)");
			stmt.setInt(1, 40);
			stmt.setDouble(2, 76.00); 
			stmt.setDouble(3, 50.00);
			stmt.setBlob(4, Blob.class.cast(p));
			stmt.setClob(5, (Clob)new Object());
			stmt.executeUpdate(); 
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Armor table
	 */
	private void insertArmor()
	{
		try {
			Object p = ImageIO.read(new File("Images/solution_db.png"));
			PreparedStatement stmt = conn.prepareStatement("insert into ARMOR values(?,?,?,?,?,?)");
			stmt.setInt(1, 40);
			stmt.setInt(2, 76); 
			stmt.setInt(3, 50);
			stmt.setString(4, "Dungeoun Cell");
			stmt.setBlob(5, Blob.class.cast(p));
			stmt.setClob(6, (Clob)new Object());
			stmt.executeUpdate(); 
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Weapon table
	 */
	private void insertWeapon()
	{
		try {
			Object p = ImageIO.read(new File("Images/solution_db.png"));
			PreparedStatement stmt = conn.prepareStatement("insert into WEAPON values(?,?,?,?,?,?)");
			stmt.setInt(1, 40);
			stmt.setInt(2, 76); 
			stmt.setInt(3, 50);
			stmt.setString(4, "Dungeoun Cell");
			stmt.setBlob(5, Blob.class.cast(p));
			stmt.setClob(6, (Clob)new Object());
			stmt.executeUpdate(); 
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
	}
	/**
	 * method that inserts values in Creature table
	 */
	private void insertCreature()
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("insert into CREATURE values(?,?,?,?,?,?,?,?)");
			stmt.setInt(1, 78);
			stmt.setInt(2, 87); 
			stmt.setString(3, "Dungeon Orc Keeper 1");
			stmt.setInt(4, 250);
			stmt.setInt(5, 260);
			stmt.setInt(6, 400);
			stmt.setInt(7, 0);
			stmt.setInt(8, 2);
			stmt.executeUpdate(); 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * method that registers driver with DBMS system
	 */
	private boolean activateJDBC()
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Connection suceessful...");
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        connectionDB();

        return true;
    }
 
	/**
	 * method that establishes connection with DBMS
	 */
	private boolean connectionDB()
	{
		try 
		{
			conn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}

		return true; 
	}
	
}
