/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package ru.none.module.module.Hud;

import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import ru.none.Gui;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;

public class ClickGui
extends Module {
    Gui gui;
    ModeSetting mode;
    public static ModeSetting anime;

    public ClickGui() {
        super("ClickGui", Category.Hud);
        this.setKey(54);
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Panel");
        strings.add("White");
        strings.add("NFS");
        ArrayList<String> animes = new ArrayList<String>();
        animes.add("NSFW1");
        animes.add("NSFW2");
        animes.add("megumin");
        animes.add("babidgon");
        this.gui = new Gui();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.mc.displayGuiScreen((GuiScreen)None.guiWhite);
        this.toggle();
    }
}

