package ftb.dimension.core;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
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
		EntityPlayerMP player = (EntityPlayerMP)sender;
		player.travelToDimension(DimensionMod.instance.dimensions.get(player.username));
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return null;
	}
}
