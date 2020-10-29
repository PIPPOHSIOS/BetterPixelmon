package com.pippohsios.betterpixelmon.tabs;

import com.pippohsios.betterpixelmon.registry.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class BetterPixelmonTab extends CreativeTabs {

	public BetterPixelmonTab(String label) {
		super(label);
	}

	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.PIXELMON_BOSS_SPAWN_EGG);
	}
}
