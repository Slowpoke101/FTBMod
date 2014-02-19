package com.feedthebeast.TileEntitys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.feedthebeast.TeamMod;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.ILuaContext;
import dan200.computer.api.IPeripheral;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTeamPeripheral extends TileEntity implements IPeripheral
{
	HashSet<IComputerAccess> attachedComputers = new HashSet<IComputerAccess>();
	@Override
	public String getType()
	{
		return "teamPeripheral";
	}

	@Override
	public String[] getMethodNames()
	{
		return new String[] { "getTeamForPlayer", "setTeamForPlayer", "getPlayersForTeam", "getTeamsWithPlayers" };
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws Exception
	{
		Double teamID;
		switch (method)
		{
			case 0: // getTeamForPlayer
				if (arguments.length < 1 || !(arguments[0] instanceof String))
				{
					throw new Exception("getTeamForPlayer PLAYERNAME");
				}
				return new Object[] { TeamMod.instance.teamHandler.getPlayerTeam((String) arguments[0]) };
			case 1: // setTeamForPlayer
				if (arguments.length < 2 || !(arguments[0] instanceof String) || !(arguments[1] instanceof Double))
				{
					throw new Exception("setTeamForPlayer PLAYERNAME TEAMID");
				}
				teamID = (Double) arguments[1];
				TeamMod.instance.teamHandler.setPlayerTeam((String) arguments[0], (int)((double)teamID));
				return null;
			case 2: // getPlayersForTeam
				if (arguments.length < 1 || !(arguments[0] instanceof Double))
				{
					throw new Exception("getPlayersForTeam TEAMID");
				}
				teamID = (Double) arguments[1];
				return new Object[]{generatePlayerMap(TeamMod.instance.teamHandler.getPlayersForTeam( (int)((double)teamID)).toArray(new String[]{}))};
			case 3: // getTeamsWithPlayers
				return new Object[]{generateTeamMap(TeamMod.instance.teamHandler.getTeamsWithPlayers().toArray(new Double[]{}))};
		}
		return null;
	}
	
	private Map<Double,String> generatePlayerMap(String[] array)
	{
		HashMap<Double,String> map = new HashMap<Double,String>();
		double counter=1.0;
		for (String s:array)
		{
			map.put(counter, s);
			counter+=1.0;
		}
		
		return map;
	}
	
	private Map<Double,Double> generateTeamMap(Double[] array)
	{
		HashMap<Double,Double> map = new HashMap<Double,Double>();
		double counter=1.0;
		for (Double d:array)
		{
			map.put(counter, d);
			counter+=1.0;
		}
		
		return map;
	}

	@Override
	public boolean canAttachToSide(int side)
	{
		return true;
	}

	@Override
	public void attach(IComputerAccess computer)
	{
		attachedComputers.add(computer);
	}

	@Override
	public void detach(IComputerAccess computer)
	{
		attachedComputers.remove(computer);
	}

}
