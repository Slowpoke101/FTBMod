package com.feedthebeast.Handler;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.feedthebeast.TeamMod;
import com.feedthebeast.Library.TeamNames;
import com.feedthebeast.Network.PacketTypeHandler;
import com.feedthebeast.Network.Packets.PacketTeam;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent.Save;

public class TeamEventHandler
{
	Color rectColor = new Color(0, 0, 0, 200);
	Color rectColorWhite = new Color(255, 255, 255, 200);

	@ForgeSubscribe
	public void entityJoining(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayerMP && !event.entity.worldObj.isRemote)
		{
			EntityPlayerMP player = (EntityPlayerMP) event.entity;
			PacketDispatcher.sendPacketToAllInDimension(PacketTypeHandler.populatePacket(new PacketTeam(player.username, TeamMod.instance.teamHandler.getPlayerTeam(player.username))), player.worldObj.provider.dimensionId);
		}
	}

	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void eventHandler(RenderGameOverlayEvent event)
	{
		if (event.isCancelable() || event.type != event.type.HOTBAR)
		{
			return;
		}
		if (ClientTeamHandler.teamCache.containsKey(Minecraft.getMinecraft().thePlayer.username))
		{
			GL11.glDisable(GL11.GL_LIGHTING);

			int teamID = ClientTeamHandler.teamCache.get(Minecraft.getMinecraft().thePlayer.username);
			if (teamID != -1)
			{
				String toRender = TeamNames.teamNames[teamID];
				FontRenderer f = Minecraft.getMinecraft().fontRenderer;

				if (teamID == 0)
				{
					Gui.drawRect(0, 0, f.getStringWidth(toRender) + 1, f.FONT_HEIGHT + 1, rectColorWhite.getRGB());
				}
				else
				{
					Gui.drawRect(0, 0, f.getStringWidth(toRender) + 1, f.FONT_HEIGHT + 1, rectColor.getRGB());
				}

				f.drawString(toRender, 1, 1, ItemDye.dyeColors[teamID]);
			}
		}
	}
}
