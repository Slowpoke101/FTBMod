package com.feedthebeast.virtualchest.client.core;

import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;

import com.feedthebeast.virtualchest.blocks.tile.TileEntityVirtualChest;
import com.feedthebeast.virtualchest.blocks.tile.TileEntityVirtualInventory;
import com.feedthebeast.virtualchest.client.render.TileEntityVirtualRenderer;
import com.feedthebeast.virtualchest.core.CommonProxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void RegisterRenderers() {
		super.RegisterRenderers();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVirtualChest.class, new TileEntityVirtualRenderer());
	}
	

}
