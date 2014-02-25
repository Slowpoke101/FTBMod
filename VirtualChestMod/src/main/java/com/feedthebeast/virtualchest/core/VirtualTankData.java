/**
 * 
 */
package com.feedthebeast.virtualchest.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;

/**
 * @author Katrina
 *
 */
public class VirtualTankData extends WorldSavedData {

	public FluidTank tank = new FluidTank(Integer.MAX_VALUE);
	public Map<Fluid, FluidTank> fluids=new HashMap<Fluid, FluidTank>();
	public Map<Fluid,Double> caps=new HashMap<Fluid, Double>();
	public Map<Fluid,Double> changes=new HashMap<Fluid, Double>();
	public Map<Fluid,Double> intervals=new HashMap<Fluid, Double>();
	public static String TAG_TANKS="Tanks";
	public static String TAG_CAPS="Caps";
	public static String TAG_CHANGES="Changes";
	public static String TAG_INTERVALS="Intervals";
	public static String TAG_FLUID="Fluid";
	public static String TAG_AMOUNT="Amount";
	public VirtualTankData(String par1Str) {
		super(par1Str);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.world.WorldSavedData#readFromNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		NBTTagList list=nbttagcompound.getTagList(TAG_TANKS);
		for(int i=0;i<list.tagCount();i++)
		{
			NBTTagCompound cmp=(NBTTagCompound) list.tagAt(i);
			FluidTank tnk=new FluidTank(Integer.MAX_VALUE);
			tnk.readFromNBT(cmp);
			fluids.put(tnk.getFluid().getFluid(), tnk);
		}
		tank.readFromNBT(nbttagcompound);
		NBTTagList capsList=nbttagcompound.getTagList(TAG_CAPS);
		for(int i=0;i<capsList.tagCount();i++)
		{
			NBTTagCompound capCmp=(NBTTagCompound) capsList.tagAt(i);
			Fluid fld;
			double amount;
			fld=FluidRegistry.getFluid(capCmp.getString(TAG_FLUID));
			amount=capCmp.getDouble(TAG_AMOUNT);
			caps.put(fld, amount);
		}
		NBTTagList changesList=nbttagcompound.getTagList(TAG_CHANGES);
		for(int i=0;i<changesList.tagCount();i++)
		{
			NBTTagCompound changesCmp=(NBTTagCompound)changesList.tagAt(i);
			Fluid fld;
			double amount;
			fld=FluidRegistry.getFluid(changesCmp.getString(TAG_FLUID));
			amount=changesCmp.getDouble(TAG_AMOUNT);
			changes.put(fld, amount);
		}
		NBTTagList intervalsList=nbttagcompound.getTagList(TAG_INTERVALS);
		for(int i=0;i<intervalsList.tagCount();i++)
		{
			NBTTagCompound intervalsCmp=(NBTTagCompound)intervalsList.tagAt(i);
			Fluid fld;
			double amount;
			fld=FluidRegistry.getFluid(intervalsCmp.getString(TAG_FLUID));
			amount=intervalsCmp.getDouble(TAG_AMOUNT);
			intervals.put(fld, amount);
		}

	}

	/* (non-Javadoc)
	 * @see net.minecraft.world.WorldSavedData#writeToNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		NBTTagList list=new NBTTagList();
		for(FluidTank tnk:fluids.values())
		{
			NBTTagCompound cmp=new NBTTagCompound();
			tnk.writeToNBT(cmp);
			list.appendTag(cmp);
		}
		nbttagcompound.setTag(TAG_TANKS, list);
		tank.writeToNBT(nbttagcompound);
		NBTTagList capList=new NBTTagList();
		for(Entry<Fluid, Double> entry:caps.entrySet())
		{
			NBTTagCompound capCmp=new NBTTagCompound();
			capCmp.setString(TAG_FLUID, entry.getKey().getName());
			capCmp.setDouble(TAG_AMOUNT, entry.getValue());
			capList.appendTag(capCmp);
		}
		nbttagcompound.setTag(TAG_CAPS, capList);
		
		NBTTagList changesList=new NBTTagList();
		for(Entry<Fluid, Double> entry:changes.entrySet())
		{
			NBTTagCompound changesCmp=new NBTTagCompound();
			changesCmp.setString(TAG_FLUID, entry.getKey().getName());
			changesCmp.setDouble(TAG_AMOUNT, entry.getValue());
			changesList.appendTag(changesCmp);
		}
		nbttagcompound.setTag(TAG_CHANGES, changesList);

		NBTTagList intervalList=new NBTTagList();
		for(Entry<Fluid, Double> entry:intervals.entrySet())
		{
			NBTTagCompound intervalCmp=new NBTTagCompound();
			intervalCmp.setString(TAG_FLUID, entry.getKey().getName());
			intervalCmp.setDouble(TAG_AMOUNT, entry.getValue());
			intervalList.appendTag(intervalCmp);
		}
		nbttagcompound.setTag(TAG_INTERVALS, intervalList);
	}

}
