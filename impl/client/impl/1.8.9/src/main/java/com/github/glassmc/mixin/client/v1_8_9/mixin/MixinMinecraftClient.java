package com.github.glassmc.mixin.client.v1_8_9.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Shadow
    private ServerInfo currentServerEntry;

    @Inject(method = "tick()V", at = @At("HEAD"))
    public void onTick(CallbackInfo callbackInfo) {
        System.out.println(currentServerEntry);
        System.out.println("Test6");
    }

}
