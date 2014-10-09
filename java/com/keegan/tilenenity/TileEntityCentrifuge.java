package com.keegan.tilenenity;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCentrifuge extends TileEntity implements IInventory
{
	
	private ItemStack[] chestContents  = new ItemStack[1];
	
	private float startTime;
	private boolean spinning = false;
	
	private ArrayList<ItemStack[]> recipes;
	
	public TileEntityCentrifuge()
	{
		recipes = new ArrayList<ItemStack[]>();
	}
	
	private float getTime()
	{
		return System.nanoTime() / 60000000000L;
	}
	
	private void spin()
	{
		for(int i = 0; i < recipes.size(); i++)
		{
			if(getStackInSlot(0) == recipes.get(i)[0])
			{
				startTime = getTime();
				spinning = true;
			}
		}
	}
	
	@Override
	public void updateEntity()
	{
		if((getTime()-startTime >= 5))
		{
			for(int i = 0; i < recipes.size(); i++)
			{
				startTime = 0;
				spinning = false;
				decrStackSize(0, 1);
				this.setInventorySlotContents(1, recipes.get(i)[1]);
			}
		}
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
				if (stack.stackSize <= amt)
				{
					setInventorySlotContents(slot, null);
				}
				else
				{
					stack = stack.splitStack(amt);
					if (stack.stackSize == 0)
					{
						setInventorySlotContents(slot, null);
					}
				}
		}
		return stack;
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return "Centrifuge";
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return chestContents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		return chestContents[slot];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		// TODO Auto-generated method stub
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}


	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) 
	{
		for(int i = 0; i < recipes.size(); i++)
		{
			if(slot == 0 && stack == recipes.get(i)[0])
			{
				return true;
			}
			if(slot == 1 && stack == recipes.get(i)[1])
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		chestContents[slot] = itemstack;
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		
	}
}
