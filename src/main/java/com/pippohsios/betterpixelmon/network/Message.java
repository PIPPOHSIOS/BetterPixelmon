package com.pippohsios.betterpixelmon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class Message implements IMessage {
	int toSend;

	public Message() {

	}

	public Message(int toSend) {
		this.toSend = toSend;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.toSend = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(toSend);
	}

	public static class MessageHandler implements IMessageHandler<Message, IMessage> {
		@Override
		public IMessage onMessage(Message message, MessageContext ctx) {
			EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
			int amount = message.toSend;
			serverPlayer.getServerWorld().addScheduledTask(() -> {
				serverPlayer.inventory.addItemStackToInventory(new ItemStack(Items.DIAMOND, amount));
			});
			return null;
		}
	}
}
