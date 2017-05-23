package com.phenom.argonauts.denizens;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.phenom.argonauts.Main;

import net.aufdemrand.denizen.objects.dEntity;
import net.aufdemrand.denizencore.objects.Element;
import net.aufdemrand.denizencore.objects.Mechanism;
import net.aufdemrand.denizencore.objects.dList;
import net.aufdemrand.denizencore.objects.dObject;
import net.aufdemrand.denizencore.tags.Attribute;

public class dEntityMetaDataExtension extends dObjectExtension {

	private dEntity dentity;
	
	/*
	 * In the constructor we get the instance of the dEntity
	 */
	public dEntityMetaDataExtension(dEntity e) {
		this.dentity = e;
	}

	/*
	 * The following 2 methods are needed by denizen itself.
	 */
	public static boolean describes(dObject object) {
        return object instanceof dEntity;
    }
    
    public static dEntityMetaDataExtension getFrom(dObject o) {
    	if (!describes(o)) return null;
    	return new dEntityMetaDataExtension((dEntity)o);
    }

    /*
     * Now we override the getAttribute method from property class and use our own methods.
     * @see com.gmail.berndivader.forricky.dObjectExtension#getAttribute(net.aufdemrand.denizencore.tags.Attribute)
     */
    @Override
    public String getAttribute(Attribute a) {

    	/*
    	 * @attribute <dentity.hasmetadata[<String>]>
    	 * We check if the attribute match and if the attribute has 1 context:
    	 */
    	if (a.startsWith("hasmetadata") && a.hasContext(1)) {
    		String metaname = a.getContext(1);
    		/*
    		 * We check if the entity has our metadata and return true or false.
    		 */
     		return new Element(dentity.getBukkitEntity().hasMetadata(metaname)).getAttribute(a.fulfill(1));
    	} else if (a.startsWith("getmetadata") && a.hasContext(1)) {
    		String metaname = a.getContext(1);
    		/*
    		 * we create a dList for all the metadataentries of the metadata.
    		 */
    		dList metalist = new dList();
    		/*
    		 * loop all entries of our metadata
    		 */
    		for (MetadataValue entry : dentity.getBukkitEntity().getMetadata(metaname)) {
    			/*
    			 * we create a new Element as string and add it to our dList
    			 */
    			metalist.add(new Element(entry.asString()).asString());
    		}
    		/*
    		 * and return the dList back to denizen.
    		 */
    		return metalist.getAttribute(a.fulfill(1));
    	}
    	
    	/*
    	 * this return is needed if the attribute didnt match with one of ours, so we return the dEntity instance back to 
    	 * denizen and denizen can go on....
    	 */
        return new Element(this.dentity.identify()).getAttribute(a);
    }
    
    /*
     * And here we overide the adjust method
     * @see com.gmail.berndivader.forricky.dObjectExtension#adjust(net.aufdemrand.denizencore.objects.Mechanism)
     */
    
	@Override
	public void adjust(Mechanism m) {
		
		/*
		 * @adjust <dEntity> setmetadata:<"name,value">
		 */
		if (m.matches("setmetadata") && m.hasValue()) {
			String[] parse = m.getValue().asString().split(",");
			String metaname = parse[0];
			String metavalue = parse[1];
	        this.dentity.getBukkitEntity().setMetadata(metaname, new FixedMetadataValue(Main.plugin, metavalue));
		}
		
	}
	
}
