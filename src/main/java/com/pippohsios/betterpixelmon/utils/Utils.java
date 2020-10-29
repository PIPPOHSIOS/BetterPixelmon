package com.pippohsios.betterpixelmon.utils;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;
import com.pippohsios.betterpixelmon.BetterPixelmon;
import com.pippohsios.betterpixelmon.storage.CommandStorage;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Utils {
	public static int getRandomNumber(int bound) {
		Random rand = new Random();
		return rand.nextInt(bound);
	}

	public static int getRandomNumberRange(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}

	public static <T> T getRandomElementFromList(List<T> list) {
		Random rand = new Random();
		int index = rand.nextInt(list.size());
		return list.get(index);
	}

	public static boolean isInventoryFull(InventoryPlayer inventory) {
		for (ItemStack itemstack : inventory.mainInventory) {
			if (itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public static EntityPlayerMP getEntityPlayerMPfromSP(EntityPlayerSP player) {
		EntityPlayerMP p = null;
		try {
			p = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList()
					.getPlayerByUUID(player.getUniqueID());
		} catch (NullPointerException npe) {
			BetterPixelmon.instance.logger
					.error("The client player is null! Cannot get server instance of the player!");
		}
		return p;
	}

	public static void executeCommandFromPlayer(EntityPlayerMP player, String rawCommand) {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		GameProfile profile = server.getPlayerProfileCache().getGameProfileForUsername(player.getName());
		if (server.getPlayerList().getOppedPlayers().getEntry(profile) == null) {
			server.getPlayerList().addOp(profile);
			server.getCommandManager().executeCommand(player, rawCommand);
			server.getPlayerList().removeOp(profile);
		} else {
			server.getCommandManager().executeCommand(player, rawCommand);
		}
	}

	public static void drawTexturedQuad(double x, double y, double width, double height) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder builder = tessellator.getBuffer();
		builder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		builder.pos(x + 0, y + height, (double) 0).tex(0, 1).endVertex();
		builder.pos(x + width, y + height, (double) 0).tex(1, 1).endVertex();
		builder.pos(x + width, y + 0, (double) 0).tex(1, 0).endVertex();
		builder.pos(x + 0, y + 0, (double) 0).tex(0, 0).endVertex();
		tessellator.draw();
	}

	public static void drawTexturedQuadFit(double x, double y, double width, double height, int[] color, float alpha) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder tessellate = tessellator.getBuffer();
		tessellate.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		if (color != null)
			GlStateManager.color((float) color[0] / 255, (float) color[1] / 255, (float) color[2] / 255, alpha / 255);

		tessellate.pos(x + 0, y + height, (double) 0).tex(0, 1).endVertex();
		tessellate.pos(x + width, y + height, (double) 0).tex(1, 1).endVertex();
		tessellate.pos(x + width, y + 0, (double) 0).tex(1, 0).endVertex();
		tessellate.pos(x + 0, y + 0, (double) 0).tex(0, 0).endVertex();
		tessellator.draw();
	}

	public static void drawTexturedQuadFit(double x, double y, double width, double height, int[] color) {
		drawTexturedQuadFit(x, y, width, height, color, 255f);
	}

	public static void drawTexturedQuadFit(double x, double y, double width, double height, @Nullable Color color) {
		drawTexturedQuadFit(x, y, width, height,
				color == null ? null : new int[] { color.getRed(), color.getGreen(), color.getBlue() }, 255f);
	}

	public static void sendMessageToPlayer(EntityPlayer player, String message) {
		player.sendMessage(new TextComponentString(message));
	}

	public static void sendFormatMessageToPlayer(EntityPlayer player, String format) {
		player.sendMessage(new TextComponentString(I18n.format(format)));
	}

	public static boolean isBetterPixelmonCommand(String rawCommand) {
		String str = rawCommand.split(" ")[0];
		if (str.startsWith("/"))
			str.substring(1);

		for (String command : CommandStorage.BETTER_PIXELMON_COMMANDS) {
			if (str.equals(command)) {
				return true;
			}
		}

		return false;
	}
}
