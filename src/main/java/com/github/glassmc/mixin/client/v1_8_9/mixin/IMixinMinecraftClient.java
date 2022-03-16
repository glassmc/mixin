package com.github.glassmc.mixin.client.v1_8_9.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import v1_8_9.net.minecraft.client.MinecraftClient;
import v1_8_9.net.minecraft.entity.Entity;

@Mixin(MinecraftClient.class)
public interface IMixinMinecraftClient {

    @Accessor
    Entity getCameraEntity();

}
