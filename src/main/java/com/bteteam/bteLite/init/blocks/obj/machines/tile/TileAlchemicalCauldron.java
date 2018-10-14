package com.bteteam.bteLite.init.blocks.obj.machines.tile;

import java.util.List;
import java.util.Stack;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileAlchemicalCauldron extends TileEntity implements ITickable {

	public Stack<ItemStack> items = new Stack();
	
	public TileAlchemicalCauldron() {}

	@Override
	public void update() {

	}

	public void acceptItem(ItemStack stack) {
		ItemStack prepared = stack.copy();
		prepared.setCount(1);
		items.push(prepared);
	}
	
	public ItemStack[] getContents() {
		return (ItemStack[]) items.toArray();
	}
	
	public ItemStack remove() {
		if(items.isEmpty()) {
			return ItemStack.EMPTY;
		}
		return items.pop();
	}
	
	public ItemStack removeOfType(Item type) {
		if(items.contains(new ItemStack(type))) {
			int index = items.lastIndexOf(new ItemStack(type));
			return items.remove(index);
		}
		return ItemStack.EMPTY;
	}
	
	public List<ItemStack> extractAll(){
		List list = (Stack) items.clone();
		items.removeAllElements();
		return list;
	}
	
	//TODO: write/read from nbt
}
