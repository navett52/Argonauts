package com.phenom.argonauts.listeners;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.Main;

public class JoinListener implements Listener 
{
	@EventHandler
    public void onAdventurerJoin(PlayerJoinEvent event) 
	{
		
		UUID uuid = null;
		
		if (Main.db.checkForAdventurer(event.getPlayer().getName())) { //If this passes they already exist as an adventurer in the database!
			Main.adventurers.put(event.getPlayer().getName(), new Adventurer(event.getPlayer(), UUID.fromString(Main.db.getUUID(event.getPlayer().getName()))));
			Main.db.initAdventurer(Main.adventurers.get(event.getPlayer().getName()));
		}
		else {
			uuid = event.getPlayer().getUniqueId();
		}
		
		if (uuid != null) { //If an adventurer was not found by name try the stuff below
			if (Main.db.checkForAdventurer(uuid)) { //If we can't find the player by name, check for the UUID as the player may have changed names
				Main.db.updateName(uuid.toString(), event.getPlayer().getName());
				Main.adventurers.put(event.getPlayer().getName(), new Adventurer(event.getPlayer(), uuid));
				Main.db.initAdventurer(Main.adventurers.get(event.getPlayer().getName()));
			}
			else { //If we cannot find the player by uuid, they are new and need to be created
				Main.log.info(event.getPlayer().getName());
				Main.db.addNewAdventurer(event.getPlayer().getName(), uuid, "Adventurer");
				Main.adventurers.put(event.getPlayer().getName(), new Adventurer(event.getPlayer(), uuid));
				Main.db.initAdventurer(Main.adventurers.get(event.getPlayer().getName()));
			}
		}
	}
}