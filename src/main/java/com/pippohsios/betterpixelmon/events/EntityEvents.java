package com.pippohsios.betterpixelmon.events;

import com.pippohsios.betterpixelmon.BetterPixelmon;
import com.pippohsios.betterpixelmon.capabilities.PlayerAbilityCapabilityProvider;
import com.pippohsios.betterpixelmon.capabilities.PlayerSpecialAbilityCapabilityProvider;
import com.pippohsios.betterpixelmon.item.ItemFlyRing;
import com.pippohsios.betterpixelmon.item.ItemGodRing;
import com.pippohsios.betterpixelmon.reference.Reference;
import com.pippohsios.betterpixelmon.storage.CommandStorage;
import com.pippohsios.betterpixelmon.storage.SpecialAbilityStorage;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

public class EntityEvents {
	@SubscribeEvent
	public void attachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof EntityPlayer) {
			event.addCapability(new ResourceLocation(Reference.MOD_ID, "capabilities.PlayerAbilityCapabilityProvider"),
					new PlayerAbilityCapabilityProvider());
			event.addCapability(
					new ResourceLocation(Reference.MOD_ID, "capabilities.PlayerSpeciaAbilityCapabilityProvider"),
					new PlayerSpecialAbilityCapabilityProvider());
		}
	}

	@SubscribeEvent
	public void onEndPhaseTick(ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers().forEach((player) -> {
				// player must not be in creative
				if (!player.capabilities.isCreativeMode || !player.capabilities.allowEdit) {
					boolean foundFly = false;
					boolean foundGod = false;
					for (int i = 0; i < 46; i++) {
						ItemStack ring = player.inventory.getStackInSlot(i);
						if (!ring.isEmpty() && ring.getItem() instanceof ItemFlyRing) {
							foundFly = true;
						} else if (!ring.isEmpty() && ring.getItem() instanceof ItemGodRing) {
							foundGod = true;
						}
					}

					IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
					int[] slots = BaubleType.RING.getValidSlots();
					for (int i = 0; i < slots.length; i++) {
						ItemStack ring = handler.getStackInSlot(slots[i]);
						if (!ring.isEmpty() && ring.getItem() instanceof ItemFlyRing) {
							foundFly = true;
						} else if (!ring.isEmpty() && ring.getItem() instanceof ItemGodRing) {
							foundGod = true;
						}
					}

					SpecialAbilityStorage storage = (SpecialAbilityStorage) BetterPixelmon
							.getPlayerSpecialAbilityHandler(player);

					if (foundFly) { // add fly capability
						player.capabilities.allowFlying = true;
					} else {
						player.capabilities.allowFlying = false;
						player.capabilities.isFlying = false;

						storage.setAbilityActive(storage.FLY_ABILITY.id, false);
					}

					if (foundGod) { // add god capability
						player.capabilities.disableDamage = true;
					} else {
						player.capabilities.disableDamage = false;

						storage.setAbilityActive(storage.GOD_ABILITY.id, false);
					}

					player.sendPlayerAbilities();
				}
			});
		}
	}

	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone event) {
		CommandStorage storageO = (CommandStorage) BetterPixelmon.getPlayerAbilityHandler(event.getOriginal());
		NBTTagCompound nbt = storageO.serializeNBT();
		CommandStorage storageN = (CommandStorage) BetterPixelmon.getPlayerAbilityHandler(event.getEntityPlayer());
		storageN.deserializeNBT(nbt);

		SpecialAbilityStorage s_storageO = (SpecialAbilityStorage) BetterPixelmon
				.getPlayerSpecialAbilityHandler(event.getOriginal());
		nbt = s_storageO.serializeNBT();
		SpecialAbilityStorage s_storageN = (SpecialAbilityStorage) BetterPixelmon
				.getPlayerSpecialAbilityHandler(event.getEntityPlayer());
		s_storageN.deserializeNBT(nbt);
	}
}
