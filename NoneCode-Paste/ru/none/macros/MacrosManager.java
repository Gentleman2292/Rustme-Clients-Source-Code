/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package ru.none.macros;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import ru.none.macros.Macros;

public class MacrosManager {
    ArrayList<Macros> macroses = new ArrayList();

    public void add(String name, String command, String key2) {
        this.macroses.add(new Macros(name, command, Keyboard.getKeyIndex((String)key2.toUpperCase())));
    }

    public void remove(String name) {
        for (Macros macros : this.macroses) {
            if (name != macros.getName()) continue;
            this.macroses.remove(macros);
            return;
        }
    }

    public void clear() {
        this.macroses.clear();
    }

    public void onKey(int key2) {
        for (Macros macros : this.macroses) {
            if (macros.getKey() != key2) continue;
            macros.use();
        }
    }
}

