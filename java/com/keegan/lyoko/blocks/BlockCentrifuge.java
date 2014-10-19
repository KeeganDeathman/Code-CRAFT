package com.keegan.lyoko.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.keegan.lyoko.Main;
import com.keegan.lyoko.tilenenity.TileEntityCentrifuge;

public class BlockCentrifuge extends Block implements ITileEntityProvider
{

	public BlockCentrifuge(Material p_i45394_1_) 
	{
		super(p_i45394_1_);
	}
	
	 @Override
	    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par5, float par6, float par7, float par8)
	    {
		 if(!world.isRemote)
		 {
			 player.openGui(Main.instance, 0, world, x, y, z);
			 return true;			 
		 }
		 return false;
	    }

	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		// TODO Auto-generated method stub
		return new TileEntityCentrifuge();
	}


}
