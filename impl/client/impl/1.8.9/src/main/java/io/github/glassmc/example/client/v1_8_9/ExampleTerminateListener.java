package io.github.glassmc.example.client.v1_8_9;

import io.github.glassmc.loader.GlassLoader;
import io.github.glassmc.loader.Listener;
import io.github.glassmc.loader.util.Identifier;

public class ExampleTerminateListener implements Listener {

    @Override
    public void run() {
        GlassLoader.getInstance().unregisterTransformer(MinecraftClientTransformer.class);
        GlassLoader.getInstance().registerReloadClass(Identifier.parse("net/minecraft/client/MinecraftClient").getClassName());
    }

}
