/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Misc;

import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class FastPlace
extends Module {
    public FastPlace() {
        super("FastPlace", Category.Misc);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        FastPlace.mc.rightClickDelayTimer = 1;
    }
}

