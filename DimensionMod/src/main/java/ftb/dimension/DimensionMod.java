package ftb.dimension;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.command.CommandHandler;
import net.minecraft.item.Item;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ftb.dimension.core.CommandJoL;
import ftb.dimension.core.CommonProxy;
import ftb.dimension.core.DimensionFTB;
import ftb.dimension.core.WorldHandler;
import ftb.dimension.items.BlockPortalFTB;
import ftb.dimension.items.ItemKey;
import ftb.dimension.util.Utils;

@Mod(modid = Utils.modid, version = Utils.version, name = Utils.name)
public class DimensionMod 
{
	@Instance(Utils.modid)
	public static DimensionMod instance;
	
	@SidedProxy(clientSide = "ftb.dimension.client.core.ClientProxy", serverSide = "ftb.dimension.core.CommonProxy")
	public static CommonProxy proxy;
	
	public static Item key;
	public static int keyID;
	
	public static Block portalBlock;
	public static int portalBlockID;
	
	public HashMap<Integer, DimensionFTB> dimensions = new HashMap<Integer, DimensionFTB>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		keyID = config.getItem("key", 23456).getInt();
		portalBlockID = config.getBlock("portalblock", 2600).getInt();
		config.save();
		key = new ItemKey(keyID);
		portalBlock = new BlockPortalFTB(portalBlockID);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		LanguageRegistry.addName(key, "Portal Key");        
	}
	
	@EventHandler
	public void serverStarted(FMLServerStartedEvent event)
	{
		GameRegistry.registerPlayerTracker(new WorldHandler());
		if(DimensionManager.getWorlds().length < 4)
		{
			for(int i = 0; i < 16; i++)
			{
				DimensionManager.registerProviderType(2 + (i * 3), WorldProviderSurface.class, false);
				DimensionManager.registerDimension(2 + (i * 3), 2 + (i * 3));
				DimensionManager.registerProviderType(3 + (i * 3), WorldProviderHell.class, false);
				DimensionManager.registerDimension(3 + (i * 3), 3 + (i * 3));
				DimensionManager.registerProviderType(4 + (i * 3), WorldProviderEnd.class, false);
				DimensionManager.registerDimension(4 + (i * 3), 4 + (i * 3));
				dimensions.put(i, new DimensionFTB(2 + (i * 3), 3 + (i * 3), 4 + (i * 3)));
			}
		}
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{		
		CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
		commandManager.registerCommand(new CommandJoL());
	}
}