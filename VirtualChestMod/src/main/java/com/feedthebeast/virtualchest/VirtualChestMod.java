package com.feedthebeast.virtualchest;

import java.util.logging.Logger;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

import com.feedthebeast.virtualchest.blocks.ModBlocks;
import com.feedthebeast.virtualchest.core.CommonProxy;
import com.feedthebeast.virtualchest.core.InventoryManager;

import com.feedthebeast.virtualchest.lib.LibMisc;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=LibMisc.MODID,name=LibMisc.MODNAME,version=LibMisc.VERSION)
@NetworkMod(clientSideRequired=true)
public class VirtualChestMod {
	@Instance(LibMisc.MODID)
	public static VirtualChestMod instance;

	@SidedProxy(clientSide="com.feedthebeast.virtualchest.client.core.ClientProxy",serverSide="com.feedthebeast.virtualchest.core.CommonProxy")
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
		//MinecraftForge.EVENT_BUS.register(InventoryManager.getInstance());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
	@EventHandler
	public void onServerStopped(FMLServerStoppedEvent event) 
	{
		InventoryManager.getInstance().ClearInventories();
		System.out.println("Stopping Server");
	}
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		
	}
}
