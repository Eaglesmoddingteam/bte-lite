package com.bteteam.bteLite.init.entities.obj;

import com.bteteam.bteLite.client.renders.RenderInfernalBlacksmith;
import com.bteteam.bteLite.init.entities.EntityEntry;
import com.bteteam.bteLite.main.Main;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBlackSmith extends EntityCreature {

	public EntityBlackSmith(World worldIn) {
		super(worldIn);
		setSize(0.95F, 1.6F);
		this.stepHeight = 1.0F;
	}
	
	@Override
	protected void initEntityAI() {
		tasks.addTask(7, new EntityAIWanderAvoidWater(this, 0.7));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		applyEntityAI();
		super.initEntityAI();
	}

	protected void applyEntityAI(){
		
	}
	
	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0F);
	}
	public static class Registry extends EntityEntry {

		@Override
		public void prepare() {}

		@Override
		public void onRegistry(Register<?> registry) {}

		@Override
		public Class getEntityClass() {
			return EntityBlackSmith.class;
		}

		@Override
		public ResourceLocation getResourceName() {
			return new ResourceLocation(Main.MODID ,"infernal_blacksmith");
		}

		@Override
		public int[] getEggcolors() {
			return new int[]{0xFF8C00, 0xCC4B00};
		}

		@Override
		public int getTrackingRange() {
			return 0;
		}

		@Override
		public int getUpdateFrequency() {
			return 1;
		}

		@Override
		public boolean sendsVelocityUpdates() {
			return false;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public ClientHolder getClientHolder() {
			return new ClientHolder<EntityBlackSmith>() {

				@Override
				public Class<? extends EntityBlackSmith> getEntityClass() {
					return Registry.this.getEntityClass();
				}

				@Override
				public IRenderFactory<EntityBlackSmith> getRenderFactory() {
					return new RenderInfernalBlacksmith.Factory();
				}
			};
		}
		
	}
}
