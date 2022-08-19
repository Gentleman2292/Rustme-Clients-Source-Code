/*
 * Decompiled with CFR 0.150.
 */
package ru.none.clickgui.comp;

import java.io.IOException;
import ru.none.None;
import ru.none.clickgui.Set;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.RenderUtils;

public class Combo
extends Set {
    boolean open = false;

    public Combo(ModeSetting setting) {
        super(setting);
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        ModeSetting setting = (ModeSetting)this.getSetting();
        this.fr.drawCenteredString(setting.getName(), x + 71, y + 1, -1);
        if (this.open) {
            RenderUtils.drawShadowRect(x + 30, y + 8, x + 112, y + 15 + setting.getStrings().size() * (this.fr.getHeight() + 3), 2);
            int i = y + 20;
            for (String string : setting.getStrings()) {
                this.fr.drawCenteredString(string, x + 71, i, string.equalsIgnoreCase(setting.getVal()) ? None.getColor().getRGB() : -1);
                i += this.fr.getHeight() + 2;
            }
        } else {
            RenderUtils.drawShadowRect(x + 30, y + this.fr.getHeight() + 2, x + 112, y + this.fr.getHeight() + this.fr.getHeight() + 4, 2);
        }
        this.fr.drawCenteredString(setting.getVal(), x + 71, y + this.fr.getHeight() + 2, None.getColor().getRGB());
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        ModeSetting setting = (ModeSetting)this.getSetting();
        if (mouseButton == 0 && !this.open) {
            this.open = true;
            return;
        }
        if (mouseButton == 0 && this.open) {
            int i = y + 20;
            boolean h = false;
            for (String string : setting.getStrings()) {
                if (this.ishover(x + 30, i, x + 112, i + 10, mouseX, mouseY)) {
                    h = true;
                    setting.setVal(string);
                }
                i += 10;
            }
            if (!h) {
                this.open = false;
            }
        }
    }

    @Override
    public int getHeight() {
        ModeSetting setting = (ModeSetting)this.getSetting();
        int i = 20;
        if (this.open) {
            i += setting.getStrings().size() * (this.fr.getHeight() + 2);
        }
        return i;
    }
}

