/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.TimerUtils;

public class Flight
extends Module {
    FloatSetting speed;
    ModeSetting mode;
    ArrayList<String> modes = new ArrayList();
    TimerUtils timer;
    float damageMotion;

    public Flight() {
        super("Flight", Category.Move);
        this.modes.add("Matrix Glide");
        this.modes.add("Matrix New");
        this.timer = new TimerUtils();
        this.mode = new ModeSetting("Mode", this, this.modes, "Matrix Glide");
        None.settingManager.add(this.mode);
        this.speed = new FloatSetting("Speed", this, 0.0f, 5.0f, 1.0f);
        None.settingManager.add(this.speed);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        switch (this.mode.getVal()) {
            case "Matrix Glide": {
                if (Minecraft.player.onGround) {
                    Minecraft.player.jump();
                } else {
                    Minecraft.player.motionX = 0.0;
                    Minecraft.player.motionZ = 0.0;
                    Minecraft.player.motionY = -0.01;
                    Minecraft.player.capabilities.isFlying = true;
                    Minecraft.player.capabilities.setFlySpeed(this.speed.getVal());
                    Minecraft.player.speedInAir = 0.3f;
                }
            }
            case "Matrix New": {
                if (Minecraft.player.hurtTime > 0) {
                    this.damageMotion = (float)Minecraft.player.motionY;
                    TimerUtils.reset();
                }
                if (this.timer.isDeley(5000L) || !(this.damageMotion > 0.0f)) break;
                Minecraft.player.motionY = this.damageMotion;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Minecraft.player.speedInAir = 0.02f;
        Flight.mc.timer.field_194147_b = 1.0f;
        Minecraft.player.capabilities.isFlying = false;
        Minecraft.player.capabilities.setFlySpeed(0.05f);
        Minecraft.player.motionY = 0.0;
        Minecraft.player.motionX = 0.0;
        Minecraft.player.motionZ = 0.0;
    }
}

