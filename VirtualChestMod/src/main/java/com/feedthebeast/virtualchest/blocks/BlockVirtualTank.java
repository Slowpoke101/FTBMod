package com.feedthebeast.virtualchest.blocks;

import com.feedthebeast.virtualchest.blocks.tile.TileEntityVirtualTank;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVirtualTank extends BlockContainer {

	public BlockVirtualTank(int par1) {
		super(par1, Material.iron);
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityVirtualTank();
	}

}
