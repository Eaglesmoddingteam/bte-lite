package com.bteteam.bteLite.init.blocks.trees.gen;

import java.util.Random;

import com.bteteam.bteLite.init.blocks.trees.ITree;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TreeGen<T extends ITree> extends WorldGenerator {

	IBlockState[][][] togenerate;

	T tree;

	public TreeGen(boolean notify, T tree) {
		super(notify);
		this.tree = tree;
		togenerate = new IBlockState[tree.getHeight()][(int) tree.get2D().getWidth()][(int) tree.get2D().getHeight()];
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (worldIn.getLight(position) > 8) {
			boolean flag = true;
			for (int y = 0; y < tree.getHeight(); y++) {
				flag &= worldIn.isAirBlock(position.add(0, y, 0));
			}
			if (flag) {
				BlockPos start = position.add(-1 * Math.floor(tree.get2D().getWidth() / 2), 0,
						-1 * Math.floor(tree.get2D().getHeight() / 2));
				for (int y = 0; y < tree.getHeight(); y++) {
					for (int x = 0; x < Math.floor(tree.get2D().getWidth()); x++) {
						for (int z = 0; z < Math.floor(tree.get2D().getHeight()); z++) {
							IBlockState toPlace = togenerate[y][x][z];
							if (toPlace != null) {
								worldIn.setBlockState(position.add(x, y, z), toPlace);
							}
						}
					}
				}
				return true;
			}
		}
		return false;
	}

}
