package com.feedthebeast;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.feedthebeast.Blocks.BlockTeamPeripheral;
import com.feedthebeast.Commands.CommandTeam;
import com.feedthebeast.Handler.ConfigurationHandler;
import com.feedthebeast.Handler.TeamEventHandler;
import com.feedthebeast.Handler.TeamHandler;
import com.feedthebeast.Handler.WailaHandler;
import com.feedthebeast.Library.BlockIds;
import com.feedthebeast.Library.Reference;
import com.feedthebeast.Network.PacketHandler;
import com.feedthebeast.TileEntitys.TileEntityTeamPeripheral;

import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { Reference.CHANNEL_NAME }, packetHandler = PacketHandler.class)
public class TeamMod
{
	public TeamHandler teamHandler;

	@Instance(Reference.MOD_ID)
	public static TeamMod instance;

	public static BlockTeamPeripheral teamPeripheral;

	File teamFile;

	Logger modLog;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		modLog = event.getModLog();
		MinecraftForge.EVENT_BUS.register(new TeamEventHandler());

		ConfigurationHandler.init(event);

		teamPeripheral = new BlockTeamPeripheral(BlockIds.teamPeripheral_ID);

		GameRegistry.registerTileEntity(TileEntityTeamPeripheral.class, "teamPeripheral");
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		if (Loader.isModLoaded("Waila"))
		{
			FMLInterModComms.sendMessage("Waila", "register", "com.feedthebeast.TeamMod.callbackRegister");
		}
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		teamHandler = new TeamHandler();
		NBTTagCompound nbt = null;
		MinecraftServer server = event.getServer();

		teamFile = new File(DimensionManager.getCurrentSaveRootDirectory(), "teams.dat");
		if (!teamFile.exists())
		{
			modLog.log(Level.INFO, "Creating Teams File");
			try
			{
				teamFile.createNewFile();
				CompressedStreamTools.write(new NBTTagCompound(), teamFile);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			nbt = CompressedStreamTools.read(teamFile);
		}
		catch (IOException e)
		{
			modLog.log(Level.WARNING, "Couldn't find Teams File");
			e.printStackTrace();
		}

		if (nbt == null)
		{
			try
			{
				CompressedStreamTools.write(new NBTTagCompound(), teamFile);
				nbt = CompressedStreamTools.read(teamFile);
			}
			catch (IOException e)
			{
				modLog.log(Level.WARNING,"Error reading Team Data from Disk");
				e.printStackTrace();
			}
		}
		teamHandler.readFromNBT(nbt);

		ICommandManager command = server.getCommandManager();
		ServerCommandManager servercommand = ((ServerCommandManager) command);

		servercommand.registerCommand(new CommandTeam());
	}

	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
		NBTTagCompound toWrite = new NBTTagCompound();
		teamHandler.writeToNBT(toWrite);

		try
		{
			CompressedStreamTools.write(toWrite, teamFile);
		}
		catch (IOException e)
		{
			System.out.println("Error writing Team Data to Disk");
			e.printStackTrace();
		}
	}

	@SideOnly(Side.CLIENT)
	public static void callbackRegister(IWailaRegistrar registery)
	{
		registery.registerTailProvider(new WailaHandler(), EntityOtherPlayerMP.class);
	}
}
