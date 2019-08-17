package com.lolwhat.Foreport;

import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.toasts.AdvancementToast;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.RecipeToast;
import net.minecraft.client.gui.toasts.TutorialToast;
import net.minecraft.client.gui.toasts.IToast;

public class GuiForeportToast extends GuiToast {
	public GuiForeportToast(Minecraft minecraft) {
		super(minecraft);
	}
	public void drawToast(ScaledResolution resolution) {
		Iterator<IToast> i = this.toastsQueue.iterator();
		while(i.hasNext()) {
			IToast toast = i.next();
			if(ConfigForeport.blockToasts == ConfigForeport.BlockToasts.ALL || (ConfigForeport.blockToasts == ConfigForeport.BlockToasts.ALL_EXCEPT_ADVANCEMENT && !(toast instanceof AdvancementToast)) || (ConfigForeport.blockToasts == ConfigForeport.BlockToasts.ONLY_RECIPE_AND_TUTORIAL && (toast instanceof RecipeToast || toast instanceof TutorialToast)) || (ConfigForeport.blockToasts == ConfigForeport.BlockToasts.ONLY_RECIPE && toast instanceof RecipeToast)) {
				i.remove();
			}
		}
		super.drawToast(resolution);
	}
}
