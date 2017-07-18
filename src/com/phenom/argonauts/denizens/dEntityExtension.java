package com.phenom.argonauts.denizens;

import net.aufdemrand.denizen.objects.dEntity;
import net.aufdemrand.denizencore.objects.dObject;
import net.aufdemrand.denizencore.tags.Attribute;

public class dEntityExtension extends dObjectExtension {

	private dEntity entity;
	
	public dEntityExtension(dEntity entity) {
		this.entity = entity;
	}
	
	public static boolean describes(dObject object) {
		return object instanceof dEntity;
	}
	
	public static dEntityMetaDataExtension getFrom(dObject o) {
    	if (!describes(o)) return null;
    	return new dEntityMetaDataExtension((dEntity)o);
    }
	
	@Override
	public String getAttribute(Attribute a) {
		if (a.startsWith("atk")) {
			
		}
		return null; //Just sitting this here so I don't get errors.
	}
}