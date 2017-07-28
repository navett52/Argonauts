package com.phenom.argonauts.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.phenom.argonauts.Adventurer;
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
     * Add a new record to tAdventurer
     * @param Name : Name of the Adventurer
     * @param UUID : Unique Identifier of the Adventurer (This doesn't change)
     * @param lastStyleID : The ID of the last style the adventurer played (default - 0)
     * @param homePointID : The ID of the players registered home point (default - 0)
     */
    public void addNewAdventurer(String Name, UUID UUID, String lastStyleName) 
    {
    	connection = getSQLConnection();
    	PreparedStatement addAdventurer = null;
    	ResultSet adventurerID = null;
    	int aid = 0;
    	try
    	{
    		addAdventurer = connection.prepareStatement("INSERT INTO tAdventurer (" + 
    				"UUID," +
    				"Name," +
    				"LastStyle)" +
    				"VALUES (?, ?, ?);");
    		addAdventurer.setString(1, UUID.toString());
    		addAdventurer.setString(2, Name);
    		addAdventurer.setString(3, lastStyleName);
    		addAdventurer.execute();
    	}
    	catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            close(addAdventurer);
        }
    	
    	try {
    		connection = getSQLConnection();
    		addAdventurer = connection.prepareStatement("SELECT AdventurerID FROM tAdventurer WHERE Name = ?");
    		addAdventurer.setString(1, Name);
    		adventurerID = addAdventurer.executeQuery();
    		aid = adventurerID.getInt(1);
    	}
    	catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            close(addAdventurer);
        }
    	
		try {
			connection = getSQLConnection();
			addAdventurer = connection.prepareStatement("INSERT INTO tHomePoint (AdventurerID) VALUES (?)");
			addAdventurer.setInt(1, aid);
			addAdventurer.execute();
		}
		catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            close(addAdventurer);
        }
		
		try {
			connection = getSQLConnection();
			addAdventurer = connection.prepareStatement("INSERT INTO tUnlockedRole (AbilityPoints, Strength, Vitality, Intelligence, Wisdom, RoleID, AdventurerID) VALUES (0, 0, 0, 0, 0, 1, ?)");
			addAdventurer.setInt(1, aid);
			addAdventurer.execute();
		}
		catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            close(addAdventurer);
        }
		
		try {
			connection = getSQLConnection();
			addAdventurer = connection.prepareStatement("INSERT INTO tUnlockedStyle (Attack, Defense, MagicAttack, MagicDefense, Health, Mana, Level, Exp, StyleID, AdventurerID) VALUES (0, 0, 0, 0, 0, 0, 0, 0, 1, ?)");
			addAdventurer.setInt(1, aid);
			addAdventurer.execute();
		}
		catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        finally 
        {
            close(addAdventurer);
        }
    }
    
    public void initAdventurer (Adventurer adventurer) {
    	connection = getSQLConnection();
    	PreparedStatement initAdventurer = null;
    	ResultSet response = null;
    	
    	try {
    		initAdventurer = connection.prepareStatement("SELECT tAdventurer.Name, tHomePoint.xCoord, tHomePoint.yCoord, tHomePoint.zCoord, tRole.Name, tUnlockedRole.AbilityPoints, tUnlockedRole.Strength, tUnlockedRole.Vitality, tUnlockedRole.Intelligence, tUnlockedRole.Wisdom, tStyle.Name, tUnlockedStyle.Attack, tUnlockedStyle.Defense, tUnlockedStyle.MagicAttack, tUnlockedStyle.MagicDefense, tUnlockedStyle.Health, tUnlockedStyle.Mana, tUnlockedStyle.Level, tUnlockedStyle.Exp FROM tAdventurer JOIN tHomePoint ON tHomePoint.AdventurerID = tAdventurer.AdventurerID JOIN tStyle ON tStyle.Name = tAdventurer.LastStyle JOIN tUnlockedStyle ON tUnlockedStyle.StyleID = tStyle.StyleID JOIN tRole_Style ON tRole_Style.StyleID = tStyle.StyleID JOIN tRole ON tRole.RoleID = tRole_Style.RoleID JOIN tUnlockedRole ON tUnlockedRole.RoleID = tRole.RoleID WHERE tAdventurer.Name = ?");
    		initAdventurer.setString(1, adventurer.getPlayer().getName());
    		response = initAdventurer.executeQuery();
    		//Looks like I'll need to modify the tHomePoint Schema to allow for a World to be set
    		adventurer.setHome(new Location(Bukkit.getWorld("StartingIsland"), response.getDouble(2), response.getDouble(3), response.getDouble(4)));
    		//Might need to grab column 5 : Role name if ricky wants me to add a LastRole property to adventurer for now
    		adventurer.setAbilityPoints(response.getInt(6));
    		adventurer.setStr(response.getInt(7));
    		adventurer.setVit(response.getInt(8));
    		adventurer.setIntel(response.getInt(9));
    		adventurer.setWis(response.getInt(10));
    		adventurer.setLastStyle(response.getString(11));
    		adventurer.setAtk(response.getDouble(12));
    		adventurer.setDef(response.getDouble(13));
    		adventurer.setMagAtk(response.getDouble(14));
    		adventurer.setMagDef(response.getDouble(15));
    		adventurer.setHp(response.getDouble(16));
    		adventurer.setMp(response.getDouble(17));
    		adventurer.setLvl(response.getInt(18));
    		adventurer.setExp(response.getInt(19));
    	}
    	catch (SQLException ex) {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    	finally {
            close(initAdventurer, response);
        }
    	
    }
    
    public void saveAdventurer (Adventurer adventurer) {
    	
    	PreparedStatement saveAdventurer = null;
    	
    	//Update the adventurers last style
    	try {
    		connection = getSQLConnection();
    		saveAdventurer = connection.prepareStatement("UPDATE tAdventurer SET LastStyle = ? WHERE Name = ?");
    		saveAdventurer.setString(1, adventurer.getLastStyle());
    		saveAdventurer.setString(2, adventurer.getPlayer().getName());
    		saveAdventurer.execute();
    	}
    	catch (SQLException ex) {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    	finally {
            close(saveAdventurer);
        }
    	
    	//Update the adventurers base stats
    	try {
    		connection = getSQLConnection();
    		saveAdventurer = connection.prepareStatement("UPDATE tUnlockedStyle SET Attack = ?, Defense = ?, MagicAttack = ?, MagicDefense = ?, Health = ?, Mana = ?, Level = ?, Exp = ? WHERE AdventurerID = (SELECT AdventurerID FROM tAdventurer WHERE Name = ?) AND StyleID = (SELECT StyleID FROM tStyle WHERE tStyle.Name = (SELECT tAdventurer.LastStyle FROM tAdventurer WHERE tAdventurer.Name = ?));");
    		saveAdventurer.setDouble(1, adventurer.getAtk());
    		saveAdventurer.setDouble(2, adventurer.getDef());
    		saveAdventurer.setDouble(3, adventurer.getMagAtk());
    		saveAdventurer.setDouble(4, adventurer.getMagDef());
    		saveAdventurer.setDouble(5, adventurer.getHp());
    		saveAdventurer.setDouble(6, adventurer.getMp());
    		saveAdventurer.setInt(7, adventurer.getLvl());
    		saveAdventurer.setInt(8, adventurer.getExp());
    		saveAdventurer.setString(9, adventurer.getPlayer().getName());
    		saveAdventurer.setString(10, adventurer.getPlayer().getName());
    		saveAdventurer.execute();
    	}
    	catch (SQLException ex) {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    	finally {
            close(saveAdventurer);
        }
    	
    	//Update their current roles ability points and stats
    	try {
    		connection = getSQLConnection();
    		saveAdventurer = connection.prepareStatement("UPDATE tUnlockedRole SET AbilityPoints = ?, Strength = ?, Vitality = ?, Intelligence = ?, Wisdom = ? WHERE AdventurerID = (SELECT AdventurerID FROM tAdventurer WHERE Name = ?) AND RoleID = (SELECT RoleID FROM tRole_Style WHERE StyleID = (SELECT StyleID FROM tStyle WHERE tStyle.Name = (SELECT tAdventurer.LastStyle FROM tAdventurer WHERE tAdventurer.Name = ?)));");
    		saveAdventurer.setDouble(1, adventurer.getAbilityPoints());
    		saveAdventurer.setDouble(2, adventurer.getStr());
    		saveAdventurer.setDouble(3, adventurer.getVit());
    		saveAdventurer.setDouble(4, adventurer.getIntel());
    		saveAdventurer.setDouble(5, adventurer.getWis());
    		saveAdventurer.setString(6, adventurer.getPlayer().getName());
    		saveAdventurer.setString(7, adventurer.getPlayer().getName());
    		saveAdventurer.execute();
    	}
    	catch (SQLException ex) {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    	finally {
            close(saveAdventurer);
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
    
    public String getUUID (String name) {
    	connection = getSQLConnection();
    	PreparedStatement getUUID = null;
    	ResultSet response = null;
    	String result = "";
    	
    	try {
    		getUUID = connection.prepareStatement("SELECT UUID FROM tAdventurer WHERE Name = '" + name + "';");
    		response = getUUID.executeQuery();
    		result = response.getString(1);
    	}
    	catch (SQLException ex) {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    	finally {
    		close(getUUID);
    	}
    	return result;
    }
    
    /**
     * Update a players name in the database since MC now supports name changing
     * @param uuid : A players Unique Identifier that never changes
     * @param newName : The players new name
     */
    public void updateName (String uuid, String newName) {
    	connection = getSQLConnection();
    	PreparedStatement updateName = null;
    	try {
    		updateName = connection.prepareStatement("UPDATE tAdventurer SET " + 
    				"Name = (?)" +
    				"WHERE UUID = '" + uuid + "';");
        	updateName.setString(1, newName);
        	updateName.execute();
        	
    	}
    	catch (SQLException ex) 
        {
            Main.plugin.getLogger().log(Level.SEVERE, "Unable to retreive connection", ex);
        }
    	finally {
    		close(updateName);
    	}
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
    		if (connection != null)
    		{
    			connection.close();
    		}
    	} 
    	catch (SQLException ex) 
    	{
    		Error.close(Main.plugin, ex);
    	}
    }

    /**
     * A convenience method to close a prepared statement and a result set
     * @param ps : The Prepared Statement to close
     */
    public void close(PreparedStatement ps)
    {
    	try 
    	{
    		if (ps != null)
    		{
    			ps.close();            	
    		}
    		if (connection != null)
    		{
    			connection.close();
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
