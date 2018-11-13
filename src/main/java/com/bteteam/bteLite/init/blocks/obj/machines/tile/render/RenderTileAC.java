package com.bteteam.bteLite.init.blocks.obj.machines.tile.render;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.bteteam.bteLite.init.blocks.obj.machines.tile.TileAlchemicalCauldron;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderTileAC extends TileEntitySpecialRenderer<TileAlchemicalCauldron> {

	@Override
	public void render(TileAlchemicalCauldron te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		if (!te.items.isEmpty()) {
			GlStateManager.enableRescaleNormal();
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
			GlStateManager.enableBlend();
			RenderHelper.enableStandardItemLighting();
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GlStateManager.pushMatrix();
			double current = 1.25;
			int currentIndex = -1;
			for (ItemStack item : te.items) {
				currentIndex++;
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
				GlStateManager.translate(x + 0.5, y + Math.max((current - ((te.ticksboiling + (te.ticksboiling > 0 ? partialTicks *0.1 : 0)) * 0.01)), 0.7  + currentIndex * 0.1), z + 0.5);
				GlStateManager.rotate((te.getWorld().getTotalWorldTime() + partialTicks) * 4, 0, 1, 0);
				IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(item);
				model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND,
						false);
				Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				Minecraft.getMinecraft().getRenderItem().renderItem(item, model);
				current += 0.5;
			}
			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}
}
