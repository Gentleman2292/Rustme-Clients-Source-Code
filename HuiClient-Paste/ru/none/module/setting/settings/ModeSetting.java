/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.setting.settings;

import java.util.ArrayList;
import java.util.function.Supplier;
import ru.none.module.Module;
import ru.none.module.setting.Setting;

public class ModeSetting
extends Setting {
    ArrayList<String> strings;
    String val;

    public ModeSetting(String name, Module module, ArrayList<String> strings, String val) {
        super(name, module);
        this.strings = strings;
        this.val = val;
    }

    public ModeSetting(String name, Module module, ArrayList<String> strings, String val, Supplier<Boolean> visible) {
        super(name, module, visible);
        this.strings = strings;
        this.val = val;
    }

    public ArrayList<String> getStrings() {
        return this.strings;
    }

    public String getVal() {
        return this.val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}

