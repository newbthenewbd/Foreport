package com.lolwhat.Foreport;

import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.toasts.AdvancementToast;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;

public class GuiForeportToast extends GuiToast {
	public GuiForeportToast(Minecraft minecraft) {
		super(minecraft);
	}
	public void drawToast(ScaledResolution resolution) {
		Iterator<IToast> i = this.toastsQueue.iterator();
		while(i.hasNext()) {
			IToast toast = i.next();
			if(!(toast instanceof AdvancementToast)) {
				i.remove();
			}
		}
		super.drawToast(resolution);
	}
}
