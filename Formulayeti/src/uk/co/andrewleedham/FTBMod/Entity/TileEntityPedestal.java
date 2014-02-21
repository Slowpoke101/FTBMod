package uk.co.andrewleedham.FTBMod.Entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPedestal extends TileEntity {
	private byte jewelCount = 0;
	
	public boolean addJewel(){
		if(this.jewelCount < 10){
			this.jewelCount += 1;
			return true;
		}
		return false;
	}
	
	public byte getJewels(){
		return this.jewelCount;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound ntc){
		super.readFromNBT(ntc);
		this.jewelCount = ntc.getByte("jewels");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound ntc)
    {
        super.writeToNBT(ntc);
        ntc.setByte("jewels", jewelCount);
    }
}
