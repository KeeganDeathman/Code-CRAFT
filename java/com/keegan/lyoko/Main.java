package com.keegan.lyoko;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.keegan.lyoko.blocks.*;
import com.keegan.lyoko.*;
import com.keegan.lyoko.client.*;
import com.keegan.lyoko.common.*;
import com.keegan.lyoko.gui.*;
import com.keegan.lyoko.items.*;
import com.keegan.lyoko.recipes.Recipes;
import com.keegan.lyoko.tilenenity.*;
import com.keegan.lyoko.world.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
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
	public static Block blockFlouriteOre;
	public static Block blockCentrifuge;
	public static Block blockCable;
	
	//Items
	public static Item itemUraniumDust;
	public static Item itemFlouriteIngot;
	public static Item itemFlorineBottle;
	public static Item itemEmptyCanister;
	public static Item itemWaterCanister;
	public static Item itemHydrogenCanister;
	public static Item itemDrum;
	public static Item itemHydroAcid;
	public static Item itemUraniumHexa;
	public static Item itemU235;
	public static Item itemU235Hexa;
	public static Item itemEmptyPowerCore;
	public static Item itemPowerCore;
	
	public static CreativeTabs lyokoTab = new LyokoTab("Code: CRAFT");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//Declarations
		blockUraniumOre = new BlockUraniumOre(Material.rock).setBlockName("blockuraniumOre").setBlockTextureName("lyoko:blockUraniumOre").setCreativeTab(lyokoTab).setHardness(10f);
		blockSuperComputer = new BlockSuperComputer(Material.iron).setBlockName("blockSuperComputer").setBlockTextureName("lyoko:blockSuperComputer").setCreativeTab(lyokoTab);
		blockFlouriteOre = new BlockFlouriteOre(Material.rock).setBlockName("blockFloriteOre").setBlockTextureName("lyoko:blockFlourite").setCreativeTab(lyokoTab);
		blockCentrifuge = new BlockCentrifuge(Material.iron).setBlockName("blockCentrifuge").setCreativeTab(lyokoTab);
		
		itemUraniumDust = new ItemUraniumDust().setCreativeTab(lyokoTab).setTextureName("lyoko:itemUraniumDust").setUnlocalizedName("itemUraniumDust");
		itemHydroAcid = new Item().setCreativeTab(lyokoTab).setUnlocalizedName("itemHydroAcid").setTextureName("lyoko:itemHydroAcid");
		itemHydrogenCanister = new Item().setCreativeTab(lyokoTab).setUnlocalizedName("itemHydrogenCanister").setTextureName("lyoko:itemHydrogenCanister");
		itemWaterCanister = new Item().setCreativeTab(lyokoTab).setUnlocalizedName("itemWaterCanister").setTextureName("lyoko:itemWaterCanister");
		itemEmptyCanister = new Item().setCreativeTab(lyokoTab).setUnlocalizedName("itemEmptyCanister").setTextureName("lyoko:itemEmptyCanister");
		
		//Registeries
		GameRegistry.registerBlock(blockUraniumOre, "blockUraniumOre");
		GameRegistry.registerBlock(blockSuperComputer, "blockSuperComputer");
		GameRegistry.registerBlock(blockFlouriteOre, "blockFlourite");
		GameRegistry.registerBlock(blockCentrifuge, "blockCentrifuge");
		
		GameRegistry.registerItem(itemUraniumDust, "itemUraniumDust");
		GameRegistry.registerItem(itemHydroAcid, "itemHydroAcid");
		GameRegistry.registerItem(itemWaterCanister, "itemWaterCanister");
		GameRegistry.registerItem(itemHydrogenCanister, "itemHydrogenCanister");
		GameRegistry.registerItem(itemEmptyCanister, "itemEMptyCanister");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		//Proxy junk
		proxy.registerRenders();
		
		GameRegistry.registerWorldGenerator(new LyokoOreGen(), 0);
		
		oreDict();
		
		//CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(this.itemHydroAcid), this.itemFlorineBottle, this.itemHydrogenCanister));
		GameRegistry.addRecipe(new ItemStack(itemEmptyCanister), " i ", "i i", " i ", 'i', new ItemStack(Items.iron_ingot));
		GameRegistry.addShapelessRecipe(new ItemStack(itemWaterCanister), new ItemStack(itemEmptyCanister),new ItemStack(Items.water_bucket));
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		GameRegistry.registerTileEntity(TileEntityCentrifuge.class, "Centrifuge");
		registerCentrifugeRecipes();
	}
	
	private void oreDict()
	{
		OreDictionary.registerOre("oreUranium", blockUraniumOre);
		OreDictionary.registerOre("dustUranium", itemUraniumDust);
		OreDictionary.registerOre("oreFlourite", blockFlouriteOre);
		//OreDictionary.registerOre("ingotFlourite", itemFlouriteIngot);
	}
	
	private void registerCentrifugeRecipes()
	{
		//Recipes.addCentrifugeRecipe(new ItemStack(itemUraniumHexa), new ItemStack(itemU235Hexa));
		Recipes.addCentrifugeRecipe(new ItemStack(itemWaterCanister), new ItemStack(itemHydrogenCanister));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
