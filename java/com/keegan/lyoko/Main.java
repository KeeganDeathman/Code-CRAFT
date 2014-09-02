package com.keegan.lyoko;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import com.keegan.lyoko.blocks.BlockSuperComputer;
import com.keegan.lyoko.blocks.BlockUraniumOre;
import com.keegan.lyoko.common.LyokoCommonProxy;
import com.keegan.lyoko.common.LyokoTab;
import com.keegan.lyoko.items.ItemUraniumDust;
import com.keegan.lyoko.world.LyokoOreGen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid="lyoko", version="3.0",name="Code:CRAFT")
public class Main 
{
	@SidedProxy(clientSide = "com.keegan.lyoko.client.LyokoClientProxy", serverSide = "com.keegan.lyoko.common.LyokoCommonProxy")
	public static LyokoCommonProxy proxy;
	
	@Instance("lyoko")
	public static Main instance;
	
	//Blocks
	public static Block blockTowerWall;
	public static Block blockTowerRoots;
	public static Block blockTowerFloor;
	public static Block blockTowerConsole;
	public static Block blockSuperCompInterface;
	public static Block blockSuperComputer;
	public static Block blockScanner;
	public static Block blockLyoko;
	public static Block blockUraniumOre;
	public static Block blockCentrifuge;
	public static Block blockCable;
	
	//Items
	public static Item itemUraniumDust;
	public static Item itemDrum;
	public static Item itemHydroAcid;
	public static Item itemUraniumHexa;
	public static Item itemU235;
	public static Item itemEmptyPowerCore;
	public static Item itemPowerCore;
	
	public static CreativeTabs lyokoTab = new LyokoTab("Code: CRAFT");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Declarations
		blockUraniumOre = new BlockUraniumOre(Material.rock).setBlockName("blockuraniumOre").setBlockTextureName("lyoko:blockUraniumOre").setCreativeTab(lyokoTab).setHardness(10f);
		blockSuperComputer = new BlockSuperComputer(Material.iron).setBlockName("blockSuperComputer").setBlockTextureName("lyoko:blockSuperComputer").setCreativeTab(lyokoTab);
		
		itemUraniumDust = new ItemUraniumDust().setCreativeTab(lyokoTab).setTextureName("lyoko:itemUraniumDust").setUnlocalizedName("itemUraniumDust");
		itemHydroAcid = new Item().setCreativeTab(lyokoTab).setUnlocalizedName("itemHydroAcid").setTextureName("lyoko:itemHydroAcid");
				
		
		//Registeries
		GameRegistry.registerBlock(blockUraniumOre, "blockUraniumOre");
		GameRegistry.registerBlock(blockSuperComputer, "blockSuperComputer");
		
		GameRegistry.registerItem(itemUraniumDust, "itemUraniumDust");
		GameRegistry.registerItem(itemHydroAcid, "itemHydroAcid");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//Proxy junk
		proxy.registerRenders();
		
		GameRegistry.registerWorldGenerator(new LyokoOreGen(), 0);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
