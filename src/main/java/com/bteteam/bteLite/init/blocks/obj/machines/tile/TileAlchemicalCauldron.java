package com.bteteam.bteLite.init.blocks.obj.machines.tile;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

import com.bteteam.bteLite.init.blocks.obj.machines.BlockAlchemicalCauldron;
import com.bteteam.bteLite.main.Main;
import com.bteteam.bteLite.proxy.client.particle.ParticleCauldronFinish;
import com.bteteam.bteLite.proxy.common.message.MessagePlayParticleEffect;
import com.bteteam.bteLite.proxy.common.message.MessageUpdateTE;
import com.bteteam.bteLite.util.list.Lists;
import com.bteteam.bteLite.util.nbt.NBTHelper;
import com.bteteam.bteLite.util.registry.IRegistryHolder;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class TileAlchemicalCauldron extends TileEntity
		implements ITickable, IRegistryHolder<List<ItemStack>, ItemStack> {

	public static final Stack<Entry<List<ItemStack>, ItemStack>> RECIPES = new Stack();

	public Stack<ItemStack> items = new Stack();

	boolean boiling = false;

	int tick = 0;
	public int ticksboiling = 0;
	public int animationticks = 0;

	public TileAlchemicalCauldron() {

	}

	@Override
	public void update() {
		tick++;
		if (tick == 10) {
			tick = 0;
			tick();
		}
		if (boiling) {
			ticksboiling++;
		} else {
			ticksboiling -= ticksboiling > 0 ? 1 : 0;
		}
	}

	private void tick() {
		if (world.isRemote && boiling) {
			world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, pos.getX() + 0.2, pos.getY() + 0.5, pos.getZ() + 0.2, 1,
					1, 1);
		}
		if (!world.isRemote) {
			checkBoiling();
		}
		if (ticksboiling == 100 && !world.isRemote) {
			IMessage message = new MessagePlayParticleEffect(pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5d, 1, 0.5, 1, ParticleCauldronFinish.Factory.getID());
			Main.NETWORK.sendToAllAround(message, new TargetPoint(DimensionType.OVERWORLD.getId(), pos.getX(), pos.getY(), pos.getZ(), 64));
			boiling = false;
			for (Entry<List<ItemStack>, ItemStack> e : RECIPES) {
				if (Lists.equalsUnordered(e.getKey(), items)) {
					this.extractAll();
					this.items.push(e.getValue().copy());
				}
			}
			markDirty();
		}
	}

	private void checkBoiling() {
		if (world.getBlockState(pos.down()).getBlock() == Blocks.FIRE
				&& world.getBlockState(pos).getValue(BlockAlchemicalCauldron.HAS_WATER)) {
			boolean flag = false;
			for (Entry<List<ItemStack>, ItemStack> e : RECIPES) {
				if (Lists.equalsUnordered(e.getKey(), items)) {
					flag = true;
				}
			}
			if (flag != boiling) {
				ticksboiling = 0;
				boiling = flag;
				markDirty();
			}
		} else {
			if (boiling) {
				boiling = false;
				markDirty();
			}
		}
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

	public ItemStack[] removeOfType(Item type) {
		List<ItemStack> stacks = new ArrayList<>();
		for (int i = items.size(); i >= 0; i--) {
			if (items.get(i).getItem() == type) {
				stacks.add(items.remove(i));
			}
		}
		markDirty();
		return stacks.toArray(new ItemStack[0]);
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
		compound.setBoolean("boiling", boiling);
		compound.setInteger("ticksboiling", ticksboiling);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.items = (Stack<ItemStack>) NBTHelper.fromNBT((NBTTagCompound) compound.getTag("inventory"));
		this.boiling = compound.getBoolean("boiling");
		this.ticksboiling = compound.getInteger("ticksboiling");
		markDirty();
	}

	@Override
	public void markDirty() {
		if (!world.isRemote) {
			NBTTagCompound compound = new NBTTagCompound();
			Main.NETWORK.sendToAllAround(new MessageUpdateTE(writeToNBT(compound)),
					new TargetPoint(DimensionType.OVERWORLD.getId(), pos.getX(), pos.getY(), pos.getZ(), 64));
		}
		super.markDirty();
	}

	@Override
	public void onLoad() {
		markDirty();
		super.onLoad();
	}

	@Override
	public Entry<List<ItemStack>, ItemStack> get(int index) {
		return RECIPES.get(index);
	}

	@Override
	public List<Entry<List<ItemStack>, ItemStack>> getAll() {
		return RECIPES;
	}

	@Override
	public void add(List<ItemStack> k, ItemStack v) {
		RECIPES.push(new SimpleEntry(k, v));
	}
	
}
