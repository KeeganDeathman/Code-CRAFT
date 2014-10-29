package com.keegan.lyoko.recipes;

import net.minecraft.item.ItemStack;

public class CentrifugeRecipe 
{
	public final ItemStack input;
	public final ItemStack output;
	
	public CentrifugeRecipe(ItemStack in, ItemStack out)
	{
		input = in;
		output = out;
	}

	public ItemStack getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}
}
