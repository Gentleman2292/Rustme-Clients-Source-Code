/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.setting.settings;

import java.util.function.Supplier;
import ru.none.module.Module;
import ru.none.module.setting.Setting;

public class FloatSetting
extends Setting {
    float min;
    float max;
    float val;

    public FloatSetting(String name, Module module, float min, float max, float val) {
        super(name, module);
        this.max = max;
        this.min = min;
        this.val = val;
    }

    public FloatSetting(String name, Module module, float min, float max, float val, Supplier<Boolean> visible) {
        super(name, module, visible);
        this.max = max;
        this.min = min;
        this.val = val;
    }

    public float getVal() {
        return this.val;
    }

    public float getMin() {
        return this.min;
    }

    public float getMax() {
        return this.max;
    }

    public void setVal(float val) {
        this.val = val;
    }
}

