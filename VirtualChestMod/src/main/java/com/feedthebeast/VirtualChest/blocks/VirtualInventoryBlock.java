package com.feedthebeast.VirtualChest.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class VirtualInventoryBlock extends Block {

	public VirtualInventoryBlock(int par1) {
		super(par1, Material.iron);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

}
