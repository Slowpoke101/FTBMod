package com.feedthebeast.VirtualChest.blocks.tile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import openperipheral.api.Arg;
import openperipheral.api.IAttachable;
import openperipheral.api.LuaCallable;
import openperipheral.api.LuaType;

import com.feedthebeast.VirtualChest.core.ChestInventory;
import com.google.common.collect.Lists;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.ILuaContext;
import dan200.computer.api.IPeripheral;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityVirtualInventory extends TileEntity implements IInventory,IAttachable{
	private static final String EVENT_INVENTORY_CHANGED = "inventory_changed";
	private List<IComputerAccess> computers = Lists.newArrayList();
	public TileEntityVirtualInventory()
	{
		currentPlayer="";
		currentInventory=new ChestInventory(inventorySize, this,"");
	}
	
	
	public void SetPlayer(String player)
	{
		currentPlayer=player;
		currentInventory=(ChestInventory) GetPlayer(player);
	}
	@LuaCallable( description="Setting current player inventory")
	public void setPlayer(IComputerAccess computer,@Arg(type = LuaType.STRING,name="player") String player)
	{
		currentPlayer=player;
		currentInventory=(ChestInventory) GetPlayer(player);
	}
	
	public void onInventoryChanged(ChestInventory inv) {
		super.onInventoryChanged();
		for (IComputerAccess computer : computers) {
			computer.queueEvent(EVENT_INVENTORY_CHANGED, new Object[]{inv.PlayerName});
		}
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
			IInventory inv=new ChestInventory(inventorySize,this,player);
			inventories.put(player, (ChestInventory) inv);
			return inv;
		}
	}
	public int inventorySize=36;
	public String currentPlayer="";
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
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return currentInventory.isItemValidForSlot(i, itemstack);
	}



	@LuaCallable(returnTypes={LuaType.TABLE},description="Gets player list")
	public Object getPlayersList(IComputerAccess computer) {
		HashMap<Double,String> pList=new HashMap<Double, String>();
		int i=0;
		for(String player:inventories.keySet())
		{
			pList.put((double)i++, player);
		}
		return pList;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	public static String TAG_INVENTORIES="Inventories";
	public static String TAG_INVENTORYNAME="InventoryName";
	public static String TAG_INVENTORY="Inventory";
	public static String TAG_CURRENT="CurrentInventory";
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		NBTTagList list=par1nbtTagCompound.getTagList(TAG_INVENTORIES);
		for (int i = 0; i < list.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)list.tagAt(i);
            String name=nbttagcompound1.getString(TAG_INVENTORYNAME);
            ChestInventory inv=new ChestInventory(inventorySize, this,name);
            inv.readFromNBT(nbttagcompound1.getCompoundTag(TAG_INVENTORY));
            inventories.put(name, inv);
        }
		currentPlayer=par1nbtTagCompound.getString(TAG_CURRENT);
		currentInventory=(ChestInventory) GetPlayer(currentPlayer);
	}


	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		NBTTagList nbttaglist = new NBTTagList();
		Set<Entry<String,ChestInventory>> entries=inventories.entrySet();
		for(String playerName:inventories.keySet())
        {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setString(TAG_INVENTORYNAME, playerName);
			ChestInventory inv=inventories.get(playerName);
			NBTTagCompound inventory=new NBTTagCompound();
			inv.writeToNBT(inventory);
			nbttagcompound1.setCompoundTag(TAG_INVENTORY, inventory);
			nbttaglist.appendTag(nbttagcompound1);
		}
		par1nbtTagCompound.setTag(TAG_INVENTORIES, nbttaglist);
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

}
