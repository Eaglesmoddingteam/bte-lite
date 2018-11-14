package com.bteteam.bteLite.main;

import java.util.List;

import com.bteteam.bteLite.init.Registry;
import com.bteteam.bteLite.init.blocks.Blocks;
import com.bteteam.bteLite.init.blocks.trees.ITree;
import com.bteteam.bteLite.init.blocks.trees.Trees;
import com.bteteam.bteLite.init.entities.Entities;
import com.bteteam.bteLite.init.items.Items;
import com.bteteam.bteLite.proxy.common.ISide;
import com.bteteam.bteLite.proxy.common.message.MessagePlayParticleEffect;
import com.bteteam.bteLite.proxy.common.message.MessageRequestUpdate;
import com.bteteam.bteLite.proxy.common.message.MessageUpdateTE;
import com.bteteam.bteLite.util.registry.Registries;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = Main.MODID, version = "1.0.0lite")
public class Main {

	public Main() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static final String MODID = "bte";
	public static final String CLIENT_PROXY = "com.bteteam.bteLite.proxy.client.Client";
	public static final String SERVER_PROXY = "com.bteteam.bteLite.proxy.server.Server";

	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

	@Mod.Instance
	public static Main INSTANCE;

	@SidedProxy(modId = Main.MODID, clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
	public static ISide proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent preInitializationEvent) {
		proxy.registerRenders();
		Entities.Register();
		NETWORK.registerMessage(MessageUpdateTE.HandleMessageUpdateTE.class, MessageUpdateTE.class, 0, Side.CLIENT);
		NETWORK.registerMessage(MessageRequestUpdate.Handler.class, MessageRequestUpdate.class, 1, Side.SERVER);
		NETWORK.registerMessage(MessagePlayParticleEffect.HandleMSG.class, MessagePlayParticleEffect.class, 2,
				Side.CLIENT);
	}

	@EventHandler
	public void init(FMLInitializationEvent initializationEvent) {
		System.out.println("initializing");
		proxy.registerTileEntities();
		Registries.init();
		proxy.registerParticles();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent postInitializationEvent) {

	}

	@SubscribeEvent
	public void onBlockRegister(RegistryEvent.Register<Block> event) {
		Registry.register(Blocks.INSTANCE, event);
		List<ITree> trees = Registry.getValues(Trees.INSTANCE);
		trees.forEach(tree -> tree.registerBlocks(event));
	}

	@SubscribeEvent
	public void onItemRegister(RegistryEvent.Register<Item> event) {
		Registry.register(Items.INSTANCE, event);
		IForgeRegistry registry = event.getRegistry();
		for (ItemBlock b : Blocks.INSTANCE.getItemBlocks()) {
			b.setRegistryName(new ResourceLocation(Main.MODID, b.getBlock().getUnlocalizedName()));
			registry.register((Item) b);
		}
		List<ITree> trees = Registry.getValues(Trees.INSTANCE);
		trees.forEach(tree -> tree.registerItemBlocks(event));
	}

	@SubscribeEvent
	public void onModelRegister(ModelRegistryEvent event) {
		proxy.registerModels();
	}

	@SubscribeEvent
	public void getSprites(TextureStitchEvent event) {
		proxy.registerSprites(event);
	}

}
