package ftb.dimension.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemKey extends Item
{
	public ItemKey(int par1) 
	{
		super(par1);
		this.setUnlocalizedName("KeyFTB");
		this.maxStackSize = 16;
	}
	
	@Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
    {
        if(par1ItemStack.stackTagCompound == null)
            par1ItemStack.setTagCompound(new NBTTagCompound());        
        par1ItemStack.stackTagCompound.setString("player", par3EntityPlayer.username);
    }
}
