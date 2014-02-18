package ftb.dimension.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.IPlayerTracker;
import ftb.dimension.DimensionMod;

public class WorldHandler implements IPlayerTracker
{
	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
		
		for(String s : DimensionMod.instance.dimensions.keySet())
		{
			if(s == player.username)
			{
				return;
			}
		}
		
		int i = DimensionManager.getNextFreeDimId();
		
        DimensionManager.registerProviderType(i, WorldProviderSurface.class, false);
        DimensionManager.registerDimension(i, i);
        DimensionMod.instance.dimensions.put(player.username, i);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {}
	@Override
	public void onPlayerRespawn(EntityPlayer player) {}
}
