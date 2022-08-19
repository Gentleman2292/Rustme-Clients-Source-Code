/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Hud;

import java.util.ArrayList;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;

public class Client
extends Module {
    public static ModeSetting mode;

    public Client() {
        super("Client", Category.Hud);
        ArrayList<String> modes = new ArrayList<String>();
        modes.add("NoneCode");
        modes.add("Astolfo");
        mode = new ModeSetting("Color", this, modes, "NoneCode");
        None.settingManager.add(mode);
    }
}

