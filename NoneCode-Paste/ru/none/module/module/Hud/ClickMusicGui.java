/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package ru.none.module.module.Hud;

import net.minecraft.client.gui.GuiScreen;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;

public class ClickMusicGui
extends Module {
    public ClickMusicGui() {
        super("ClickMusicGui", Category.Hud);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.mc.displayGuiScreen((GuiScreen)None.musicGui);
        this.toggle();
    }
}

