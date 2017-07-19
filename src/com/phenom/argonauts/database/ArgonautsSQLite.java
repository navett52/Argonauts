package com.phenom.argonauts.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import com.phenom.argonauts.Main;
import com.phenom.argonauts.database.ArgonautsDatabase;

/**
 * 
 * @author Evan Tellep
 * Made from template code provided by pablo67340
 * https://www.spigotmc.org/threads/how-to-sqlite.56847/
 */
public class ArgonautsSQLite extends ArgonautsDatabase
{
	
    String dbname; //The name of our database
    
    public ArgonautsSQLite(){
        dbname = Main.plugin.getConfig().getString("SQLite.Filename", "ArgonautsCore"); // Set the database name here. SQLite.Filename is the field it checks in the config. ArgonautsCore is the default if no name is found.
    }
    
    /*Create Table Template
      public String SQLiteCreate[TableNameHere]Table = "CREATE TABLE IF NOT EXISTS t[TableName] (" +
    		//Main variables
    		"[Primary Key goes Here] INTEGER PRIMARY KEY AUTOINCREMENT," +
    		"[Any other columns go here] [datatype]([limit]) [NULL|NOT NULL]," +
            //Foreign Keys (If you have any)
            "[Foreign Key Name] [datatype]([limit])" +
            //declaration of keys and constraints
            "FOREIGN KEY ([Foreign Key Name]) REFERENCES t[Foreign Key Table Name]([Foreign Key Name])" +
            ");";
     */
    
    /**
     * tTest
     * SQL String I created to help me make sure my syntax in my other SQL strings were correct
     */
    public String SQLiteCreateTestTable = "CREATE TABLE IF NOT EXISTS tTest (TestID INTEGER PRIMARY KEY AUTOINCREMENT, Test VARCHAR(32) NOT NULL);";
    
    /**
     * tAdventurer
     * SQL Table String
     */
    public String SQLiteCreateAdventurerTable = "CREATE TABLE IF NOT EXISTS tAdventurer (" + // make sure to put your table name in here too.
    		//Main variables
    		"AdventurerID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		"UUID VARCHAR(64) NOT NULL UNIQUE," + //The UUID of the player attached to this Adventurer
            "Name VARCHAR(32) NOT NULL UNIQUE," + //The name of the player attached to this Adventurer
    		"LastStyle VARCHAR(25)," + //The last style played by the adventurer
            //Foreign Keys
            "HomePointID INT UNIQUE," +
            //declaration of keys and constraints
            "FOREIGN KEY (HomePointID) REFERENCES tHomePoint(HomePointID)" +
            ");";
    
    /**
     * tUnlockedRole
     * SQL Table String
     */
    public String SQLiteCreateUnlockedRoleTable = "CREATE TABLE IF NOT EXISTS tUnlockedRole (" +
    		//Main variables
    		"UnlockedRoleID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		"AbilityPoints INT(255)," +
    		"Strength INT(255)," +
    		"Vitality INT(255)," +
    		"Intelligence INT(255)," +
    		"Wisdom INT(255)," +
            //Foreign Keys (If you have any)
            "RoleID INT(255) NOT NULL," +
            "AdventurerID INT(255) NOT NULL," +
            //declaration of keys and constraints
            "FOREIGN KEY (RoleID) REFERENCES tRole(RoleID)," +
            "FOREIGN KEY (AdventurerID) REFERENCES tAdventurer(AdventurerID)" +
            ");";
    
    /**
     * tUnlockedStyle
     * SQL Table String
     */
    public String SQLiteCreateUnlockedStyleTable = "CREATE TABLE IF NOT EXISTS tUnlockedStyle (" +
    		//Main variables
    		"UnlockedStyleID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		//Base Stats
    		"Attack DOUBLE(11) NOT NULL," +
            "Defense DOUBLE(11) NOT NULL," +
            "MagicAttack DOUBLE(11) NOT NULL," +
            "MagicDefense DOUBLE(11) NOT NULL," +
            "Health DOUBLE(11) NOT NULL," +
            "Mana DOUBLE(11) NOT NULL," +
            "Level INT(4) NOT NULL," +
            "Exp INT(50) NOT NULL," +
            //Foreign Keys (If you have any)
            "StyleID INT(255) NOT NULL," +
            "AdventurerID INT(255) NOT NULL," +
            //declaration of keys and constraints
            "FOREIGN KEY (StyleID) REFERENCES tStyle(StyleID)," +
            "FOREIGN KEY (AdventurerID) REFERENCES tAdventurer(AdventurerID)" +
            ");";
    
    /**
     * tHomepoint
     * SQL Table String
     */
    public String SQLiteCreateHomepointTable = "CREATE TABLE IF NOT EXISTS tHomePoint (" +
    		//Main variables
    		"HomePointID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		"xCoord INT(10)," +
    		"yCoord INT(10)," +
    		"zCoord INT(10)" +
    		//Declarations of keys and constraints
            ");";
    
    /**
     * tRole
     * SQL Table String
     */
    public String SQLiteCreateRoleTable = "CREATE TABLE IF NOT EXISTS tRole (" +
    		//Main Variables
    		"RoleID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		"Name VARCHAR(50)," +
    		//Foreign Keys
    		"TraitID INT(255)," +
    		//Declaration of keys and constraints
    		"FOREIGN KEY (TraitID) REFERENCES tTrait(TraitID)" +
            ");";
    
    /**
     * tStyle
     * SQL Table String
     */
    public String SQLiteCreateStyleTable = "CREATE TABLE IF NOT EXISTS tStyle (" +
    		//Main Variables
    		"StyleID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		"Name VARCHAR(50)" +
    		//Foreign Keys
    		//Declaration of keys and constraints
            ");";
    
    /**
     * tRole_Style
     * SQL Table String
     */
    public String SQLiteCreateRole_StyleTable = "CREATE TABLE IF NOT EXISTS tRole_Style (" +
    		//Main Variables
    		"Role_StyleID INTEGER PRIMARY KEY AUTOINCREMENT," +
    		//Foreign Keys
    		"RoleID INT(255)," +
    		"StyleID INT(255)," +
    		//Declaration of keys and constraints
    		"FOREIGN KEY (RoleID) REFERENCES tRole(RoleID)," +
    		"FOREIGN KEY (StyleID) REFERENCES tStyle(StyleID)" +
            ");";

    /**
     * Creates the connection to our database.
     * If the database doesn't exist it creates it for us.
     * Returns a JDBC Connection object
     */
    public Connection getSQLConnection() 
    {
        File dataFolder = new File(Main.plugin.getDataFolder(), dbname+".db");
        if (!dataFolder.exists())
        {
            try 
            {
                dataFolder.createNewFile();
                Main.log.info("Attempted to make 'dataFolder'");
            } 
            catch (IOException e) 
            {
                Main.plugin.getLogger().log(Level.SEVERE, "File write error: "+dbname+".db");
            }
        }
        try 
        {
            if(connection != null && (!connection.isClosed()))
            {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        }
        catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE,"SQLite exception on initialize", ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }
    
    /**
     * Establishes connection and adds all tables if they do not already exist within the database.
     * This method should be called asap within the onEnable method to ensure the database exists and has all appropriate tables.
     */
    public void load() 
    {
        connection = getSQLConnection();
        ArrayList<String> tables = new ArrayList<String>();
        tables.add(SQLiteCreateAdventurerTable);
        tables.add(SQLiteCreateUnlockedRoleTable);
        tables.add(SQLiteCreateUnlockedStyleTable);
        tables.add(SQLiteCreateHomepointTable);
        tables.add(SQLiteCreateRoleTable);
        tables.add(SQLiteCreateStyleTable);
        tables.add(SQLiteCreateRole_StyleTable);
        try 
        {
            Statement s = connection.createStatement();
            for (String tableString : tables) 
            {
            	s.executeUpdate(tableString);
            	s.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally 
        {
            try
            {
                if (connection != null)
                {
                	connection.close();                	
                }
            } 
            catch (SQLException ex) 
            {
                Main.plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
    }
}
