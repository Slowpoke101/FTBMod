package com.feedthebeast.Handler;

import com.feedthebeast.Library.BlockIds;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationHandler
{
	public static void init(FMLPreInitializationEvent event)
	{
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		cfg.load();
		
		BlockIds.teamPeripheral_ID = cfg.getBlock("TeamPeripheral", 2100).getInt();
	}
}
