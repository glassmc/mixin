package org.spongepowered.asm.mixin.transformer;

import com.github.glassmc.loader.api.loader.Transformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.service.glass.BytecodeProvider;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;
import org.spongepowered.asm.service.MixinService;

public class GlassMixinTransformer implements Transformer {

    private final MixinTransformer mixinTransformer = new MixinTransformer();

    private final MixinEnvironment environment;

    public GlassMixinTransformer() {
        this.environment = MixinEnvironment.getDefaultEnvironment();
        SyntheticClassRegistry syntheticClassRegistry = new SyntheticClassRegistry();
        Extensions extensions = new Extensions(syntheticClassRegistry);
        MixinCoprocessorNestHost mixinCoprocessorNestHost = new MixinCoprocessorNestHost();

        DefaultExtensions.create(environment, extensions, syntheticClassRegistry, mixinCoprocessorNestHost);
    }

    @Override
    public boolean canTransform(String name) {
        return !name.startsWith("org/objectweb/") && !name.startsWith("org/spongepowered/") && !name.startsWith("com/google/");
    }

    @Override
    public byte[] transform(String name, byte[] data) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(data);
        classReader.accept(classNode, 0);

        BytecodeProvider bytecodeProvider = (BytecodeProvider) MixinService.getService().getBytecodeProvider();

        if(!bytecodeProvider.getIgnore().contains(name)) {
            if (this.mixinTransformer.transformClass(environment, name, classNode)) {
                ClassWriter classWriter = new ClassWriter(0);
                classNode.accept(classWriter);
                return classWriter.toByteArray();
            }
        } else {
            bytecodeProvider.getIgnore().remove(name);
        }

        return data;
    }
}
