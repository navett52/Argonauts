package com.phenom.argonauts;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.phenom.argonauts.database.ArgonautsDatabase;
import com.phenom.argonauts.database.ArgonautsSQLite;
import com.phenom.argonauts.denizens.DenizenModule;
import com.phenom.argonauts.denizens.Support;
import com.phenom.argonauts.denizens.SupportManager;
import com.phenom.argonauts.listeners.JoinListener;
import com.phenom.argonauts.listeners.LogoffListener;

public class Main extends JavaPlugin {
	
	public static JavaPlugin plugin; //A reference to the plugin
	public static Logger log; //A logger to log info to the console
	public static PluginDescriptionFile pdf; //A variable to reference the plugin description file
	public static ArgonautsDatabase db; //Used to connect to our database instance - ArgonautsCore.db
	public static ConsoleCommandSender console; //Declaring a console reference so we can send color messages to console if we want
	public static HashMap<String, Adventurer> adventurers = new HashMap<String, Adventurer>(); //The in memory list of Adventurers
	private static SupportManager supportmanager;//Support manager for handling Denizens support stuff (Don't fully understand all this yet)
	
	/**
	 * Anything inside this method runs when the plugin is enabled.
	 */
	public void onEnable() {
		plugin = this; //Initializing the plugin reference
		log = plugin.getLogger(); //Initializing the logger
		pdf = plugin.getDescription(); //Initializing the plugin description file
		createPluginFolder(); //Create the plugin directory where all plugin related files will be stored if it does not already exist.
		db = new ArgonautsSQLite(); //Initializing our database variable
		db.load(); //Loads in our db instance. Creates all the tables if they do not exist
		console = getServer().getConsoleSender(); //Instantiating our console reference
		Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), plugin); //Register a join event listener to listen for when players join
		Bukkit.getServer().getPluginManager().registerEvents(new LogoffListener(), plugin); //Register a listener to see when a player logs off
		log.info(pdf.getName() + " version: " + pdf.getVersion() + " has been loaded!"); //Log to the console the plguin name and current version number
		
		//===Start Denizens implementation stuffs
		PluginManager pm = getServer().getPluginManager();
		if (pm.getPlugin("Denizen") == null) {
			initFail("Denizen not found!");
			return;
		}
		/**
		 * We create a new SupportManager to use in our plugin
		 */
        supportmanager = new SupportManager(plugin);
        try {
        	/**
        	 * and register our interface class DenizenModule.class
        	 * In this class we have all our new denizen stuff.
        	 */
        	supportmanager.register(Support.setPlugin(DenizenModule.class, pm.getPlugin(this.getName())));
		} catch (Exception e) {
			initError(e);
			return;
		}
        supportmanager.registerNewObjects();
        //===End Denizens implementation stuffs
	}
	
	/**
	 * Anything in this method will run when the plugin is disabled.
	 */
	public void onDisable() {
		
	}
	
	/**
	 * Creates the directory that will hold any files pertaining to the plugin if it does not already exist.
	 */
	public void createPluginFolder() {
		File pluginDirectory = new File(plugin.getDataFolder() + "/"); //Declare and instantiate the file object to be created
		//Check to see if the directory doesn't exist
		if(!pluginDirectory.exists()) {
			pluginDirectory.mkdir(); //Make directory
			log.info("The Argonauts directory has been created."); //Let people know it was made
		} else {
			log.info("The Argonauts directory already exists."); //Let people know it was not made because it already exists
		}
	}
	
	private void initFail(String reason) {
		getLogger().warning(reason);
		getPluginLoader().disablePlugin(this);
	}

	private void initError(Exception e) {
		getLogger().warning("There was a problem registering our DenizenModule!");
		e.printStackTrace();
		getPluginLoader().disablePlugin(this);
	}
	
}
