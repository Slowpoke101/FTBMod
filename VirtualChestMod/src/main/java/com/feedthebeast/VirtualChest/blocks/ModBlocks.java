package com.feedthebeast.VirtualChest.blocks;

import com.feedthebeast.VirtualChest.Configs;
import com.feedthebeast.VirtualChest.blocks.tile.TileEntityVirtualChest;
import com.feedthebeast.VirtualChest.blocks.tile.TileEntityVirtualInventory;
import com.feedthebeast.VirtualChest.blocks.tile.TileEntityVirtualTank;
import com.feedthebeast.VirtualChest.lib.LibBlockNames;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;

public class ModBlocks {

	public static Block VirtualStorage;
	public static Block VirtualChest;
	public static Block VirtualTank;
	public static void InitBlocks()
	{
		VirtualStorage=new BlockVirtualInventory(Configs.VirtualStoreId).setUnlocalizedName(LibBlockNames.VIRTUAL_STORAGE);
		VirtualChest=new BlockVirtualInventoryChest(Configs.VirtualChestId).setUnlocalizedName(LibBlockNames.VIRTUAL_CHEST);
		VirtualTank=new BlockVirtualTank(Configs.VirtualTankId).setUnlocalizedName(LibBlockNames.VIRTUAL_TANK);


		RegisterBlocks();
		InitTileEntities();
	}

	public static void RegisterBlocks()
	{
		GameRegistry.registerBlock(VirtualChest, LibBlockNames.VIRTUAL_CHEST);
		GameRegistry.registerBlock(VirtualStorage, LibBlockNames.VIRTUAL_STORAGE);
		GameRegistry.registerBlock(VirtualTank, LibBlockNames.VIRTUAL_TANK);
	}

	public static void InitTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityVirtualInventory.class, LibBlockNames.VIRTUAL_STORAGE);
		GameRegistry.registerTileEntity(TileEntityVirtualChest.class, LibBlockNames.VIRTUAL_CHEST);
		GameRegistry.registerTileEntity(TileEntityVirtualTank.class, LibBlockNames.VIRTUAL_TANK);
	}
}
