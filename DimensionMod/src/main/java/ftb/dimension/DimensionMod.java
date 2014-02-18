package ftb.dimension;

import java.util.HashMap;

import net.minecraft.command.CommandHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
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
import ftb.dimension.core.WorldHandler;
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
	
	public HashMap<String, Integer> dimensions = new HashMap<String, Integer>();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		keyID = config.getItem("key", 23456).getInt();
		config.save();
		key = new ItemKey(keyID);
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
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{		
		CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
		commandManager.registerCommand(new CommandJoL());
	}
}