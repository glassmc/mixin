package com.github.glassmc.mixin.client.v1_8_9;

import com.github.glassmc.loader.GlassLoader;
import com.github.glassmc.loader.Listener;
import com.github.glassmc.mixin.Mixin;

public class MixinMixinListener implements Listener {

    @Override
    public void run() {
        GlassLoader.getInstance().getAPI(Mixin.class).addConfiguration("mixins.test.json");
    }

}
