package com.feedthebeast.virtualchest.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.World;

public class InventoryManager {

	
	private static Map<String, VirtualChestData> inventories=new HashMap<String, VirtualChestData>();
	
	
	
	public static VirtualChestData getChest(String name,World world,int size)
	{
		if(inventories.containsKey(name))
		{
			return inventories.get(name);
		}
		VirtualChestData chest=(VirtualChestData) world.loadItemData(VirtualChestData.class, name);
		if(chest==null)
			chest=new VirtualChestData(name);
		chest.Initialize(size);
		inventories.put(name, chest);
		world.setItemData(name, chest);
		return chest;
	}



	public static Object getInventories() {
		// TODO Auto-generated method stub
		return null;
	}
}
