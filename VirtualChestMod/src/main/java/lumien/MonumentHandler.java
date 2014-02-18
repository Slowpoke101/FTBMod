package lumien;

import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class MonumentHandler
{
	public static boolean isMonumentSpawned(World worldObj)
	{
		WorldSavedData savedData = worldObj.loadItemData(MonumentData.class, "monument");
		MonumentData md=null;
		if (savedData==null || !(savedData instanceof MonumentData))
		{
			worldObj.setItemData("monument", new MonumentData());
			md = (MonumentData) worldObj.loadItemData(MonumentData.class, "monument");
		}
		else
		{
			md = (MonumentData)savedData;
		}
		return md.isMonumentSpawned();
	}
	
	public static void setMonumentSpawned(World worldObj,boolean spawned)
	{
		WorldSavedData savedData = worldObj.loadItemData(MonumentData.class, "monument");
		MonumentData md=null;
		if (savedData==null || !(savedData instanceof MonumentData))
		{
			worldObj.setItemData("monument", new MonumentData());
			md = (MonumentData) worldObj.loadItemData(MonumentData.class, "monument");
		}
		else
		{
			md = (MonumentData)savedData;
		}
		
		md.setMonumentSpawned(spawned);
	}
}
