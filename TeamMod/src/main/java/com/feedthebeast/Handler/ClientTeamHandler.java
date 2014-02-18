package com.feedthebeast.Handler;

import java.util.HashMap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientTeamHandler
{
	public static HashMap<String,Integer> teamCache = new HashMap<String,Integer>();
}
