package com.pippohsios.betterpixelmon.storage;

import java.util.ArrayList;
import java.util.List;

import com.pippohsios.betterpixelmon.capabilities.IPlayerAbilityHandler;
import com.pippohsios.betterpixelmon.utils.ExecutableCommand;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class CommandStorage implements IPlayerAbilityHandler, INBTSerializable<NBTTagCompound> {
	public static final String[] BETTER_PIXELMON_COMMANDS = { "beggsteps", "bevs", "bivs", "bpc", "bphelp", "cs", "csl",
			"pheal" };

	private int id = 0;
	public final ExecutableCommand PHEAL = new ExecutableCommand("pheal", id++);
	public final ExecutableCommand BPC = new ExecutableCommand("bpc", id++);

	public List<ExecutableCommand> commands = new ArrayList<ExecutableCommand>();

	public void initCommands() {
		commands.add(PHEAL);
		commands.add(BPC);
	}

	@Override
	public ExecutableCommand getCommand(int index) {
		return this.commands.get(index);
	}

	@Override
	public List<ExecutableCommand> getCommands() {
		return this.commands;
	}

	@Override
	public List<ExecutableCommand> getExecutableCommands() {
		List<ExecutableCommand> list = new ArrayList<ExecutableCommand>();
		this.commands.forEach((command) -> {
			if (command.executable)
				list.add(command);
		});
		return list;
	}

	@Override
	public List<ExecutableCommand> getUnexecutableCommands() {
		List<ExecutableCommand> list = new ArrayList<ExecutableCommand>();
		this.commands.forEach((command) -> {
			if (!command.executable)
				list.add(command);
		});
		return list;
	}

	@Override
	public void setCommandExecutable(int index, boolean executable) {
		this.commands.get(index).executable = executable;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.commands.forEach((command) -> {
			nbt.setBoolean("executable_" + command.getCommand(), command.executable);
		});
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.commands.forEach((command) -> {
			command.executable = nbt.getBoolean("executable_" + command.getCommand());
		});
	}
}
