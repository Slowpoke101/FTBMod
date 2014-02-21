package com.feedthebeast.virtualchest.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;

public class InventoryManager {

	private static InventoryManager instance;
	
	public static InventoryManager getInstance()
	{
		if(instance==null)
			instance=new InventoryManager();
		return instance;
	}
	
	private  Map<String, VirtualChestData> inventories=new HashMap<String, VirtualChestData>();
	
	
	
	public  VirtualChestData getChest(String name,World world,int size)
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



	public  Object getInventories() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@ForgeSubscribe
	public void OnWorldUnload(WorldEvent.Unload event)
	{
		inventories.clear();
	}
}
