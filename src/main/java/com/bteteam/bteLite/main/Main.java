package com.bteteam.bteLite.main;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Main.MODID, version = "1.0.0lite")
public class Main {

	public static final String MODID = "bte";

	@SubscribeEvent
	public void preInit(FMLPreInitializationEvent preInitializationEvent) {

	}

	@SubscribeEvent
	public void init(FMLInitializationEvent initializationEvent) {

	}

	@SubscribeEvent
	public void postInit(FMLPostInitializationEvent postInitializationEvent) {

	}
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		
	}


	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		
	}
	

	@SubscribeEvent
	public void registerItems(ModelRegistryEvent event) {
		
	}


}
