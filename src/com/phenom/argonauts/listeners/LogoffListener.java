package com.phenom.argonauts.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogoffListener implements Listener 
{
	@EventHandler
	public void onPlayerLogoff (PlayerQuitEvent event)
	{
		Bukkit.broadcastMessage(ChatColor.GREEN + event.getPlayer().getName() + " has logged off.");
	}
}
