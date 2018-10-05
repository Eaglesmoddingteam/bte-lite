package com.bteteam.bteLite.init.entities;

import com.bteteam.bteLite.init.IEntryHolder;
import com.bteteam.bteLite.init.items.Items;

import net.minecraft.entity.Entity;

public class Entities implements IEntryHolder<Entity>{

	private static final Entities INSTANCE = new Entities();

	/**
	 * hidden Constructor, use {@code IEntryHolder.getInstance()}
	 */
	private Entities() {}
	
	@Override
	public Class<? extends Entity> getEntryType() {
		return Entity.class;
	}

	@Override
	public IEntryHolder<Entity> getInstance() {
		return INSTANCE;
	}

}
