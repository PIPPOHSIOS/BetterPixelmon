package com.pippohsios.betterpixelmon.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class ExecutableCommand implements INBTSerializable<NBTTagCompound> {
	public boolean executable;
	private String raw; // aka raw command
	public int id;

	public ExecutableCommand(String raw, int id) {
		this.executable = false;
		this.raw = raw;
		this.id = id;
	}

	public ExecutableCommand(ExecutableCommand command) {
		this.executable = command.executable;
		this.raw = command.getCommand();
		this.id = command.id;
	}

	public ExecutableCommand(ExecutableCommand command, boolean executable) {
		this.executable = executable;
		this.raw = command.getCommand();
		this.id = command.id;
	}

	public String getCommand() {
		return raw;
	}

	public void setCommand(String raw) {
		this.raw = raw;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("executable_" + this.getCommand(), this.executable);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.executable = nbt.getBoolean("executable_" + this.getCommand());
	}
}
