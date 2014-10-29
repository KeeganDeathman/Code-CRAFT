package com.keegan.lyoko.recipes;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class Recipes 
{
	public static ArrayList<CentrifugeRecipe> centrifuge = new ArrayList<CentrifugeRecipe>();
	
	
	public static void addCentrifugeRecipe(ItemStack input, ItemStack output)
	{
		CentrifugeRecipe recipe = new CentrifugeRecipe(input, output);
		centrifuge.add(recipe);
	}
	
	public static ArrayList<CentrifugeRecipe> getCentrifuge()
	{
		return centrifuge;
	}
}
