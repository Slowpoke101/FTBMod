package com.feedthebeast.VirtualChest.client.core;

import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;

import com.feedthebeast.VirtualChest.blocks.tile.TileEntityVirtualChest;
import com.feedthebeast.VirtualChest.blocks.tile.TileEntityVirtualInventory;
import com.feedthebeast.VirtualChest.client.render.TileEntityVirtualRenderer;
import com.feedthebeast.VirtualChest.core.CommonProxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{

	@Override
	public void RegisterRenderers() {
		super.RegisterRenderers();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVirtualChest.class, new TileEntityVirtualRenderer());
	}
	

}
