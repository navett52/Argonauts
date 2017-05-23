package com.phenom.argonauts.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.Main;

public class JoinListener implements Listener 
{
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) 
	{
		if (Main.db.checkForAdventurer(event.getPlayer().getName(), event.getPlayer().getUniqueId())) //Check if the player exists in the database by name first
		{
			Main.adventurers.put(event.getPlayer().getName(), new Adventurer(event.getPlayer()));
		}
		else
		{
			Main.log.info(event.getPlayer().getName());
			Main.db.addNewAdventurer(event.getPlayer().getName(), event.getPlayer().getUniqueId(), 0, 0, 0, 0, 0, 0);
			Main.adventurers.put(event.getPlayer().getName(), new Adventurer(event.getPlayer()));
		}
	}
}