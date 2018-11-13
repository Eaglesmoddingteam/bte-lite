package com.bteteam.bteLite.init.entities;

import java.util.List;

import com.bteteam.bteLite.init.IEntry;
import com.bteteam.bteLite.init.IEntryHolder;
import com.bteteam.bteLite.init.Registry;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Entities implements IEntryHolder<EntityEntry> {

	private static final Entities INSTANCE = new Entities();

	/**
	 * hidden Constructor, use {@code IEntryHolder.getInstance()}
	 */
	private Entities() {
	}

	@Override
	public Class<? extends EntityEntry> getEntryType() {
		return EntityEntry.class;
	}

	@Override
	public IEntryHolder<EntityEntry> getInstance() {
		return INSTANCE;
	}

	public static void Register() {
		List<EntityEntry> entries = Registry.getValues(INSTANCE);
		for (EntityEntry e : entries) {
			e.register();
		}
	}
}
