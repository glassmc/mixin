package com.github.glassmc.mixin;

import com.github.glassmc.loader.api.ClassDefinition;
import com.github.glassmc.loader.api.InternalLoader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.util.List;

public class MixinInternalLoader implements InternalLoader {


    @Override
    public void filterClasses(String className, List<ClassDefinition> possibleClasses) {
        if (className.equals("com/google/common/io/Closeables")) {
            possibleClasses.removeIf(classDefinition -> {
                ClassReader classReader = new ClassReader(classDefinition.getData());
                ClassNode classNode = new ClassNode();
                classReader.accept(classNode, 0);

                return classNode.methods.stream().noneMatch(methodNode -> methodNode.name.equals("closeQuietly") && methodNode.desc.equals("(Ljava/io/Reader;)V"));
            });
        }
    }

}
