package io.github.glassmc.example.client.v1_17;

import io.github.glassmc.example.client.IExampleInterface;
import io.github.glassmc.loader.GlassLoader;
import io.github.glassmc.loader.Listener;

public class ExampleInitializeListener implements Listener {

    @Override
    public void run() {
        GlassLoader.getInstance().registerInterface(IExampleInterface.class, new ExampleInterfaceImpl());
    }

}
