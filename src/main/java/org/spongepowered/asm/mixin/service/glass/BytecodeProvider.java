package org.spongepowered.asm.mixin.service.glass;

import com.github.glassmc.loader.api.GlassLoader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.IClassBytecodeProvider;

import java.util.ArrayList;
import java.util.List;

public class BytecodeProvider implements IClassBytecodeProvider {

    private final List<String> ignore = new ArrayList<>();

    @Override
    public ClassNode getClassNode(String name) {
        this.ignore.add(name);
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(GlassLoader.getInstance().getClassBytes(name.replace(".", "/")));
        classReader.accept(classNode, 0);
        return classNode;
    }

    @Override
    public ClassNode getClassNode(String name, boolean runTransformers) {
        return this.getClassNode(name);
    }

    public List<String> getIgnore() {
        return ignore;
    }

}
