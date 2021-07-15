package com.github.glassmc.example.client.v1_8_9.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "tick()V", at = @At("HEAD"))
    public void onTick(CallbackInfo callbackInfo) {
        System.out.println("Test");
    }

}