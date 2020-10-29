package com.pippohsios.betterpixelmon.proxy;

import org.lwjgl.input.Keyboard;

import com.pippohsios.betterpixelmon.gui.GuiCommandExecutor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	public static final KeyBinding COMMAND_EXECUTOR_SCREEN_KEY = new KeyBinding(
			I18n.format("keybindings.keyopenmenu.desc"), Keyboard.KEY_H, "BetterPixelmon");

	@Override
	public void setupClient() {
		ClientRegistry.registerKeyBinding(COMMAND_EXECUTOR_SCREEN_KEY);
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	public static void openCommandExecutorWindow() {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (player != null) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiCommandExecutor());
		}
	}
}
