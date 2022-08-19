/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.setting;

import java.util.ArrayList;
import ru.none.module.Module;
import ru.none.module.setting.Setting;

public class SettingManager {
    public static ArrayList<Setting> settings = new ArrayList();

    public ArrayList<Setting> getSettings(Module module) {
        ArrayList<Setting> list = new ArrayList<Setting>();
        for (Setting setting : settings) {
            if (setting.getModule() != module) continue;
            list.add(setting);
        }
        return list;
    }

    public void add(Setting setting) {
        settings.add(setting);
    }
}

