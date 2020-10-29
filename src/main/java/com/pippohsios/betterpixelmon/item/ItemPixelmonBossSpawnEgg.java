package com.pippohsios.betterpixelmon.item;

import com.mojang.authlib.GameProfile;
import com.pippohsios.betterpixelmon.storage.PokemonStorage;
import com.pippohsios.betterpixelmon.utils.Utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ItemPixelmonBossSpawnEgg extends ItemBase {
	public ItemPixelmonBossSpawnEgg(String name) {
		super(name);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (worldIn.isRemote)
			return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
		itemstack.shrink(1);

		playerIn.getCooldownTracker().setCooldown(this, 200);

		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		GameProfile gameprofile = server.getPlayerProfileCache().getGameProfileForUsername(playerIn.getName());
		if (server.getPlayerList().getOppedPlayers().getEntry(gameprofile) == null) {
			server.getPlayerList().addOp(gameprofile);
			server.getCommandManager().executeCommand(playerIn, "pokespawn "
					+ PokemonStorage.getRandomNameWithoutLegendaries() + " boss:" + Utils.getRandomNumberRange(1, 4));
			server.getPlayerList().removeOp(gameprofile);
		} else {
			server.getCommandManager().executeCommand(playerIn, "pokespawn "
					+ PokemonStorage.getRandomNameWithoutLegendaries() + " boss:" + Utils.getRandomNumberRange(1, 4));
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
}
