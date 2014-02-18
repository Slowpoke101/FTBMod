package com.feedthebeast;

import java.io.File;
import java.io.IOException;

import com.feedthebeast.Commands.CommandTeam;
import com.feedthebeast.Handler.TeamEventHandler;
import com.feedthebeast.Handler.TeamHandler;
import com.feedthebeast.Library.Reference;
import com.feedthebeast.Network.PacketHandler;

import net.minecraft.block.Block;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { Reference.CHANNEL_NAME }, packetHandler = PacketHandler.class)
public class TeamMod
{
	public TeamHandler teamHandler;

	@Instance(Reference.MOD_ID)
	public static TeamMod instance;
	
	public static Block teamPeripheral;
	
	File teamFile;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new TeamEventHandler());
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		teamHandler = new TeamHandler();
		NBTTagCompound nbt = null;

		teamFile = new File("teams.dat");
		try
		{
			nbt = CompressedStreamTools.read(teamFile);
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find Teams File");
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
				System.out.println("Error reading Team Data from Disk");
				e.printStackTrace();
			}
		}
		teamHandler.readFromNBT(nbt);
		
		MinecraftServer server = MinecraftServer.getServer();

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
}
