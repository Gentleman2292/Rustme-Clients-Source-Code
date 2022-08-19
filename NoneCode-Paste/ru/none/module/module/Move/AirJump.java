/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
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

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (this.mode.getVal().equalsIgnoreCase("Matrix")) {
            float ex = 1.0f;
            float ex2 = this.mc.player.isJumping() ? 1.0f : 0.25f;
            this.mc.player.jumpTicks = 1;
            if (!(this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY - (double)ex, this.mc.player.posZ)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY - (double)ex, this.mc.player.posZ)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX - (double)ex2, this.mc.player.posY - (double)ex, this.mc.player.posZ - (double)ex2)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX + (double)ex2, this.mc.player.posY - (double)ex, this.mc.player.posZ + (double)ex2)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX - (double)ex2, this.mc.player.posY - (double)ex, this.mc.player.posZ + (double)ex2)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX + (double)ex2, this.mc.player.posY - (double)ex, this.mc.player.posZ - (double)ex2)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX + (double)ex2, this.mc.player.posY - (double)ex, this.mc.player.posZ)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX - (double)ex2, this.mc.player.posY - (double)ex, this.mc.player.posZ)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY - (double)ex, this.mc.player.posZ + (double)ex2)).getBlock() == Blocks.AIR && this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY - (double)ex, this.mc.player.posZ - (double)ex2)).getBlock() == Blocks.AIR || this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY + (double)2.1f, this.mc.player.posZ)).getBlock() == Blocks.AIR || this.mc.player.isCollidedHorizontally)) {
                this.mc.player.onGround = true;
                MoveUtils.setSpeed(MoveUtils.getSpeed());
                this.mc.player.motionY = 0.0;
                this.mc.player.fallDistance = 0.0f;
            }
            if (this.mc.player.isCollidedHorizontally && this.mc.player.ticksExisted % 4 == 0) {
                this.mc.player.onGround = true;
                this.mc.player.jump();
            } else if (this.mc.player.fallDistance != 0.0f) {
                this.mc.player.fallDistance = 0.0f;
                this.mc.player.jump();
                this.mc.player.motionY = 0.4101f;
            } else {
                this.mc.player.onGround = true;
            }
        }
    }
}

