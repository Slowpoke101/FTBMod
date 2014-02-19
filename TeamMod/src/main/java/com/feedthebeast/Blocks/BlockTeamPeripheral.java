package com.feedthebeast.Blocks;

import com.feedthebeast.TileEntitys.TileEntityTeamPeripheral;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTeamPeripheral extends BlockContainer
{

	public BlockTeamPeripheral(int blockID)
	{
		super(blockID,Material.rock);
		this.setUnlocalizedName("teamPeripheral");
		this.setCreativeTab(CreativeTabs.tabRedstone);
		
		GameRegistry.registerBlock(this, "teamPeripheral");
		LanguageRegistry.addName(this, "Team Peripheral");
		
	}

	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return new TileEntityTeamPeripheral();
	}

}
