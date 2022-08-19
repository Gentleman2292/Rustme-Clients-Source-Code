/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.utils;

import net.minecraft.client.Minecraft;

public class MoveUtils {
    public static Minecraft mc = Minecraft.getMinecraft();

    public static float getMoveDirection() {
        double motionX = Minecraft.player.motionX;
        double motionZ = Minecraft.player.motionZ;
        float direction = (float)(Math.atan2(motionX, motionZ) / Math.PI * 180.0);
        return -direction;
    }

    public static float getDirection() {
        float rotationYaw = Minecraft.player.rotationYaw;
        float factor = 0.0f;
        if (Minecraft.player.movementInput.field_192832_b > 0.0f) {
            factor = 1.0f;
        }
        if (Minecraft.player.movementInput.field_192832_b < 0.0f) {
            factor = -1.0f;
        }
        if (factor == 0.0f) {
            if (Minecraft.player.movementInput.moveStrafe > 0.0f) {
                rotationYaw -= 90.0f;
            }
            if (Minecraft.player.movementInput.moveStrafe < 0.0f) {
                rotationYaw += 90.0f;
            }
        } else {
            if (Minecraft.player.movementInput.moveStrafe > 0.0f) {
                rotationYaw -= 45.0f * factor;
            }
            if (Minecraft.player.movementInput.moveStrafe < 0.0f) {
                rotationYaw += 45.0f * factor;
            }
        }
        if (factor < 0.0f) {
            rotationYaw -= 180.0f;
        }
        return (float)Math.toRadians(rotationYaw);
    }

    public static float getDirection2() {
        Minecraft mc = Minecraft.getMinecraft();
        float var1 = Minecraft.player.rotationYaw;
        if (Minecraft.player.moveForward < 0.0f) {
            var1 += 180.0f;
        }
        float forward = 1.0f;
        if (Minecraft.player.moveForward < 0.0f) {
            forward = -50.5f;
        } else if (Minecraft.player.moveForward > 0.0f) {
            forward = 50.5f;
        }
        if (Minecraft.player.moveStrafing > 0.0f) {
            var1 -= 22.0f * forward;
        }
        if (Minecraft.player.moveStrafing < 0.0f) {
            var1 += 22.0f * forward;
        }
        return var1 *= (float)Math.PI / 180;
    }

    public static double getXDirAt(float angle) {
        double rot = 90.0;
        return Math.cos((rot + (double)angle) * Math.PI / 180.0);
    }

    public static double getZDirAt(float angle) {
        double rot = 90.0;
        return Math.sin((rot + (double)angle) * Math.PI / 180.0);
    }

    public static boolean isOnGround() {
        return !Minecraft.player.onGround ? false : Minecraft.player.isCollidedVertically;
    }

    public static void setSpeed(double d, float f, double d2, double d3) {
        double d4 = d3;
        double d5 = d2;
        float f2 = f;
        if (d3 == 0.0 && d2 == 0.0) {
            Minecraft.player.motionZ = 0.0;
            Minecraft.player.motionX = 0.0;
        } else {
            if (d3 != 0.0) {
                if (d2 > 0.0) {
                    f2 = f + (float)(d3 > 0.0 ? -45 : 45);
                } else if (d2 < 0.0) {
                    f2 = f + (float)(d3 > 0.0 ? 45 : -45);
                }
                d5 = 0.0;
                if (d3 > 0.0) {
                    d4 = 1.0;
                } else if (d3 < 0.0) {
                    d4 = -1.0;
                }
            }
            double d6 = Math.cos(Math.toRadians(f2 + 90.0f));
            double d7 = Math.sin(Math.toRadians(f2 + 90.0f));
            Minecraft.player.motionX = d4 * d * d6 + d5 * d * d7;
            Minecraft.player.motionZ = d4 * d * d7 - d5 * d * d6;
        }
    }

    public static void strafe() {
        if (!MoveUtils.mc.gameSettings.keyBindBack.isKeyDown()) {
            MoveUtils.strafe(MoveUtils.getSpeed());
        }
    }

    public static float getSpeed() {
        return (float)Math.sqrt(Minecraft.player.motionX * Minecraft.player.motionX + Minecraft.player.motionZ * Minecraft.player.motionZ);
    }

    public static boolean isMoving() {
        if (Minecraft.player == null) {
            return false;
        }
        if (Minecraft.player.movementInput.field_192832_b != 0.0f) {
            return true;
        }
        return Minecraft.player.movementInput.moveStrafe != 0.0f;
    }

    public static void strafe(float speed) {
        if (MoveUtils.isMoving()) {
            double yaw = MoveUtils.getDirection();
            Minecraft.player.motionX = -Math.sin(yaw) * (double)speed;
            Minecraft.player.motionZ = Math.cos(yaw) * (double)speed;
        }
    }

    public static void setSpeed(double speed) {
        Minecraft.getMinecraft();
        double forward = Minecraft.player.movementInput.field_192832_b;
        Minecraft.getMinecraft();
        double strafe = Minecraft.player.movementInput.moveStrafe;
        Minecraft.getMinecraft();
        float yaw = Minecraft.player.rotationYaw;
        if (forward == 0.0 && strafe == 0.0) {
            Minecraft.getMinecraft();
            Minecraft.player.motionX = 0.0;
            Minecraft.getMinecraft();
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
            Minecraft.getMinecraft();
            Minecraft.player.motionX = forward * speed * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * speed * Math.sin(Math.toRadians(yaw + 90.0f));
            Minecraft.getMinecraft();
            Minecraft.player.motionZ = forward * speed * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * speed * Math.cos(Math.toRadians(yaw + 90.0f));
        }
    }

    public static void unsafeSetSpeed(double speed) {
        Minecraft.getMinecraft();
        double strafe = Minecraft.player.movementInput.moveStrafe;
        Minecraft.getMinecraft();
        float yaw = Minecraft.player.rotationYaw;
        if (strafe == 0.0) {
            Minecraft.getMinecraft();
            Minecraft.player.motionX = 0.0;
            Minecraft.getMinecraft();
            Minecraft.player.motionZ = 0.0;
        }
        Minecraft.getMinecraft();
        Minecraft.player.motionX = speed * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * speed * Math.sin(Math.toRadians(yaw + 90.0f));
        Minecraft.getMinecraft();
        Minecraft.player.motionZ = speed * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * speed * Math.cos(Math.toRadians(yaw + 90.0f));
    }
}

