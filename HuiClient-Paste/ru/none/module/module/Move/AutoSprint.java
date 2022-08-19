/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.module.module.Move;

import net.minecraft.client.Minecraft;
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
        if (Minecraft.player.movementInput.field_192832_b > 0.0f) {
            if (AutoSprint.mc.currentScreen == null) {
                AutoSprint.mc.gameSettings.keyBindSprint.pressed = true;
            } else {
                Minecraft.player.setSprinting(true);
            }
        }
    }
}

