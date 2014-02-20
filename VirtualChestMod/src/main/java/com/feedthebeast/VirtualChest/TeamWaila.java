package com.feedthebeast.VirtualChest;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.feedthebeast.TeamMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class TeamWaila implements IWailaEntityProvider, IWailaConfigHandler {

	@Override
	public boolean getConfig(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getConfig(String arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<String, String> getConfigKeys(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getModuleNames() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<String> getWailaBody(Entity arg0, List<String> arg1,
			IWailaEntityAccessor arg2, IWailaConfigHandler arg3) {
		
		if(arg0 instanceof EntityPlayer)
		{
				EntityPlayer player=(EntityPlayer)arg0;
				int teamid=TeamMod.instance.teamHandler.getPlayerTeam(player.username);
				arg1.add(StatCollector.translateToLocal("ftb.teamname."+teamid));
		}
		return arg1;
	}

	@Override
	public List<String> getWailaHead(Entity arg0, List<String> arg1,
			IWailaEntityAccessor arg2, IWailaConfigHandler arg3) {
		// TODO Auto-generated method stub
		return arg1;
	}



	@Override
	public Entity getWailaOverride(IWailaEntityAccessor arg0,
			IWailaConfigHandler arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getWailaTail(Entity arg0, List<String> arg1,
			IWailaEntityAccessor arg2, IWailaConfigHandler arg3) {
		// TODO Auto-generated method stub
		return arg1;
	}
	
	
	public static void callbackRegister(IWailaRegistrar registar)
	{
		registar.registerBodyProvider(new TeamWaila(), EntityPlayerMP.class);
	}

}
