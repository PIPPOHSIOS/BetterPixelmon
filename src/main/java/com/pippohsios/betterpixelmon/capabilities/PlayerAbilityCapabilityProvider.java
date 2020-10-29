package com.pippohsios.betterpixelmon.capabilities;

import com.pippohsios.betterpixelmon.storage.CommandStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PlayerAbilityCapabilityProvider implements ICapabilitySerializable<NBTTagCompound>, ICapabilityProvider {
	private final CommandStorage storage;

	public PlayerAbilityCapabilityProvider() {
		storage = new CommandStorage();
		storage.initCommands();
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == Capabilities.PLAYER_ABILITY_CAPABILITY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == Capabilities.PLAYER_ABILITY_CAPABILITY ? (T) storage : null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return this.storage.serializeNBT();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.storage.deserializeNBT(nbt);
	}
}
