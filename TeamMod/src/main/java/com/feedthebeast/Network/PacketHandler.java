package com.feedthebeast.Network;



import com.feedthebeast.Library.Reference;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player playerEntity)
	{
		if (packet.channel.equals(Reference.CHANNEL_NAME))
		{
			PacketME packetME = PacketTypeHandler.buildPacket(packet.data);
			packetME.execute(manager, playerEntity);
		}

	}

}