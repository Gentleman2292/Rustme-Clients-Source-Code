/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.input.Mouse
 */
package ru.none.module.module.Misc;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.input.Mouse;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class AutoTool
extends Module {
    public AutoTool() {
        super("AutoTool", Category.Misc);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (Mouse.isButtonDown((int)this.mc.gameSettings.keyBindAttack.getKeyCode())) {
            Block block = (Block)this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos());
            BlockPos blockPos = this.mc.objectMouseOver.getBlockPos();
        }
    }
}

