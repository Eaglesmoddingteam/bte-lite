package com.bteteam.bteLite.proxy.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayParticleEffect implements IMessage {

	boolean hasParticleTypeEnum = true;
	public EnumParticleTypes particleType;
	public int particleID;
	public double xCoord;
	public double yCoord;
	public double zCoord;
	public double xSpeed;
	public double ySpeed;
	public double zSpeed;

	@Deprecated
	public MessagePlayParticleEffect() {

	}

	public MessagePlayParticleEffect(double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed,
			double zSpeed, EnumParticleTypes particleType) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.zSpeed = zSpeed;
		this.particleType = particleType;
	}

	public MessagePlayParticleEffect(double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed,
			double zSpeed, int particleType) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.zSpeed = zSpeed;
		this.particleID = particleType;
		hasParticleTypeEnum = false;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.xCoord = buf.readDouble();
		this.yCoord = buf.readDouble();
		this.zCoord = buf.readDouble();
		this.xSpeed = buf.readDouble();
		this.ySpeed = buf.readDouble();
		this.zSpeed = buf.readDouble();
		this.hasParticleTypeEnum = buf.readBoolean();
		if (hasParticleTypeEnum)
			this.particleType = EnumParticleTypes.getParticleFromId(buf.readInt());
		else
			this.particleID = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(xCoord);
		buf.writeDouble(yCoord);
		buf.writeDouble(zCoord);
		buf.writeDouble(xSpeed);
		buf.writeDouble(ySpeed);
		buf.writeDouble(zSpeed);
		buf.writeBoolean(hasParticleTypeEnum);

		buf.writeInt(hasParticleTypeEnum ? particleType.getParticleID() : particleID);

	}

	public static class HandleMSG implements IMessageHandler<MessagePlayParticleEffect, IMessage> {

		@Override
		public IMessage onMessage(MessagePlayParticleEffect message, MessageContext ctx) {
			Minecraft m = Minecraft.getMinecraft();
			if (message.hasParticleTypeEnum)
				m.addScheduledTask(() -> {
					for (int i = 0; i < 100; i++)
						m.world.spawnParticle(message.particleType, message.xCoord, message.yCoord, message.zCoord,
								message.xSpeed, message.ySpeed, message.zSpeed);
				});
			else
				m.addScheduledTask(() -> {
					Particle p = m.effectRenderer.spawnEffectParticle(message.particleID, message.xCoord,
							message.yCoord, message.zCoord, message.xSpeed, message.ySpeed, message.zSpeed);
					m.effectRenderer.addEffect(p);
				});
			return null;
		}

	}

}
