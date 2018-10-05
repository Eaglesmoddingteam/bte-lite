package com.bteteam.bteLite.init.items;

import com.bteteam.bteLite.init.IEntryHolder;
import com.bteteam.bteLite.init.blocks.Blocks;
import com.bteteam.bteLite.init.items.obj.ItemBase;

//creative tabs
import static com.bteteam.bteLite.main.CTabs.*;

import net.minecraft.item.Item;

public class Items implements IEntryHolder<Item> {
	public static final Items INSTANCE = new Items();

	/**
	 * hidden Constructor, use {@code IEntryHolder.getInstance()}
	 */
	private Items() {
	}

	@Override
	public IEntryHolder<Item> getInstance() {
		return INSTANCE;
	}

	@Override
	public Class<? extends Item> getEntryType() {
		return ItemBase.class;
	}

	public ItemBase picker_ant = new ItemBase(bugstab);
	public ItemBase queen_ant = new ItemBase(bugstab);
	public ItemBase worker_ant = new ItemBase(bugstab);
	public ItemBase blue_beatle = new ItemBase(bugstab);
	public ItemBase beatle = new ItemBase(bugstab);
	public ItemBase alien_bee = new ItemBase(bugstab);
	public ItemBase jungle_bee = new ItemBase(bugstab);
	public ItemBase queen_alien = new ItemBase(bugstab);
	public ItemBase queen_jungle = new ItemBase(bugstab);
	public ItemBase bee = new ItemBase(bugstab);
	public ItemBase butterfly = new ItemBase(bugstab);
	public ItemBase dragonfly = new ItemBase(bugstab);
	public ItemBase dragonfly2 = new ItemBase(bugstab);
	public ItemBase dragonfly3 = new ItemBase(bugstab);
	public ItemBase earthworm = new ItemBase(bugstab);
	public ItemBase firefly = new ItemBase(bugstab);
	public ItemBase ladybird = new ItemBase(bugstab);
	public ItemBase moth = new ItemBase(bugstab);
	public ItemBase pinchbug = new ItemBase(bugstab);
	public ItemBase shrimp = new ItemBase(bugstab);
	public ItemBase silkworm = new ItemBase(bugstab);
	public ItemBase wasp = new ItemBase(bugstab);
	public ItemBase worm = new ItemBase(bugstab);

}
