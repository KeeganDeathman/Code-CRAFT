package com.keegan.tilenenity;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.keegan.lyoko.common.Recipes;

public class TileEntityCentrifuge extends TileEntity implements IInventory
{
	
	private ItemStack[] contents  = new ItemStack[2];
	
	private float startTime;
	private boolean spinning = false;
	
	private ArrayList<ItemStack[]> recipes;
	
	public TileEntityCentrifuge()
	{
		recipes = Recipes.getCentrifuge();
	}
	
	private float getTime()
	{
		//Returns time in minutes
		return System.nanoTime() / 60000000000L;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.contents.length; i++)
        {
            ItemStack stack = this.contents[i];
            if (stack != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Inventory", itemList);
        tagCompound.setFloat("startTime", startTime);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
        for (int i = 0; i < tagList.tagCount(); i++)
        {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.contents.length)
                this.contents[slot] = ItemStack.loadItemStackFromNBT(tag);
        }
        startTime = tagCompound.getFloat("startTime");
	}
	
	private void spin()
	{
		recipes = Recipes.getCentrifuge();
		for(int i = 0; i < recipes.size(); i++)
		{
			System.out.println(recipes.size());
			System.out.println(i);
			if(getStackInSlot(0) == recipes.get(i)[0])
			{
				System.out.println("Beginning spin");
				startTime = getTime();
				spinning = true;
			}
		}
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		recipes = Recipes.getCentrifuge();
		if(startTime > 0)
		{
			float elapsedTime = getTime() - startTime;
			System.out.println("Started at " + startTime);
			System.out.println(elapsedTime + " = elapsed time");
			if((elapsedTime >= 5) && spinning && startTime > -1)
			{
				for(int i = 0; i < recipes.size(); i++)
				{
					if(getStackInSlot(0) == recipes.get(i)[0])
					{
						startTime = 0;
						spinning = false;
						decrStackSize(0, 1);
						this.setInventorySlotContents(1, recipes.get(i)[1]);
					}
				}
			}
		}
		if(getStackInSlot(0) != null)
		{
				System.out.println("Attempting Spin");
				spin();
		}
		else
		{
			startTime = 0;
			spinning = false;
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
		return contents.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		return contents[slot];
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
		contents[slot] = itemstack;
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		
	}
}
