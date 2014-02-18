package com.feedthebeast.VirtualChest.blocks.tile;

import java.util.HashMap;

import com.feedthebeast.VirtualChest.core.ChestInventory;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.ILuaContext;
import dan200.computer.api.IPeripheral;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVirtualInventory extends TileEntity implements IInventory,IPeripheral{

	public TileEntityVirtualInventory()
	{
		
	}
	public void SetPlayer(String player)
	{
		currentInventory=(ChestInventory) GetPlayer(player);
	}
	public IInventory getCurrentInventory()
	{
		return currentInventory;
	}
	public IInventory GetPlayer(String player)
	{
		if(inventories.containsKey(player))
		{
			return inventories.get(player);
		}
		else
		{
			IInventory inv=new ChestInventory(inventorySize,this);
			inventories.put(player, (ChestInventory) inv);
			return inv;
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
		
	}

	@Override
	public void closeChest() {
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return currentInventory.isItemValidForSlot(i, itemstack);
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "VirtualInventory";
	}
	@Override
	public String[] getMethodNames() {
		// TODO Auto-generated method stub
		return new String[]{"setPlayer"};
	}
	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context,
			int method, Object[] arguments) throws Exception {
		switch(method)
		{
		case 1:
			SetPlayer((String)arguments[0]);
			return new String[]{};
		}
		return null;
	}
	@Override
	public boolean canAttachToSide(int side) {
		return true;
	}
	@Override
	public void attach(IComputerAccess computer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void detach(IComputerAccess computer) {
		// TODO Auto-generated method stub
		
	}

}
