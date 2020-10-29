package com.pippohsios.betterpixelmon.capabilities;

import java.util.concurrent.Callable;

import com.pippohsios.betterpixelmon.storage.SpecialAbilityStorage;

public class PlayerSpecialAbilityCapabilityFactory implements Callable<IPlayerSpecialAbilityHandler> {
	@Override
	public IPlayerSpecialAbilityHandler call() throws Exception {
		return new SpecialAbilityStorage();
	}
}
