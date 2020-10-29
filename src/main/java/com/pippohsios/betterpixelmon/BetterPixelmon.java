package com.pippohsios.betterpixelmon;

import org.apache.logging.log4j.Logger;

import com.pippohsios.betterpixelmon.capabilities.Capabilities;
import com.pippohsios.betterpixelmon.capabilities.IPlayerAbilityHandler;
import com.pippohsios.betterpixelmon.capabilities.IPlayerSpecialAbilityHandler;
import com.pippohsios.betterpixelmon.commands.BEVSCommand;
import com.pippohsios.betterpixelmon.commands.BEggStepsCommand;
import com.pippohsios.betterpixelmon.commands.BIVSCommand;
import com.pippohsios.betterpixelmon.commands.BPCCommand;
import com.pippohsios.betterpixelmon.commands.BPHelpCommand;
import com.pippohsios.betterpixelmon.commands.CSCommand;
import com.pippohsios.betterpixelmon.commands.CSLCommand;
import com.pippohsios.betterpixelmon.commands.PHealCommand;
import com.pippohsios.betterpixelmon.events.EntityEvents;
import com.pippohsios.betterpixelmon.events.InputEvents;
import com.pippohsios.betterpixelmon.proxy.CommonProxy;
import com.pippohsios.betterpixelmon.reference.Reference;
import com.pippohsios.betterpixelmon.registry.PacketRegistry;
import com.pippohsios.betterpixelmon.registry.RegistryEvents;
import com.pippohsios.betterpixelmon.storage.PokemonStorage;
import com.pippohsios.betterpixelmon.storage.Tier1DropStorage;
import com.pippohsios.betterpixelmon.storage.Tier2DropStorage;
import com.pippohsios.betterpixelmon.storage.Tier3DropStorage;
import com.pippohsios.betterpixelmon.storage.UltraSpaceDropStorage;
import com.pippohsios.betterpixelmon.tabs.BetterPixelmonTab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * BetterPixelmon is a mod that adds many simple utilities to improve Pixelmon
 * gameplay mod.
 * 
 * @author PIPPOHSIOS
 * @since 22 October 2020
 * @version 1.1.1
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = "required-after:baubles@[1.5.2,);pixelmon@[8.1.0,);")
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class BetterPixelmon {
	@Instance(Reference.MOD_ID)
	public static BetterPixelmon instance;

	@SidedProxy(serverSide = Reference.COMMON_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
	public static CommonProxy proxy;

	public Logger logger;

	public static final CreativeTabs BETTER_PIXELMON_TAB = new BetterPixelmonTab("better_pixelmon_tab");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();

		MinecraftForge.EVENT_BUS.register(this);

		logger.info("Registering events");
		MinecraftForge.EVENT_BUS.register(new RegistryEvents());
		MinecraftForge.EVENT_BUS.register(new EntityEvents());
		MinecraftForge.EVENT_BUS.register(new InputEvents());

		logger.info("Registering capabilities");
		Capabilities.register();

		PokemonStorage.init();
		logger.info("Loading Pixelmon Pokechest drops");
		Tier1DropStorage.init();
		Tier2DropStorage.init();
		Tier3DropStorage.init();
		UltraSpaceDropStorage.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.setupClient();
		PacketRegistry.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	@EventHandler
	public void onStarting(FMLServerStartingEvent event) {
		logger.info("Loading commands");
		event.registerServerCommand(new PHealCommand());
		event.registerServerCommand(new BPCCommand());
		event.registerServerCommand(new BIVSCommand());
		event.registerServerCommand(new BEVSCommand());
		event.registerServerCommand(new BEggStepsCommand());
		event.registerServerCommand(new CSCommand());
		event.registerServerCommand(new CSLCommand());

		event.registerServerCommand(new BPHelpCommand());
	}

	public static IPlayerAbilityHandler getPlayerAbilityHandler(EntityPlayer player) {
		return player.getCapability(Capabilities.PLAYER_ABILITY_CAPABILITY, null);
	}

	public static IPlayerSpecialAbilityHandler getPlayerSpecialAbilityHandler(EntityPlayer player) {
		return player.getCapability(Capabilities.PLAYER_SPECIAL_ABILITY_CAPABILITY, null);
	}
}
