package com.pippohsios.betterpixelmon.capabilities;

import com.pippohsios.betterpixelmon.storage.SpecialAbilityStorage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PlayerSpecialAbilityCapabilityProvider
		implements ICapabilitySerializable<NBTTagCompound>, ICapabilityProvider {
	private final SpecialAbilityStorage storage;

	public PlayerSpecialAbilityCapabilityProvider() {
		storage = new SpecialAbilityStorage();
		storage.initAbilities();
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return storage.serializeNBT();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		storage.deserializeNBT(nbt);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == Capabilities.PLAYER_SPECIAL_ABILITY_CAPABILITY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == Capabilities.PLAYER_SPECIAL_ABILITY_CAPABILITY ? (T) storage : null;
	}

}
