package com.feedthebeast.VirtualChest.blocks;

import com.feedthebeast.VirtualChest.Configs;
import com.feedthebeast.VirtualChest.blocks.tile.TileEntityVirtualInventory;
import com.feedthebeast.VirtualChest.lib.LibBlockNames;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;

public class ModBlocks {

	public static Block VirtualStorage;
	public static Block VirtualChest;
	public static void InitBlocks()
	{
		VirtualStorage=new VirtualInventoryBlock(Configs.VirtualStoreId).setUnlocalizedName(LibBlockNames.VIRTUAL_STORAGE);
		VirtualChest=new VirtualInventoryChest(Configs.VirtualChestId).setUnlocalizedName(LibBlockNames.VIRTUAL_CHEST);
		
		
		
		RegisterBlocks();
		InitTileEntities();
	}
	
	public static void RegisterBlocks()
	{
		GameRegistry.registerBlock(VirtualChest, LibBlockNames.VIRTUAL_CHEST);
		GameRegistry.registerBlock(VirtualStorage, LibBlockNames.VIRTUAL_STORAGE);
	}
	
	public static void InitTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityVirtualInventory.class, LibBlockNames.VIRTUAL_CHEST);
	}
}
