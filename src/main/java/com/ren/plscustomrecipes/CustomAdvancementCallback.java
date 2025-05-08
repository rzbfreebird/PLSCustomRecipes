package com.ren.plscustomrecipes;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.server.network.ServerPlayerEntity;

public interface CustomAdvancementCallback {
    Event<CustomAdvancementCallback> EVENT = EventFactory.createArrayBacked(CustomAdvancementCallback.class,
            (listeners) -> (player, advancement) -> {
                for (CustomAdvancementCallback listener : listeners) {
                    listener.onAdvancementCompleted(player, advancement);
                }
            });

    void onAdvancementCompleted(ServerPlayerEntity player, Advancement advancement);
} 