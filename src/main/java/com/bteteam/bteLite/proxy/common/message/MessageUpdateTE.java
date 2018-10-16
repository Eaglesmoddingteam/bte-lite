package com.bteteam.bteLite.proxy.common.message;

import java.io.IOException;
import java.util.Stack;

import com.bteteam.bteLite.init.blocks.obj.machines.tile.TileAlchemicalCauldron;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateTE implements IMessage {

	NBTTagCompound compound;
	
	public MessageUpdateTE(NBTTagCompound compound) {
		this.compound = compound;
	}
	
	//TODO: make general te update packet; use PacketBuffer::writeTag();
	public MessageUpdateTE() {
		compound = new NBTTagCompound();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		try {
			compound = buffer.readCompoundTag();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer buffer = new PacketBuffer(buf);
		buffer.writeCompoundTag(compound);
	}
	
	public static class HandleMessageUpdateTE implements IMessageHandler<MessageUpdateTE, IMessage>{

		@Override
		public IMessage onMessage(MessageUpdateTE message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() ->{
				BlockPos pos = new BlockPos(message.compound.getInteger("x"), message.compound.getInteger("y"), message.compound.getInteger("z"));
				TileAlchemicalCauldron te = (TileAlchemicalCauldron) Minecraft.getMinecraft().world.getTileEntity(pos);
				te.readFromNBT(message.compound);
			});
			return null;
		}
		
	}

}
