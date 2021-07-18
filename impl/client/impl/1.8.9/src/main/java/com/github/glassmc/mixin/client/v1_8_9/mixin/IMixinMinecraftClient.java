package com.github.glassmc.mixin.client.v1_8_9.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftClient.class)
public interface IMixinMinecraftClient {

    @Accessor void setCrashed(boolean crashed);
    @Accessor boolean isCrashed();

}
