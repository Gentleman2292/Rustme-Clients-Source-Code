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
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class ClickCheck
extends ClickSetting {
    float enableAnim = 0.0f;

    public ClickCheck(BooleanSetting setting) {
        super(setting);
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY) && mouseButton == 0) {
            BooleanSetting s;
            s.setVal((s = (BooleanSetting)this.setting).getVal() == false);
        }
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        BooleanSetting s = (BooleanSetting)this.setting;
        this.enableAnim = MathUtils.lerp(this.enableAnim, s.getVal() != false ? 10.0f : 0.0f, 0.1f);
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(x, y, this.getWidth(), this.getHeight(), this.anim);
        this.fr.drawString(this.setting.getName(), x + 2, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), new Color(30, 30, 30).getRGB());
        RenderUtils.drawRect((float)x + this.getWidth() - 20.0f, y + 4, (float)x + this.getWidth() - 5.0f, (float)y + this.getHeight() - 4.0f, Color.lightGray.getRGB());
        RenderUtils.drawRect((float)x + this.getWidth() - 20.0f + this.enableAnim, y + 3, (float)x + this.getWidth() - 15.0f + this.enableAnim, (float)y + this.getHeight() - 3.0f, s.getVal() != false ? None.getColor().getRGB() : Color.gray.getRGB());
        GL11.glPopMatrix();
    }

    @Override
    public float getHeight() {
        return 15.0f;
    }

    @Override
    public float getWidth() {
        return super.getWidth();
    }
}

