/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.setting;

import java.util.function.Supplier;
import ru.none.module.Module;

public class Setting {
    String name;
    Module module;
    protected Supplier<Boolean> visible;

    public Setting(String name, Module module) {
        this.module = module;
        this.name = name;
        this.visible = () -> true;
    }

    public Setting(String name, Module module, Supplier<Boolean> visible) {
        this.module = module;
        this.name = name;
        this.visible = visible;
    }

    public boolean isVisible() {
        return this.visible.get();
    }

    public String getName() {
        return this.name;
    }

    public Module getModule() {
        return this.module;
    }
}

