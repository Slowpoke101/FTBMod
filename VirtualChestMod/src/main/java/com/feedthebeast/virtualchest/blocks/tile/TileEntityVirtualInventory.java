package com.feedthebeast.virtualchest.blocks.tile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import openperipheral.api.Arg;
import openperipheral.api.IAttachable;
import openperipheral.api.LuaCallable;
import openperipheral.api.LuaType;

import com.feedthebeast.TeamMod;
import com.feedthebeast.virtualchest.core.ChestInventory;
import com.feedthebeast.virtualchest.core.InventoryManager;
import com.feedthebeast.virtualchest.core.VirtualChestData;
import com.google.common.collect.Lists;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.ILuaContext;
import dan200.computer.api.IPeripheral;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVirtualInventory extends TileEntity implements IInventory,IAttachable{
	@Override
	public void validate() {
		super.validate();
		currentData=InventoryManager.getInstance().getChest(currentPlayer,  inventorySize);
	}


	private static final String EVENT_INVENTORY_CHANGED = "inventory_changed";
	private List<IComputerAccess> computers = Lists.newArrayList();
	public TileEntityVirtualInventory()
	{
		currentPlayer="Team--1";
		//currentData=InventoryManager.getChest("Team-1", getWorldObj(), inventorySize);
	}
	
	
	public void SetPlayer(String player)
	{
		currentPlayer=player;
		currentData=InventoryManager.getInstance().getChest(currentPlayer,inventorySize);
	}
	
	@LuaCallable( description="Setting current team inventory")
	public void setTeam(IComputerAccess computer,@Arg(type = LuaType.NUMBER,name="team") int team)
	{
		currentPlayer="Team-"+team;
		currentData=InventoryManager.getInstance().getChest(currentPlayer,inventorySize);
	}
	
	@LuaCallable( description="Setting current team inventory")
	public void setTeamByPlayer(IComputerAccess computer,@Arg(type = LuaType.STRING,name="player") String player)
	{
		currentPlayer="Team-"+TeamMod.instance.teamHandler.getPlayerTeam(player);
		currentData=InventoryManager.getInstance().getChest(currentPlayer,inventorySize);
	}
	@LuaCallable( description="Setting current player inventory")
	public void setPlayer(IComputerAccess computer,@Arg(type = LuaType.STRING,name="player") String player)
	{
		currentPlayer=player;
		currentData=InventoryManager.getInstance().getChest(currentPlayer,inventorySize);
	}
	
	public void onInventoryChanged(ChestInventory inv) {
		super.onInventoryChanged();
		for (IComputerAccess computer : computers) {
			computer.queueEvent(EVENT_INVENTORY_CHANGED, new Object[]{inv.PlayerName});
		}
	}


	public IInventory getCurrentInventory()
	{
		return currentData.inventory;
	}
	
	
	public int inventorySize=36;
	public String currentPlayer="Team--1";
	private VirtualChestData currentData;
	
	@Override
	public int getSizeInventory() {
		return currentData.inventory.getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return currentData.inventory.getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		return currentData.inventory.decrStackSize(i, j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return currentData.inventory.getStackInSlotOnClosing(i);
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		currentData.inventory.setInventorySlotContents(i, itemstack);
		
	}

	@Override
	public String getInvName() {
		return currentData.inventory.getInvName();
	}

	@Override
	public boolean isInvNameLocalized() {
		return currentData.inventory.isInvNameLocalized();
	}

	@Override
	public int getInventoryStackLimit() {
		return currentData.inventory.getInventoryStackLimit();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return currentData.inventory.isUseableByPlayer(entityplayer);
	}



	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return currentData.inventory.isItemValidForSlot(i, itemstack);
	}



	@LuaCallable(returnTypes={LuaType.TABLE},description="Gets inventory list")
	public Object getInventoryList(IComputerAccess computer) {
		HashMap<Double,String> pList=new HashMap<Double, String>();
		return InventoryManager.getInstance().getInventories();
	}

	@Override
	public void openChest() {

		
	}

	@Override
	public void closeChest() {
		
		
	}

	public static String TAG_INVENTORIES="Inventories";
	public static String TAG_INVENTORYNAME="InventoryName";
	public static String TAG_INVENTORY="Inventory";
	public static String TAG_CURRENT="CurrentInventory";
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		currentPlayer=par1nbtTagCompound.getString(TAG_CURRENT);
		//
	}


	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setString(TAG_CURRENT, currentPlayer);
	}


	@Override
	public void addComputer(IComputerAccess computer) {
		if (!computers.contains(computer)) {
			computers.add(computer);
		}
		
	}


	@Override
	public void removeComputer(IComputerAccess computer) {
		computers.remove(computer);
		
	}


	public IInventory GetInventory(String string) {
		VirtualChestData inv=InventoryManager.getInstance().getChest(string,inventorySize);
		inv.AddVirtualBlock(this);
		return inv.inventory;
	}

}
