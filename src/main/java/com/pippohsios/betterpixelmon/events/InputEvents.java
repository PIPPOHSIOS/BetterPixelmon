package com.pippohsios.betterpixelmon.events;

import com.pippohsios.betterpixelmon.network.CommandExecutorMessage;
import com.pippohsios.betterpixelmon.proxy.ClientProxy;
import com.pippohsios.betterpixelmon.registry.PacketRegistry;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class InputEvents {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().currentScreen == null) {
			if (ClientProxy.COMMAND_EXECUTOR_SCREEN_KEY.isPressed()) {
				PacketRegistry.INSTANCE.sendToServer(new CommandExecutorMessage());
			}
		}
	}
}
