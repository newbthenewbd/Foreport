package com.lolwhat.Foreport;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Foreport.MODID, name = Foreport.NAME, version = Foreport.VERSION, clientSideOnly = true)
public class Foreport {
	public static final String MODID = "foreport";
	public static final String NAME = "Foreport";
	public static final String VERSION = "1.0.0";
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Minecraft.getMinecraft().toastGui = new GuiForeportToast(Minecraft.getMinecraft());
		MinecraftForge.EVENT_BUS.register(new ForeportEventHandler());
	}
}
