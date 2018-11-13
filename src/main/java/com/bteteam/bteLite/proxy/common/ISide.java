package com.bteteam.bteLite.proxy.common;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.relauncher.Side;

public interface ISide {

	public void registerModels();
	
	public void registerTileEntities();
	
	public void openGUI();
	
	public void registerRenders();
	
	public default void registerParticles() {}

	public default void registerSprites(TextureStitchEvent event) {};
	
	public void executeSided(IFunction func, Side side, Object... args);
	
}