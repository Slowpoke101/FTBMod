package ftb.dimension.core;

import com.feedthebeast.TeamMod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import ftb.dimension.DimensionMod;

public class CommandJoL extends CommandBase
{
	@Override
    public int getRequiredPermissionLevel()
    {
        return 1;
    }
	
	@Override
	public String getCommandName()
	{
		return "jol";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) 
	{
		String s = args[0];
		if(s.matches("tele"))
		{
			EntityPlayerMP player = (EntityPlayerMP)sender;
			player.travelToDimension(DimensionMod.instance.dimensions.get(TeamMod.instance.teamHandler.getPlayerTeam(player.username)).teamOverworld);
		}
		else
		{
			int i = Integer.parseInt(s);
			EntityPlayer player = (EntityPlayer) sender;
			player.worldObj.setBlock((int)player.posX + 1, (int)player.posY, (int)player.posZ, DimensionMod.portalBlock.blockID, i, 2);
			player.worldObj.setBlock((int)player.posX, (int)player.posY, (int)player.posZ, DimensionMod.portalBlock.blockID, i, 2);
			player.worldObj.setBlock((int)player.posX + 1, (int)player.posY + 1, (int)player.posZ, DimensionMod.portalBlock.blockID, i, 2);
			player.worldObj.setBlock((int)player.posX, (int)player.posY + 1, (int)player.posZ, DimensionMod.portalBlock.blockID, i, 2);
			player.worldObj.setBlock((int)player.posX + 1, (int)player.posY + 2, (int)player.posZ, DimensionMod.portalBlock.blockID, i, 2);
			player.worldObj.setBlock((int)player.posX, (int)player.posY + 2, (int)player.posZ, DimensionMod.portalBlock.blockID, i, 2);
		}
 	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return null;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
