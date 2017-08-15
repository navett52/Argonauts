package com.phenom.argonauts.commands;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.Main;

public class Debug implements CommandExecutor {
	
	public String[] debugCommands = {"isAdventurer", "check", "isConnected", "save"};
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (command.getName().equalsIgnoreCase("isAdventurer")) {
			if (sender instanceof Player) {
				String specifiedPlayer = "";
				Player p = (Player) sender;
				if (args.length != 0) {
					if (!args[0].isEmpty() && args != null) {
						specifiedPlayer = args[0];
					}
				}
				else {
					p.sendMessage(Main.adventurers.containsKey(p.getName()) + "");
					return true;
				}
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getServer().getPlayer(specifiedPlayer))) {
					p.sendMessage(args[0] + ": " + Main.adventurers.containsKey(specifiedPlayer));
					return true;
				}
				else {
					p.sendMessage(specifiedPlayer + ChatColor.RED + " is either not online or does not exist!");
					return false;
				}
			}
		}
		
		if (command.getName().equalsIgnoreCase("check")) {
			if (sender instanceof Player) {
				String specifiedPlayer = "";
				Player p = (Player) sender;
				Adventurer a = null;
				if (args.length != 0) {
					if (!args[0].isEmpty() && args != null) {
						specifiedPlayer = args[0];
					}
				}
				else {
					a = Main.adventurers.get(p.getName());
					p.sendMessage(ChatColor.GOLD + "Here are " + ChatColor.GREEN + p.getName() + "'s" + ChatColor.GOLD + " current stats.");
					p.sendMessage("Style: " + a.getLastStyle());
					p.sendMessage("Atk: " + a.getAtk());
					p.sendMessage("Def: " + a.getDef());
					p.sendMessage("MagAtk: " + a.getMagAtk());
					p.sendMessage("MagDef: " + a.getMagDef());
					p.sendMessage("Hp: " + a.getHp());
					p.sendMessage("Mp: " + a.getMp());
					p.sendMessage("Lvl: " + a.getLvl());
					p.sendMessage("Exp: " + a.getExp());
					p.sendMessage("AbilityPoints: " + a.getAbilityPoints());
					p.sendMessage("Str: " + a.getStr());
					p.sendMessage("Vit: " + a.getVit());
					p.sendMessage("Intel: " + a.getIntel());
					p.sendMessage("Wis: " + a.getWis());
					return true;
				}
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getServer().getPlayer(specifiedPlayer))) {
					a = Main.adventurers.get(specifiedPlayer);
					p.sendMessage(ChatColor.GOLD + "Here are " + ChatColor.GREEN + specifiedPlayer + "'s" + ChatColor.GOLD + " current stats.");
					p.sendMessage("Style: " + a.getLastStyle());
					p.sendMessage("Atk: " + a.getAtk());
					p.sendMessage("Def: " + a.getDef());
					p.sendMessage("MagAtk: " + a.getMagAtk());
					p.sendMessage("MagDef: " + a.getMagDef());
					p.sendMessage("Hp: " + a.getHp());
					p.sendMessage("Mp: " + a.getMp());
					p.sendMessage("Lvl: " + a.getLvl());
					p.sendMessage("Exp: " + a.getExp());
					p.sendMessage("AbilityPoints: " + a.getAbilityPoints());
					p.sendMessage("Str: " + a.getStr());
					p.sendMessage("Vit: " + a.getVit());
					p.sendMessage("Intel: " + a.getIntel());
					p.sendMessage("Wis: " + a.getWis());
					return true;
				}
				else {
					p.sendMessage(specifiedPlayer + ChatColor.RED + " is either not online or does not exist!");
					return false;
				}
			}
		}
		
		if (command.getName().equalsIgnoreCase("isConnected")) {
			try {
				sender.sendMessage(!Main.db.getSQLConnection().isClosed() + "");
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		if (command.getName().equalsIgnoreCase("save")) {
			if (sender instanceof Player) {
				String specifiedPlayer = "";
				Player p = (Player) sender;
				if (args.length != 0) {
					if (!args[0].isEmpty() && args != null) {
						specifiedPlayer = args[0];
					}
				}
				else {
					Main.db.saveAdventurer(Main.adventurers.get(p.getName()));
					p.sendMessage(ChatColor.GOLD + "Your stats have been saved in the database!");
					return true;
				}
				if (Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getServer().getPlayer(specifiedPlayer))) {
					Main.db.saveAdventurer(Main.adventurers.get(specifiedPlayer));
					p.sendMessage(ChatColor.GREEN + specifiedPlayer + "'s" + ChatColor.GOLD + "stats have been saved in the database!");
					return true;
				}
				else {
					p.sendMessage(specifiedPlayer + ChatColor.RED + " is either not online or does not exist!");
					return false;
				}
			}
		}
		return false;
	}
}
