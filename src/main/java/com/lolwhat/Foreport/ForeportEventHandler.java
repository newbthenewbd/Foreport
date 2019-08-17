package com.lolwhat.Foreport;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForeportEventHandler {
	public static boolean guiRender = false;
	@SubscribeEvent
	public void onConfigChangedEvent(OnConfigChangedEvent event) {
		if(event.getModID().equals(Foreport.MODID)) {
			ConfigManager.sync(Foreport.MODID, Config.Type.INSTANCE);
		}
	}
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onPostGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
		if(ConfigForeport.disableRecipeBook) {
			for(GuiButton button : event.getButtonList()) {
				if(button.id == 10 && button instanceof GuiButtonImage) {
					String path = ((GuiButtonImage) button).resourceLocation.getResourcePath();
					if(path == "textures/gui/container/crafting_table.png" || path == "textures/gui/container/inventory.png") {
						try {
							if(event.getGui() instanceof GuiInventory) {
								GuiInventory gui = (GuiInventory) event.getGui();
								if(gui.recipeBookGui.isVisible()) {
									gui.actionPerformed(button);
								}
							} else if(event.getGui() instanceof GuiCrafting) {
								GuiCrafting gui = (GuiCrafting) event.getGui();
								if(gui.recipeBookGui.isVisible()) {
									gui.actionPerformed(button);
								}
							}
						} catch(Exception e) {
							
						}
						button.visible = false;
					}
				}
			}
		}
	}
	@SubscribeEvent(receiveCanceled=true)
	public void onPreGuiDraw(GuiScreenEvent.DrawScreenEvent.Pre event) {
		guiRender = true;
	}
	@SubscribeEvent(receiveCanceled=true)
	public void onPreRenderPlayer(RenderPlayerEvent.Pre event) {
		GlStateManager.pushMatrix();
		if(!event.isCanceled()) {
			AbstractClientPlayer entity = (AbstractClientPlayer) event.getEntity();
			if(entity.isSneaking() && ConfigForeport.fixSneaking) {
				GlStateManager.translate(0, 0.125, 0);
			}
			if(!guiRender && !entity.isRiding() && ConfigForeport.fixWalkingBackwards) {
				entity.renderYawOffset = entity.prevRenderYawOffset;
				double vectorX = entity.posX - entity.prevPosX;
				double vectorY = entity.posZ - entity.prevPosZ;
				float vectorSqLen = (float) (vectorX * vectorX + vectorY * vectorY);
				float offset = entity.renderYawOffset;
				if(vectorSqLen > 0.0025000002f) {
					offset = ((float) MathHelper.atan2(vectorY, vectorX)) * (180.0f / ((float) Math.PI)) - 90.0f;
				}
				entity.renderYawOffset += MathHelper.wrapDegrees(offset - entity.prevRenderYawOffset) * 0.3f;
				vectorSqLen = MathHelper.wrapDegrees(entity.rotationYaw - entity.renderYawOffset);
				if(vectorSqLen < -75.0f) {
					vectorSqLen = -75.0f;
				} else if(vectorSqLen >= 75.0f) {
					vectorSqLen = 75.0f;
				}
				entity.renderYawOffset = entity.rotationYaw - vectorSqLen;
				if(vectorSqLen * vectorSqLen > 2500.0f) {
					entity.renderYawOffset += vectorSqLen * 0.2f;
				}
			}
		}
	}
	@SubscribeEvent(receiveCanceled=true)
	public void onPostRenderPlayer(RenderPlayerEvent.Post event) {
		GlStateManager.popMatrix();
	}
	@SubscribeEvent(receiveCanceled=true)
	public void onPostGuiDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
		guiRender = false;
	}
}
