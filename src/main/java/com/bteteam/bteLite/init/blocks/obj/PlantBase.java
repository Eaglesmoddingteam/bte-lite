package com.bteteam.bteLite.init.blocks.obj;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PlantBase extends BlockBase {

	public static final List<Block> DEFAULT_SOIL = new ArrayList<Block>();
	
	public static final IProperty<Integer> STAGE = PropertyInteger.create("stage", 0, Integer.MAX_VALUE);
	
	private static final Random randomizer = new Random();
	
	private final List<Block> soil;
	private final float chance;
	private final int stages;
	
	static{
		DEFAULT_SOIL.add(Blocks.FARMLAND);
	}
	
	/**
	 * @param soil the blocks this plant can grow on
	 * @param chance the chance a block has to grow into the next stage
	 * @param material the block's material
	 */
	public PlantBase(List<Block> soil, float chance, int stages) {
		super(Material.PLANTS);
		this.chance = Math.max(0, 1 - chance);
		this.soil = soil;
		this.stages = stages - 1;
		setSoundType(SoundType.PLANT);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int stage = state.getValue(STAGE);
		if(stage < stages) {
			Float f = rand.nextFloat();
			if(f > chance) {
				worldIn.setBlockState(pos, state.withProperty(STAGE, ++stage));
			}
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, STAGE);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
		int fortune) {
		drops.add(new ItemStack(this));
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

}
