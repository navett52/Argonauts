package com.phenom.argonauts.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.Main;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void Return(PlayerInteractEvent event)
	{
		//event.getPlayer().sendMessage("You are in the interact event.");
		if ((event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BEACON) && 
				Main.adventurers.containsKey(event.getPlayer().getName()) && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK))
		{
			//event.getPlayer().sendMessage("You meet the requirements.");
			Adventurer adventurer = Main.adventurers.get(event.getPlayer().getName());
			adventurer.returnToHome();
		}
	}
}