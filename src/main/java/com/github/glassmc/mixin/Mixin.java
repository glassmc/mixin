package com.github.glassmc.mixin;

import org.spongepowered.asm.mixin.Mixins;

public class Mixin {

    public void addConfiguration(String path) {
        Mixins.addConfiguration(path);
    }

}
