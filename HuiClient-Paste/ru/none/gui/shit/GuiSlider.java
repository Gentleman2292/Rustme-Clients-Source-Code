/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec2f
 *  org.lwjgl.input.Mouse
 */
package ru.none.gui.shit;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.input.Mouse;
import ru.none.None;
import ru.none.gui.Component;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class GuiSlider
extends Component {
    FloatSetting setting;
    boolean draging = false;

    public GuiSlider(FloatSetting setting) {
        this.setting = setting;
    }

    @Override
    public float getWidth() {
        return 290.0f;
    }

    @Override
    public float getHeight() {
        return 15.0f;
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY)) {
            RenderUtils.drawRect228(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), new Color(45, 45, 45).getRGB());
        }
        if (!Mouse.isButtonDown((int)0)) {
            this.draging = false;
        }
        this.fr.drawString(this.setting.getName(), x + 5, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), -1);
        this.fr.drawString(this.setting.getVal() + "", (float)x + this.getWidth() / 2.0f - (float)this.fr.getStringWidth(this.setting.getVal() + " "), (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), -1);
        if (this.draging) {
            this.setting.setVal((float)MathUtils.round((float)((double)((float)mouseX - ((float)x + this.getWidth() / 2.0f)) * (double)(this.setting.getMax() - this.setting.getMin()) / (double)(this.getWidth() / 2.0f) + (double)this.setting.getMin()), 0.01f));
            if (this.setting.getVal() > this.setting.getMax()) {
                this.setting.setVal(this.setting.getMax());
            } else if (this.setting.getVal() < this.setting.getMin()) {
                this.setting.setVal(this.setting.getMin());
            }
        }
        float max = this.setting.getMax() - this.setting.getMin();
        float one_pice = this.getWidth() / 2.0f / max;
        int i = (int)((float)x + this.getWidth() / 2.0f);
        int j = (int)this.setting.getMin();
        while ((float)j < this.setting.getMax()) {
            ArrayList<Vec2f> vec2fs = new ArrayList<Vec2f>();
            vec2fs.add(new Vec2f((float)(i + 1), (float)(y + 14)));
            vec2fs.add(new Vec2f((float)(i + 3), (float)(y + 1)));
            vec2fs.add(new Vec2f((float)(i - 1) + one_pice, (float)(y + 1)));
            vec2fs.add(new Vec2f((float)(i - 3) + one_pice, (float)(y + 14)));
            vec2fs.add(new Vec2f((float)(i + 1), (float)(y + 14)));
            RenderUtils.drawSome(vec2fs, None.getColor().getRGB());
            if (this.setting.getVal() > (float)j) {
                RenderUtils.drawSome2(vec2fs, None.getColor().getRGB());
            }
            i = (int)((float)i + one_pice);
            ++j;
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY) && mouseButton == 0) {
            this.draging = true;
        }
    }

    public FloatSetting getSetting() {
        return this.setting;
    }
}

