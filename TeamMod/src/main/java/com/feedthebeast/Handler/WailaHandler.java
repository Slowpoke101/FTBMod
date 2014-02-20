package com.feedthebeast.Handler;

import java.util.List;

import com.feedthebeast.TeamMod;
import com.feedthebeast.Library.TeamNames;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;

public class WailaHandler implements IWailaEntityProvider
{

	@Override
	public List<String> getWailaBody(Entity arg0, List<String> arg1, IWailaEntityAccessor arg2, IWailaConfigHandler arg3)
	{
		return arg1;
	}

	@Override
	public List<String> getWailaHead(Entity arg0, List<String> arg1, IWailaEntityAccessor arg2, IWailaConfigHandler arg3)
	{
		return arg1;
	}

	@Override
	public Entity getWailaOverride(IWailaEntityAccessor arg0, IWailaConfigHandler arg1)
	{
		return null;
	}

	@Override
	public List<String> getWailaTail(Entity arg0, List<String> arg1, IWailaEntityAccessor arg2, IWailaConfigHandler arg3)
	{
		if (arg0 instanceof EntityOtherPlayerMP)
		{
			EntityOtherPlayerMP player = (EntityOtherPlayerMP) arg0;
			if (ClientTeamHandler.teamCache.containsKey(player.username))
			{
				arg1.add(TeamNames.teamNames[ClientTeamHandler.teamCache.get(player.username)]);
			}
		}
		return arg1;
	}

	

}
