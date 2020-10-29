package com.pippohsios.betterpixelmon.commands;

import com.pippohsios.betterpixelmon.BetterPixelmon;
import com.pippohsios.betterpixelmon.storage.CommandStorage;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.storage.PCStorage;
import com.pixelmonmod.pixelmon.comm.packetHandlers.OpenScreen;
import com.pixelmonmod.pixelmon.comm.packetHandlers.clientStorage.newStorage.pc.ClientChangeOpenPC;
import com.pixelmonmod.pixelmon.enums.EnumGuiScreen;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class BPCCommand extends CommandBase {
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true; // execute always
	}

	@Override
	public String getName() {
		return "bpc";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.bpc.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = getCommandSenderAsPlayer(sender);
		CommandStorage storage = (CommandStorage) BetterPixelmon.getPlayerAbilityHandler(player);
		if (storage.getCommand(storage.BPC.id).executable) {
			PCStorage pc = Pixelmon.storageManager.getPC(player, null);
			Pixelmon.network.sendTo((IMessage) new ClientChangeOpenPC(pc.uuid), player);
			OpenScreen.open((EntityPlayer) player, EnumGuiScreen.PC, new int[0]);
		} else {
			throw new CommandException("betterpixelmon.commands.bpc.notexecutable", new Object[0]);
		}
	}
}
