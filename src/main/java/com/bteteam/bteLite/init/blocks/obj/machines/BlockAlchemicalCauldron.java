package com.bteteam.bteLite.init.blocks.obj.machines;

import static net.minecraft.inventory.InventoryHelper.spawnItemStack;

import java.util.List;

import com.bteteam.bteLite.init.blocks.obj.BlockBase;
import com.bteteam.bteLite.init.blocks.obj.machines.tile.TileAlchemicalCauldron;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAlchemicalCauldron extends BlockBase {

	public PropertyBool HAS_WATER;

	public BlockAlchemicalCauldron() {
		super(Material.IRON);
		setHardness(2.3f);
		setHarvestLevel("pickaxe", 2);
		setSoundType(SoundType.METAL);
		setHardness(3.0f);
		setHarvestLevel("pickaxe", 2);
		HAS_WATER = PropertyBool.create("haswater");
	}

	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}

	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	
	//TODO: test item storage and item insertion in survival inventory
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (!playerIn.isSneaking()) {
				if (playerIn.getHeldItem(hand).getItem() == Items.BUCKET && state.getValue(HAS_WATER)) {
					playerIn.getHeldItem(hand).shrink(1);
					if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
						spawnItemStack(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5,
								new ItemStack(Items.WATER_BUCKET));
					}
					worldIn.setBlockState(pos, state.withProperty(HAS_WATER, false));
					return true;
				} else if (playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET && !state.getValue(HAS_WATER)) {
					playerIn.getHeldItem(hand).shrink(1);
					if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET))) {
						spawnItemStack(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5,
								new ItemStack(Items.BUCKET));
					}
					worldIn.setBlockState(pos, state.withProperty(HAS_WATER, true));
					return true;
				} else {
					ItemStack stack = playerIn.getHeldItem(hand);
					TileAlchemicalCauldron tile = (TileAlchemicalCauldron) worldIn.getTileEntity(pos);
					if (!stack.isEmpty()) {
						tile.acceptItem(stack);
						return true;
					} else {
						ItemStack extracted = tile.remove();
						if (!extracted.isEmpty())
							if (!playerIn.inventory.addItemStackToInventory(extracted)) {
								spawnItemStack(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5,
										extracted);
							}
					}
				}
			} else {
				TileAlchemicalCauldron tile = (TileAlchemicalCauldron) worldIn.getTileEntity(pos);
				if (playerIn.getHeldItem(hand).isEmpty()) {
					List<ItemStack> todrop = tile.extractAll();
					for (ItemStack i : todrop) {
						if (!playerIn.inventory.addItemStackToInventory(i)) {
							spawnItemStack(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, i);
						}
					}
				} else {
					ItemStack todrop = tile.removeOfType(playerIn.getHeldItem(hand).getItem());
					if (!playerIn.inventory.addItemStackToInventory(todrop)) {
						spawnItemStack(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, todrop);
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileAlchemicalCauldron();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if (HAS_WATER == null) {
			HAS_WATER = PropertyBool.create("haswater");
		}
		return new BlockStateContainer(this, HAS_WATER);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(HAS_WATER) ? 1 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return blockState.getBaseState().withProperty(HAS_WATER, meta == 1);
	}

}
