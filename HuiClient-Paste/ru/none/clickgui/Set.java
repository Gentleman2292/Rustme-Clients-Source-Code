/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package ru.none.clickgui;

import java.io.IOException;
import net.minecraft.util.ResourceLocation;
import ru.none.module.setting.Setting;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class Set {
    Setting setting;
    protected CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/latoregular.ttf"), 20.0f, 0), true, true);

    public Set(Setting setting) {
        this.setting = setting;
    }

    public boolean ishover(float x1, float y1, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x1 && (float)mouseX <= x2 && (float)mouseY >= y1 && (float)mouseY <= y2;
    }

    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
    }

    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
    }

    protected void reset() {
    }

    protected void mouseReleased(int x, int y, int mouseX, int mouseY, int state) {
    }

    public int getHeight() {
        return 10;
    }

    public Setting getSetting() {
        return this.setting;
    }
}

