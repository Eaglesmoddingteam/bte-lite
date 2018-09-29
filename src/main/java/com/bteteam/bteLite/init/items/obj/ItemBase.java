package com.bteteam.bteLite.init.items.obj;

import com.bteteam.bteLite.init.IEntry;
import com.bteteam.bteLite.main.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class ItemBase extends Item implements IEntry{
	public ItemBase(CreativeTabs tab) {
		super();
		setCreativeTab(tab);
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
		setRegistryName(new ResourceLocation(Main.MODID, name));
	}

	@Override
	public void onRegistry(Register<?> registry) {
		
	}
}
