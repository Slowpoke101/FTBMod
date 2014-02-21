package com.feedthebeast.virtualchest.blocks;

import com.feedthebeast.TeamMod;
import com.feedthebeast.virtualchest.blocks.tile.TileEntityVirtualChest;
import com.feedthebeast.virtualchest.blocks.tile.TileEntityVirtualInventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVirtualInventoryChest extends BlockVirtualInventory {

	public BlockVirtualInventoryChest(int par1) {
		super(par1);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
	}


	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityVirtualChest();
	}


	public boolean isOpaqueCube()
	{
		return false;
	}
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	public int getRenderType()
	{
		return 22;
	}
	
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
        	TileEntityVirtualInventory inv= (TileEntityVirtualInventory)par1World.getBlockTileEntity(par2, par3, par4);
        	//inv.SetPlayer(par5EntityPlayer.username);
            IInventory iinventory = inv.GetInventory("Team-"+TeamMod.instance.teamHandler.getPlayerTeam(par5EntityPlayer.username));

            if (this.canGetInventory(par1World, par2, par3, par4))
            {
                par5EntityPlayer.displayGUIChest(iinventory);
            }

            return true;
        }
    }
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		
	}
	 @SideOnly(Side.CLIENT)

	    /**
	     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	     * is the only chance you get to register icons.
	     */
	    public void registerIcons(IconRegister par1IconRegister)
	    {
	        this.blockIcon = par1IconRegister.registerIcon("planks_oak");
	    }

}
