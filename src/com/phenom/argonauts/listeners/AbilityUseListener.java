package com.phenom.argonauts.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.phenom.argonauts.Main;
import com.phenom.argonauts.events.AbilityUseEvent;
import com.phenom.argonauts.tasks.CooldownCountdown;

public class AbilityUseListener implements Listener 
{
	/**
	 * The listener that handles activating the ability.
	 * @param event The event the listener listens for.
	 */
	@EventHandler
	public void useAbility(AbilityUseEvent event) 
	{
		event.getAbility().activate(event.getAdventurer()); //Calling the activate method that every ability will have but for each ability it's different
	}
	
	/**
	 * The listener that handles the cooldown for the ability.
	 * @param event The event the listener listens for.
	 */
	@EventHandler
    public void coolDown(AbilityUseEvent event)
	{
		event.getAbility().setActive(false); //Set the ability's active flag to false since it's just been used
		
		CooldownCountdown cd = new CooldownCountdown(event.getAbility(), event.getAdventurer().getPlayer());
		cd.runTaskTimer(Main.plugin, 0, 20);
		
		//Schedule an event to set the active flag back to true
		/*Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() 
		{
			public void run() 
			{
				event.getAbility().setActive(true);
			}
		}, event.getAbility().getCooldown()); //Set the scheduled event to take place in the future for the length of time the ability's cooldown is.*/
	}
}
