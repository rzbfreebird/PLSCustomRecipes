package com.ren.plscustomrecipes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public class PLSCustomRecipes implements ModInitializer {
	public static final String MOD_ID = "plscustomrecipes";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("初始化自定义合成mod");
		
		// 移除物品注册，只保留进度监听器
		DragonProgressionListener.register();
	}
}