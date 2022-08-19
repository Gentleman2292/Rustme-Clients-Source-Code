/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.MoveUtils;

public class AirJump
extends Module {
    ModeSetting mode;

    public AirJump() {
        super("AirJump", Category.Move);
        ArrayList<String> modes = new ArrayList<String>();
        modes.add("Default");
        modes.add("Matrix");
        this.mode = new ModeSetting("Mode", this, modes, "Default");
        None.settingManager.add(this.mode);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onUpdate(PlayerHook hook) {
        block9: {
            block8: {
                super.onUpdate(hook);
                if (!this.mode.getVal().equalsIgnoreCase("Matrix")) return;
                float ex = 1.0f;
                float ex2 = Minecraft.player.isJumping() ? 1.0f : 0.25f;
                Minecraft.player.jumpTicks = 1;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY - (double)ex, Minecraft.player.posZ)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY - (double)ex, Minecraft.player.posZ)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX - (double)ex2, Minecraft.player.posY - (double)ex, Minecraft.player.posZ - (double)ex2)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX + (double)ex2, Minecraft.player.posY - (double)ex, Minecraft.player.posZ + (double)ex2)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX - (double)ex2, Minecraft.player.posY - (double)ex, Minecraft.player.posZ + (double)ex2)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX + (double)ex2, Minecraft.player.posY - (double)ex, Minecraft.player.posZ - (double)ex2)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX + (double)ex2, Minecraft.player.posY - (double)ex, Minecraft.player.posZ)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX - (double)ex2, Minecraft.player.posY - (double)ex, Minecraft.player.posZ)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY - (double)ex, Minecraft.player.posZ + (double)ex2)).getBlock() != Blocks.AIR) break block8;
                if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY - (double)ex, Minecraft.player.posZ - (double)ex2)).getBlock() == Blocks.AIR) break block9;
            }
            if (AirJump.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY + (double)2.1f, Minecraft.player.posZ)).getBlock() != Blocks.AIR) {
                if (!Minecraft.player.isCollidedHorizontally) {
                    Minecraft.player.onGround = true;
                    MoveUtils.setSpeed(MoveUtils.getSpeed());
                    Minecraft.player.motionY = 0.0;
                    Minecraft.player.fallDistance = 0.0f;
                }
            }
        }
        if (Minecraft.player.isCollidedHorizontally) {
            if (Minecraft.player.ticksExisted % 4 == 0) {
                Minecraft.player.onGround = true;
                Minecraft.player.jump();
                return;
            }
        }
        if (Minecraft.player.fallDistance != 0.0f) {
            Minecraft.player.fallDistance = 0.0f;
            Minecraft.player.jump();
            Minecraft.player.motionY = 0.4101f;
            return;
        }
        Minecraft.player.onGround = true;
    }
}

