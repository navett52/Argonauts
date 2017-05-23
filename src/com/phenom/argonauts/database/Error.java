package com.phenom.argonauts.database;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import com.phenom.argonauts.Main;

/**
 * 
 * @author pablo67340
 *
 */
public class Error 
{
    public static void execute(Main plugin, Exception ex)
    {
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);   
    }
    
    public static void close(JavaPlugin plugin, Exception ex)
    {
        plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}
