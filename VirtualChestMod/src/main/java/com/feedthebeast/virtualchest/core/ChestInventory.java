package com.feedthebeast.virtualchest.core;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ChestInventory implements IInventory {
	private ItemStack[] chestContents;
	public int size;
	public VirtualChestData parent;
	public String PlayerName;
	public ChestInventory(int size,VirtualChestData parent,String Name)
	{
		this.size=size;
		this.parent=(VirtualChestData) parent;
		this.PlayerName=Name;
		chestContents=new ItemStack[size];
	}
	
	@Override
	public int getSizeInventory() {
		return size;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.chestContents[i];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.chestContents[par1] != null)
        {
            ItemStack itemstack;

            if (this.chestContents[par1].stackSize <= par2)
            {
                itemstack = this.chestContents[par1];
                this.chestContents[par1] = null;
                parent.onInventoryChanged(this);
                return itemstack;
            }
            else
            {
                itemstack = this.chestContents[par1].splitStack(par2);

                if (this.chestContents[par1].stackSize == 0)
                {
                    this.chestContents[par1] = null;
                }

                parent.onInventoryChanged(this);
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.chestContents[i] != null)
        {
            ItemStack itemstack = this.chestContents[i];
            this.chestContents[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		 this.chestContents[par1] = par2ItemStack;

	        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
	        {
	            par2ItemStack.stackSize = this.getInventoryStackLimit();
	        }

	        parent.onInventoryChanged(this);

	}

	@Override
	public String getInvName() {
		return "container.chest";
	}

	@Override
	public boolean isInvNameLocalized() {
		 return false;
	}

	@Override
	public int getInventoryStackLimit() {
		 return 64;
	}

	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {
		((VirtualChestData)parent).openChest();
	}

	@Override
	public void closeChest() {
		((VirtualChestData)parent).closeChest();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Items");
        this.chestContents = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.chestContents.length)
            {
                this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.PlayerName=par1nbtTagCompound.getString("PlayerName");
	}


	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.chestContents.length; ++i)
        {
            if (this.chestContents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.chestContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1nbtTagCompound.setTag("Items", nbttaglist);
        par1nbtTagCompound.setString("PlayerName",this.PlayerName);
	}
}
