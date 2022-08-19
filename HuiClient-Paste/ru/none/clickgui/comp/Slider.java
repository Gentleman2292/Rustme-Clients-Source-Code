/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 */
package ru.none.clickgui.comp;

import java.awt.Color;
import java.io.IOException;
import org.lwjgl.input.Mouse;
import ru.none.None;
import ru.none.clickgui.Set;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class Slider
extends Set {
    boolean use = false;
    float anim = 0.0f;

    public Slider(FloatSetting setting) {
        super(setting);
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        FloatSetting setting = (FloatSetting)this.getSetting();
        this.fr.drawCenteredString(setting.getName(), x + 71, y + 1, -1);
        this.fr.drawString(setting.getVal() + "", x + 142 - this.fr.getStringWidth(setting.getVal() + ""), y + 1, this.use ? Color.RED.getRGB() : -1);
        this.anim = MathUtils.lerp(this.anim, setting.getVal(), 0.1f);
        if (this.use) {
            setting.setVal((float)MathUtils.round((float)((double)(mouseX - x) * (double)(setting.getMax() - setting.getMin()) / 142.0 + (double)setting.getMin()), 0.01f));
            if (setting.getVal() > setting.getMax()) {
                setting.setVal(setting.getMax());
            } else if (setting.getVal() < setting.getMin()) {
                setting.setVal(setting.getMin());
            }
        }
        double optionValue = MathUtils.round(this.anim, 0.01);
        double renderPerc = 142.0 / (double)(setting.getMax() - setting.getMin());
        double barWidth = renderPerc * optionValue - renderPerc * (double)setting.getMin();
        RenderUtils.drawRect((double)x, (double)(y + 10), this.use ? (double)x + barWidth - 6.0 : (double)x + barWidth - 4.0, (double)(y + 12), None.getColor().getRGB());
        RenderUtils.drawCircle((float)((double)x + barWidth), y + 11, this.use ? 3.0f : 2.0f, None.getColor().getRGB());
        if (!Mouse.isButtonDown((int)0)) {
            this.use = false;
        }
    }

    @Override
    public int getHeight() {
        return 17;
    }

    @Override
    protected void reset() {
        super.reset();
        this.anim = 0.0f;
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            this.use = true;
        }
    }
}

