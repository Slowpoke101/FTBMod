package uk.co.andrewleedham.FTBMod;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import uk.co.andrewleedham.FTBMod.Entity.BlockPedestal;
import uk.co.andrewleedham.FTBMod.Entity.ItemRendererPedestal;
import uk.co.andrewleedham.FTBMod.Entity.TileEntityPedestal;
import uk.co.andrewleedham.FTBMod.Entity.TileEntityPedestalRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "ftbmod", name="FTB Mod", version="0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class FTBMod 
{
	public static CreativeTabs tabFTB = new CreativeTabs("tabFTB"){
		public ItemStack getIconItemStack(){
			return new ItemStack(Item.appleRed, 1, 0);
		}
	};
	
	public static Block pedestal = new BlockPedestal(1024).setCreativeTab(tabFTB);
	
	@Instance("ftbmod")
	public FTBMod instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabFTB", "Feed The Beast");
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TileEntityPedestalRenderer());
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "tileEntityPedestal");
		MinecraftForgeClient.registerItemRenderer(pedestal.blockID, new ItemRendererPedestal());
		GameRegistry.registerBlock(pedestal, "ftbPedestal");
		LanguageRegistry.addName(pedestal, "Pedestal");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
