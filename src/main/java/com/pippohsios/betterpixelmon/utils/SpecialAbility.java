package com.pippohsios.betterpixelmon.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class SpecialAbility implements INBTSerializable<NBTTagCompound> {
	public boolean active;
	public String name;
	public int id;

	public SpecialAbility(String name, int id) {
		this.active = false;
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("special_ability_" + this.name, this.active);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.active = nbt.getBoolean("special_ability_" + this.name);
	}
}
