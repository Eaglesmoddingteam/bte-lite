package com.bteteam.bteLite.proxy.server;

import com.bteteam.bteLite.init.blocks.obj.machines.tile.TileAlchemicalCauldron;
import com.bteteam.bteLite.main.Main;
import com.bteteam.bteLite.proxy.common.IFunction;
import com.bteteam.bteLite.proxy.common.ISide;
import static net.minecraftforge.fml.common.registry.GameRegistry.registerTileEntity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class Server implements ISide {

	@Override
	public void registerModels() {

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
		
	}

	@Override
	public void executeSided(IFunction func, Side side, Object... args) {
		if(side.isServer()) {
			func.exec(args);
		}
	}

}
