package com.feedthebeast.VirtualChest.core;

import com.feedthebeast.VirtualChest.blocks.tile.TileEntityVirtualInventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ChestInventory implements IInventory {
	private ItemStack[] chestContents;
	public int size;
	public TileEntity parent;
	public ChestInventory(int size,TileEntity parent)
	{
		this.size=size;
		this.parent=parent;
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
                parent.onInventoryChanged();
                return itemstack;
            }
            else
            {
                itemstack = this.chestContents[par1].splitStack(par2);

                if (this.chestContents[par1].stackSize == 0)
                {
                    this.chestContents[par1] = null;
                }

                parent.onInventoryChanged();
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

	        parent.onInventoryChanged();

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
		((TileEntityVirtualInventory)parent).openChest();
	}

	@Override
	public void closeChest() {
		((TileEntityVirtualInventory)parent).closeChest();
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
