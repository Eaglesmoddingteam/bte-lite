package com.bteteam.bteLite.client.renders;

import static com.bteteam.bteLite.main.Main.MODID;

import javax.annotation.Nullable;

import com.bteteam.bteLite.client.models.InfernalBlacksmith;
import com.bteteam.bteLite.init.entities.obj.EntityBlackSmith;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderInfernalBlacksmith extends RenderLiving<EntityBlackSmith> {

	private ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/blacksmith/infernal_blacksmith.png");

	public RenderInfernalBlacksmith(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new InfernalBlacksmith(), 0.5F);
	}

	@Override
	public void doRender(EntityBlackSmith entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityBlackSmith entity) {
		return TEXTURE;
	}

	public static class Factory implements IRenderFactory<EntityBlackSmith> {

		@Override
		public Render<? super EntityBlackSmith> createRenderFor(RenderManager manager) {
			return new RenderInfernalBlacksmith(manager);
		}
	}
}
