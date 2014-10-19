package com.keegan.lyoko.slot;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.keegan.lyoko.container.ContainerCentrifuge;

public class SlotCentrifuge extends Slot {

	public SlotCentrifuge(ContainerCentrifuge par1ContainerCentrifuge, IInventory par2iInventory, int par3, int par4, int par5) 
	{
		super(par2iInventory, par3, par4, par5);
	}
	
	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return true;
	}
	
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

}
