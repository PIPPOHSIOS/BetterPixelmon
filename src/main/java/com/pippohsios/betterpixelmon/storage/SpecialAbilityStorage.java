package com.pippohsios.betterpixelmon.storage;

import java.util.ArrayList;
import java.util.List;

import com.pippohsios.betterpixelmon.capabilities.IPlayerSpecialAbilityHandler;
import com.pippohsios.betterpixelmon.utils.SpecialAbility;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class SpecialAbilityStorage implements IPlayerSpecialAbilityHandler, INBTSerializable<NBTTagCompound> {
	private int id = 0;
	public final SpecialAbility FLY_ABILITY = new SpecialAbility("fly", id++);
	public final SpecialAbility GOD_ABILITY = new SpecialAbility("god", id++);

	public List<SpecialAbility> abilities = new ArrayList<SpecialAbility>();

	public void initAbilities() {
		abilities.add(FLY_ABILITY);
		abilities.add(GOD_ABILITY);
	}

	@Override
	public SpecialAbility getAbility(int index) {
		return abilities.get(index);
	}

	@Override
	public List<SpecialAbility> getAbilities() {
		return abilities;
	}

	@Override
	public List<SpecialAbility> getActiveAbilities() {
		List<SpecialAbility> list = new ArrayList<SpecialAbility>();
		abilities.forEach((ability) -> {
			if (ability.active) {
				list.add(ability);
			}
		});
		return list;
	}

	@Override
	public List<SpecialAbility> getInactiveAbilities() {
		List<SpecialAbility> list = new ArrayList<SpecialAbility>();
		abilities.forEach((ability) -> {
			if (!ability.active) {
				list.add(ability);
			}
		});
		return list;
	}

	@Override
	public void setAbilityActive(int index, boolean active) {
		abilities.get(index).active = active;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		abilities.forEach((ability) -> {
			nbt.setBoolean("active_" + ability.getName(), ability.active);
		});
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		abilities.forEach((ability) -> {
			ability.active = nbt.getBoolean("active_" + ability.getName());
		});
	}
}
