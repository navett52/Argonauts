package com.phenom.argonauts.denizens;

import net.aufdemrand.denizen.objects.dEntity;

public class DenizenModule extends Support {
	
	/*
	 * Constructor
	 */
	@SuppressWarnings("unchecked")
	public DenizenModule() {

		/*
		 * We create our first command, the command will return us a dList with all players on the server.
		 * To do this, we first write "new getAllOnlinePlayers();" Create the class and extend the class with AbstractCommand.
		 * In the getAllOnlinePlayers class we implement the methods and save the file. now we go back into the DenizenModule and 
		 * do .activeate().as("this is the commandname for denizen").withOptions("a usage hint", int of args required);
		 * 
		 */
		new getAllOnlinePlayers().activate().as("allonlineplayers").withOptions("- allonlineplayers [world:string]", 0);
		
		
		/*
		 * We create a dEntity.class extension. A extension add some method to an already existing dClass. In our case
		 * we extend the dEntity.class with some example metatag methods. For easier use of the Property.class we created
		 * the dObjectExtions.class and extend our new Extension class with it:
		 */
		
		registerProperty(dEntityMetaDataExtension.class, dEntity.class);
		
	}
	
}
