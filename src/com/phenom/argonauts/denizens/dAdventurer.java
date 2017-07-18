package com.phenom.argonauts.denizens;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.phenom.argonauts.Adventurer;
import com.phenom.argonauts.Main;

import net.aufdemrand.denizencore.objects.Adjustable;
import net.aufdemrand.denizencore.objects.Element;
import net.aufdemrand.denizencore.objects.Fetchable;
import net.aufdemrand.denizencore.objects.Mechanism;
import net.aufdemrand.denizencore.objects.dObject;
import net.aufdemrand.denizencore.tags.Attribute;
import net.aufdemrand.denizencore.tags.TagContext;

public class dAdventurer implements dObject, Adjustable {
	
	protected Player player;
	protected Adventurer adventurer;
	protected String prefix;
 
	/**
	 * @constructor
	 * @param Adventurer
	 */
	public dAdventurer(Adventurer adventurer) {
		if (adventurer!=null) {
			this.adventurer = adventurer;
			this.player = this.adventurer.getPlayer();
		}
	}
	
	public Adventurer getAdventurer() {
		return this.adventurer;
	}
	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public String getAttribute(Attribute a) {
		if (a==null) new Element(identify());
		
		if (a.startsWith("abilitypoints")) {
			return new Element(this.adventurer.getAbilityPoints()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("atk")) {
			return new Element(this.adventurer.getAtk()).getAttribute(a.fulfill(1));
		}
		return new Element(identify()).getAttribute(a);
	}
	
	@Override
	public void adjust(Mechanism m) {
		Element value = m.getValue();
		if (m.matches("abilitypoints") && m.requireDouble()) {
			this.adventurer.setAbilityPoints(value.asDouble());
		} else if (m.matches("atk") && m.requireDouble()) {
			this.adventurer.setAtk(value.asDouble());
		}
	}

	@Override
	public String debug() {
		return prefix+"='<A>"+identify()+"<G>'";
	}

	@Override
	public String getObjectType() {
		return "Adventurer";
	}

	@Override
	public String getPrefix() {
		return this.prefix;
	}

	@Override
	public String identify() {
		return "adventurer@" + this.getPlayer().getUniqueId();
	}

	@Override
	public String identifySimple() {
		return identify();
	}

	@Override
	public boolean isUnique() {
		return true;
	}

	@Override
	public dObject setPrefix(String string) {
		this.prefix = string;
		return this;
	}

	@Override
	public void applyProperty(Mechanism m) {
		// TODO Auto-generated method stub
		
	}
    @Fetchable("adventurer")
    public static dAdventurer valueOf(String string, TagContext context) {
        if (string == null) {
            return null;
        }
        try {
            string = string.replace("adventurer@", "");
            UUID uuid = UUID.fromString(string);
            Player player = Bukkit.getPlayer(uuid);
            if (!Main.adventurers.containsKey(player.getName())) return null;
            return new dAdventurer(Main.adventurers.get(player.getName()));
        }
        catch (Exception e) {
            return null;
        }
    }
	
}
