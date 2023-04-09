package io.github.zekerzhayard.npe_nbthelper;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class MixinConfigPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        MappingResolver mr = FabricLoader.getInstance().getMappingResolver();
        for (MethodNode mn : targetClass.methods) {
            if (Objects.equals(mn.name, mr.mapMethodName("intermediary", "net.minecraft.class_2512", "method_25930", "(Lnet/minecraft/class_2520;)Ljava/util/UUID;")) && Objects.equals(mn.desc, "(L" + mr.mapClassName("intermediary", "net.minecraft.class_2520").replace('.', '/') + ";)Ljava/util/UUID;")) {
                InsnList il = new InsnList();
                LabelNode ln = new LabelNode();
                il.add(new VarInsnNode(Opcodes.ALOAD, 0));
                il.add(new JumpInsnNode(Opcodes.IFNONNULL, ln));
                il.add(new InsnNode(Opcodes.ACONST_NULL));
                il.add(new InsnNode(Opcodes.ARETURN));
                il.add(ln);
                mn.instructions.insert(il);
            }
        }
    }
}
