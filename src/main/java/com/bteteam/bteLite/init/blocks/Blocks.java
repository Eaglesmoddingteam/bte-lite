package com.bteteam.bteLite.init.blocks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.bteteam.bteLite.init.IEntryHolder;
import com.bteteam.bteLite.init.NoItemBlock;
import com.bteteam.bteLite.init.blocks.obj.BlockBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class Blocks implements IEntryHolder<Block>{

	public static final Blocks INSTANCE = new Blocks();
	
	/**
	 * hidden Constructor, use {@code IEntryHolder.getInstance()}
	 */
	private Blocks() {}
	
	@Override
	public Class<? extends Block> getEntryType() {
		return BlockBase.class;
	}

	@Override
	public IEntryHolder<Block> getInstance() {
		return INSTANCE;
	}
	
	
	public ItemBlock[] getItemBlocks() {
		Field[] fields = this.getClass().getFields();
		List<ItemBlock> itemblocks = new ArrayList<>();
		for(Field f : fields) {
			if(!f.isAnnotationPresent(NoItemBlock.class)) {
				if(f.getType().equals(getEntryType())) {
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
}
