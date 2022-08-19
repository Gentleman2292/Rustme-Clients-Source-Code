/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
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
                if (this.mc.player.onGround) {
                    this.mc.player.jump();
                } else {
                    this.mc.player.motionX = 0.0;
                    this.mc.player.motionZ = 0.0;
                    this.mc.player.motionY = -0.01;
                    this.mc.player.capabilities.isFlying = true;
                    this.mc.player.capabilities.setFlySpeed(this.speed.getVal());
                    this.mc.player.speedInAir = 0.3f;
                }
            }
            case "Matrix New": {
                if (this.mc.player.hurtTime > 0) {
                    this.damageMotion = (float)this.mc.player.motionY;
                    this.timer.reset();
                }
                if (this.timer.isDeley(5000L) || !(this.damageMotion > 0.0f)) break;
                this.mc.player.motionY = this.damageMotion;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.player.speedInAir = 0.02f;
        this.mc.timer.field_194147_b = 1.0f;
        this.mc.player.capabilities.isFlying = false;
        this.mc.player.capabilities.setFlySpeed(0.05f);
        this.mc.player.motionY = 0.0;
        this.mc.player.motionX = 0.0;
        this.mc.player.motionZ = 0.0;
    }
}

