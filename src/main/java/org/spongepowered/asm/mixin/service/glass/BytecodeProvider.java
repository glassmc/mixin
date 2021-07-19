package org.spongepowered.asm.mixin.service.glass;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.service.IClassBytecodeProvider;

import java.io.IOException;
import java.io.InputStream;

public class BytecodeProvider implements IClassBytecodeProvider {

    @Override
    public ClassNode getClassNode(String name) throws IOException {
        InputStream classInputStream = this.getClass().getClassLoader().getResourceAsStream(name.replace(".", "/") + ".class");

        ClassNode classNode = new ClassNode();
        byte[] data = new byte[classInputStream.available()];
        classInputStream.read(data);
        ClassReader classReader = new ClassReader(data);
        classReader.accept(classNode, 0);
        return classNode;
    }

    @Override
    public ClassNode getClassNode(String name, boolean runTransformers) throws IOException {
        return this.getClassNode(name);
    }

}
