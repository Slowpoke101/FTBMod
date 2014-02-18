package com.feedthebeast.VirtualChest;

import java.util.logging.Logger;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.ForgeSubscribe;

import com.feedthebeast.VirtualChest.blocks.ModBlocks;
import com.feedthebeast.VirtualChest.core.CommonProxy;

import com.feedthebeast.VirtualChest.lib.LibMisc;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=LibMisc.MODID,name=LibMisc.MODNAME,version=LibMisc.VERSION)
@NetworkMod(clientSideRequired=true)
public class VirtualChestMod {
	@Instance(LibMisc.MODID)
	public static VirtualChestMod instance;

	@SidedProxy(clientSide="com.feedthebeast.VirtualChest.client.core.ClientProxy",serverSide="com.feedthebeast.VirtualChest.core.CommonProxy")
	public static CommonProxy proxy;
	public static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		logger.setParent(FMLLog.getLogger());

		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		Configs.load(config);
		ModBlocks.InitBlocks();
		proxy.RegisterRenderers();

		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{

	}
}
