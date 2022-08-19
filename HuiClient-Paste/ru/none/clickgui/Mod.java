/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 */
package ru.none.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import ru.none.None;
import ru.none.clickgui.Set;
import ru.none.clickgui.comp.Check;
import ru.none.clickgui.comp.Combo;
import ru.none.clickgui.comp.Slider;
import ru.none.module.Module;
import ru.none.module.setting.Setting;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.ColorUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class Mod {
    Module module;
    boolean open = false;
    boolean binding = false;
    ArrayList<Set> sets = new ArrayList();
    protected CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/latoregular.ttf"), 23.0f, 0), true, true);
    int color = new Color(30, 30, 30, 200).getRGB();
    int color2 = new Color(30, 30, 30, 200).getRGB();

    public boolean ishover(float x1, float y1, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x1 && (float)mouseX <= x2 && (float)mouseY >= y1 && (float)mouseY <= y2;
    }

    public Mod(Module module) {
        this.module = module;
        ArrayList<Setting> settings = new ArrayList<Setting>(None.settingManager.getSettings(module));
        for (Setting setting : settings) {
            if (setting instanceof BooleanSetting) {
                this.sets.add(new Check((BooleanSetting)setting));
            }
            if (setting instanceof FloatSetting) {
                this.sets.add(new Slider((FloatSetting)setting));
            }
            if (!(setting instanceof ModeSetting)) continue;
            this.sets.add(new Combo((ModeSetting)setting));
        }
    }

    public Module getModule() {
        return this.module;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        if (this.binding && Keyboard.isKeyDown((int)Keyboard.getEventKey())) {
            this.module.setKey(Keyboard.getEventKey());
            this.binding = false;
        }
        if (this.open) {
            int i = y + 17;
            for (Set set : this.sets) {
                if (set.getSetting().isVisible()) {
                    set.drawScreen(x + 3, i, mouseX, mouseY, partialTicks);
                    i += set.getHeight();
                    continue;
                }
                set.reset();
            }
        }
        this.color = this.module.isEnable() ? ColorUtils.TwoColoreffect(None.getColor(), new Color(this.color), 0.1f).getRGB() : ColorUtils.TwoColoreffect(new Color(30, 30, 30, 200), new Color(this.color), 0.1f).getRGB();
        if (this.open) {
            int b = 17;
            for (Set set : this.sets) {
                if (!set.getSetting().isVisible()) continue;
                b += set.getHeight();
            }
            RenderUtils.drawShadowRect(x, y, x + 146, y + 15, None.getColor().getRGB(), 4);
            RenderUtils.drawGradientRect(x, y + b, x + 73, y + b + 2, true, new Color(30, 30, 30, 200).getRGB(), None.getColor().getRGB());
            RenderUtils.drawGradientRect(x + 73, y + b, x + 146, y + b + 2, true, None.getColor().getRGB(), new Color(30, 30, 30, 200).getRGB());
        }
        RenderUtils.drawGradientRect(x, y, x + 73, y + 15, true, this.color2, this.color);
        RenderUtils.drawGradientRect(x + 73, y, x + 146, y + 15, true, this.color, this.color2);
        this.fr.drawCenteredString(this.binding ? "Binding..." : this.module.getName(), x + 73, y + 7 - this.fr.getHeight() / 2, -1);
        if (None.settingManager.getSettings(this.module).size() != 0) {
            this.fr.drawString("...", x + 146 - this.fr.getStringWidth("....."), y + 7 - this.fr.getHeight() / 2, -1);
        }
    }

    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.open) {
            int i = y + 17;
            for (Set set : this.sets) {
                if (!set.getSetting().isVisible()) continue;
                if (this.ishover(x, i, x + 146, i + set.getHeight(), mouseX, mouseY)) {
                    set.mouseClicked(x + 3, i, mouseX, mouseY, mouseButton);
                }
                i += set.getHeight();
            }
        }
        if (this.ishover(x, y, x + 146, y + 10, mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.module.toggle();
            }
            if (mouseButton == 1 && None.settingManager.getSettings(this.module).size() != 0) {
                boolean bl = this.open = !this.open;
            }
            if (mouseButton == 2) {
                this.binding = !this.binding;
            }
        }
    }

    protected void mouseReleased(int x, int y, int mouseX, int mouseY, int state) {
        if (this.open) {
            int i = y + 17;
            for (Set set : this.sets) {
                if (!set.getSetting().isVisible()) continue;
                if (this.ishover(x, i, x + 146, y + set.getHeight(), mouseX, mouseY)) {
                    set.mouseReleased(x + 3, i, mouseX, mouseY, state);
                }
                i += set.getHeight();
            }
        }
    }

    public int getHeight() {
        if (this.open) {
            int i = 17;
            for (Set set : this.sets) {
                if (!set.getSetting().isVisible()) continue;
                i += set.getHeight();
            }
            return i;
        }
        return 15;
    }
}

