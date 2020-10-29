package com.pippohsios.betterpixelmon.registry;

import java.util.ArrayList;
import java.util.List;

import com.pippohsios.betterpixelmon.item.ItemBPCPermission;
import com.pippohsios.betterpixelmon.item.ItemBeastLoot;
import com.pippohsios.betterpixelmon.item.ItemFlyRing;
import com.pippohsios.betterpixelmon.item.ItemGodRing;
import com.pippohsios.betterpixelmon.item.ItemMasterLoot;
import com.pippohsios.betterpixelmon.item.ItemPHealPermission;
import com.pippohsios.betterpixelmon.item.ItemPixelmonBossSpawnEgg;
import com.pippohsios.betterpixelmon.item.ItemPokeLoot;
import com.pippohsios.betterpixelmon.item.ItemUltraLoot;

import net.minecraft.item.Item;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();

	// utils
	public static final Item FLY_RING = new ItemFlyRing("fly_ring");
	public static final Item GOD_RING = new ItemGodRing("god_ring");

	// permissions
	public static final Item PHEAL_PERMISSION = new ItemPHealPermission("pheal_permission");
	public static final Item BPC_PERMISSION = new ItemBPCPermission("bpc_permission");

	// loots
	public static final Item PIXELMON_BOSS_SPAWN_EGG = new ItemPixelmonBossSpawnEgg("pixelmon_boss_spawn_egg");
	public static final Item POKE_LOOT = new ItemPokeLoot("poke_loot");
	public static final Item ULTRA_LOOT = new ItemUltraLoot("ultra_loot");
	public static final Item MASTER_LOOT = new ItemMasterLoot("master_loot");
	public static final Item BEAST_LOOT = new ItemBeastLoot("beast_loot");
}
