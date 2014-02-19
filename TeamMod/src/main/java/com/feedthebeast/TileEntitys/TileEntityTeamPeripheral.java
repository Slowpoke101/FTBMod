package com.feedthebeast.TileEntitys;

import com.feedthebeast.TeamMod;

import dan200.computer.api.IComputerAccess;
import dan200.computer.api.ILuaContext;
import dan200.computer.api.IPeripheral;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTeamPeripheral extends TileEntity implements IPeripheral
{

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
				Double teamID=(Double)arguments[1];
				
				TeamMod.instance.teamHandler.setPlayerTeam((String) arguments[0], (int)(double)teamID);
				return null;
			case 2: // getPlayersForTeam
				if (arguments.length < 1 || !(arguments[0] instanceof Double))
				{
					throw new Exception("getPlayersForTeam TEAMID");
				}
				Double teamID2=(Double)arguments[1];
				return TeamMod.instance.teamHandler.getPlayersForTeam( (int)(double) teamID2).toArray(new String[]{});
			case 3: // getTeamsWithPlayers
				return TeamMod.instance.teamHandler.getTeamsWithPlayers().toArray(new Double[]{});
		}
		return null;
	}

	@Override
	public boolean canAttachToSide(int side)
	{
		return true;
	}

	@Override
	public void attach(IComputerAccess computer)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void detach(IComputerAccess computer)
	{
		// TODO Auto-generated method stub

	}

}
