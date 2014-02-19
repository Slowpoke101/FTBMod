package com.feedthebeast.VirtualChest.blocks.tile;

import java.util.Iterator;
import java.util.List;

import com.feedthebeast.VirtualChest.blocks.BlockVirtualInventory;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityVirtualChest extends TileEntityVirtualInventory implements IInventory {
	
	/** The current angle of the lid (between 0 and 1) */
    public float lidAngle;

    /** The angle of the lid last tick */
    public float prevLidAngle;

    /** The number of players currently using this chest */
    public int numUsingPlayers;

    /** Server sync counter (once per 20 ticks) */
    private int ticksSinceSync;

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		super.openChest();
		 if (this.numUsingPlayers < 0)
	        {
	            this.numUsingPlayers = 0;
	        }

	        ++this.numUsingPlayers;
	        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.numUsingPlayers);
	        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID);
	        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
	}

	@Override
	public void closeChest() {
		super.closeChest();
		 if (this.getBlockType() != null && this.getBlockType() instanceof BlockVirtualInventory)
	        {
	            --this.numUsingPlayers;
	            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.numUsingPlayers);
	            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID);
	            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
	        }
	}

	@Override
	public void updateEntity() {
		 super.updateEntity();
	        ++this.ticksSinceSync;
	        float f;

	        if (!this.worldObj.isRemote && this.numUsingPlayers != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
	        {
	            this.numUsingPlayers = 0;
	            f = 5.0F;
	            List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getAABBPool().getAABB((double)((float)this.xCoord - f), (double)((float)this.yCoord - f), (double)((float)this.zCoord - f), (double)((float)(this.xCoord + 1) + f), (double)((float)(this.yCoord + 1) + f), (double)((float)(this.zCoord + 1) + f)));
	            Iterator iterator = list.iterator();

	            while (iterator.hasNext())
	            {
	                EntityPlayer entityplayer = (EntityPlayer)iterator.next();

	                if (entityplayer.openContainer instanceof ContainerChest)
	                {
	                    IInventory iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();

	                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).isPartOfLargeChest(this))
	                    {
	                        ++this.numUsingPlayers;
	                    }
	                }
	            }
	        }

	        this.prevLidAngle = this.lidAngle;
	        f = 0.1F;
	        double d0;

	        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F )
	        {
	            double d1 = (double)this.xCoord + 0.5D;
	            d0 = (double)this.zCoord + 0.5D;

	            this.worldObj.playSoundEffect(d1, (double)this.yCoord + 0.5D, d0, "random.chestopen", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
	        }

	        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
	        {
	            float f1 = this.lidAngle;

	            if (this.numUsingPlayers > 0)
	            {
	                this.lidAngle += f;
	            }
	            else
	            {
	                this.lidAngle -= f;
	            }

	            if (this.lidAngle > 1.0F)
	            {
	                this.lidAngle = 1.0F;
	            }

	            float f2 = 0.5F;

	            if (this.lidAngle < f2 && f1 >= f2 )
	            {
	                d0 = (double)this.xCoord + 0.5D;
	                double d2 = (double)this.zCoord + 0.5D;

	                this.worldObj.playSoundEffect(d0, (double)this.yCoord + 0.5D, d2, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
	            }

	            if (this.lidAngle < 0.0F)
	            {
	                this.lidAngle = 0.0F;
	            }
	        }
	}

	@Override
	public boolean receiveClientEvent(int par1, int par2) {
		if (par1 == 1)
        {
            this.numUsingPlayers = par2;
            return true;
        }
        else
        {
            return super.receiveClientEvent(par1, par2);
        }
	}

}
