/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.setting.settings;

import java.util.function.Supplier;
import ru.none.module.Module;
import ru.none.module.setting.Setting;

public class BooleanSetting
extends Setting {
    Boolean val;

    public BooleanSetting(String name, Module module, Boolean val) {
        super(name, module);
        this.val = val;
    }

    public BooleanSetting(String name, Module module, Boolean val, Supplier<Boolean> visible) {
        super(name, module, visible);
        this.val = val;
    }

    public Boolean getVal() {
        return this.val;
    }

    public void setVal(Boolean val) {
        this.val = val;
    }
}

