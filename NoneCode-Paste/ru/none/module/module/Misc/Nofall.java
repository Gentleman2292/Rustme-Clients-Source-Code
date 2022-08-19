/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Misc;

import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class Nofall
extends Module {
    public Nofall() {
        super("Nofall", Category.Misc);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (this.mc.player.fallDistance > 10.0f && this.mc.player.isSneaking()) {
            this.mc.player.setPosition(this.mc.player.lastTickPosX, this.mc.player.lastTickPosY, this.mc.player.lastTickPosZ);
        }
    }
}

