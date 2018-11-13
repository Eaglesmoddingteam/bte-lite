package com.bteteam.bteLite.init.blocks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.bteteam.bteLite.init.IEntryHolder;
import com.bteteam.bteLite.init.NoItemBlock;
import com.bteteam.bteLite.init.blocks.obj.BlockBase;
import com.bteteam.bteLite.init.blocks.obj.PlantBase;
import com.bteteam.bteLite.init.blocks.obj.machines.BlockAlchemicalCauldron;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class Blocks implements IEntryHolder<Block> {

	public static final Blocks INSTANCE = new Blocks();

	/**
	 * hidden Constructor, use {@code IEntryHolder.getInstance()}
	 */
	private Blocks() {
	}

	@Override
	public Class<? extends Block> getEntryType() {
		return Block.class;
	}

	@Override
	public IEntryHolder<Block> getInstance() {
		return INSTANCE;
	}

	public ItemBlock[] getItemBlocks() {
		Field[] fields = this.getClass().getFields();
		List<ItemBlock> itemblocks = new ArrayList<>();
		for (Field f : fields) {
			if (!f.isAnnotationPresent(NoItemBlock.class)) {
				if (f.getType().equals(getEntryType())) {
					Block block;
					try {
						block = (Block) f.get(this);
						ItemBlock b = new ItemBlock(block);
						itemblocks.add(b);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return itemblocks.toArray(new ItemBlock[0]);
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * begin blocks
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public static Block ant_farm = new BlockBase(Material.WOOD) {
		public void init() {
			setSoundType(SoundType.WOOD);
			setHardness(2.0f);
			setHarvestLevel("axe", 0);
		};
	};

	public static Block bee_hive = new BlockBase(Material.WOOD) {
		public void init() {
			setSoundType(SoundType.WOOD);
			setHardness(2.0f);
			setHarvestLevel("axe", 0);
		};
	};

	public static Block amethyst_ore = new BlockBase(Material.ROCK) {
		public void init() {
			setSoundType(SoundType.STONE);
			setHardness(3.0f);
			setHarvestLevel("pickaxe", 2);
		};
	};

	public static Block amethyst_block = new BlockBase(Material.ROCK) {
		public void init() {
			setSoundType(SoundType.STONE);
			setHardness(3.0f);
			setHarvestLevel("pickaxe", 2);
		};
	};

	public static Block alchemical_cauldron = new BlockAlchemicalCauldron();

	public static AxisAlignedBB PLANTS_HITBOX = new AxisAlignedBB(0, 0, 0, 1, 0.8, 1);

	public static Block agawa = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block aqumius = PlantBase.withAABB(PLANTS_HITBOX);;

	public static Block araucaria = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block small_cactus = new PlantBase();

	public static Block coral = new PlantBase();

	public static Block curcuma = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block dragon_lily = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block ender_bloom = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block ender_rose = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block endregor_mudroot = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block flycatcher = new PlantBase();

	public static Block horsetail_plant = new PlantBase();

	public static Block lovage = new PlantBase();

	public static Block phoenix_flower = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block pineapple = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block seaweed = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block shamrock = new PlantBase();

	public static Block tea_plant = PlantBase.withAABB(PLANTS_HITBOX);

	public static Block beetle_nest = new BlockBase(Material.WOOD) {
		public void init() {
			setSoundType(SoundType.WOOD);
			setHardness(2.0f);
			setHarvestLevel("axe", 0);
		};
	};
	
}