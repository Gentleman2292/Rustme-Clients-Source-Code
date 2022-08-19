/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package ru.none.clickguiWhite;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.clickguiWhite.ClickCheck;
import ru.none.clickguiWhite.ClickMode;
import ru.none.clickguiWhite.ClickObject;
import ru.none.clickguiWhite.ClickSetting;
import ru.none.clickguiWhite.ClickSlider;
import ru.none.module.Module;
import ru.none.module.setting.Setting;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class ClickMod
extends ClickObject {
    float openAnim = 0.0f;
    boolean open = false;
    float enaleAnim = 0.0f;
    Module module;
    float anim = 0.0f;
    boolean binding = false;
    ArrayList<ClickSetting> settings = new ArrayList();

    public ClickMod(Module module) {
        this.module = module;
        for (Setting setting : None.settingManager.getSettings(module)) {
            if (setting instanceof BooleanSetting) {
                this.settings.add(new ClickCheck((BooleanSetting)setting));
            }
            if (setting instanceof FloatSetting) {
                this.settings.add(new ClickSlider((FloatSetting)setting));
            }
            if (!(setting instanceof ModeSetting)) continue;
            this.settings.add(new ClickMode((ModeSetting)setting));
        }
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void closeCat() {
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        int n;
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (this.binding && Keyboard.isKeyDown((int)Keyboard.getEventKey())) {
            if (Keyboard.isKeyDown((int)211)) {
                this.module.setKey(0);
            } else {
                this.module.setKey(Keyboard.getEventKey());
            }
            this.binding = false;
        }
        if (!this.open) {
            for (ClickSetting clickSetting : this.settings) {
                clickSetting.resetAnim();
            }
        } else if (this.settings.size() > 0) {
            this.settings.get((int)0).anim = MathUtils.lerp(this.settings.get((int)0).anim, 1.0f, 0.3f);
            for (int i = 0; i < this.settings.size(); ++i) {
                if (i > this.settings.size() - 2 || !((double)this.settings.get((int)i).anim >= 0.9)) continue;
                this.settings.get((int)(i + 1)).anim = MathUtils.lerp(this.settings.get((int)(i + 1)).anim, 1.0f, 0.3f);
            }
        }
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(x, y, this.getWidth(), this.getHeight(), this.anim);
        if (this.module.isEnable()) {
            this.enaleAnim = MathUtils.lerp(this.enaleAnim, 6.0f, 0.1f);
            RenderUtils.drawRect(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), None.getColor().getRGB());
        } else {
            this.enaleAnim = MathUtils.lerp(this.enaleAnim, 2.0f, 0.1f);
        }
        if (this.open) {
            int i = 0;
            for (ClickSetting setting : this.settings) {
                if (!setting.setting.isVisible()) continue;
                i = (int)((float)i + setting.getHeight());
            }
            this.openAnim = MathUtils.lerp(this.openAnim, i, 0.2f);
        } else {
            this.openAnim = MathUtils.lerp(this.openAnim, 0.0f, 0.2f);
        }
        this.fr.drawString(this.binding ? "Binding... " + Keyboard.getKeyName((int)this.module.getKey()) : this.module.getName(), (float)x + this.enaleAnim, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), new Color(30, 30, 30).getRGB());
        int b = 0;
        for (ClickSetting setting : this.settings) {
            if (!setting.setting.isVisible()) continue;
            b = (int)((float)b + setting.getHeight());
        }
        int n2 = (int)((float)y + this.getHeight());
        if (this.open) {
            RenderUtils.drawUpShadow(x, n2, (float)x + this.getWidth(), n2 + 5);
        }
        for (ClickSetting setting : this.settings) {
            if (!setting.setting.isVisible()) continue;
            setting.drawScreen(x, n, mouseX, mouseY, partialTicks);
            n = (int)((float)n + setting.getHeight());
        }
        if (this.open) {
            RenderUtils.drawDownShadow(x, (float)(n - 5), (float)x + this.getWidth(), n);
        }
        GL11.glPopMatrix();
    }

    public void resetAnim() {
        this.anim = 0.0f;
        for (ClickSetting clickSetting : this.settings) {
            clickSetting.resetAnim();
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        this.anim = 0.0f;
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.module.toggle();
            }
            if (mouseButton == 1 && None.settingManager.getSettings(this.module).size() > 0) {
                boolean bl = this.open = !this.open;
            }
            if (mouseButton == 2) {
                boolean bl = this.binding = !this.binding;
            }
        }
        if (this.settings.size() > 0 && this.open) {
            int i = (int)((float)y + this.getHeight());
            for (ClickSetting setting : this.settings) {
                if (!setting.setting.isVisible()) continue;
                setting.mouseClicked(x, i, mouseX, mouseY, mouseButton);
                i = (int)((float)i + setting.getHeight());
            }
        }
    }

    @Override
    public float getHeight() {
        return 14.0f;
    }

    public float getHeight2() {
        int i = (int)this.getHeight();
        i = (int)((float)i + this.openAnim);
        return i;
    }

    @Override
    public float getWidth() {
        return 100.0f;
    }
}

