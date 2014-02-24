package com.feedthebeast.virtualchest.core;

import java.util.ArrayList;
import java.util.List;

import com.feedthebeast.virtualchest.blocks.tile.TileEntityVirtualInventory;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class VirtualChestData extends WorldSavedData {

	public ChestInventory inventory;
	public int inventorySize;
	public List<TileEntityVirtualInventory> InventoriesOpen=new ArrayList<TileEntityVirtualInventory>();
	public static String TAG_INVENTORIES="Inventories";
	public static String TAG_INVENTORYNAME="InventoryName";
	public static String TAG_INVENTORY="Inventory";
	public static String TAG_CURRENT="CurrentInventory";
	public VirtualChestData(String Team) {
		super(Team);
		
	}
	public void Initialize(int size)
	{
		inventorySize=size;
		inventory=new ChestInventory(size, this.mapName);
	}
	public void onInventoryChanged(ChestInventory chest)
	{
		this.markDirty();
		for(TileEntityVirtualInventory inv:InventoriesOpen)
			inv.onInventoryChanged(chest);
	}
	public void AddVirtualBlock(TileEntityVirtualInventory in)
	{
		if(!InventoriesOpen.contains(in))
			InventoriesOpen.add(in);
	}
	
	public void RemoveVirtualBlock(TileEntityVirtualInventory in)
	{
		if(InventoriesOpen.contains(in))
			InventoriesOpen.remove(in);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		 String name=nbttagcompound.getString(TAG_INVENTORYNAME);
         ChestInventory inv=new ChestInventory(inventorySize, name);
         inv.readFromNBT(nbttagcompound.getCompoundTag(TAG_INVENTORY));
         inventory=inv;

	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setString(TAG_INVENTORYNAME, this.mapName);
		NBTTagCompound inventoryTag=new NBTTagCompound();
		inventory.writeToNBT(inventoryTag);
		nbttagcompound.setCompoundTag(TAG_INVENTORY, inventoryTag);

	}
	public void openChest() {
		for(TileEntityVirtualInventory inv:InventoriesOpen)
			inv.openChest();
		
	}
	public void closeChest() {
		for(TileEntityVirtualInventory inv:InventoriesOpen)
			inv.closeChest();
		
	}

}
