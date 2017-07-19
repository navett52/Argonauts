package com.phenom.argonauts.abilities.titan;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.abilities.Ability;
import com.phenom.argonauts.abilities.Cooldown;

public class Test extends Ability {

	public Test(Cooldown cooldown) {
		this.setCooldown(cooldown);
		this.setIcon(new ItemStack(Material.BAKED_POTATO)); //The icon for Return should always be a beacon
		this.setActive(true);
	}

	@Override
	public void activate(Adventurer adventurer) {
		adventurer.getPlayer().sendMessage("You have successfully used the Test ability as a Titan!");
	}

}
