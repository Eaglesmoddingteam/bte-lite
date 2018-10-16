package com.bteteam.bteLite.init.blocks.obj.machines.tile;

import java.util.List;
import java.util.Stack;

import com.bteteam.bteLite.main.Main;
import com.bteteam.bteLite.proxy.common.message.MessageUpdateTE;
import com.bteteam.bteLite.util.nbt.NBTHelper;
import com.google.gson.stream.MalformedJsonException;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileAlchemicalCauldron extends TileEntity implements ITickable {

	public Stack<ItemStack> items = new Stack();

	public TileAlchemicalCauldron() {

	}

	@Override
	public void update() {

	}

	public void acceptItem(ItemStack stack) {
		ItemStack prepared = stack.copy();
		prepared.setCount(1);
		items.push(prepared);
		markDirty();
	}

	public List<ItemStack> getContents() {
		return items;
	}

	public ItemStack remove() {
		if (items.isEmpty()) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = items.pop();
		markDirty();
		return stack;
	}

	public ItemStack removeOfType(Item type) {
		// TODO: fix
		if (items.contains(new ItemStack(type))) {
			int index = items.lastIndexOf(new ItemStack(type));
			ItemStack stack = items.remove(index);
			return stack;
		}
		markDirty();
		return ItemStack.EMPTY;
	}

	public List<ItemStack> extractAll() {
		List list = (Stack) items.clone();
		items.removeAllElements();
		markDirty();
		return list;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("inventory", NBTHelper.toNBT(items));
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.items = (Stack<ItemStack>) NBTHelper.fromNBT((NBTTagCompound) compound.getTag("inventory"));
		markDirty();
	}

	@Override
	public void markDirty() {
		if (!world.isRemote) {
			NBTTagCompound compound = new NBTTagCompound();
			Main.NETWORK.sendToAll(new MessageUpdateTE(writeToNBT(compound)));
		}
		super.markDirty();
	}
	
	@Override
	public void onLoad() {
		markDirty();
		super.onLoad();
	}
}
