package com.pippohsios.betterpixelmon.capabilities;

import java.util.List;

import com.pippohsios.betterpixelmon.utils.SpecialAbility;

public interface IPlayerSpecialAbilityHandler {
	public SpecialAbility getAbility(int index);

	public List<SpecialAbility> getAbilities();

	public List<SpecialAbility> getActiveAbilities();

	public List<SpecialAbility> getInactiveAbilities();

	public void setAbilityActive(int index, boolean active);
}
