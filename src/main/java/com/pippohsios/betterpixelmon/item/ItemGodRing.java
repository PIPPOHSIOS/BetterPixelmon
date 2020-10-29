package com.pippohsios.betterpixelmon.item;

import com.pippohsios.betterpixelmon.BetterPixelmon;
import com.pippohsios.betterpixelmon.storage.SpecialAbilityStorage;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemGodRing extends ItemBase implements IBauble {
	public ItemGodRing(String name) {
		super(name);
		this.setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		IBaublesItemHandler handler = BaublesApi.getBaublesHandler(playerIn);
		ItemStack stack = playerIn.getHeldItem(handIn);
		int index = BaubleType.RING.getValidSlots()[1];
		ItemStack remainder = handler.insertItem(index, stack.copy(), true);
		if (remainder.getCount() < stack.getCount()) {
			playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 1.0F, 1.0F);
			handler.insertItem(index, stack.copy(), false);
			stack.setCount(0);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityIn;
			SpecialAbilityStorage storage = (SpecialAbilityStorage) BetterPixelmon
					.getPlayerSpecialAbilityHandler(player);
			storage.setAbilityActive(storage.GOD_ABILITY.id, true);
		}
	}

	@Override
	public BaubleType getBaubleType(ItemStack stack) {
		return BaubleType.RING;
	}

	@Override
	public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}
}
