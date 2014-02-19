package com.feedthebeast.Handler;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Team
{
	int teamID;
	private ArrayList<String> members;
	
	public Team(int teamID)
	{
		this.teamID = teamID;
		
		members = new ArrayList<String>();
	}
	
	public void readFromNBT(NBTTagCompound nbt)
	{
		NBTTagList list = nbt.getTagList("members");
		for (int i=0;i<list.tagCount();i++)
		{
			members.add(((NBTTagCompound)list.tagAt(i)).getString("playerName"));
		}
	}
	
	public boolean hasMembers()
	{
		return !members.isEmpty();
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		NBTTagList list = new NBTTagList();
		for (String s:members)
		{
			NBTTagCompound playerTag = new NBTTagCompound();
			playerTag.setString("playerName", s);
			list.appendTag(playerTag);
		}
		nbt.setTag("members", list);
	}
	
	public void addMember(String playerName)
	{
		this.members.add(playerName);
	}
	
	public void removeMember(String playerName)
	{
		for (int i=0;i<members.size();i++)
		{
			if (members.get(i).equals(playerName))
			{
				members.remove(i);
				return;
			}
		}
	}

	public boolean containsPlayer(String playerName)
	{
		for (String s:members)
		{
			if (s.equals(playerName))
			{
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> getMembers()
	{
		return (ArrayList<String>) members.clone();
	}
}
