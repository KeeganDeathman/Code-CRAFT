package com.keegan.lyoko.common;

import com.keegan.lyoko.Main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LyokoTab extends CreativeTabs {

	public LyokoTab(String arg1) {
		super(arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(Main.blockUraniumOre);
	}
	
	public String getTranslatedTabLabel()
	{
		
		return "LabStuff";
		
	}

}
