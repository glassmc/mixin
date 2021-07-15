package com.github.glassmc.example.client.v1_8_9;

import com.github.glassmc.loader.GlassLoader;
import com.github.glassmc.loader.Listener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.transformer.GlassMixinTransformer;

public class ExampleInitializeListener implements Listener {

    @Override
    public void run() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.test.json");

        GlassLoader.getInstance().registerTransformer(GlassMixinTransformer.class);
    }

}
