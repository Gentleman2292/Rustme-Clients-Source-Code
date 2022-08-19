/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Move;

import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;

public class LongJump
extends Module {
    private final FloatSetting boost = new FloatSetting("Boost", this, 0.03f, 1.0f, 0.07f);
    private int jumps = 0;

    public LongJump() {
        super("LongJump", Category.Move);
        None.settingManager.add(this.boost);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.jumps = 0;
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (this.mc.player.hurtTime > 0 && this.mc.player.onGround) {
            this.mc.player.jump();
        }
        if (!this.mc.player.onGround && this.mc.player.hurtTime > 0) {
            this.mc.timer.field_194148_c = 5.0f;
        } else {
            this.mc.player.speedInAir = 0.02f;
        }
    }
}

