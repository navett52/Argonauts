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
	
    public static boolean matches(String string) {
        return valueOf(string) != null;
    }
    
	public static dAdventurer valueOf(String uuid) {
		return valueOf(uuid, null);
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
		
		if (a.startsWith("atk")) {
			return new Element(this.adventurer.getAtk()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("def")) {
			return new Element(this.adventurer.getDef()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("magatk")) {
			return new Element(this.adventurer.getMagAtk()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("magdef")) {
			return new Element(this.adventurer.getMagDef()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("hp")) {
			return new Element(this.adventurer.getHp()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("mp")) {
			return new Element(this.adventurer.getMp()).getAttribute(a.fulfill(1));
		}
		
		if (a.startsWith("abilitypoints")) {
			return new Element(this.adventurer.getAbilityPoints()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("str")) {
			return new Element(this.adventurer.getStr()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("vit")) {
			return new Element(this.adventurer.getVit()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("intel")) {
			return new Element(this.adventurer.getIntel()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("wis")) {
			return new Element(this.adventurer.getWis()).getAttribute(a.fulfill(1));
		}
		
		if (a.startsWith("homeX")) {
			return new Element(this.adventurer.getHome().getX()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("homeY")) {
			return new Element(this.adventurer.getHome().getY()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("homeZ")) {
			return new Element(this.adventurer.getHome().getZ()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("lvl")) {
			return new Element(this.adventurer.getLvl()).getAttribute(a.fulfill(1));
		}
		if (a.startsWith("exp")) {
			return new Element(this.adventurer.getExp()).getAttribute(a.fulfill(1));
		}
		
		if (a.startsWith("auid")) {
			return new Element(this.adventurer.getUuid().toString()).getAttribute(a.fulfill(1));
		}
		
		if (a.startsWith("save")) {
			Main.db.saveAdventurer(this.adventurer);
		}
		return new Element(identify()).getAttribute(a);
	}
	
	@Override
	public void adjust(Mechanism m) {
		Element value = m.getValue();
		//Base Stats
		if (m.matches("atk") && m.requireDouble()) {
			this.adventurer.setAtk(value.asDouble());
		}
		else if (m.matches("def") && m.requireDouble()) {
			this.adventurer.setDef(value.asDouble());
		}
		else if (m.matches("magatk") && m.requireDouble()) {
			this.adventurer.setMagAtk(value.asDouble());
		}
		else if (m.matches("magdef") && m.requireDouble()) {
			this.adventurer.setMagDef(value.asDouble());
		}
		else if (m.matches("hp") && m.requireDouble()) {
			this.adventurer.setHp(value.asDouble());
		}
		else if (m.matches("mp") && m.requireDouble()) {
			this.adventurer.setDef(value.asDouble());
		}
		//Ability points and Ability Stats
		else if (m.matches("abilitypoints") && m.requireDouble()) {
			this.adventurer.setAbilityPoints(value.asDouble());
		}
		else if (m.matches("str") && m.requireDouble()) {
			this.adventurer.setStr(value.asDouble());
		}
		else if (m.matches("vit") && m.requireDouble()) {
			this.adventurer.setVit(value.asDouble());
		}
		else if (m.matches("intel") && m.requireDouble()) {
			this.adventurer.setIntel(value.asDouble());
		}
		else if (m.matches("wis") && m.requireDouble()) {
			this.adventurer.setWis(value.asDouble());
		}
		
		else if (m.matches("homeX") && m.requireDouble()) {
			this.adventurer.getHome().setX(value.asDouble());;
		}
		else if (m.matches("homeY") && m.requireDouble()) {
			this.adventurer.getHome().setY(value.asDouble());;
		}
		else if (m.matches("homeZ") && m.requireDouble()) {
			this.adventurer.getHome().setZ(value.asDouble());;
		}
		else if (m.matches("lvl") && m.requireDouble()) {
			this.adventurer.setLvl(value.asInt());
		}
		else if (m.matches("exp") && m.requireDouble()) {
			this.adventurer.setExp(value.asInt());
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
