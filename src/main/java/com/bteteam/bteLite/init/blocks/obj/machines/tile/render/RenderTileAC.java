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
			if (te.items.size() == 1) {
				GlStateManager.translate(x + 0.5 , y+ 1.25,  z + 0.5);
				IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(te.items.get(0), te.getWorld(), null);
				model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
				Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
				Minecraft.getMinecraft().getRenderItem().renderItem(te.items.get(0), model);
			} else {
				double radians = ((360 / te.items.size()) / 180) * Math.PI;
				double current = radians;
				for(ItemStack item : te.items) {
					GlStateManager.translate(x + Math.sin(current) * 4, y + 1.25, z + Math.cos(current) * 4);
					IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(item, te.getWorld(), null);
					model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);
					Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
					Minecraft.getMinecraft().getRenderItem().renderItem(item, model);
					current += radians;
				}
			}
			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}
}
