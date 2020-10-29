package com.pippohsios.betterpixelmon.item;

import com.pippohsios.betterpixelmon.BetterPixelmon;
import com.pippohsios.betterpixelmon.storage.CommandStorage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBPCPermission extends ItemBase {
	public ItemBPCPermission(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		itemstack.shrink(1);
		CommandStorage storage = (CommandStorage) BetterPixelmon.getPlayerAbilityHandler(playerIn);
		storage.setCommandExecutable(storage.BPC.id, true);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
}
