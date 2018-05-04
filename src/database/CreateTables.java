package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Create the tables we need in the database
 * @author Denny Fleagle
 *
 */
public class CreateTables
{
	private Connection conn;
	
    /**
     * runner for creating tables
     * @param args
     */
    public static void main(String[] args)
    {
    	new CreateTables();
    }
    
	/**
	 * Class constructor
	 */
	public CreateTables()
	{
		//activate jdbc 
		activateJDBC();
		
		//create all the tables one by one...
		createAbility();
		createLocation();
		createModerator();
		createModPermissions();
		createManager();
		createManPermissions();
		createPlayer();
		createGameCharacter();
		createContainer();
		createGenericItem();
		createArmor();
		createWeapon();
		createCreature();
		createLikedCreature();
		createHatedCreature();
		createCreatureAbilities();
		createItemLocation();
		createLikedPlayer();
		createHatedPlayer();
		
		try
		{
			conn.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create the ability table
	 */
	private void createAbility()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS ABILITY "
    			+" ( AID 			INT(4) 		NOT NULL, "
    			+ "Repetition_time	TIME, "
    			+ "Repetition_Rate	TIME, "
    			+ "Duration			TIME, "
    			+ "Amount			INT(2)		NOT NULL, "
    			+ "Targets 			VARCHAR(20)	NOT NULL, "
    			+ "Type				VARCHAR(20)	NOT NULL, "
    			+ "CONSTRAINT chk_amount CHECK (Amount >= 0))";
 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Ability Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * create the location table
	 */
	private void createLocation()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS LOCATION "
    			+" ( L_ID		INT(4)			NOT NULL, "
    			+ "Exit_ID		INT(4)			NOT NULL, "
    			+ "Size			DECIMAL(10,2)	NOT NULL, "
    			+ "Type 		VARCHAR(50)		NOT NULL, "
    			+ "Direction	VARCHAR(50)		NOT NULL, "
    			+ "PRIMARY KEY (L_ID))";
 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Location Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}		
	}

	/**
	 * create the moderator table
	 */
	private void createModerator()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS MODERATOR "
    			+" (Mod_login 		CHAR(10)		NOT NULL, "
    			+ "email			VARCHAR(30)		NOT NULL, "
    			+ "PRIMARY KEY (Mod_login))";
 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Moderator Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create mod permissions table
	 */
	private void createModPermissions()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS MOD_PERMISSIONS ("
				+ "Mod_login 		CHAR(10)		NOT NULL, "
    			+ "Mod_Permission	VARCHAR(50)		NOT NULL, "
    			+ "PRIMARY KEY (Mod_login, Mod_Permission)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Mod_Permissions Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create manager table
	 */
	private void createManager()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS MANAGER ("
				+ "Man_login 		CHAR(10)		NOT NULL, "
    			+ "Mod_login		CHAR(10)		NOT NULL, "
    			+ "email			VARCHAR(30)		NOT NULL, "
    			+ "PRIMARY KEY (Man_login) "
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Manager Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * create manPermission table
	 */
	private void createManPermissions()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS MANAGER_PERMISSIONS ("
				+ "Man_login 		CHAR(10)		NOT NULL, "
    			+ "Man_Permissions	VARCHAR(50)		NOT NULL, "
    			+ "PRIMARY KEY (Man_login, Man_Permissions), "
    			+ "FOREIGN KEY (Man_login) REFERENCES MANAGER (Man_login)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Man_Permissions Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create the player table
	 */
	private void createPlayer()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS PLAYER ("
				+ "login 			CHAR(10)		NOT NULL, "
				+ "Mod_login		CHAR(10)		NOT NULL, "
				+ "Man_login 		CHAR(10)		NOT NULL, "
				+ "Password 		VARCHAR(15)		NOT NULL, "
				+ "email 			VARCHAR(30)		NOT NULL, "
				+ "PRIMARY KEY (login), "
				+ "FOREIGN KEY (Mod_login) REFERENCES MODERATOR (Mod_login), "
				+ "FOREIGN KEY (Man_login) REFERENCES MANAGER (Man_login)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Player Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create game character table
	 */
	private void createGameCharacter()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS GAME_CHARACTER ("
				+ "Cname 			CHAR(20)		NOT NULL, "
				+ "login 			CHAR(10)		NOT NULL, "
				+ "Mod_login		CHAR(10)		NOT NULL, "
				+ "L_ID 			INT(4)			NOT NULL, "
				+ "Man_login		CHAR(10)		NOT NULL, "
				+ "Max_hp			INT(3)			NOT NULL, "
				+ "Current_hp		INT(3)			NOT NULL, "
				+ "Strength			INT(3)			NOT NULL, "
				+ "Stamina			INT(3)			NOT NULL, "
				+ "PRIMARY KEY (Cname), "
				+ "FOREIGN KEY (login) REFERENCES PLAYER (login), "
				+ "FOREIGN KEY (Mod_login)	REFERENCES MODERATOR (L_ID), "
				+ "FOREIGN KEY (L_ID) REFERENCES LOCATION (L_ID), "
				+ "FOREIGN KEY (Man_login) REFERENCES MANAGER (Man_login), "
				+ "CONSTRAINT chkMaxhp CHECK (Max_hp = 100), "
				+ "CONSTRAINT chkCurhp CHECK (Current_hp >= 0 AND Current_hp <= 100), "
				+ "CONSTRAINT chkStrength CHECK (Strength >= 0), "
				+ "CONSTRAINT chkStamina CHECK (Stamina >= 0)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Game Character Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create container table
	 */
	private void createContainer()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS CONTAINER ("
				+ "CID				INT(4)			NOT NULL, "
				+ "volume_limit		DECIMAL(4,2)	NOT NULL, "
				+ "weight_limit		DECIMAL(4,2)	NOT NULL, "
				+ "image 			BLOB			NOT NULL, "
				+ "description		TEXT			NOT NULL, "
    			+ "PRIMARY KEY (CID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Container Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create generic item table
	 */
	private void createGenericItem()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS GENERIC_ITEM ("
				+ "GID				INT(4)				NOT NULL, "
				+ "CID				INT(4), "
				+ "Cname 			CHAR(20), "
				+ "volume			DECIMAL(4,2)			NOT NULL, "
				+ "weight 			DECIMAL(4,2)			NOT NULL, "
				+ "image			BLOB					NOT NULL, "
				+ "description 		TEXT					NOT NULL, "
				+ "left_hand		BOOLEAN, "
				+ "belt				BOOLEAN, "
				+ "right_hand 		BOOLEAN, "
				+ "backpack			BOOLEAN, "
				+ "PRIMARY KEY (GID), "
				+ "FOREIGN KEY (CID) REFERENCES CONTAINER (CID), "
				+ "FOREIGN KEY (Cname) REFERENCES GAME_CHARACTER (Cname), "
				+ "CONSTRAINT chkVolume CHECK (volume > 0), "
				+ "CONSTRAINT chkWeight CHECK (weight > 0)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Generic Item Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create armor table
	 */
	private void createArmor()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS ARMOR ("
				+ "ArID					INT(4)			NOT NULL, "
				+ "GID					INT(4)			NOT NULL, "
				+ "Protection_Amount	INT(3)			NOT NULL, "
				+ "Armor_Location 		VARCHAR(50)		NOT NULL, "
				+ "Image				BLOB 			NOT NULL, "
				+ "Description 			TEXT			NOT NULL, "
    			+ "PRIMARY KEY (ArID), "
    			+ "FOREIGN KEY (GID) REFERENCES GENERIC_ITEM (GID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Armor Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create weapon table
	 */
	private void createWeapon()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS WEAPON ("
				+ "WID 					INT(4)			NOT NULL, "
				+ "GID 					INT(4)			NOT NULL, "
				+ "AID					INT(4)			NOT NULL, "
				+ "Weapon_Location 		VARCHAR(5)		NOT NULL, "
				+ "image				BLOB			NOT NULL, "
				+ "description 			TEXT			NOT NULL, "
				+ "PRIMARY KEY (WID), "
				+ "FOREIGN KEY (GID) REFERENCES GENERIC_ITEM (GID), "
				+ "FOREIGN KEY (AID) REFERENCES ABILITY (AID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Weapon Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create creature table
	 */
	private void createCreature()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS CREATURE ("
				+ "CR_ID 			INT(4)			NOT NULL, "
				+ "L_ID 			INT(4)			NOT NULL, "
				+ "Creature_name 	VARCHAR(50)		NOT NULL, "
				+ "Max_hp			INT(3)			NOT NULL, "
				+ "Current_hp		INT(3)			NOT NULL, "
				+ "Strength			INT(3)			NOT NULL, "
				+ "Protection 		INT(3)			NOT NULL, "
				+ "Stamina			INT(3)			NOT NULL, "
				+ "PRIMARY KEY (CR_ID), "
				+ "FOREIGN KEY (L_ID) REFERENCES LOCATION (L_ID), "
				+ "CONSTRAINT chkStrength CHECK (Strength >= 0), "
				+ "CONSTRAINT chkProtection CHECK (Stamina >= 0)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Creature Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create liked creature table
	 */
	private void createLikedCreature()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS LIKE_CREATURE ("
				+ "CR_ID			INT(4)			NOT NULL, "
				+ "liked_CR_ID		INT(4)			NOT NULL, "
				+ "PRIMARY KEY (CR_ID, liked_CR_ID), "
				+ "FOREIGN KEY (CR_ID) REFERENCES CREATURE (CR_ID),"
				+ "FOREIGN KEY (liked_CR_ID) REFERENCES CREATURE (CR_ID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Like Creature Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create hated creature table
	 */
	private void createHatedCreature()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS HATED_CREATURE ("
				+ "CR_ID			INT(4)			NOT NULL, "
				+ "hated_CR_ID		INT(4)			NOT NULL, "
				+ "PRIMARY KEY (CR_ID, hated_CR_ID), "
				+ "FOREIGN KEY (CR_ID) REFERENCES CREATURE (CR_ID),"
				+ "FOREIGN KEY (hated_CR_ID) REFERENCES CREATURE (CR_ID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Hated Creature Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create creature abilities table
	 */
	private void createCreatureAbilities()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS CREATURE_ABILITIES ("
				+ "CR_ID			INT(4)			NOT NULL, "
				+ "AID				INT(4)			NOT NULL, "
				+ "PRIMARY KEY (CR_ID, AID), "
				+ "FOREIGN KEY (CR_ID) REFERENCES CREATURE (CR_ID), "
				+ "FOREIGN KEY (AID) REFERENCES ABILITY (AID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Creature Abilities Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create item location table
	 */
	private void createItemLocation()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS ITEM_LOCATION ("
				+ "L_ID				INT(4)			NOT NULL, "
				+ "GID 				INT(4)			NOT NULL, "
				+ "PRIMARY KEY (L_ID, GID), "
				+ "FOREIGN KEY (L_ID) REFERENCES LOCATION (L_ID), "
				+ "FOREIGN KEY (GID) REFERENCES GENERIC_ITEM (GID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Item Location Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create liked player
	 */
	private void createLikedPlayer()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS LIKED_PLAYER ("
				+ "player_login			CHAR(10)			NOT NULL, "
				+ "CR_ID				INT(4)				NOT NULL, "
				+ "PRIMARY KEY (player_login, CR_ID), "
				+ "FOREIGN KEY (player_login) REFERENCES PLAYER (login), "
				+ "FOREIGN KEY (CR_ID) REFERENCES CREATURE (CR_ID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Liked Player Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * create hated player table
	 */
	private void createHatedPlayer()
	{
		String sqlCreate = "CREATE TABLE IF NOT EXISTS HATED_PLAYER ("
				+ "player_login			CHAR(10)			NOT NULL, "
				+ "CR_ID				INT(4)				NOT NULL, "
				+ "PRIMARY KEY (player_login, CR_ID), "
				+ "FOREIGN KEY (player_login) REFERENCES PLAYER (login), "
				+ "FOREIGN KEY (CR_ID) REFERENCES CREATURE (CR_ID)"
    			+ ")";
				 
		try
		{
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);
			System.out.println("Hated Creature Table Created");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * method used to activate the JDBC drivers and connection with database
	 * @return
	 */
	private boolean activateJDBC()
    {
        Database db = new Database();
        conn = db.getConnection();
        
        if(db.hasConnection())
        {
        	System.out.println("Connection suceessful...");
        }
        else
        {
        	System.out.println("Connection failure...");
        }
           
        return true;
    }
}
