package com.pippohsios.betterpixelmon.item;

import com.pippohsios.betterpixelmon.storage.Tier1DropStorage;
import com.pippohsios.betterpixelmon.utils.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemPokeLoot extends ItemBase {
	public ItemPokeLoot(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (worldIn.isRemote)
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);

		if (!playerIn.capabilities.isCreativeMode)
			itemstack.shrink(1);

		if (Utils.isInventoryFull(playerIn.inventory)) {
			playerIn.dropItem(Item.getByNameOrId(Utils.getRandomElementFromList(Tier1DropStorage.drops)), 1);
		} else {
			playerIn.inventory.addItemStackToInventory(
					new ItemStack(Item.getByNameOrId(Utils.getRandomElementFromList(Tier1DropStorage.drops))));
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
}
