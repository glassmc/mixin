package io.github.glassmc.example.client.v1_17;

import io.github.glassmc.example.client.IExampleInterface;
import net.minecraft.client.MinecraftClient;

public class ExampleInterfaceImpl implements IExampleInterface {

    @Override
    public boolean isMAC() {
        return MinecraftClient.IS_SYSTEM_MAC;
    }

}
