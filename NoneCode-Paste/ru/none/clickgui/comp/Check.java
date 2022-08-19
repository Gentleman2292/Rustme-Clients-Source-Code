/*
 * Decompiled with CFR 0.150.
 */
package ru.none.clickgui.comp;

import java.io.IOException;
import ru.none.None;
import ru.none.clickgui.Set;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class Check
extends Set {
    float anim = 0.0f;

    public Check(BooleanSetting setting) {
        super(setting);
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        BooleanSetting setting = (BooleanSetting)this.getSetting();
        this.fr.drawCenteredString(setting.getName(), x + 71, y + 6 - this.fr.getHeight() / 2, -1);
        this.anim = setting.getVal() != false ? MathUtils.lerp(this.anim, 10.0f, 0.1f) : MathUtils.lerp(this.anim, 0.0f, 0.1f);
        if (this.anim > 3.0f) {
            RenderUtils.drawLine(x, y + 4, x + 3, y + 6 + 3, None.getColor().getRGB());
            RenderUtils.drawLine(x + 3, y + 6 + 3, (float)(x + 3) + (this.anim - 3.0f), (float)(y + 6 + 3) - (this.anim - 3.0f), None.getColor().getRGB());
        } else {
            RenderUtils.drawLine(x, y + 6, (float)x + this.anim, (float)(y + 6) + this.anim, None.getColor().getRGB());
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        BooleanSetting setting = (BooleanSetting)this.getSetting();
        if (mouseButton == 0) {
            setting.setVal(setting.getVal() == false);
        }
    }

    @Override
    public int getHeight() {
        return 13;
    }

    @Override
    protected void reset() {
        super.reset();
        this.anim = 0.0f;
    }
}

