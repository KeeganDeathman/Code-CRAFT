package com.keegan.lyoko.common;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class Recipes 
{
	public static ArrayList<ItemStack[]> centrifuge = new ArrayList<ItemStack[]>();
	
	
	public static void addCentrifugeRecipe(ItemStack input, ItemStack output)
	{
		ItemStack[] array = new ItemStack[2];
		array[0] = input;
		array[1] = output;
		centrifuge.add(array);
	}
	
	public static ArrayList<ItemStack[]> getCentrifuge()
	{
		return centrifuge;
	}
}
