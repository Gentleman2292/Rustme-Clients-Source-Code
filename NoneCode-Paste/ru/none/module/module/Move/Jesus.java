/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;

public class Jesus
extends Module {
    FloatSetting speed;
    ModeSetting mode;
    ArrayList<String> modes = new ArrayList();

    public Jesus() {
        super("Jesus", Category.Move);
        this.modes.add("MatrixZoom");
        this.modes.add("ReallyWorld");
        this.mode = new ModeSetting("Mode", this, this.modes, "MatrixZoom");
        None.settingManager.add(this.mode);
        this.speed = new FloatSetting("Speed", this, 0.0f, 10.0f, 9.9f);
        None.settingManager.add(this.speed);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        switch (this.mode.getVal()) {
            case "MatrixZoom": {
                double x = this.mc.player.posX;
                double y = this.mc.player.posY;
                double z = this.mc.player.posZ;
                if (this.mc.world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x + 0.3, y, z)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x - 0.3, y, z)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x, y, z + 0.3)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x, y, z - 0.3)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x + 0.3, y, z + 0.3)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x - 0.3, y, z - 0.3)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x - 0.3, y, z + 0.3)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x + 0.3, y, z - 0.3)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.LAVA || this.mc.world.getBlockState(new BlockPos(x + 0.3, y, z)).getBlock() == Blocks.LAVA || this.mc.world.getBlockState(new BlockPos(x - 0.3, y, z)).getBlock() == Blocks.LAVA || this.mc.world.getBlockState(new BlockPos(x, y, z + 0.3)).getBlock() == Blocks.LAVA || this.mc.world.getBlockState(new BlockPos(x, y, z - 0.3)).getBlock() == Blocks.LAVA || this.mc.world.getBlockState(new BlockPos(x + 0.3, y, z + 0.3)).getBlock() == Blocks.LAVA || this.mc.world.getBlockState(new BlockPos(x - 0.3, y, z - 0.3)).getBlock() == Blocks.LAVA || this.mc.world.getBlockState(new BlockPos(x - 0.3, y, z + 0.3)).getBlock() == Blocks.LAVA || this.mc.world.getBlockState(new BlockPos(x + 0.3, y, z - 0.3)).getBlock() == Blocks.LAVA) {
                    if (!this.mc.player.isInWater() || !this.mc.player.isInLava()) {
                        this.mc.player.motionX = 0.0;
                        this.mc.player.motionZ = 0.0;
                    }
                    this.mc.player.motionY = 0.0391f;
                    this.mc.player.speedInAir = this.speed.getVal() / 1.345f;
                    this.mc.player.jumpMovementFactor = 0.0f;
                    if (this.mc.player.fallDistance >= 3.0f) {
                        this.mc.player.motionY = -0.42;
                    }
                    if (this.mc.player.motionY == -0.42 && this.mc.player.fallDistance == 0.0f) {
                        this.mc.player.motionY = 0.185;
                    }
                    if (this.mc.player.isCollidedHorizontally) {
                        if (this.mc.player.isJumping) {
                            this.mc.player.motionY = 0.419999024;
                        }
                        this.mc.player.speedInAir = 0.02f;
                        this.mc.player.jumpMovementFactor = 0.26f;
                    }
                } else if (this.mc.player.speedInAir == this.speed.getVal() / 1.345f) {
                    this.mc.player.speedInAir = 0.02f;
                }
                if (this.mc.player.isInWater() || this.mc.player.isInLava()) {
                    this.mc.player.motionY = this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY + 1.5, this.mc.player.posZ)).getBlock() == Blocks.WATER || this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY + 1.5, this.mc.player.posZ)).getBlock() == Blocks.LAVA ? (this.mc.player.motionY += 0.099) : (this.mc.player.motionY += 0.167);
                }
            }
            case "ReallyWorld": {
                Block block2 = this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY - 0.02, this.mc.player.posZ)).getBlock();
                if (this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY - 0.5, this.mc.player.posZ)).getBlock() == Blocks.WATER && this.mc.player.onGround) {
                    this.mc.player.motionY = 0.1;
                }
                if (block2 instanceof BlockLiquid && !this.mc.player.onGround) {
                    if (this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY + 0.15, this.mc.player.posZ)).getBlock() == Blocks.WATER) {
                        this.mc.player.motionY = 0.1f;
                        this.setSpeed(0.07f);
                        this.mc.player.jumpMovementFactor = 0.0f;
                    } else {
                        this.setSpeed(1.1f);
                        this.mc.player.motionY = -0.1f;
                    }
                    if (this.mc.player.isCollidedHorizontally) {
                        double d = this.mc.player.motionY = this.mc.player.fallDistance > 3.0f ? -0.4 : (double)0.2f;
                    }
                }
                if (!(this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY + 0.2, this.mc.player.posZ)).getBlock() instanceof BlockLiquid)) break;
                this.mc.player.motionY = 0.14;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.player.speedInAir = 0.02f;
    }

    public void setSpeed(double speed) {
        double forward = this.mc.player.movementInput.field_192832_b;
        double strafe = this.mc.player.movementInput.moveStrafe;
        float yaw = this.mc.player.rotationYaw;
        if (forward == 0.0 && strafe == 0.0) {
            this.mc.player.motionX = 0.0;
            this.mc.player.motionZ = 0.0;
        } else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += (float)(forward > 0.0 ? -45 : 45);
                } else if (strafe < 0.0) {
                    yaw += (float)(forward > 0.0 ? 45 : -45);
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                } else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
            this.mc.player.motionX = forward * speed * Math.cos(Math.toRadians((double)yaw + 90.0)) + strafe * speed * Math.sin(Math.toRadians((double)yaw + 90.0));
            this.mc.player.motionZ = forward * speed * Math.sin(Math.toRadians((double)yaw + 90.0)) - strafe * speed * Math.cos(Math.toRadians((double)yaw + 90.0));
        }
    }
}

