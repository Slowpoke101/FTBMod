package uk.co.andrewleedham.FTBMod.Entity;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPedestal extends BlockContainer{

	public BlockPedestal(int id) {
		super(id, Material.rock);
		this.setTickRandomly(true);
	}
	
	@Override
	public boolean canProvidePower(){
		return true;
	}
	
	@Override
    public int isProvidingWeakPower(IBlockAccess ba, int x, int y, int z, int side)
    {
        return ((TileEntityPedestal)ba.getBlockTileEntity(x, y, z)).getJewels() == 10 ? 15 : 0;
    }
	
	public void neighbor(World world, int x, int y, int z){
        world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID);
        world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
        world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
        world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
        world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		if(player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().itemID == Item.goldNugget.itemID){
			if(((TileEntityPedestal)world.getBlockTileEntity(x, y, z)).addJewel()){
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
				neighbor(world, x, y, z);
				return true;
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityPedestal();
	}
	
	@Override
	public int getRenderType(){
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public void registerIcons(IconRegister icon){
		this.blockIcon = icon.registerIcon("ftbmod:PedastoolIcon");
	}

}
