package com.phenom.argonauts.denizens;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import net.aufdemrand.denizen.objects.dPlayer;
import net.aufdemrand.denizencore.exceptions.CommandExecutionException;
import net.aufdemrand.denizencore.exceptions.InvalidArgumentsException;
import net.aufdemrand.denizencore.objects.Element;
import net.aufdemrand.denizencore.objects.aH;
import net.aufdemrand.denizencore.objects.dList;
import net.aufdemrand.denizencore.scripts.ScriptEntry;
import net.aufdemrand.denizencore.scripts.commands.AbstractCommand;

public class getAllOnlinePlayers extends AbstractCommand {

	@Override
	public void parseArgs(ScriptEntry entry) throws InvalidArgumentsException {
		/*
		 * we loop the args
		 */
		for (aH.Argument arg : aH.interpret(entry.getArguments())) {
			/*
			 * check if the entry dont have a world arg & if the arg prefix match world
			 */
			if (!entry.hasObject("world") && arg.matchesPrefix("world")) {
				/*
				 * create a new element with the value of the world prefix and add it to the entry
				 */
				entry.addObject("world", new Element(arg.getValue()));
			} else arg.reportUnhandled();
		}
	}
	
	@Override
	public void execute(ScriptEntry entry) throws CommandExecutionException {
		dList onlineplayers = new dList();
		if (entry.hasObject("world")) {
			String w = entry.getElement("world").asString();
			World world = Bukkit.getServer().getWorld(w);
			if (world!=null) {
				for (Player player : world.getPlayers()) {
					onlineplayers.add(new dPlayer(player).identify());
				}
			} else {
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					onlineplayers.add(new dPlayer(player).identify());
				}
			}
		}
		entry.addObject("playerlist", onlineplayers);
	}
	
}
