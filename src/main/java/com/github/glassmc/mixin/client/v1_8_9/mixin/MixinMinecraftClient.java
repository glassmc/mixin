package com.github.glassmc.mixin.client.v1_8_9.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import v1_8_9.net.minecraft.client.MinecraftClient;

import java.lang.reflect.InvocationTargetException;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "tick()V", at = @At("HEAD"))
    public void onTick(CallbackInfo callbackInfo) {
        System.out.println(((IMixinMinecraftClient) MinecraftClient.getInstance()).getCameraEntity());
    }

}
