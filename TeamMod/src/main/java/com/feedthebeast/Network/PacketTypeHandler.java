package com.feedthebeast.Network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import com.feedthebeast.Library.Reference;
import com.feedthebeast.Network.Packets.PacketTeam;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public enum PacketTypeHandler
{
	TEAM(PacketTeam.class);
	

	private Class<? extends PacketME> claz;

	PacketTypeHandler(Class<? extends PacketME> clazz)
	{
		this.claz = clazz;
	}
	
	public static PacketME buildPacket(byte[] data)
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int identifier = bis.read();
		DataInputStream dis = new DataInputStream(bis);
		
		PacketME packet = null;
		
		try {
			packet = values()[identifier].claz.newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
		
		packet.readPopulate(dis);
		
		return packet;
	}
	
	public static PacketME buildPacket(PacketTypeHandler type) {

        PacketME packet = null;

        try {
            packet = values()[type.ordinal()].claz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return packet;
    }

    public static Packet populatePacket(PacketME packetEE) {

        byte[] data = packetEE.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = Reference.CHANNEL_NAME;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = false;

        return packet250;
    }
}