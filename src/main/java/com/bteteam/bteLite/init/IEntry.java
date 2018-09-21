package com.bteteam.bteLite.init;

import net.minecraftforge.event.RegistryEvent;

public interface IEntry {

	public void prepare();
	
	public default void prepare(byte... info) {
		prepare();
	}
	
	public void onRegistry(RegistryEvent.Register<?> registry);
}
