package com.phenom.argonauts.denizens;

import com.phenom.argonauts.Main;

import net.aufdemrand.denizen.objects.dPlayer;
import net.aufdemrand.denizencore.objects.Element;
import net.aufdemrand.denizencore.objects.Mechanism;
import net.aufdemrand.denizencore.objects.dObject;
import net.aufdemrand.denizencore.tags.Attribute;

public class dPlayerExt extends dObjectExtension {
	private dPlayer dplayer;
	
	
    public dPlayerExt(dPlayer player) {
    	this.dplayer = player;
	}

	public static boolean describes(dObject object) {
        return object instanceof dPlayer;
    }
    
    public static dPlayerExt getFrom(dObject o) {
    	if (!describes(o)) return null;
    	return new dPlayerExt((dPlayer)o);
    }
    
    @Override
    public String getAttribute(Attribute a) {
    	if (a.startsWith("isadventurer")) {
    		return new Element(Main.adventurers.containsKey(dplayer.getName())).getAttribute(a.fulfill(1));
    	}
    	
    	//Here is my attempt of how I think I'd go about returning a dAdventurer
    	if (a.startsWith("adventurer")) {
    		return new dAdventurer(Main.adventurers.get(this.dplayer.getName())).getAttribute(a.fulfill(1));
    	}
    	//End Nave's Attempt
    	
    	return new Element(this.dplayer.identify()).getAttribute(a);
    }
	
	@Override
	public void adjust(Mechanism m) {
	}
    
}