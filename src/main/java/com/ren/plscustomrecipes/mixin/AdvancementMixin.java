package com.ren.plscustomrecipes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ren.plscustomrecipes.CustomAdvancementCallback;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(PlayerAdvancementTracker.class)
public class AdvancementMixin {
    @Shadow private ServerPlayerEntity owner;

    @Inject(method = "grantCriterion", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementRewards;apply(Lnet/minecraft/server/network/ServerPlayerEntity;)V"))
    private void onAdvancementCompleted(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        CustomAdvancementCallback.EVENT.invoker().onAdvancementCompleted(owner, advancement);
    }
} 