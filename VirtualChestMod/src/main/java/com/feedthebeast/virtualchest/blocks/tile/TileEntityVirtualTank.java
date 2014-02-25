package com.feedthebeast.virtualchest.blocks.tile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feedthebeast.virtualchest.core.VirtualTankData;
import com.google.common.collect.Lists;

import openperipheral.api.Arg;
import openperipheral.api.IAttachable;
import openperipheral.api.LuaCallable;
import openperipheral.api.LuaType;
import dan200.computer.api.IComputerAccess;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityVirtualTank extends TileEntity implements IFluidHandler,IAttachable{
	private List<IComputerAccess> computers = Lists.newArrayList();
	private VirtualTankData tankData;
	public TileEntityVirtualTank()
	{
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		
	}

	public FluidTank GetTank(Fluid fluid)
	{
		if(!tankData.fluids.containsKey(fluid))
		{
			tankData.fluids.put(fluid, new FluidTank(Integer.MAX_VALUE));
		}
		return tankData.fluids.get(fluid);
	}
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		Fluid fluid=resource.getFluid();
		FluidTank tank=GetTank(fluid);
		
		
		int accepted=tank.fill(resource, doFill);
		if(tankData.intervals.containsKey(fluid) && doFill)
		{
			if(!tankData.changes.containsKey(fluid))
				tankData.changes.put(fluid, (double)accepted);
			else
			{
				tankData.changes.put(fluid, tankData.changes.get(fluid)+accepted);
			}
			double change=tankData.changes.get(fluid);
			if(change>=tankData.intervals.get(fluid))
			{
				tankData.changes.put(fluid,0D);
				for(IComputerAccess computer:computers)
				computer.queueEvent("fluid_interval", new Object[]{fluid.getUnlocalizedName(),change,tank.getFluidAmount()});
			}
		}
		if(tankData.caps.containsKey(fluid) && accepted >0 && doFill)
		{
			if(tank.getFluidAmount()>tankData.caps.get(fluid))
			{
				tankData.caps.put(fluid, (double)Integer.MAX_VALUE);
				for(IComputerAccess computer:computers)
					computer.queueEvent("fluid_cap", new Object[]{fluid.getUnlocalizedName(),tank.getFluidAmount()});
			}
		}
		return accepted;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if (resource == null || !resource.isFluidEqual(tankData.tank.getFluid()))
		{
			return null;
		}
		return tankData.tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tankData.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		FluidTankInfo[] tanks=new FluidTankInfo[tankData.fluids.size()];
		int i=0;
		for(FluidTank tank :tankData.fluids.values())
		{
			tanks[i++]=tank.getInfo();
		}
		return tanks;
	}

	@LuaCallable( description="Gets Available fluids",returnTypes=LuaType.TABLE)
	public HashMap<String, Double> getFluids(IComputerAccess computer)
	{
		HashMap<String, Double> retval=new HashMap<String, Double>();
		int i=1;
		for(FluidTank tank :tankData.fluids.values())
		{
			retval.put(tank.getFluid().getFluid().getUnlocalizedName(), (double) i++);
		}
		return retval;
	}

	@LuaCallable( description="Gets Available fluid amounts",returnTypes=LuaType.TABLE)
	public HashMap<String, Double> getFluidAmounts(IComputerAccess computer)
	{
		HashMap<String, Double> retval=new HashMap<String, Double>();
		int i=1;
		for(FluidTank tank :tankData.fluids.values())
		{
			retval.put(tank.getFluid().getFluid().getUnlocalizedName(), (double) tank.getFluidAmount());
		}
		return retval;
	}
	
	@LuaCallable( description="Sets the softcap on event")
	public void setSoftCap(IComputerAccess computer,@Arg(name="fluidName", type = LuaType.STRING)String fluidName,@Arg(name="amount",type=LuaType.NUMBER)double amount)
	{
		Fluid fluid=FluidRegistry.getFluid(fluidName);
		if(fluid==null)
			return;
		FluidTank tank=GetTank(fluid);
		tankData.caps.put(fluid, amount);
	}
	
	
	@LuaCallable( description="Sets the change interval event")
	public void setChangeInterval(IComputerAccess computer,@Arg(name="fluidName", type = LuaType.STRING)String fluidName,@Arg(name="amount",type=LuaType.NUMBER)double amount)
	{
		Fluid fluid=FluidRegistry.getFluid(fluidName);
		if(fluid==null)
			return;
		FluidTank tank=GetTank(fluid);
		tankData.intervals.put(fluid, amount);
		tankData.changes.put(fluid, 0D);
	}
	// UNSTAGED CHANGE
	@Override
	public void addComputer(IComputerAccess computer) {
		if (!computers.contains(computer)) {
			computers.add(computer);
		}

	}


	@Override
	public void removeComputer(IComputerAccess computer) {
		computers.remove(computer);

	}
}
