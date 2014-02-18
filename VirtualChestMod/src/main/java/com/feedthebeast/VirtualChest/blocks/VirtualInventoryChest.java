package com.feedthebeast.VirtualChest.blocks;

import com.feedthebeast.VirtualChest.blocks.tile.TileEntityVirtualChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class VirtualInventoryChest extends VirtualInventoryBlock {

	public VirtualInventoryChest(int par1) {
		super(par1);
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
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
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
