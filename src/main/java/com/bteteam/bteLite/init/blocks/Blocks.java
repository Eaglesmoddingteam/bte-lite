package com.bteteam.bteLite.init.blocks;

import com.bteteam.bteLite.init.AddItemBlock;
import com.bteteam.bteLite.init.IEntryHolder;
import com.bteteam.bteLite.init.blocks.obj.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Blocks implements IEntryHolder<Block>{

	public static final Blocks INSTANCE = new Blocks();
	
	/**
	 * hidden Constructor, use {@code IEntryHolder.getInstance()}
	 */
	private Blocks() {}
	
	@Override
	public Class<? extends Block> getEntryType() {
		return BlockBase.class;
	}

	@Override
	public IEntryHolder<Block> getInstance() {
		return INSTANCE;
	}
	
	@AddItemBlock(value = true)
	public BlockBase testblock = new BlockBase(Material.ROCK);
}
