package com.phenom.argonauts.denizens;

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
	
	private String prefix;
	
	Adventurer adventurer;
	
	public dAdventurer (Adventurer adventurer)
	{
		if (adventurer == null)
		{
			return;
		}
		this.adventurer = adventurer;
	}
	
	public static dAdventurer valueOf (String name)
	{
		return valueOf(name, null);
	}
	
	@Override
	public void adjust(Mechanism arg0) {
	}

	@Override
	public void applyProperty(Mechanism arg0) {
	}

	@Override
	public String debug() {
		return prefix + "='<A>" + identify() + "<G>'";
	}

	@Override
	public String getAttribute(Attribute attribute) {
		if (attribute == null)
		{
			return null;
		}
		return new Element(identify()).getAttribute(attribute);
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
		return "adventurer@" + this.adventurer.getPlayer().getName();
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
	public dObject setPrefix(String newPrefix) {
		this.prefix = newPrefix;
		return this;
	}

	@Fetchable("adventurer")
    public static dAdventurer valueOf(String uniqueName, TagContext context) 
	{
        if (uniqueName == null) 
        {
            return null;
        }
        try 
        {
        	uniqueName = uniqueName.replace("adventurer@", "");
            if (!Main.adventurers.containsKey(uniqueName)) 
            {
                return null;
            }
            return new dAdventurer(Main.adventurers.get(uniqueName));
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
