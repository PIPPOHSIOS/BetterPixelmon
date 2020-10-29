package com.pippohsios.betterpixelmon.item;

import com.pippohsios.betterpixelmon.BetterPixelmon;
import com.pippohsios.betterpixelmon.registry.ModItems;
import com.pippohsios.betterpixelmon.utils.IHasModel;

import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
	public ItemBase(String name) {
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(BetterPixelmon.BETTER_PIXELMON_TAB);

		ModItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		BetterPixelmon.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
