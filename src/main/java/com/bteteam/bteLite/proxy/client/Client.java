package com.bteteam.bteLite.proxy.client;

import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;

import java.util.List;

import com.bteteam.bteLite.init.Registry;
import com.bteteam.bteLite.init.blocks.Blocks;
import com.bteteam.bteLite.init.blocks.obj.machines.tile.TileAlchemicalCauldron;
import com.bteteam.bteLite.init.blocks.obj.machines.tile.render.RenderTileAC;
import com.bteteam.bteLite.init.items.Items;
import com.bteteam.bteLite.main.Main;
import com.bteteam.bteLite.proxy.common.ISide;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Client implements ISide {

	@Override
	public void registerModels() {
		List<Item> items = Registry.getValues(Items.INSTANCE);
		for(Item i : items) {
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
		}
		List<Block> blocks = Registry.getValues(Blocks.INSTANCE);
		for(Block b : blocks) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new  ModelResourceLocation(b.getRegistryName(), "inventory"));
		}
	}

	@Override
	public void registerTileEntities() {
		registerTileEntity(TileAlchemicalCauldron.class, new ResourceLocation(Main.MODID, "tile_alchemical_cauldron"));
	}

	@Override
	public void openGUI() {

	}

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileAlchemicalCauldron.class, new RenderTileAC());
		
	}

}
