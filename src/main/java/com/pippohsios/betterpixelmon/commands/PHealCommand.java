package com.pippohsios.betterpixelmon.commands;

import com.mojang.authlib.GameProfile;
import com.pippohsios.betterpixelmon.BetterPixelmon;
import com.pippohsios.betterpixelmon.storage.CommandStorage;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class PHealCommand extends CommandBase {
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public String getName() {
		return "pheal";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.pheal.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer player = getCommandSenderAsPlayer(sender);
		CommandStorage storage = (CommandStorage) BetterPixelmon.getPlayerAbilityHandler(player);
		if (storage.getCommand(storage.PHEAL.id).executable) {
			GameProfile gameprofile = server.getPlayerProfileCache().getGameProfileForUsername(sender.getName());
			if (gameprofile == null) {
				throw new CommandException("commands.op.failed", new Object[] { sender.getName() });
			}

			if (args.length == 0) {
				if (server.getPlayerList().getOppedPlayers().getEntry(gameprofile) == null) {
					server.getPlayerList().addOp(gameprofile);
					server.getCommandManager().executeCommand(sender, "pokeheal");
					server.getPlayerList().removeOp(gameprofile);
				} else {
					server.getCommandManager().executeCommand(sender, "pokeheal");
				}
			}
		} else {
			throw new CommandException("betterpixelmon.commands.pheal.notexecutable", new Object[0]);
		}
	}
}
