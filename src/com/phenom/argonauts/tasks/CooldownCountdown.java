package com.phenom.argonauts.tasks;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.phenom.argonauts.abilities.Ability;

public class CooldownCountdown extends BukkitRunnable 
{
	private ItemStack icon;
	private Player player;
	private int itemSlot;
	private int remainingCooldown;
	private Ability ability;
	
	public CooldownCountdown (Ability ability, Player player)
	{
		icon = ability.getIcon();
		this.player = player;
		this.remainingCooldown = ability.getCooldown().getTime() / 1000;
		this.ability = ability;
		this.itemSlot = player.getInventory().getHeldItemSlot();
	}
	
	@Override
	public void run() 
	{
		ItemStack countdown = new ItemStack(icon.getType(), remainingCooldown);
		player.getInventory().setItem(itemSlot, countdown);
		remainingCooldown--;
		if (remainingCooldown == 0)
		{
			ability.setActive(true);
			player.sendMessage(ability.getAfterMessage());
			this.cancel();
		}
	}

}
