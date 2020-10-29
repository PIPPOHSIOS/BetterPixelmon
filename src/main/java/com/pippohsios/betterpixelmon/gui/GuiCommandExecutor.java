package com.pippohsios.betterpixelmon.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.pippohsios.betterpixelmon.network.SendCommandMessage;
import com.pippohsios.betterpixelmon.reference.Reference;
import com.pippohsios.betterpixelmon.registry.PacketRegistry;
import com.pippohsios.betterpixelmon.utils.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiCommandExecutor extends GuiScreen {
	private int backgroundWidth = 200;
	private int backgroundHeight = 70;
	private String title = "gui.commandexecutor.title";
	private GuiTextField console;

	private static final int BUTTON_HELP = 1;

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		this.console.textboxKeyTyped(typedChar, keyCode);

		if (keyCode == Keyboard.KEY_RETURN) {
			this.mc.player.closeScreen();
			this.mc.currentScreen = null;
			PacketRegistry.INSTANCE.sendToServer(new SendCommandMessage(
					Utils.isBetterPixelmonCommand(this.console.getText()), this.console.getText()));
		}

		if (keyCode == 1)
			this.mc.player.closeScreen();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		this.buttonList.clear();

		FontRenderer fr = this.mc.fontRenderer;

		int guiLeft = (width - backgroundWidth) / 2;
		int guiTop = (height - backgroundHeight) / 2;
		int width = 180;
		int height = 20;
		int x = this.width / 2 - width / 2;
		int y = guiTop + 10 + 20 + 10;

		this.buttonList.add(new GuiButton(BUTTON_HELP, guiLeft + backgroundWidth - 30, guiTop + 10, 20, 20, "?"));

		console = new GuiTextField(0, fr, x, y, width, height);
		console.setCanLoseFocus(true);
		console.setFocused(true);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//		this.drawDefaultBackground();
		FontRenderer fr = this.mc.fontRenderer;
		this.mc.renderEngine.bindTexture(new ResourceLocation(Reference.PREFIX_GUI + "command_executor_screen.png"));
		int guiLeft = (width - backgroundWidth) / 2;
		int guiTop = (height - backgroundHeight) / 2;
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, backgroundWidth + 1, backgroundHeight + 1);
		fr.drawStringWithShadow(I18n.format(this.title), guiLeft + 10, guiTop + 10, 0xffffff);
		this.console.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == BUTTON_HELP) {
			Utils.sendFormatMessageToPlayer(Minecraft.getMinecraft().player, "commands.bphelp.usage");
		}
	}

	@Override
	public void updateScreen() {
		this.console.updateCursorCounter();
	}
}
