package com.keegan.lyoko.tilenenity;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.keegan.lyoko.recipes.CentrifugeRecipe;
import com.keegan.lyoko.recipes.Recipes;

public class TileEntityCentrifuge extends TileEntity implements IInventory
{
	
	private ItemStack[] chestContents;
	
	private float startTime;
	private String spinning = "still";
	
	private int time;
	
	private ArrayList<CentrifugeRecipe> recipes;
	
	public TileEntityCentrifuge()
	{
		chestContents  = new ItemStack[2];
		recipes = Recipes.getCentrifuge();
	}
	
	private float getTime()
	{
		//Returns time in minutes
		return time;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.chestContents.length; i++)
        {
            ItemStack stack = this.chestContents[i];
            if (stack != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Inventory", itemList);
        tagCompound.setString("Spinning", spinning);
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
            if (slot >= 0 && slot < this.chestContents.length)
                this.chestContents[slot] = ItemStack.loadItemStackFromNBT(tag);
        }
        startTime = tagCompound.getFloat("startTime");
        spinning = tagCompound.getString("spinning");
	}
	
	private void spin()
	{
		recipes = Recipes.getCentrifuge();
		if (spinning.equals("still")) {
			for (int i = 0; i < recipes.size(); i++) {
				System.out.println(recipes.size());
				System.out.println(i);
				//System.out.println("wanted is not called " + recipes.get(i).getInput().getItem().getUnlocalizedName());
				if (getStackInSlot(0) != null && getStackInSlot(0).isItemEqual(recipes.get(i).getInput())) 
				{
					spinning = "spinning";
					System.out.println("Beginning spin " + spinning);
					worldObj.scheduleBlockUpdate(xCoord, yCoord, zCoord, getBlockType(), 6000);
				}
			}
		}
		else
		{
			System.out.println(spinning);
		}
	}
	
	public void completeSpin()
	{
		if(spinning.equals("spinning"))
		{
			for(int i = 0; i < recipes.size(); i++)
			{
				if(getStackInSlot(0).isItemEqual(recipes.get(i).getInput()))
				{
					decrStackSize(0, 1);
					setInventorySlotContents(1, recipes.get(i).getOutput());
					spinning = "still";
				}
			}
		}
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		recipes = Recipes.getCentrifuge();
		if(getStackInSlot(0) != null && !spinning.equals("spinning"))
		{
				System.out.println("Attempting Spin");
				spin();
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
			if(slot == 0 && stack == recipes.get(i).getInput())
			{
				return true;
			}
			if(slot == 1 && stack == recipes.get(i).getOutput())
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
