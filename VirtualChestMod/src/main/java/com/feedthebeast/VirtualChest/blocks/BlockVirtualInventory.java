package com.feedthebeast.virtualchest.blocks;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import com.feedthebeast.TeamMod;
import com.feedthebeast.Handler.TeamHandler;
import com.feedthebeast.virtualchest.blocks.tile.TileEntityVirtualInventory;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class BlockVirtualInventory extends BlockContainer {

	public BlockVirtualInventory(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	
	
	public boolean canGetInventory(World par1World, int par2, int par3, int par4)
    {
        Object object = (TileEntityVirtualInventory)par1World.getBlockTileEntity(par2, par3, par4);

        if (object == null)
        {
            return false;
        }
        else if (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
        {
            return false;
        }
        else if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 - 1, par3 + 1, par4, DOWN) ))
        {
            return false;
        }
        else if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 + 1, par3 + 1, par4, DOWN) ))
        {
            return false;
        }
        else if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 - 1, DOWN)))
        {
            return false;
        }
        else if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 + 1, DOWN) ))
        {
            return false;
        }
        else
        {
            

            return true;
        }
    }
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityVirtualInventory();
	}

}
