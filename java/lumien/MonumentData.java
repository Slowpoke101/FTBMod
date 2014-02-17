package lumien;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class MonumentData extends WorldSavedData
{
	boolean monumentSpawned;
	
	public MonumentData()
	{
		super("monument");
		
		monumentSpawned = false;
	}
	
	public MonumentData(String s)
	{
		super("monument");
		
		monumentSpawned = false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		this.monumentSpawned = nbttagcompound.getBoolean("monumentSpawned");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setBoolean("monumentSpawned", monumentSpawned);
	}
	
	public boolean isMonumentSpawned()
	{
		return monumentSpawned;
	}
	
	public void setMonumentSpawned(boolean spawned)
	{
		this.monumentSpawned = spawned;
		this.markDirty();
	}
}

