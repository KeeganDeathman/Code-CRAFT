package com.keegan.lyoko.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.keegan.lyoko.container.ContainerCentrifuge;
import com.keegan.lyoko.tilenenity.TileEntityCentrifuge;

public class GuiCentrifuge extends GuiContainer 
{

	TileEntityCentrifuge tile;
	EntityPlayer player;
	
	public GuiCentrifuge(InventoryPlayer inv, TileEntityCentrifuge tileEntity) 
	{
		super(new ContainerCentrifuge(inv, tileEntity));
		tile = tileEntity;
		player = inv.player;
		
		this.xSize = 256;
		this.ySize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float arg0, int arg1, int arg2) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("lyoko:textures/gui/centrifuge.png"));
		int x = (this.width - xSize) / 2;
		int y = (this.height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
