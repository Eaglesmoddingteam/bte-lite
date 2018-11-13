package com.bteteam.bteLite.proxy.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRequestUpdate implements IMessage {

	BlockPos pos;
	
	public MessageRequestUpdate(BlockPos pos) {
		this.pos = pos;
	}
	
	public MessageRequestUpdate() {
		pos = null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
	}

	public static class Handler implements IMessageHandler<MessageRequestUpdate, MessageUpdateTE>{

		@Override
		public MessageUpdateTE onMessage(MessageRequestUpdate message, MessageContext ctx) {
			World w = ctx.getServerHandler().player.getServerWorld();
			NBTTagCompound compound = w.getTileEntity(message.pos).writeToNBT(new NBTTagCompound());
			return new MessageUpdateTE(compound);
		}
		
	}
}
