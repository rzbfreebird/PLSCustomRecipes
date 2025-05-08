package com.ren.plscustomrecipes.items;

import com.ren.plscustomrecipes.PLSCustomRecipes;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item ELYTRA_WING = registerItem("elytra_wing", 
        new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PLSCustomRecipes.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PLSCustomRecipes.LOGGER.info("注册自定义物品");
    }
} 