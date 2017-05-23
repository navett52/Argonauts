package com.phenom.argonauts.denizens;

import com.phenom.argonauts.Adventurer;

import net.aufdemrand.denizencore.objects.Adjustable;
import net.aufdemrand.denizencore.objects.Mechanism;
import net.aufdemrand.denizencore.objects.dObject;
import net.aufdemrand.denizencore.tags.Attribute;

public class AdventurerProperties implements dObject, Adjustable {
	
	public Adventurer adventurer;
	
	public AdventurerProperties (Adventurer adventurer)
	{
		if (adventurer == null)
		{
			return;
		}
		this.adventurer = adventurer;
		
	}
	
	/*public static AdventurerProperties valueOf (String uuid)
	{
		return valueOf(uuid, null);
	}*/
	
	@Override
	public void adjust(Mechanism arg0) {
		//Leaving blank for now as I don't know if Ricky wishes to change Adventurer stats through Denizens
	}

	@Override
	public void applyProperty(Mechanism arg0) {
		//Leaving blank as Properties I don't think would be applied to an Adventurer
	}

	@Override
	public String debug() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttribute(Attribute attribute) {
		if (attribute.startsWith("argonauts")) 
		{
			return "42";
		}
		return null;
	}

	@Override
	public String getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String identifySimple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUnique() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public dObject setPrefix(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Fetchable("argonauts")
    public static AdventurerProperties valueOf(String string, TagContext context) 
	{
        if (string == null) 
        {
            return null;
        }
        try 
        {
            string = string.replace("activemob@", "");
            UUID uuid = UUID.fromString(string);
            if (!Main.adventurers.containsKey(uuid)) 
            {
                return null;
            }
            return //new dActiveMob(MythicMobsAddon.getActiveMob(dEntity.getEntityForID(uuid)));
        }
        catch (Exception e) 
        {
            return null;
        }
    }*/
}
