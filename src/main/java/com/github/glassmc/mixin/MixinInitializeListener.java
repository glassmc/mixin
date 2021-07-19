package com.github.glassmc.mixin;

import com.github.glassmc.loader.GlassLoader;
import com.github.glassmc.loader.Listener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.transformer.GlassMixinTransformer;

public class MixinInitializeListener implements Listener {

    @Override
    public void run() {
        MixinBootstrap.init();

        GlassLoader.getInstance().registerAPI(new Mixin());
        GlassLoader.getInstance().runHooks("mixin");

        GlassLoader.getInstance().registerTransformer(GlassMixinTransformer.class);
    }

}
