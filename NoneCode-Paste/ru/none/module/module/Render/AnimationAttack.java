/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Render;

import java.util.ArrayList;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;

public class AnimationAttack
extends Module {
    public static ModeSetting mode;

    public AnimationAttack() {
        super("AnimationAttack", Category.Render);
        ArrayList<String> modes = new ArrayList<String>();
        modes.add("Orbit");
        modes.add("Liquar");
        modes.add("Exhibition");
        mode = new ModeSetting("Mode", this, modes, "Orbit");
        None.settingManager.add(mode);
    }
}

