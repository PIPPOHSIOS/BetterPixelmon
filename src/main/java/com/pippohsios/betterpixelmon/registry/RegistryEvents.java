package com.pippohsios.betterpixelmon.registry;

import com.pippohsios.betterpixelmon.utils.IHasModel;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class RegistryEvents {
	@SubscribeEvent
	public void onRegistryItem(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		for (Item item : ModItems.ITEMS) {
			registry.register(item);
		}
	}

	@SubscribeEvent
	public void onRegistryModel(ModelRegistryEvent event) {
		for (Item item : ModItems.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
	}
}
