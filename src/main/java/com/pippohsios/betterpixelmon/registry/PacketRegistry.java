package com.pippohsios.betterpixelmon.registry;

import com.pippohsios.betterpixelmon.network.CommandExecutorMessage;
import com.pippohsios.betterpixelmon.network.CommandExecutorWindowMessage;
import com.pippohsios.betterpixelmon.network.Message;
import com.pippohsios.betterpixelmon.network.SendCommandMessage;
import com.pippohsios.betterpixelmon.reference.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketRegistry {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	private static int id = 0;

	public static void init() {
		INSTANCE.registerMessage(Message.MessageHandler.class, Message.class, id++, Side.SERVER);
		INSTANCE.registerMessage(SendCommandMessage.class, SendCommandMessage.class, id++, Side.SERVER);
		INSTANCE.registerMessage(CommandExecutorMessage.class, CommandExecutorMessage.class, id++, Side.SERVER);
		INSTANCE.registerMessage(CommandExecutorWindowMessage.class, CommandExecutorWindowMessage.class, id++,
				Side.CLIENT);
	}
}
