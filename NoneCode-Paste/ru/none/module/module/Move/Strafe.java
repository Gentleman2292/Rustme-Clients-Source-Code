/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.MoveUtils;

public class Strafe
extends Module {
    ModeSetting mode;
    ArrayList<String> modes = new ArrayList();

    public Strafe() {
        super("Strafe", Category.Move);
        this.modes.add("Matrix");
        this.modes.add("Vanilla");
        this.mode = new ModeSetting("Mode", this, this.modes, "Matrix");
        None.settingManager.add(this.mode);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        switch (this.mode.getVal()) {
            case "Matrix": {
                if (!this.mc.gameSettings.keyBindForward.pressed) {
                    return;
                }
                if (this.mc.player.motionY == 0.33319999363422365) {
                    MoveUtils.strafe(MoveUtils.getSpeed());
                }
            }
            case "Vanilla": {
                MoveUtils.strafe(1.0f);
            }
        }
    }
}

