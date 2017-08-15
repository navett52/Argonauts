package com.phenom.argonauts;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.phenom.argonauts.abilities.Ability;

public class Adventurer {
	
	//Base stats used to calculate damages and defenses as well as how much health and mana points you have
	private String lastStyle;
	private double atk; //Attack
	private double def; //Defense
	private double magAtk; //Magic Attack
	private double magDef; //Magic Defense
	private double hp; //Health Points
	private double mp; //Mana Points
	
	//Player Stats. Put ability points here to raise you're base stats.
	private double abilityPoints; //Ability points will be granted to a player meeting certain conditions upon leveling up.
	private double str; //Strength : atk
	private double vit; //Vitality : def, hp
	private double intel; //Intelligence : magAtk
	private double wis; //Wisdom : magDef, mp
	
	//Other relevant info
	private Location home; //The home point of the player. Able to be set and changed by going to Inns|Crystals at towns.
	private int lvl; //The players current level.
	private int exp; //The players current amount of experience.
	
	//The player object we're wrapping
	private UUID uuid; //The Unique ID of the player object we're wrapping
	private Player player; //The player object we're wrapping
	
	/**
	 * Holds the abilities for the adventurer
	 */
	private HashMap<String, Ability> abilities = new HashMap<String, Ability>(); //Hashmap to hold the abilities

	/**
	 * Constructor for the Adventurer object.
	 * @param player The player we want to make an adventurer.
	 */
	public Adventurer(Player player, UUID uuid) {
		//TODO : Check to make sure a home point is set before allowing this ability to fire
		Main.adventurers.put(player.getName(), this);
		this.player = player;
		this.uuid = uuid;
	}
	
	/*
	 * All of the getters and setters for the declared variables.
	 */
	public double getAtk() {
		return atk;
	}

	public void setAtk(double atk) {
		this.atk = atk;
	}

	public double getDef() {
		return def;
	}

	public void setDef(double def) {
		this.def = def;
	}

	public double getMagAtk() {
		return magAtk;
	}

	public void setMagAtk(double magAtk) {
		this.magAtk = magAtk;
	}

	public double getMagDef() {
		return magDef;
	}

	public void setMagDef(double magDef) {
		this.magDef = magDef;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getMp() {
		return mp;
	}

	public void setMp(double mp) {
		this.mp = mp;
	}

	public double getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(double abilityPoints) {
		this.abilityPoints = abilityPoints;
	}
	
	public double getStr() {
		return str;
	}

	public void setStr(double str) {
		this.str = str;
	}

	public double getVit() {
		return vit;
	}

	public void setVit(double vit) {
		this.vit = vit;
	}

	public double getIntel() {
		return intel;
	}

	public void setIntel(double intel) {
		this.intel = intel;
	}

	public double getWis() {
		return wis;
	}

	public void setWis(double wis) {
		this.wis = wis;
	}

	public Location getHome() {
		return home;
	}

	public void setHome(Location home) {
		this.home = home;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public HashMap<String, Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(HashMap<String, Ability> abilities) {
		this.abilities = abilities;
	}
	
	public void addAbility(String name, Ability ability) {
		this.abilities.put(name, ability);
	}

	public String getLastStyle() {
		return lastStyle;
	}

	public void setLastStyle(String lastStyle) {
		this.lastStyle = lastStyle;
	}
}
