package com.bteteam.bteLite.main;

import com.bteteam.bteLite.init.Registry;
import com.bteteam.bteLite.init.blocks.Blocks;
import com.bteteam.bteLite.init.items.Items;
import com.bteteam.bteLite.proxy.common.ISide;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Main.MODID, version = "1.0.0lite")
public class Main {

	public static final String MODID = "bte";
	public static final String CLIENT_PROXY = "com.tebreca.bteteam.bteLite.proxy.client.Client";
	public static final String SERVER_PROXY = "com.tebreca.bteteam.bteLite.proxy.server.Server";

	@SidedProxy(modId = Main.MODID, clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
	public static ISide proxy;

	@SubscribeEvent
	public void preInit(FMLPreInitializationEvent preInitializationEvent) {
		
	}

	@SubscribeEvent
	public void init(FMLInitializationEvent initializationEvent) {
		proxy.registerTileEntities();
	}

	@SubscribeEvent
	public void postInit(FMLPostInitializationEvent postInitializationEvent) {

	}

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		Registry.register(Blocks.INSTANCE.getInstance(), event);
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		Registry.register(Items.INSTANCE.getInstance(), event);
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		proxy.registerModels();
	}

}
