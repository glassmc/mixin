package io.github.glassmc.example.client.v1_8_9;

import io.github.glassmc.loader.loader.ITransformer;
import io.github.glassmc.loader.util.Identifier;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class MinecraftClientTransformer implements ITransformer {

    private final Identifier MINECRAFT_CLIENT = Identifier.parse("net/minecraft/client/MinecraftClient");
    private final Identifier RUN_GAME_LOOP = Identifier.parse("net/minecraft/client/MinecraftClient#runGameLoop()V");

    @Override
    public byte[] transform(String name, byte[] data) {
        if(name.equals(MINECRAFT_CLIENT.getClassName())) {
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(data);
            classReader.accept(classNode, 0);

            for(MethodNode methodNode : classNode.methods) {
                if(methodNode.name.equals(RUN_GAME_LOOP.getMethodName()) && methodNode.desc.equals(RUN_GAME_LOOP.getMethodDesc())) {
                    methodNode.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, Hook.class.getName().replace(".", "/"), "test", "()V"));
                }
            }

            ClassWriter classWriter = new ClassWriter(0);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }
        return data;
    }

}
