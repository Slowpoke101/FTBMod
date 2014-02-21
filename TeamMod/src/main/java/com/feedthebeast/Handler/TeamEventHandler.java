package com.feedthebeast.Handler;

import com.feedthebeast.TeamMod;
import com.feedthebeast.Network.PacketTypeHandler;
import com.feedthebeast.Network.Packets.PacketTeam;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class TeamEventHandler
{
	@ForgeSubscribe
	public void entityJoining(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayerMP && !event.entity.worldObj.isRemote)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.entity;
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketTeam(player.username,TeamMod.instance.teamHandler.getPlayerTeam(player.username))), player.worldObj.provider.dimensionId);
		}
	}
}
