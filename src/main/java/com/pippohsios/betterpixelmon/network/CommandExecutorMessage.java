package com.pippohsios.betterpixelmon.network;

import com.pippohsios.betterpixelmon.registry.PacketRegistry;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommandExecutorMessage implements IMessage, IMessageHandler<CommandExecutorMessage, IMessage> {
	public CommandExecutorMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	@Override
	public IMessage onMessage(CommandExecutorMessage message, MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
			EntityPlayerMP player = ctx.getServerHandler().player;
			if (player != null) {
				PacketRegistry.INSTANCE.sendTo(new CommandExecutorWindowMessage(), player);
			}
		});
		return null;
	}
}
