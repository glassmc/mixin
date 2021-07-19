package org.spongepowered.asm.mixin.transformer;

import com.github.glassmc.loader.loader.ITransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.transformer.ext.Extensions;

public class GlassMixinTransformer implements ITransformer {

    private final MixinProcessor processor;

    public GlassMixinTransformer() {
        MixinEnvironment environment = MixinEnvironment.getDefaultEnvironment();
        SyntheticClassRegistry syntheticClassRegistry = new SyntheticClassRegistry();
        Extensions extensions = new Extensions(syntheticClassRegistry);
        this.processor = new MixinProcessor(environment, extensions, null);

        DefaultExtensions.create(environment, extensions, syntheticClassRegistry);
    }

    @Override
    public byte[] transform(String name, byte[] data) {
        if(!name.startsWith("org.objectweb") && !name.startsWith("org.spongepowered") && !name.startsWith("com.google")) {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(data);
            classReader.accept(classNode, ClassReader.EXPAND_FRAMES);

            if(this.processor.applyMixins(MixinEnvironment.getDefaultEnvironment(), name, classNode)) {
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                classNode.accept(classWriter);
                return classWriter.toByteArray();
            }
        }
        return data;
    }
}
