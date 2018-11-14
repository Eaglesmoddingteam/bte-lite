package com.bteteam.bteLite.proxy.client;

import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;

import java.util.List;

import com.bteteam.bteLite.init.Registry;
import com.bteteam.bteLite.init.blocks.Blocks;
import com.bteteam.bteLite.init.blocks.obj.machines.tile.TileAlchemicalCauldron;
import com.bteteam.bteLite.init.blocks.obj.machines.tile.render.RenderTileAC;
import com.bteteam.bteLite.init.items.Items;
import com.bteteam.bteLite.main.Main;
import com.bteteam.bteLite.proxy.client.particle.ParticleCauldronFinish;
import com.bteteam.bteLite.proxy.common.IFunction;
import com.bteteam.bteLite.proxy.common.IFunction.NoArgs;
import com.bteteam.bteLite.proxy.common.ISide;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class Client implements ISide {

	public static final int DEFAULT_ID = 4112;

	@Override
	public void registerModels() {
		List<Item> items = Registry.getValues(Items.INSTANCE);
		for (Item i : items) {
			ModelLoader.setCustomModelResourceLocation(i, 0,
					new ModelResourceLocation(i.getRegistryName(), "inventory"));
		}
		List<Block> blocks = Registry.getValues(Blocks.INSTANCE);
		for (Block b : blocks) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0,
					new ModelResourceLocation(b.getRegistryName(), "inventory"));
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

	@Override
	public void registerParticles() {
		ParticleManager manager = Minecraft.getMinecraft().effectRenderer;
		ParticleCauldronFinish.Factory.register(manager, DEFAULT_ID + 1);
	}

	@Override
	public void registerSprites(TextureStitchEvent event) {
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks()//
				.registerSprite(new ResourceLocation(Main.MODID, "particle/al_couldron"));
		ParticleCauldronFinish.sprite = sprite;
	}
	
	@Override
	public void executeSided(IFunction func, Side side, Object... args) {
		if(side.isClient()) {
			func.exec(args);
		}
	}

	@Override
	public void executeSided(NoArgs func, Side side) {
		if(side.isClient()) {
			func.exec();
		}
	}
}
