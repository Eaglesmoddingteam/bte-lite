package com.bteteam.bteLite.main;

import com.bteteam.bteLite.init.items.Items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CTabs {

	public static final CreativeTabs bugstab = new CreativeTabs("bugstab") {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.INSTANCE.bee);
		}
	};
}
