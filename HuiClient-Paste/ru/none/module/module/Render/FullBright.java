/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Render;

import ru.none.module.Category;
import ru.none.module.Module;

public class FullBright
extends Module {
    float oldBright = 0.0f;

    public FullBright() {
        super("FullBright", Category.Render);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.oldBright = FullBright.mc.gameSettings.gammaSetting;
        FullBright.mc.gameSettings.gammaSetting = 1000.0f;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        FullBright.mc.gameSettings.gammaSetting = this.oldBright;
    }
}

