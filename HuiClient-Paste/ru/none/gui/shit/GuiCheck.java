/*
 * Decompiled with CFR 0.150.
 */
package ru.none.gui.shit;

import java.awt.Color;
import java.io.IOException;
import ru.none.None;
import ru.none.gui.Component;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.utils.RenderUtils;
import ru.none.utils.Utils;

public class GuiCheck
extends Component {
    BooleanSetting setting;

    public GuiCheck(BooleanSetting setting) {
        this.setting = setting;
    }

    public BooleanSetting getSetting() {
        return this.setting;
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY)) {
            RenderUtils.drawRect228(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), new Color(45, 45, 45).getRGB());
        }
        this.fr.drawString(this.setting.getName(), x + 5, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), -1);
        this.fr.drawCenteredString(this.setting.getVal() != false ? "Enabled" : "Disabled", (float)x + this.getWidth() / 4.0f * 3.0f, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), this.setting.getVal() != false ? None.getColor().getRGB() : -1);
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY)) {
            Utils.playSound("click.wav");
            this.setting.setVal(this.setting.getVal() == false);
        }
    }

    @Override
    public float getHeight() {
        return 15.0f;
    }

    @Override
    public float getWidth() {
        return 290.0f;
    }
}

