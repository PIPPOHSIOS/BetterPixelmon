package com.pippohsios.betterpixelmon.commands;

import java.util.Arrays;
import java.util.List;

import com.pippohsios.betterpixelmon.storage.CommandStorage;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class BPHelpCommand extends CommandBase {
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true; // execute always
	}

	@Override
	public String getName() {
		return "bphelp";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.bphelp.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			sendMessage(sender, "commands.bphelp.usage");
		} else if (args.length == 1) {
			switch (args[0]) {
			case "beggsteps":
				sendMessage(sender, "commands.bphelp.beegsteps");
				break;
			case "bevs":
				sendMessage(sender, "commands.bphelp.bevs");
				break;
			case "bivs":
				sendMessage(sender, "commands.bphelp.bivs");
				break;
			case "bpc":
				sendMessage(sender, "commands.bphelp.bpc");
				break;
			case "bphelp":
				sendMessage(sender, "commands.bphelp.bphelp");
				break;
			case "cs":
				sendMessage(sender, "commands.bphelp.cs");
				break;
			case "csl":
				sendMessage(sender, "commands.bphelp.csl");
				break;
			case "pheal":
				sendMessage(sender, "commands.bphelp.pheal");
				break;
			default:
				sendMessage(sender, "commands.bphelp.usage");
				break;
			}
		} else {
			throw new WrongUsageException(I18n.format("commands.bphelp.toomanyarguments", new Object[0]));
		}
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return Arrays.asList(CommandStorage.BETTER_PIXELMON_COMMANDS);
	}

	public void sendMessage(ICommandSender sender, String key) {
		TextComponentTranslation text = new TextComponentTranslation(key);
		sender.sendMessage((ITextComponent) text);
	}
}
