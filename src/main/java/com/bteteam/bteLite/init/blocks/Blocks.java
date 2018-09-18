package com.bteteam.bteLite.init.blocks;

import com.bteteam.bteLite.init.IEntryHolder;

import net.minecraft.block.Block;

public class Blocks implements IEntryHolder<Block>{

	private static final Blocks INSTANCE = new Blocks();
	
	/**
	 * hidden Constructor, use {@code IEntryHolder.getInstance()}
	 */
	private Blocks() {}
	
	@Override
	public Class<? extends Block> getEntryType() {
		return null;
	}

	@Override
	public IEntryHolder<Block> getInstance() {
		return INSTANCE;
	}

}
