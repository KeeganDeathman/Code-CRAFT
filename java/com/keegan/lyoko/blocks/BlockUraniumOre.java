package com.keegan.lyoko.blocks;

import java.util.Random;

import com.keegan.lyoko.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockUraniumOre extends Block {

	public BlockUraniumOre(Material p_i45394_1_) {
		super(p_i45394_1_);
	}
	
	@Override
	public Item getItemDropped(int par1, Random rand, int par3)
	{
		return Main.itemUraniumDust;
	}

}
