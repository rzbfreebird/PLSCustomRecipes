package com.ren.plscustomrecipes;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class DragonProgressionListener {
    private static final Identifier DRAGON_ADVANCEMENT = new Identifier("end/kill_dragon");
    private static final Identifier ELYTRA_RECIPE_ID = new Identifier(PLSCustomRecipes.MOD_ID, "elytra");

    public static void register() {
        // 服务器启动时检查龙是否已被击败
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            checkDragonStatus(server);
        });

        // 玩家加入服务器时，检查它们是否应该解锁配方
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            if (isDragonDefeated(server)) {
                unlockElytraRecipe(player);
            }
        });

        // 监听进度解锁
        CustomAdvancementCallback.EVENT.register((player, advancement) -> {
            if (advancement.getId().equals(DRAGON_ADVANCEMENT)) {
                // 龙被击败，为所有玩家解锁配方
                player.getServer().getPlayerManager().getPlayerList().forEach(DragonProgressionListener::unlockElytraRecipe);
            }
        });
    }

    private static boolean isDragonDefeated(MinecraftServer server) {
        // 检查服务器的进度数据，看龙是否已被击败
        return server.getAdvancementLoader().get(DRAGON_ADVANCEMENT)
                .getCriteria()
                .entrySet()
                .stream()
                .anyMatch(entry -> server.getScoreboard().getObjective(entry.getKey()) != null);
    }

    private static void checkDragonStatus(MinecraftServer server) {
        if (isDragonDefeated(server)) {
            // 如果龙已被击败，为所有玩家解锁配方
            server.getPlayerManager().getPlayerList().forEach(DragonProgressionListener::unlockElytraRecipe);
        }
    }

    private static void unlockElytraRecipe(ServerPlayerEntity player) {
        player.unlockRecipes(new Identifier[]{ELYTRA_RECIPE_ID});
        PLSCustomRecipes.LOGGER.info("为玩家 {} 解锁鞘翅合成配方", player.getName().getString());
    }
} 