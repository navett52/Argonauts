package com.phenom.argonauts.abilities.adventurer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.abilities.Ability;
import com.phenom.argonauts.abilities.Cooldown;

/**
 * 
 * @author Evan Tellep
 *
 */
public class Return extends Ability 
{
	/**
	 * Create a new Return ability
	 * @param cooldown : The cooldown to set for this ability
	 */
	public Return(Cooldown cooldown) 
	{
		this.setCooldown(cooldown);
		this.setIcon(new ItemStack(Material.BEACON)); //The icon for Return should always be a beacon
		this.setActive(true);
		//this.setAfterMessage("Return is now ready for use!");
	}
	
	/**
	 * Return the adventurer to their registered home point
	 */
	@Override
	public void activate(Adventurer adventurer) 
	{
		adventurer.getPlayer().teleport(adventurer.getHome());
	}
}
