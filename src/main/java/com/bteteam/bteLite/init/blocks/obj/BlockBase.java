package com.bteteam.bteLite.init.blocks.obj;

import com.bteteam.bteLite.init.IEntry;
import com.bteteam.bteLite.main.Main;
import static com.bteteam.bteLite.main.CTabs.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBase extends Block implements IEntry {

	public BlockBase(Material materialIn) {
		super(materialIn);
		init();
		setCreativeTab(blockstab);
	}

	@Deprecated
	@Override
	public void prepare() {
		throw new IllegalAccessError("You shouldn't use this method!! Use IEntry::prepare(byte... info)");
	}
	
	@Override
	public void prepare(byte... info) {
		String name = new String(info);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(Main.MODID, name));
	}

	@Override
	public void onRegistry(Register<?> registry) {
		
	}
	
	public void init() {
		
	}

}
