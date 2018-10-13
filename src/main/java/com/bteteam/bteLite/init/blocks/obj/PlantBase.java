package com.bteteam.bteLite.init.blocks.obj;

import com.bteteam.bteLite.init.IEntry;
import com.bteteam.bteLite.main.Main;
import static com.bteteam.bteLite.main.CTabs.*;

import net.minecraft.block.BlockBush;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class PlantBase extends BlockBush implements IEntry {

	public PlantBase() {
		super();
		init();
		setCreativeTab(blockstab);
	}

	@Override
	public void prepare(byte... info) {
		String name = new String(info);
		setUnlocalizedName(name);
		setRegistryName(new ResourceLocation(Main.MODID, name));
	}

	@Deprecated
	@Override
	public void prepare() {
		throw new IllegalAccessError("You shouldn't use this method!! Use IEntry::prepare(byte... info)");
	}

	@Override
	public void onRegistry(Register<?> registry) {

	}
	
	public void init() {
		
	}

}