package com.pippohsios.betterpixelmon.network;

import com.pippohsios.betterpixelmon.proxy.ClientProxy;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommandExecutorWindowMessage implements IMessage, IMessageHandler<CommandExecutorWindowMessage, IMessage> {
	public CommandExecutorWindowMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	@Override
	public IMessage onMessage(CommandExecutorWindowMessage message, MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
			ClientProxy.openCommandExecutorWindow();
		});
		return null;
	}
}
