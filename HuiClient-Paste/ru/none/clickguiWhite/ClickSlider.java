/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package ru.none.clickguiWhite;

import java.awt.Color;
import java.io.IOException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.clickguiWhite.ClickSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class ClickSlider
extends ClickSetting {
    boolean draging = false;

    public ClickSlider(FloatSetting setting) {
        super(setting);
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (!Mouse.isButtonDown((int)0)) {
            this.draging = false;
        }
        FloatSetting setting = (FloatSetting)this.getSetting();
        if (this.draging) {
            setting.setVal((float)MathUtils.round((float)((double)(mouseX - x) * (double)(setting.getMax() - setting.getMin()) / (double)(this.getWidth() - 4.0f) + (double)setting.getMin()), 0.01f));
            if (setting.getVal() > setting.getMax()) {
                setting.setVal(setting.getMax());
            } else if (setting.getVal() < setting.getMin()) {
                setting.setVal(setting.getMin());
            }
        }
        double optionValue = MathUtils.round(setting.getVal(), 0.01);
        double renderPerc = (double)(this.getWidth() - 4.0f) / (double)(setting.getMax() - setting.getMin());
        double barWidth = renderPerc * optionValue - renderPerc * (double)setting.getMin();
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(x, y, this.getWidth(), this.getHeight(), this.anim);
        RenderUtils.drawRect((double)(x + 2), (double)((float)y + 15.0f), (double)x + barWidth, (double)(y + 17), None.getColor().getRGB());
        RenderUtils.drawRect((double)x + barWidth - 1.0, (double)(y + 14), (double)x + barWidth + 1.0, (double)(y + 18), None.getColor().getRGB());
        this.fr.drawString(setting.getName(), x + 2, y + 7 - this.fr.getHeight() / 2, new Color(30, 30, 30).getRGB());
        this.fr.drawString(setting.getVal() + "", (float)x + this.getWidth() - (float)this.fr.getStringWidth(setting.getVal() + "  "), y + 7 - this.fr.getHeight() / 2, new Color(30, 30, 30).getRGB());
        GL11.glPopMatrix();
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY) && mouseButton == 0) {
            this.draging = true;
        }
    }

    @Override
    public float getWidth() {
        return super.getWidth();
    }

    @Override
    public float getHeight() {
        return 20.0f;
    }
}

