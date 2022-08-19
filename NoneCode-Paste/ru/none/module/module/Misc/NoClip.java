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
            this.mc.player.motionY = 0.0;
            this.mc.player.motionX = 0.0;
            this.mc.player.motionZ = 0.0;
            this.mc.player.onGround = false;
            this.mc.player.jumpMovementFactor = 0.0f;
            if (this.mc.gameSettings.keyBindJump.pressed) {
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 20.0, this.mc.player.posZ, true));
            }
            if (this.mc.gameSettings.keyBindForward.pressed) {
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX - Math.sin(Math.toRadians(Minecraft.getMinecraft().player.rotationYaw)) * 15.0, this.mc.player.posY, this.mc.player.posZ + Math.cos(Math.toRadians(Minecraft.getMinecraft().player.rotationYaw)) * 15.0, true));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY - 15.0, this.mc.player.posZ, true));
                if (this.mc.player.ticksExisted % 9 == 0) {
                    this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 1.0, this.mc.player.posZ, true));
                }
            }
            if (this.mc.gameSettings.keyBindSneak.pressed) {
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY - 2.0, this.mc.player.posZ, true));
            }
        }
        if (this.mode.getVal().equalsIgnoreCase("MatrixUp") && this.mc.player.isJumping && this.mc.player.hurtTime != 0) {
            this.mc.player.motionX = 20.0;
            this.mc.player.motionZ = 20.0;
            if (this.mc.gameSettings.keyBindJump.isKeyDown()) {
                this.mc.player.jump();
            }
            if (this.mc.player.ticksExisted % 2 == 0) {
                this.mc.player.motionX = -this.mc.player.motionX;
                this.mc.player.motionZ = -this.mc.player.motionZ;
            }
        }
        if (this.mode.getVal().equalsIgnoreCase("Vanila")) {
            this.mc.player.noClip = true;
            this.mc.player.stepHeight = 0.0f;
            double x = this.mc.player.posX;
            double y = this.mc.player.posY;
            double z = this.mc.player.posZ;
            double yaw = (double)this.mc.player.rotationYaw * 0.017453292;
            float Speed2 = 0.002494945f;
            float SpeedY = 1.0f;
            if (this.mc.player.isJumping && this.mc.player.hurtTime == 0) {
                this.mc.player.motionY = 1.0;
            }
            if (this.mc.gameSettings.keyBindSneak.isKeyDown()) {
                this.mc.player.motionY = -1.0;
                this.mc.player.setPosition(x, y - (double)0.002494945f, z);
            }
            if (this.mc.gameSettings.keyBindForward.isKeyDown()) {
                this.mc.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y, z + Math.cos(yaw) * (double)0.002494945f);
            }
            if (this.mc.gameSettings.keyBindBack.isKeyDown()) {
                this.mc.player.setPosition(x - Math.sin(yaw) * (double)-0.002494945f, y, z + Math.cos(yaw) * (double)-0.002494945f);
            }
            if (this.mc.player.isSneaking() && this.mc.gameSettings.keyBindForward.isKeyDown()) {
                this.mc.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y - (double)0.002494945f, z + Math.cos(yaw) * (double)0.002494945f);
            }
            if (this.mc.player.isJumping && this.mc.gameSettings.keyBindForward.isKeyDown()) {
                this.mc.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y + (double)0.002494945f, z + Math.cos(yaw) * (double)0.002494945f);
            }
            if (this.mc.player.isSneaking() && this.mc.gameSettings.keyBindForward.isKeyDown()) {
                this.mc.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y - (double)0.002494945f, z + Math.cos(yaw) * (double)0.002494945f);
            }
            if (this.mc.player.isJumping && this.mc.gameSettings.keyBindBack.isKeyDown()) {
                this.mc.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y + (double)0.002494945f, z + Math.cos(yaw) * (double)-0.002494945f);
            }
            if (this.mc.player.isSneaking() && this.mc.gameSettings.keyBindBack.isKeyDown()) {
                this.mc.player.setPosition(x - Math.sin(yaw) * (double)0.002494945f, y + (double)0.002494945f, z + Math.cos(yaw) * (double)-0.002494945f);
            }
        }
    }
}

