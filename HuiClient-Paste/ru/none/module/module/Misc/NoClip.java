/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.util.text.TextFormatting
 */
package ru.none.module.module.Misc;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.text.TextFormatting;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.ModeSetting;

public class NoClip
extends Module {
    BooleanSetting Up;
    ArrayList<String> modes = new ArrayList();
    ModeSetting mode;

    public NoClip() {
        super("NoClip", Category.Misc);
        this.modes.add("Vanila");
        this.modes.add("Matrix");
        this.modes.add("MatrixUp");
        this.mode = new ModeSetting("Mode", this, this.modes, "Vanila");
        None.settingManager.add(this.mode);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        this.setDisplayName(this.getName() + " " + (Object)TextFormatting.GRAY + this.mode.getVal());
        if (this.mode.getVal().equalsIgnoreCase("Matrix")) {
            Minecraft.player.motionY = 0.0;
            Minecraft.player.motionX = 0.0;
            Minecraft.player.motionZ = 0.0;
            Minecraft.player.onGround = false;
            Minecraft.player.jumpMovementFactor = 0.0f;
            if (NoClip.mc.gameSettings.keyBindJump.pressed) {
                Minecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Minecraft.player.posX, Minecraft.player.posY + 20.0, Minecraft.player.posZ, true));
            }
            if (NoClip.mc.gameSettings.keyBindForward.pressed) {
                Minecraft.getMinecraft();
                double d = Minecraft.player.posX - Math.sin(Math.toRadians(Minecraft.player.rotationYaw)) * 15.0;
                Minecraft.getMinecraft();
                Minecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, Minecraft.player.posY, Minecraft.player.posZ + Math.cos(Math.toRadians(Minecraft.player.rotationYaw)) * 15.0, true));
                Minecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Minecraft.player.posX, Minecraft.player.posY - 15.0, Minecraft.player.posZ, true));
                if (Minecraft.player.ticksExisted % 9 == 0) {
                    Minecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Minecraft.player.posX, Minecraft.player.posY + 1.0, Minecraft.player.posZ, true));
                }
            }
            if (NoClip.mc.gameSettings.keyBindSneak.pressed) {
                Minecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Minecraft.player.posX, Minecraft.player.posY - 2.0, Minecraft.player.posZ, true));
            }
        }
        if (this.mode.getVal().equalsIgnoreCase("MatrixUp")) {
            if (Minecraft.player.isJumping) {
                if (Minecraft.player.hurtTime != 0) {
                    Minecraft.player.motionX = 20.0;
                    Minecraft.player.motionZ = 20.0;
                    if (NoClip.mc.gameSettings.keyBindJump.isKeyDown()) {
                        Minecraft.player.jump();
                    }
                    if (Minecraft.player.ticksExisted % 2 == 0) {
                        Minecraft.player.motionX = -Minecraft.player.motionX;
                        Minecraft.player.motionZ = -Minecraft.player.motionZ;
                    }
                }
            }
        }
        if (this.mode.getVal().equalsIgnoreCase("Vanila")) {
            Minecraft.player.noClip = true;
            Minecraft.player.stepHeight = 0.0f;
            double x = Minecraft.player.posX;
            double y = Minecraft.player.posY;
            double z = Minecraft.player.posZ;
            double yaw = (double)Minecraft.player.rotationYaw * 0.017453292;
            float Speed2 = 0.002494945f;
            float SpeedY = 1.0f;
            if (Minecraft.player.isJumping) {
                if (Minecraft.player.hurtTime == 0) {
                    Minecraft.player.motionY = 1.0;
                }
            }
            if (NoClip.mc.gameSettings.keyBindSneak.isKeyDown()) {
                Minecraft.player.motionY = -1.0;
                Minecraft.player.setPosition(x, y - (double)0.002494945f, z);
            }
            if (NoClip.mc.gameSettings.keyBindForward.isKeyDown()) {
                Minecraft.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y, z + Math.cos(yaw) * (double)0.002494945f);
            }
            if (NoClip.mc.gameSettings.keyBindBack.isKeyDown()) {
                Minecraft.player.setPosition(x - Math.sin(yaw) * (double)-0.002494945f, y, z + Math.cos(yaw) * (double)-0.002494945f);
            }
            if (Minecraft.player.isSneaking() && NoClip.mc.gameSettings.keyBindForward.isKeyDown()) {
                Minecraft.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y - (double)0.002494945f, z + Math.cos(yaw) * (double)0.002494945f);
            }
            if (Minecraft.player.isJumping && NoClip.mc.gameSettings.keyBindForward.isKeyDown()) {
                Minecraft.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y + (double)0.002494945f, z + Math.cos(yaw) * (double)0.002494945f);
            }
            if (Minecraft.player.isSneaking() && NoClip.mc.gameSettings.keyBindForward.isKeyDown()) {
                Minecraft.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y - (double)0.002494945f, z + Math.cos(yaw) * (double)0.002494945f);
            }
            if (Minecraft.player.isJumping && NoClip.mc.gameSettings.keyBindBack.isKeyDown()) {
                Minecraft.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y + (double)0.002494945f, z + Math.cos(yaw) * (double)-0.002494945f);
            }
            if (Minecraft.player.isSneaking() && NoClip.mc.gameSettings.keyBindBack.isKeyDown()) {
                Minecraft.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y + (double)0.002494945f, z + Math.cos(yaw) * (double)-0.002494945f);
            }
        }
    }
}

