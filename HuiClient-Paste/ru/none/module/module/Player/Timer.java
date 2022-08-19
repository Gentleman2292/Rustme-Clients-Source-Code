/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Player;

import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;

public class Timer
extends Module {
    FloatSetting timer = new FloatSetting("Boost", this, 0.0f, 5.0f, 2.0f);

    public Timer() {
        super("Timer", Category.Player);
        None.settingManager.add(this.timer);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        Timer.mc.timer.field_194147_b = this.timer.getVal();
    }
}

