package com.bteteam.bteLite.util.registry;

import java.util.ArrayList;

import com.bteteam.bteLite.init.blocks.obj.machines.tile.TileAlchemicalCauldron;
import com.bteteam.bteLite.util.list.Lists;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Registries {

	private Registries() {
	}

	public static void init() {
		new TileAlchemicalCauldron().add(Lists.asList(//
				new ItemStack(Items.COAL), //
				new ItemStack(Blocks.STONE)), //
				new ItemStack(Blocks.COAL_ORE));
	}

}
