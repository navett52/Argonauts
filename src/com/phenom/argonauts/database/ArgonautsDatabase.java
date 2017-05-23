package com.phenom.argonauts.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.entity.Player;

import com.phenom.argonauts.Main;
import com.phenom.argonauts.database.Error;
import com.phenom.argonauts.database.Errors;

/**
 * 
 * @author Evan Tellep
 * Made from template code provided by pablo67340
 * https://www.spigotmc.org/threads/how-to-sqlite.56847/
 */
public abstract class ArgonautsDatabase 
{
    Connection connection; //Connection object to use in methods that communicate with the db.

    public abstract Connection getSQLConnection(); //Method charged with creating and establishing a connection with the database.

    public abstract void load(); //Charged with loading the database however the implementation sees fit.
    
    /**
     * Adds a new adventurer into the database.
     * @param UUID : The Unique ID of the player. (Required)
     * @param Name : The Name of the player. (Required)
     * @param Atk : The base attack of the player.
     * @param Def : The base defense of the player.
     * @param MagicAtk : The base magic attack of the player.
     * @param MagicDef : The base magic defense of the player.
     * @param Health : The base health of the Adventurer.
     * @param Mana : The base mana of the Adventurer.
     */
    public void addNewAdventurer(String Name, UUID UUID, double Atk, double Def, double MagicAtk, double MagicDef, double Health, double Mana) 
    {
    	connection = getSQLConnection();
    	PreparedStatement addAdventurer = null;
    	try
    	{
    		addAdventurer = connection.prepareStatement("INSERT INTO tAdventurer (" + 
    				"UUID," +
    				"Name," +
    				"Attack," +
    				"Defense," +
    				"MagicAttack," +
    				"MagicDefense," +
    				"Health," +
    				"Mana)" +
    				"VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
    		addAdventurer.setString(1, UUID.toString());
    		addAdventurer.setString(2, Name);
    		addAdventurer.setDouble(3, Atk);
    		addAdventurer.setDouble(4, Def);
    		addAdventurer.setDouble(5, MagicAtk);
    		addAdventurer.setDouble(6, MagicDef);
    		addAdventurer.setDouble(7, Health);
    		addAdventurer.setDouble(8, Mana);
    		addAdventurer.execute();
    	}
    	catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            try 
            {
                if (addAdventurer != null)
                {
                	addAdventurer.close();
                }
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
    
    /**
     * Checks to see if the player exists in the database.
     * @param playerName : The Name of the player.
     * @param UUID : The Unique ID of the player.
     * @return : True if a record is returned based on the Name and Unique ID of the player; False otherwise.
     */
    public boolean checkForAdventurer(String playerName, UUID UUID)
    {
        connection = getSQLConnection();
        PreparedStatement checkForPlayer = null;
        ResultSet rs = null;
        try
        {
        	checkForPlayer = connection.prepareStatement("SELECT * FROM tAdventurer WHERE Name = ? AND UUID = ?");
        	checkForPlayer.setString(1, playerName);
        	checkForPlayer.setString(2, UUID.toString());
            rs = checkForPlayer.executeQuery();
        } 
        catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            try 
            {
            	if (rs.isBeforeFirst()) 
            	{
            		close(checkForPlayer, rs);
            		return true;
            	}
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
		return false;
    }
    
    /**
     * Checks to see if the player exists in the database.
     * @param playerName : The Name of the player
     * @return : True if a record is returned based on the Name and Unique ID of the player; False otherwise.
     */
    public boolean checkForAdventurer(String playerName)
    {
        connection = getSQLConnection();
        PreparedStatement checkForPlayer = null;
        ResultSet rs = null;
        try
        {
        	checkForPlayer = connection.prepareStatement("SELECT * FROM tAdventurer WHERE Name = ?");
        	checkForPlayer.setString(1, playerName);
            rs = checkForPlayer.executeQuery();
        } 
        catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            try 
            {
            	if (rs.isBeforeFirst()) 
            	{
            		close(checkForPlayer, rs);
            		return true;
            	}
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
		return false;
    }
    
    /**
     * Checks to see if the player exists in the database.
     * @param UUID : The Unique ID of the player
     * @return : True if a record is returned based on the Name and Unique ID of the player; False otherwise.
     */
    public boolean checkForAdventurer(UUID UUID)
    {
        connection = getSQLConnection();
        PreparedStatement checkForPlayer = null;
        ResultSet rs = null;
        try
        {
        	checkForPlayer = connection.prepareStatement("SELECT * FROM tAdventurer WHERE UUID = ?");
        	checkForPlayer.setString(1, UUID.toString());
            rs = checkForPlayer.executeQuery();
        } 
        catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            try 
            {
            	if (rs.isBeforeFirst()) 
            	{
            		close(checkForPlayer, rs);
            		return true;
            	}
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
		return false;
    }
    
    /**
     * A convenience method to close a prepared statement and a result set
     * @param ps : The Prepared Statement to close
     * @param rs : The Result Set to close
     */
    public void close(PreparedStatement ps,ResultSet rs)
    {
    	try 
    	{
    		if (ps != null)
    		{
    			ps.close();            	
    		}
    		if (rs != null)
    		{            	
    			rs.close();
    		}
    	} 
    	catch (SQLException ex) 
    	{
    		Error.close(Main.plugin, ex);
    	}
    }

	//Leaving this here for example purposes for now
    public void setTokens(Player player, Integer tokens, Integer total) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO tTest (player,kills,total) VALUES(?,?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            ps.setString(1, player.getName().toLowerCase());                                             // YOU MUST put these into this line!! And depending on how many
                                                                                                         // colums you put (say you made 5) All 5 need to be in the brackets
                                                                                                         // Seperated with comma's (,) AND there needs to be the same amount of
                                                                                                         // question marks in the VALUES brackets. Right now i only have 3 colums
                                                                                                         // So VALUES (?,?,?) If you had 5 colums VALUES(?,?,?,?,?)
                                                                                                    
            ps.setInt(2, tokens); // This sets the value in the database. The colums go in order. Player is ID 1, kills is ID 2, Total would be 3 and so on. you can use
                                  // setInt, setString and so on. tokens and total are just variables sent in, You can manually send values in as well. p.setInt(2, 10) <-
                                  // This would set the players kills instantly to 10. Sorry about the variable names, It sets their kills to 10 i just have the variable called
                                  // Tokens from another plugin :/
            ps.setInt(3, total);
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            Main.plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                Main.plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
        return;           
    }
}
