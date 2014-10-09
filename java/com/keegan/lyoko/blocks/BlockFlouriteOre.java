package com.keegan.lyoko.blocks;

import java.util.Random;

import com.keegan.lyoko.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockFlouriteOre extends Block {

	public BlockFlouriteOre(Material arg0) {
		super(arg0);
	}
	
	@Override
	public Item getItemDropped(int par1, Random rand, int par3)
	{
		return Main.itemFlouriteIngot;
	}

}
