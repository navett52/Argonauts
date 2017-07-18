package com.phenom.argonauts.denizens;

import net.aufdemrand.denizen.objects.dPlayer;

public class DenizenModule extends Support {
	
	@SuppressWarnings("unchecked")
	public DenizenModule() {
		registerObjects(dAdventurer.class);
		registerProperty(dPlayerExt.class, dPlayer.class);
	}
}
