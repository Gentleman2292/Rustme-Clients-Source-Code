/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Move;

import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class AutoSprint
extends Module {
    public AutoSprint() {
        super("AutoSprint", Category.Move);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (this.mc.player.movementInput.field_192832_b > 0.0f) {
            if (this.mc.currentScreen == null) {
                this.mc.gameSettings.keyBindSprint.pressed = true;
            } else {
                this.mc.player.setSprinting(true);
            }
        }
    }
}

