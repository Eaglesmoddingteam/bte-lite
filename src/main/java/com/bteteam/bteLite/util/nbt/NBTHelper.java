package com.bteteam.bteLite.util.nbt;

import java.util.List;
import java.util.Stack;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class NBTHelper {

	public static NBTTagCompound toNBT(List<ItemStack> stacks) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("size", stacks.size());
		for(int i = 0; i < stacks.size(); i++) {
			compound.setTag("item" + i, toNBT(stacks.get(i)));
		}
		return compound;
	}
	
	public static NBTTagCompound toNBT(ItemStack stack) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setString("itempath", stack.getItem().getRegistryName().getResourcePath());
		compound.setString("itemdomain", stack.getItem().getRegistryName().getResourceDomain());
		compound.setInteger("count", stack.getCount());
		return compound;
	}
	
	public static List<ItemStack> fromNBT(NBTTagCompound compound) {
		Stack<ItemStack> list = new Stack();
		for(int i = 0; i < compound.getInteger("size"); i++) {
			list.push(itemStackFromNBT((NBTTagCompound) compound.getTag("item" + i)));
		}
		return list;
	}
	
	public static ItemStack itemStackFromNBT(NBTTagCompound compound) {
		int count = compound.getInteger("count");
		Item i = ForgeRegistries.ITEMS.getValue(new ResourceLocation(compound.getString("itemdomain"), compound.getString("itempath")));
		return new ItemStack(i, count);
	}

}
