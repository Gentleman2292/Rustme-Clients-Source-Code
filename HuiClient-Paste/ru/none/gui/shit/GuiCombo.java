/*
 * Decompiled with CFR 0.150.
 */
package ru.none.gui.shit;

import java.awt.Color;
import java.io.IOException;
import ru.none.None;
import ru.none.gui.Component;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.RenderUtils;
import ru.none.utils.Utils;

public class GuiCombo
extends Component {
    ModeSetting setting;

    public GuiCombo(ModeSetting setting) {
        this.setting = setting;
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY)) {
            RenderUtils.drawRect228(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), new Color(45, 45, 45).getRGB());
        }
        this.fr.drawString(this.setting.getName(), x + 5, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), -1);
        this.fr.drawCenteredString(this.setting.getVal(), (float)x + this.getWidth() / 4.0f * 3.0f, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), -1);
        this.fr.drawCenteredString("<", (float)x + this.getWidth() / 4.0f * 3.0f - 60.0f, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), this.ishover((float)x + this.getWidth() / 4.0f * 3.0f - 60.0f, y, (float)x + this.getWidth() / 4.0f * 3.0f - 50.0f, (float)y + this.getHeight(), mouseX, mouseY) ? None.getColor().getRGB() : -1);
        this.fr.drawCenteredString(">", (float)x + this.getWidth() / 4.0f * 3.0f + 60.0f, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), this.ishover((float)x + this.getWidth() / 4.0f * 3.0f + 50.0f, y, (float)x + this.getWidth() / 4.0f * 3.0f + 70.0f, (float)y + this.getHeight(), mouseX, mouseY) ? None.getColor().getRGB() : -1);
    }

    public ModeSetting getSetting() {
        return this.setting;
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        String s;
        int i;
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover((float)x + this.getWidth() / 4.0f * 3.0f - 60.0f, y, (float)x + this.getWidth() / 4.0f * 3.0f - 50.0f, (float)y + this.getHeight(), mouseX, mouseY)) {
            Utils.playSound("click.wav");
            for (i = 0; i < this.setting.getStrings().size(); ++i) {
                s = this.setting.getStrings().get(i);
                if (!s.equalsIgnoreCase(this.setting.getVal())) continue;
                if (i == 0) {
                    this.setting.setVal(this.setting.getStrings().get(this.setting.getStrings().size() - 1));
                    continue;
                }
                this.setting.setVal(this.setting.getStrings().get(i - 1));
            }
        }
        if (this.ishover((float)x + this.getWidth() / 4.0f * 3.0f + 50.0f, y, (float)x + this.getWidth() / 4.0f * 3.0f + 70.0f, (float)y + this.getHeight(), mouseX, mouseY)) {
            Utils.playSound("click.wav");
            for (i = 0; i < this.setting.getStrings().size(); ++i) {
                s = this.setting.getStrings().get(i);
                if (!s.equalsIgnoreCase(this.setting.getVal())) continue;
                if (i == this.setting.getStrings().size() - 1) {
                    this.setting.setVal(this.setting.getStrings().get(this.setting.getStrings().size() - 1));
                    continue;
                }
                this.setting.setVal(this.setting.getStrings().get(i + 1));
            }
        }
    }

    @Override
    public float getWidth() {
        return 290.0f;
    }

    @Override
    public float getHeight() {
        return 15.0f;
    }
}

