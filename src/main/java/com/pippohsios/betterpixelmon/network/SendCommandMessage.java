package com.pippohsios.betterpixelmon.network;

import com.pippohsios.betterpixelmon.utils.Utils;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SendCommandMessage implements IMessage, IMessageHandler<SendCommandMessage, IMessage> {
	boolean isBp;
	String rawCommand;

	public SendCommandMessage() {
	}

	public SendCommandMessage(boolean isBp, String rawCommand) {
		this.isBp = isBp;
		this.rawCommand = rawCommand;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.isBp = buf.readBoolean();
		byte[] dst = new byte[buf.readInt()];
		buf.readBytes(dst);
		rawCommand = new String(dst);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.isBp);
		buf.writeInt(this.rawCommand.length());
		buf.writeBytes(this.rawCommand.getBytes());
	}

	@Override
	public IMessage onMessage(SendCommandMessage message, MessageContext ctx) {
		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
			EntityPlayerMP player = ctx.getServerHandler().player;
			if (message.isBp) {
				Utils.executeCommandFromPlayer(player, message.rawCommand);
			} else {
				FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager().executeCommand(player,
						message.rawCommand);
			}
		});
		return null;
	}
}
