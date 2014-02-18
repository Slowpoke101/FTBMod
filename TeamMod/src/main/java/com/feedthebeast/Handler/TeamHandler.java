package com.feedthebeast.Handler;

import net.minecraft.nbt.NBTTagCompound;

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
		teams[id].addMember(playerName);
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
}
