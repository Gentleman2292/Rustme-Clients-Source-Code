/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package ru.none.gui;

import java.io.IOException;
import net.minecraft.util.ResourceLocation;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class Component {
    protected CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/latoregular.ttf"), 20.0f, 0), true, true);

    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
    }

    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
    }

    public float getHeight() {
        return 10.0f;
    }

    public float getWidth() {
        return 100.0f;
    }

    public boolean ishover(float x1, float y1, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x1 && (float)mouseX <= x2 && (float)mouseY >= y1 && (float)mouseY <= y2;
    }
}

