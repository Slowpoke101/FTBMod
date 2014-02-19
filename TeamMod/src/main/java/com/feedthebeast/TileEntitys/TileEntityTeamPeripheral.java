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
				TeamMod.instance.teamHandler.setPlayerTeam((String) arguments[0], (int)(double)arguments[1]);
				return null;
			case 2: // getPlayersForTeam
				if (arguments.length < 1 || !(arguments[0] instanceof Double))
				{
					throw new Exception("getPlayersForTeam TEAMID");
				}
				return TeamMod.instance.teamHandler.getPlayersForTeam( (int)(double) arguments[0]).toArray(new String[]{});
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
