/*
 * Decompiled with CFR 0.150.
 */
package ru.none.clickguiWhite;

import java.io.IOException;
import ru.none.clickguiWhite.ClickObject;
import ru.none.module.setting.Setting;

public class ClickSetting
extends ClickObject {
    float anim = 0.0f;
    Setting setting;

    public ClickSetting(Setting setting) {
        this.setting = setting;
    }

    public Setting getSetting() {
        return this.setting;
    }

    public void resetAnim() {
        this.anim = 0.0f;
    }

    @Override
    public float getWidth() {
        return 100.0f;
    }

    @Override
    public float getHeight() {
        return 10.0f;
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}

