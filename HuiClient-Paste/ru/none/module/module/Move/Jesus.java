/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.Minecraft
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
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

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        var2_2 = this.mode.getVal();
        var3_3 = -1;
        switch (var2_2.hashCode()) {
            case 42353492: {
                if (!var2_2.equals("MatrixZoom")) break;
                var3_3 = 0;
                break;
            }
            case 935423623: {
                if (!var2_2.equals("ReallyWorld")) break;
                var3_3 = 1;
                break;
            }
        }
        switch (var3_3) {
            case 0: {
                x = Minecraft.player.posX;
                y = Minecraft.player.posY;
                z = Minecraft.player.posZ;
                if (Jesus.mc.world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x + 0.3, y, z)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x - 0.3, y, z)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x, y, z + 0.3)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x, y, z - 0.3)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x + 0.3, y, z + 0.3)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x - 0.3, y, z - 0.3)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x - 0.3, y, z + 0.3)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x + 0.3, y, z - 0.3)).getBlock() == Blocks.WATER || Jesus.mc.world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.LAVA || Jesus.mc.world.getBlockState(new BlockPos(x + 0.3, y, z)).getBlock() == Blocks.LAVA || Jesus.mc.world.getBlockState(new BlockPos(x - 0.3, y, z)).getBlock() == Blocks.LAVA || Jesus.mc.world.getBlockState(new BlockPos(x, y, z + 0.3)).getBlock() == Blocks.LAVA || Jesus.mc.world.getBlockState(new BlockPos(x, y, z - 0.3)).getBlock() == Blocks.LAVA || Jesus.mc.world.getBlockState(new BlockPos(x + 0.3, y, z + 0.3)).getBlock() == Blocks.LAVA || Jesus.mc.world.getBlockState(new BlockPos(x - 0.3, y, z - 0.3)).getBlock() == Blocks.LAVA || Jesus.mc.world.getBlockState(new BlockPos(x - 0.3, y, z + 0.3)).getBlock() == Blocks.LAVA) ** GOTO lbl24
                if (Jesus.mc.world.getBlockState(new BlockPos(x + 0.3, y, z - 0.3)).getBlock() != Blocks.LAVA) ** GOTO lbl58
lbl24:
                // 2 sources

                if (!Minecraft.player.isInWater()) ** GOTO lbl28
                if (Minecraft.player.isInLava()) ** GOTO lbl32
lbl28:
                // 2 sources

                Minecraft.player.motionX = 0.0;
                Minecraft.player.motionZ = 0.0;
lbl32:
                // 2 sources

                Minecraft.player.motionY = 0.03909999877214432;
                Minecraft.player.speedInAir = this.speed.getVal() / 1.345f;
                Minecraft.player.jumpMovementFactor = 0.0f;
                if (Minecraft.player.fallDistance >= 3.0f) {
                    Minecraft.player.motionY = -0.42;
                }
                if (Minecraft.player.motionY == -0.42) {
                    if (Minecraft.player.fallDistance == 0.0f) {
                        Minecraft.player.motionY = 0.185;
                    }
                }
                if (Minecraft.player.isCollidedHorizontally) {
                    if (Minecraft.player.isJumping) {
                        Minecraft.player.motionY = 0.419999024;
                    }
                    Minecraft.player.speedInAir = 0.02f;
                    Minecraft.player.jumpMovementFactor = 0.26f;
                }
                ** GOTO lbl62
            }
lbl58:
            // 1 sources

            if (Minecraft.player.speedInAir == this.speed.getVal() / 1.345f) {
                Minecraft.player.speedInAir = 0.02f;
            }
lbl62:
            // 4 sources

            if (Minecraft.player.isInWater()) ** GOTO lbl68
            if (!Minecraft.player.isInLava()) ** GOTO lbl77
lbl68:
            // 2 sources

            if (Jesus.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY + 1.5, Minecraft.player.posZ)).getBlock() == Blocks.WATER) ** GOTO lbl74
            if (Jesus.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY + 1.5, Minecraft.player.posZ)).getBlock() == Blocks.LAVA) {
lbl74:
                // 2 sources

                Minecraft.player.motionY += 0.099;
            } else {
                Minecraft.player.motionY += 0.167;
            }
lbl77:
            // 4 sources

            case 1: {
                block2 = Jesus.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY - 0.02, Minecraft.player.posZ)).getBlock();
                if (Jesus.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY - 0.5, Minecraft.player.posZ)).getBlock() == Blocks.WATER) {
                    if (Minecraft.player.onGround) {
                        Minecraft.player.motionY = 0.1;
                    }
                }
                if (block2 instanceof BlockLiquid) {
                    if (!Minecraft.player.onGround) {
                        if (Jesus.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY + 0.15, Minecraft.player.posZ)).getBlock() == Blocks.WATER) {
                            Minecraft.player.motionY = 0.10000000149011612;
                            this.setSpeed(0.07000000029802322);
                            Minecraft.player.jumpMovementFactor = 0.0f;
                        } else {
                            this.setSpeed(1.100000023841858);
                            Minecraft.player.motionY = -0.10000000149011612;
                        }
                        if (Minecraft.player.isCollidedHorizontally) {
                            Minecraft.player.motionY = Minecraft.player.fallDistance > 3.0f ? -0.4 : 0.20000000298023224;
                        }
                    }
                }
                if (Jesus.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY + 0.2, Minecraft.player.posZ)).getBlock() instanceof BlockLiquid == false) return;
                Minecraft.player.motionY = 0.14;
                return;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Minecraft.player.speedInAir = 0.02f;
    }

    public void setSpeed(double speed) {
        double forward = Minecraft.player.movementInput.field_192832_b;
        double strafe = Minecraft.player.movementInput.moveStrafe;
        float yaw = Minecraft.player.rotationYaw;
        if (forward == 0.0 && strafe == 0.0) {
            Minecraft.player.motionX = 0.0;
            Minecraft.player.motionZ = 0.0;
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
            Minecraft.player.motionX = forward * speed * Math.cos(Math.toRadians((double)yaw + 90.0)) + strafe * speed * Math.sin(Math.toRadians((double)yaw + 90.0));
            Minecraft.player.motionZ = forward * speed * Math.sin(Math.toRadians((double)yaw + 90.0)) - strafe * speed * Math.cos(Math.toRadians((double)yaw + 90.0));
        }
    }
}

