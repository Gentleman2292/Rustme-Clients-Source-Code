/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package ru.none.clickguiWhite;

import java.awt.Color;
import java.io.IOException;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.clickguiWhite.ClickSetting;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.RenderUtils;

public class ClickMode
extends ClickSetting {
    boolean open = false;

    public ClickMode(ModeSetting setting) {
        super(setting);
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        ModeSetting setting = (ModeSetting)this.getSetting();
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(x, y, this.getWidth(), this.getHeight(), this.anim);
        this.fr.drawString(setting.getName(), x + 2, y + 7 - this.fr.getHeight() / 2, new Color(30, 30, 30).getRGB());
        if (this.open) {
            int i = y + 15;
            RenderUtils.drawUpShadow(x, y + 12, (float)x + this.getWidth(), y + 15);
            for (String mode : setting.getStrings()) {
                if (mode.equals(setting.getVal())) {
                    RenderUtils.drawRect(x, i - 1, (float)x + this.getWidth(), i + 10, None.getColor().getRGB());
                }
                this.fr.drawString(mode, x + 2, i, new Color(30, 30, 30).getRGB());
                i += 12;
            }
            RenderUtils.drawDownShadow(x, i - 3, (float)x + this.getWidth(), i);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(x, y, (float)x + this.getWidth(), y + 15, mouseX, mouseY)) {
            this.open = !this.open;
        }
        ModeSetting setting = (ModeSetting)this.getSetting();
        if (this.open) {
            int i = y + 15;
            for (String mode : setting.getStrings()) {
                if (this.ishover(x, i, (float)x + this.getWidth(), i + 10, mouseX, mouseY)) {
                    setting.setVal(mode);
                }
                this.fr.drawString(mode, x + 2, i, new Color(30, 30, 30).getRGB());
                i += 12;
            }
        }
    }

    @Override
    public float getHeight() {
        int i = 15;
        if (this.open) {
            ModeSetting setting = (ModeSetting)this.getSetting();
            i += setting.getStrings().size() * 12;
        }
        return i;
    }

    @Override
    public float getWidth() {
        return super.getWidth();
    }
}

