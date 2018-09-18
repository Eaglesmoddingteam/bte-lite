package com.bteteam.bteLite.init.items;

import com.bteteam.bteLite.init.IEntryHolder;
import com.bteteam.bteLite.init.blocks.Blocks;
import com.bteteam.bteLite.init.items.obj.ItemBase;

import net.minecraft.item.Item;

public class Items implements IEntryHolder<Item>{

	private static final Items INSTANCE = new Items();

	/**
	 * hidden Constructor, use {@code IEntryHolder.getInstance()}
	 */
	private Items() {}
	
	@Override
	public IEntryHolder<Item> getInstance() {
		return INSTANCE;
	}

	@Override
	public Class<? extends Item> getEntryType() {
		return ItemBase.class;
	}
	
	public ItemBase itemtest = new ItemBase();

}
