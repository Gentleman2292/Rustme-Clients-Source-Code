/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Misc;

import java.util.ArrayList;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;

public class Disabler
extends Module {
    ModeSetting mode;
    ArrayList<String> modes = new ArrayList();

    public Disabler() {
        super("Disabler", Category.Misc);
        this.modes.add("StormHvH");
        this.mode = new ModeSetting("Mode", this, this.modes, "StormHvH");
        None.settingManager.add(this.mode);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        this.setDisplayName(this.getName() + " " + this.mode.getVal());
        switch (this.mode.getVal()) {
            case "StormHvH": {
                hook.setOnGround(false);
            }
        }
    }
}

