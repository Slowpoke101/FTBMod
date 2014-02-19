package com.feedthebeast.VirtualChest.blocks.tile;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityVirtualTank extends TileEntity implements IFluidHandler{
	protected FluidTank tank = new FluidTank(Integer.MAX_VALUE);
	public Map<Fluid, FluidTank> fluids=new HashMap<Fluid, FluidTank>();
	protected FluidTank currentTank;
	
	public TileEntityVirtualTank()
	{
		currentTank=tank;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		tank.readFromNBT(par1nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		tank.writeToNBT(par1nbtTagCompound);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		Fluid fluid=resource.getFluid();
		if(!fluids.containsKey(fluid))
		{
			fluids.put(fluid, new FluidTank(Integer.MAX_VALUE));
		}
		return fluids.get(fluid).fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		 if (resource == null || !resource.isFluidEqual(tank.getFluid()))
	        {
	            return null;
	        }
	        return tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return tank.drain(maxDrain, doDrain);
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
		FluidTankInfo[] tanks=new FluidTankInfo[fluids.size()];
		int i=0;
		for(FluidTank tank :fluids.values())
		{
			tanks[i++]=tank.getInfo();
		}
		return tanks;
	}

}
