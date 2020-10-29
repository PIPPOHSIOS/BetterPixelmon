package com.pippohsios.betterpixelmon.capabilities;

import java.util.concurrent.Callable;

import com.pippohsios.betterpixelmon.storage.CommandStorage;

public class PlayerAbilityCapabilityFactory implements Callable<IPlayerAbilityHandler> {
	@Override
	public IPlayerAbilityHandler call() throws Exception {
		return new CommandStorage();
	}
}
