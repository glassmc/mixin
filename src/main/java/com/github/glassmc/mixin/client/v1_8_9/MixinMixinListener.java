package com.github.glassmc.mixin.client.v1_8_9;

import com.github.glassmc.loader.api.GlassLoader;
import com.github.glassmc.loader.api.Listener;
import com.github.glassmc.mixin.Mixin;

public class MixinMixinListener implements Listener {

    @Override
    public void run() {
        if(this.isDevelopmentEnvironment()) {
            GlassLoader.getInstance().getAPI(Mixin.class).addConfiguration("mixins.test.json");
        }
    }

    private boolean isDevelopmentEnvironment() {
        return false;
        //return MixinMixinListener.class.getProtectionDomain().getCodeSource().getLocation() == null;
    }

}
