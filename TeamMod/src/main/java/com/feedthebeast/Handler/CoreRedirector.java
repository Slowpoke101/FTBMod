package com.feedthebeast.Handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemDye;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CoreRedirector
{
	public static int getPlayerColor(String playerName)
	{
		FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
		//fontRenderer.drawString("Test", -fontRenderer.getStringWidth("Test") / 2, -10, 553648127);
		if (ClientTeamHandler.teamCache.containsKey(playerName))
		{
			int team = ClientTeamHandler.teamCache.get(playerName);
			
			if (team==-1)
			{
				return -1;
			}
			else
			{
				return ItemDye.dyeColors[team];
			}
		}
		else
		{
			return -1;
		}
		
	}
	
	public static void drawPlayerString(String string,int posX,int posY,int color)
	{
		
	}
}
