package com.feedthebeast.Handler;

import java.util.ArrayList;

import com.feedthebeast.TeamMod;
import com.feedthebeast.Network.PacketTypeHandler;
import com.feedthebeast.Network.Packets.PacketTeam;

import cpw.mods.fml.common.network.PacketDispatcher;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class TeamHandler
{
	Team[] teams;
	
	public TeamHandler()
	{
		teams = new Team[16];
		for (int i=0;i<16;i++)
		{
			teams[i] = new Team(i);
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		for (int i=0;i<16;i++)
		{
			Team t = teams[i];
			NBTTagCompound teamTag = new NBTTagCompound();
			t.writeToNBT(teamTag);
			nbt.setCompoundTag("teamTag_"+i, teamTag);
		}
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		for (int i=0;i<16;i++)
		{
			NBTTagCompound teamTag = nbt.getCompoundTag("teamTag_"+i);
			teams[i].readFromNBT(teamTag);
		}
	}
	
	public void setPlayerTeam(String playerName,int id)
	{
		if (id < 0 || id > 15)
		{
			System.out.println("Wrong Team ID ("+id+")");
			return;
		}
		
		if (id==getPlayerTeam(playerName))
		{
			return;
		}
		
		if (TeamMod.instance.teamHandler.getPlayerTeam(playerName)!=-1)
		{
			TeamMod.instance.teamHandler.getTeam(TeamMod.instance.teamHandler.getPlayerTeam(playerName)).removeMember(playerName);
		}
		
		teams[id].addMember(playerName);
		
		EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(playerName);
		if (player != null)
		{
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketTeam(player.username, TeamMod.instance.teamHandler.getPlayerTeam(playerName))), player.worldObj.provider.dimensionId);
		}
	}
	
	public int getPlayerTeam(String playerName)
	{
		for (Team t:teams)
		{
			if (t.containsPlayer(playerName))
			{
				return t.teamID;
			}
		}
		return -1;
	}

	public Team getTeam(int id)
	{
		return teams[id];
	}
	
	public ArrayList<String> getPlayersForTeam(int teamID)
	{
		if (teamID < 0 || teamID > 15)
		{
			System.out.println("Wrong Team ID ("+teamID+")");
			return new ArrayList<String>();
		}
		return teams[teamID].getMembers();
	}
	
	public ArrayList<Double> getTeamsWithPlayers()
	{
		ArrayList<Double> teamsWithPlayers = new ArrayList<Double>();
		
		for (Team t:teams)
		{
			if (t.hasMembers())
			{
				teamsWithPlayers.add((double) t.teamID);
			}
		}
		return teamsWithPlayers;
	}
}
