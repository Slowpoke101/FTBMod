package com.feedthebeast.Network.Packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.feedthebeast.Handler.ClientTeamHandler;
import com.feedthebeast.Network.PacketME;
import com.feedthebeast.Network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketTeam extends PacketME
{
	String playerName;
	int teamID;

	public PacketTeam()
	{
		super(PacketTypeHandler.TEAM);
	}
	
	public PacketTeam(String playerName,int teamID)
	{
		super(PacketTypeHandler.TEAM);
		
		this.teamID = teamID;
		this.playerName = playerName;
	}
	
	@Override
	public void writeData(DataOutputStream data) throws IOException
	{
		data.writeUTF(playerName);
		data.writeInt(teamID);
	}

	@Override
	public void readData(DataInputStream data) throws IOException
	{
		this.playerName = data.readUTF();
		this.teamID = data.readInt();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void execute(INetworkManager manager, Player player)
	{
		ClientTeamHandler.teamCache.put(playerName, teamID);
	}

}
