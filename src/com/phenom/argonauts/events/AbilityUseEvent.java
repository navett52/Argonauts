package com.phenom.argonauts.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.abilities.Ability;

/**
 * 
 * @author Evan Tellep
 *
 */
public class AbilityUseEvent extends Event 
{
	private static final HandlerList HANDLERS = new HandlerList(); //The list of handlers for this event
	
	private Adventurer adventurer; //The adventurer associated with the event
	private Ability ability; //The ability associated with the event
	
	/**
	 * Create an Ability Use Event Object
	 * @param adventurer : The adventurer associated with the event
	 * @param ability : The ability associated with the event
	 */
	public AbilityUseEvent(Adventurer adventurer, Ability ability) 
	{
		this.adventurer = adventurer;
		this.setAbility(ability);
	}

	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
        return HANDLERS;
    }

	public Adventurer getAdventurer() {
		return adventurer;
	}

	public void setAdventurer(Adventurer adventurer) {
		this.adventurer = adventurer;
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		this.ability = ability;
	}
	
}
