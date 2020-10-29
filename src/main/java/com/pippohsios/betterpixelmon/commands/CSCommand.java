package com.pippohsios.betterpixelmon.commands;

import com.mojang.authlib.GameProfile;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CSCommand extends CommandBase {
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public String getName() {
		return "cs";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/cs";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		GameProfile gameprofile = server.getPlayerProfileCache().getGameProfileForUsername(sender.getName());
		if (gameprofile == null) {
			throw new CommandException("commands.op.failed", new Object[] { sender.getName() });
		}
		if (args.length == 0) {
			if (server.getPlayerList().getOppedPlayers().getEntry(gameprofile) == null) {
				server.getPlayerList().addOp(gameprofile);
				server.getCommandManager().executeCommand(sender, "checkspawns");
				server.getPlayerList().removeOp(gameprofile);
			} else {
				server.getCommandManager().executeCommand(sender, "checkspawns");
			}
		}
	}
}
