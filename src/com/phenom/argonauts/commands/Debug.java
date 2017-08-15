package com.phenom.argonauts.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.Main;

public class Debug implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("sethomepoint")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Adventurer adventurer = Main.adventurers.get(player.getName());
				adventurer.setHome(player.getLocation());
				player.sendMessage(ChatColor.GREEN + "You've set a homepoint!");
			}
		}
		
		if (command.getName().equalsIgnoreCase("adventurer")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage(Main.adventurers.containsKey(player.getName()) + "");
			}
		}
		
		if (command.getName().equalsIgnoreCase("mark")) 
		{
			if (sender instanceof Player)
			{
				Player player = (Player) sender;
				player.setCompassTarget(player.getLocation());
			}
		}
		
		if (command.getName().equalsIgnoreCase("init")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				Adventurer a = Main.adventurers.get(player.getName());
				player.sendMessage("Style: " + a.getLastStyle());
				player.sendMessage("Atk: " + a.getAtk());
				player.sendMessage("Def: " + a.getDef());
				player.sendMessage("MagAtk: " + a.getMagAtk());
				player.sendMessage("MagDef: " + a.getMagDef());
				player.sendMessage("Hp: " + a.getHp());
				player.sendMessage("Mp: " + a.getMp());
				player.sendMessage("Lvl: " + a.getLvl());
				player.sendMessage("Exp: " + a.getExp());
				
				player.sendMessage("AP: " + a.getAbilityPoints());
				player.sendMessage("Str: " + a.getStr());
				player.sendMessage("Vit: " + a.getVit());
				player.sendMessage("Intel: " + a.getIntel());
				player.sendMessage("Wis: " + a.getWis());
			}
		}
		
		if (command.getName().equalsIgnoreCase("db")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(Main.db.getUUID(p.getName()));
				return true;
			}
		}
		
		if (command.getName().equalsIgnoreCase("save")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				p.sendMessage(Main.adventurers.get(p.getName()).getUuid() + "!");
				Main.db.saveAdventurer(Main.adventurers.get(p.getName()));
				p.sendMessage("Ran through the Save command");
			}
		}
		
		return false;
	}

}
