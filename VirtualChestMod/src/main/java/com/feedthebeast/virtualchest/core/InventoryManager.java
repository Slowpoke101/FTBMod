package com.feedthebeast.virtualchest.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feedthebeast.virtualchest.blocks.tile.TileEntityVirtualInventory;
import com.feedthebeast.virtualchest.client.render.TileEntityVirtualRenderer;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
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
	
	private List<TileEntityVirtualInventory> chests=new ArrayList<TileEntityVirtualInventory>();
	
	
	
	public void onInventoryChanged(ChestInventory inv)
	{
		for(TileEntityVirtualInventory te:chests)
			te.onInventoryChanged(inv);
	}
	
	public void openChest(ChestInventory inv)
	{
		for(TileEntityVirtualInventory te:chests)
		{
			if(te.currentData.inventory.equals(inv))
				te.openChest();
		}
	}
	
	public void closeChest(ChestInventory inv)
	{
		for(TileEntityVirtualInventory te:chests)
		{
			if(te.currentData.inventory.equals(inv))
				te.closeChest();
		}
	}
	
	public void RegisterChest(TileEntityVirtualInventory chest)
	{
		if(!chests.contains(chest))
			chests.add(chest);
	}
	
	public void DeRegisterChest(TileEntityVirtualInventory chest)
	{
		if(chests.contains(chest))
			chests.remove(chest);
	}
	public  VirtualChestData getChest(String name,int size)
	{
		World world=DimensionManager.getWorld(0);
		MinecraftServer.getServer().getFolderName();
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
	public void OnWorldSave(WorldEvent.Save event)
	{
		
	}

	@ForgeSubscribe
	public void OnWorldLoad(WorldEvent.Load event)
	{
		
	}
	public void ClearInventories()
	{
		inventories.clear();
	}
}
