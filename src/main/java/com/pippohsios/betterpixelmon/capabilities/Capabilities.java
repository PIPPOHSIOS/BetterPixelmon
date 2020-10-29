package com.pippohsios.betterpixelmon.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class Capabilities {
	@CapabilityInject(IPlayerAbilityHandler.class)
	public static Capability<IPlayerAbilityHandler> PLAYER_ABILITY_CAPABILITY = null;

	@CapabilityInject(IPlayerSpecialAbilityHandler.class)
	public static Capability<IPlayerSpecialAbilityHandler> PLAYER_SPECIAL_ABILITY_CAPABILITY = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IPlayerAbilityHandler.class, new PlayerAbilityStorage(),
				new PlayerAbilityCapabilityFactory());
		CapabilityManager.INSTANCE.register(IPlayerSpecialAbilityHandler.class, new PlayerSpecialAbilityStorage(),
				new PlayerSpecialAbilityCapabilityFactory());
	}

	private static class PlayerAbilityStorage implements Capability.IStorage<IPlayerAbilityHandler> {
		@Override
		public NBTBase writeNBT(Capability<IPlayerAbilityHandler> capability, IPlayerAbilityHandler instance,
				EnumFacing side) {
			return null;
		}

		@Override
		public void readNBT(Capability<IPlayerAbilityHandler> capability, IPlayerAbilityHandler instance,
				EnumFacing side, NBTBase nbt) {

		}
	}

	private static class PlayerSpecialAbilityStorage implements Capability.IStorage<IPlayerSpecialAbilityHandler> {
		@Override
		public NBTBase writeNBT(Capability<IPlayerSpecialAbilityHandler> capability,
				IPlayerSpecialAbilityHandler instance, EnumFacing side) {
			return null;
		}

		@Override
		public void readNBT(Capability<IPlayerSpecialAbilityHandler> capability, IPlayerSpecialAbilityHandler instance,
				EnumFacing side, NBTBase nbt) {

		}
	}
}
