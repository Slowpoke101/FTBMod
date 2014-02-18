package com.feedthebeast.VirtualChest.blocks.tile;

import java.util.HashMap;

import com.feedthebeast.VirtualChest.core.ChestInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVirtualInventory extends TileEntity implements IInventory{

	public TileEntityVirtualInventory()
	{
		
	}
	public void SetPlayer(String player)
	{
		if(inventories.containsKey(player))
		{
			currentInventory=inventories.get(player);
			return;
		}
		else
		{
			currentInventory=new ChestInventory(inventorySize);
			inventories.put(player, currentInventory);
			return;
		}
	}
	public int inventorySize=36;
	private HashMap<String,  ChestInventory> inventories=new HashMap<String, ChestInventory>();
	private ChestInventory currentInventory;
	@Override
	public int getSizeInventory() {
		return currentInventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return currentInventory.getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		return currentInventory.decrStackSize(i, j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return currentInventory.getStackInSlotOnClosing(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		currentInventory.setInventorySlotContents(i, itemstack);
		
	}

	@Override
	public String getInvName() {
		return currentInventory.getInvName();
	}

	@Override
	public boolean isInvNameLocalized() {
		return currentInventory.isInvNameLocalized();
	}

	@Override
	public int getInventoryStackLimit() {
		return currentInventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return currentInventory.isUseableByPlayer(entityplayer);
	}

	@Override
	public void openChest() {
		currentInventory.openChest();
		
	}

	@Override
	public void closeChest() {
		currentInventory.closeChest();
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return currentInventory.isItemValidForSlot(i, itemstack);
	}

}
