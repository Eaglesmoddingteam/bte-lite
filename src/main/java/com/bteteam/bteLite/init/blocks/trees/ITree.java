package com.bteteam.bteLite.init.blocks.trees;

import java.awt.Dimension;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public interface ITree {
	
	static final Random random = new Random();

	public void registerBlocks(RegistryEvent.Register<Block> registry);

	public void registerItemBlocks(RegistryEvent.Register<Item> registry);

	public interface TreeHolder {
		
		public boolean isComplete();
		
		public boolean shouldDecay();
	}
	
	public void generate(IBlockState[][][] blockPlacement);
	
	public int getHeight();
	
	public Dimension get2D();
}
