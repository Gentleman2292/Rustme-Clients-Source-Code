/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec2f
 *  org.lwjgl.input.Keyboard
 */
package ru.none.gui.shit;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.input.Keyboard;
import ru.none.None;
import ru.none.gui.ClickGuiScreen2;
import ru.none.gui.Component;
import ru.none.gui.shit.GuiCheck;
import ru.none.gui.shit.GuiCombo;
import ru.none.gui.shit.GuiSlider;
import ru.none.module.Module;
import ru.none.module.setting.Setting;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.Utils;

public class GuiModule
extends Component {
    float anim = 0.0f;
    int meganie = 0;
    Module module;
    boolean binding = false;
    ArrayList<Component> settings = new ArrayList();

    public GuiModule(Module module) {
        this.module = module;
        for (Setting setting : None.settingManager.getSettings(module)) {
            if (setting instanceof BooleanSetting) {
                this.settings.add(new GuiCheck((BooleanSetting)setting));
            }
            if (setting instanceof FloatSetting) {
                this.settings.add(new GuiSlider((FloatSetting)setting));
            }
            if (!(setting instanceof ModeSetting)) continue;
            this.settings.add(new GuiCombo((ModeSetting)setting));
        }
    }

    public ArrayList<Component> getSettings() {
        return this.settings;
    }

    public Module getModule() {
        return this.module;
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (this.binding && Keyboard.isKeyDown((int)Keyboard.getEventKey())) {
            if (Keyboard.getEventKey() == 211) {
                this.module.setKey(0);
            } else {
                this.module.setKey(Keyboard.getEventKey());
            }
            this.binding = false;
        }
        if (ClickGuiScreen2.module == this) {
            this.anim = MathUtils.lerp(this.anim, 145.0f, 0.5f);
            if (this.meganie < 10) {
                ++this.meganie;
            } else if (this.meganie >= 10) {
                this.meganie = -10;
            }
        } else {
            this.anim = MathUtils.lerp(this.anim, 0.0f, 0.5f);
        }
        float bebra = 0.0f;
        if (this.anim < 100.0f) {
            bebra = 100.0f - this.anim;
        }
        if (this.anim >= 5.0f) {
            RenderUtils.drawRect(x + 145, y, x + 150, (float)y + this.getHeight(), this.meganie >= 0 ? new Color(60, 60, 60).getRGB() : new Color(40, 40, 40).getRGB());
            RenderUtils.drawRect(x, y, (float)x + this.anim, (float)y + this.getHeight(), new Color(35, 35, 35).getRGB());
            ArrayList<Vec2f> vec2fs = new ArrayList<Vec2f>();
            vec2fs.add(new Vec2f((float)x + this.anim - 100.0f + bebra, (float)y));
            vec2fs.add(new Vec2f((float)x + this.anim - 95.0f + bebra, (float)(y + 3)));
            vec2fs.add(new Vec2f((float)x + this.anim, (float)(y + 3)));
            vec2fs.add(new Vec2f((float)x + this.anim, (float)y));
            vec2fs.add(new Vec2f((float)x + this.anim - 100.0f + bebra, (float)y));
            RenderUtils.drawSome2(vec2fs, None.getColor().getRGB());
            vec2fs.clear();
            vec2fs.add(new Vec2f((float)x + this.anim - 100.0f + bebra, (float)y + this.getHeight()));
            vec2fs.add(new Vec2f((float)x + this.anim - 95.0f + bebra, (float)(y - 3) + this.getHeight()));
            vec2fs.add(new Vec2f((float)x + this.anim, (float)(y - 3) + this.getHeight()));
            vec2fs.add(new Vec2f((float)x + this.anim, (float)y + this.getHeight()));
            vec2fs.add(new Vec2f((float)x + this.anim - 100.0f + bebra, (float)y + this.getHeight()));
            RenderUtils.drawSome2(vec2fs, None.getColor().getRGB());
        }
        this.fr.drawString(this.module.getName(), x + 150 - this.fr.getStringWidth(this.module.getName()) - 15, (float)y + this.getHeight() / 2.0f - (float)(this.fr.getHeight() / 2), this.module.isEnable() ? None.getColor().getRGB() : -1);
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY)) {
            if (mouseButton == 0) {
                Utils.playSound("click.wav");
                this.module.toggle();
            }
            if (mouseButton == 1) {
                Utils.playSound("chouse.wav");
                ClickGuiScreen2.module = this;
            }
            if (mouseButton == 2) {
                this.binding = !this.binding;
            }
        }
    }

    @Override
    public float getWidth() {
        return 150.0f;
    }

    @Override
    public float getHeight() {
        return 15.0f;
    }
}

