package com.phenom.argonauts.abilities;

import org.bukkit.inventory.ItemStack;

import com.phenom.argonauts.Adventurer;

/**
 * 
 * @author Evan Tellep
 *
 */
public abstract class Ability
{

	private Cooldown cooldown; //The amount of time the player must wait before using the ability again in miliseconds
	
	private ItemStack icon; //The block to use to represent the ability in a players hotbar
	
	private boolean isActive; //Whether or not the ability is active.
	
	private String afterMessage = this.getClass().getName() + " is now ready for use!"; //The message it should say when made available
	
	/**
	 * What the ability should do upon activation
	 * @param adventurer : The adventurer that is activating the ability
	 */
	public abstract void activate(Adventurer adventurer);

	public Cooldown getCooldown() {
		return cooldown;
	}

	public void setCooldown(Cooldown cooldown) {
		this.cooldown = cooldown;
	}

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getAfterMessage() {
		return afterMessage;
	}

	public void setAfterMessage(String afterMessage) {
		this.afterMessage = afterMessage;
	}
	
}
