package com.pippohsios.betterpixelmon.commands;

import com.mojang.authlib.GameProfile;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;

public class BIVSCommand extends CommandBase {
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true; // execute always
	}

	@Override
	public String getName() {
		return "bivs";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.bivs.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		GameProfile gameprofile = server.getPlayerProfileCache().getGameProfileForUsername(sender.getName());
		if (gameprofile == null) {
			throw new CommandException("commands.op.failed", new Object[] { sender.getName() });
		}
		if (args.length == 0) {
			throw new WrongUsageException(this.getUsage(sender), new Object[0]);
		} else if (args.length == 1) {
			if (server.getPlayerList().getOppedPlayers().getEntry(gameprofile) == null) {
				server.getPlayerList().addOp(gameprofile);
				server.getCommandManager().executeCommand(sender, "ivs " + args[0]);
				server.getPlayerList().removeOp(gameprofile);
			} else {
				server.getCommandManager().executeCommand(sender, "ivs " + args[0]);
			}
		} else {
			throw new WrongUsageException(this.getUsage(sender), new Object[0]);
		}
	}
}
