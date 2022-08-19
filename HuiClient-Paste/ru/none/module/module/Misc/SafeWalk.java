/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.input.Keyboard
 */
package ru.none.module.module.Misc;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.input.Keyboard;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class SafeWalk
extends Module {
    public SafeWalk() {
        super("SafeWalk", Category.Misc);
    }

    public static boolean isCollidable(Block block) {
        return block != Blocks.AIR && block != Blocks.BEETROOTS && block != Blocks.CARROTS && block != Blocks.DEADBUSH && block != Blocks.DOUBLE_PLANT && block != Blocks.FLOWING_LAVA && block != Blocks.FLOWING_WATER && block != Blocks.LAVA && block != Blocks.MELON_STEM && block != Blocks.NETHER_WART && block != Blocks.POTATOES && block != Blocks.PUMPKIN_STEM && block != Blocks.RED_FLOWER && block != Blocks.RED_MUSHROOM && block != Blocks.REDSTONE_TORCH && block != Blocks.TALLGRASS && block != Blocks.TORCH && block != Blocks.UNLIT_REDSTONE_TORCH && block != Blocks.YELLOW_FLOWER && block != Blocks.VINE && block != Blocks.WATER && block != Blocks.WEB && block != Blocks.WHEAT;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (Minecraft.player.onGround && !SafeWalk.mc.gameSettings.keyBindJump.isPressed()) {
            if (!SafeWalk.isCollidable(SafeWalk.mc.world.getBlockState(new BlockPos(Minecraft.player.getPositionVector().add(new Vec3d(0.0, -0.5, 0.0)))).getBlock())) {
                KeyBinding.setKeyBindState((int)Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), (boolean)true);
                return;
            }
        }
        if (Keyboard.isKeyDown((int)Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())) return;
        KeyBinding.setKeyBindState((int)Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), (boolean)false);
    }
}

