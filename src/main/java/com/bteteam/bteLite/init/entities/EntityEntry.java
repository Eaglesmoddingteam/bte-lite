package com.bteteam.bteLite.init.entities;

import com.bteteam.bteLite.init.IEntry;
import com.bteteam.bteLite.main.Main;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityEntry<T extends Entity> implements IEntry {

	static int ID = 0;

	public abstract Class<? extends Entity> getEntityClass();

	public abstract ResourceLocation getResourceName();

	public abstract int[] getEggcolors();

	public abstract int getTrackingRange();

	public abstract int getUpdateFrequency();

	public abstract boolean sendsVelocityUpdates();

	@SideOnly(Side.CLIENT)
	public abstract ClientHolder<T> getClientHolder();

	public void register() {
		EntityRegistry.registerModEntity(getResourceName(), getEntityClass(), getResourceName().getResourcePath(), ++ID,
				Main.INSTANCE, getTrackingRange(), getUpdateFrequency(), sendsVelocityUpdates(), getEggcolors()[0],
				getEggcolors()[1]);
		Main.proxy.executeSided(getClientHolder()::registerModel, Side.CLIENT);
	}

	@SideOnly(Side.CLIENT)
	public static interface ClientHolder<T extends Entity> {

		public Class<? extends T> getEntityClass();

		public IRenderFactory<T> getRenderFactory();

		public default void registerModel(Object... args) {
			RenderingRegistry.registerEntityRenderingHandler(getEntityClass(), getRenderFactory());
		}
	}
}
