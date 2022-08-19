/*
 * Decompiled with CFR 0.150.
 */
package ru.none.musicgui;

import java.io.IOException;

public class MusicButton {
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
    }

    public void initGui() {
    }

    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
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

