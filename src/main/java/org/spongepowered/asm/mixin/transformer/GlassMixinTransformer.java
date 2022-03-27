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

    private final MixinEnvironment environment;
    private final MixinProcessor processor;

    public GlassMixinTransformer() {
        this.environment = MixinEnvironment.getDefaultEnvironment();
        SyntheticClassRegistry syntheticClassRegistry = new SyntheticClassRegistry();
        Extensions extensions = new Extensions(syntheticClassRegistry);
        MixinCoprocessorNestHost mixinCoprocessorNestHost = new MixinCoprocessorNestHost();
        this.processor = new MixinProcessor(environment, extensions, null, mixinCoprocessorNestHost);

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

        //System.out.println(name + " " + bytecodeProvider.getIgnore().contains(name) + " " + this.processor.applyMixins(this.environment, name, classNode));

        if(!bytecodeProvider.getIgnore().contains(name) && this.processor.applyMixins(this.environment, name, classNode)) {
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        } else {
            bytecodeProvider.getIgnore().remove(name);
            return data;
        }
    }
}
