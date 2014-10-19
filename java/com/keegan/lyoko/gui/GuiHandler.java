package com.keegan.lyoko.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.keegan.lyoko.container.ContainerCentrifuge;
import com.keegan.tilenenity.TileEntityCentrifuge;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tileEntity = world.getTileEntity(x,y,z);
		if(tileEntity instanceof TileEntityCentrifuge)
			return new GuiCentrifuge(player.inventory, (TileEntityCentrifuge)tileEntity);
		return null;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if(tileEntity instanceof TileEntityCentrifuge)
			return new ContainerCentrifuge(player.inventory, (TileEntityCentrifuge)tileEntity);
		return null;
	}

}
