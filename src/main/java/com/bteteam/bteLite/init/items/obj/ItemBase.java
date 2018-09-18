package com.bteteam.bteLite.init.items.obj;

import com.bteteam.bteLite.init.IEntry;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item implements IEntry{

	public ItemBase() {
		
	}

	@Deprecated
	@Override
	public void prepare() {
		setUnlocalizedName("default" + this.getClass().toString());
	}
	
	@Override
	public void prepare(byte... info) {
		String name = new String(info);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(name));
	}
}
