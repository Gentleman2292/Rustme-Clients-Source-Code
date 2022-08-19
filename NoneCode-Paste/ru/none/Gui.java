/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package ru.none;

import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.Setting;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.TimerUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class Gui
extends GuiScreen {
    public static CustomFontRenderer roatrage = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/roadrage.ttf"), 25.0f, 0), true, true);
    public static CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/main.ttf"), 20.0f, 0), true, true);
    public static CustomFontRenderer icon = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/stylesicons.ttf"), 20.0f, 0), true, true);
    boolean an;
    boolean click;
    int but;
    Module currentmod;
    Category currentcat;
    Module mod_to_bind;
    boolean binding = false;
    float sizeX;
    float sizeY;
    float PosX;
    float PosY;
    float module_sizeX;
    float module_sizeY;
    float setting_size;
    float anim = 0.0f;
    TimerUtils timer;

    public void initGui() {
        super.initGui();
        this.sizeX = GuiScreen.width / 5 * 2;
        this.sizeY = GuiScreen.height / 4 * 2;
        this.PosX = (float)((double)(GuiScreen.width / 5) * 1.5);
        this.PosY = GuiScreen.height / 4;
        this.module_sizeX = this.sizeX / 4.0f - 2.0f;
        this.module_sizeY = this.sizeY / 4.0f - 2.0f;
        this.setting_size = this.sizeX / 3.0f - 4.0f;
        this.anim = 0.8f;
        this.an = false;
        this.timer = new TimerUtils();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawDefaultBackground();
        this.anim = this.timer.isDeley(200L) ? MathUtils.lerp(this.anim, 1.0f, 0.333333f) : MathUtils.lerp(this.anim, 1.1f, 0.333333f);
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(this.PosX, this.PosY, this.sizeX, this.sizeY, this.anim);
        if (this.binding && Keyboard.isKeyDown((int)Keyboard.getEventKey())) {
            if (Keyboard.getEventKey() == 211) {
                this.mod_to_bind.setKey(0);
            } else {
                this.mod_to_bind.setKey(Keyboard.getEventKey());
            }
            this.binding = false;
        }
        RenderUtils.drawShadowRect(this.PosX, this.PosY, this.PosX + this.sizeX, this.PosY + this.sizeY, 5);
        RenderUtils.drawShadowRect(this.PosX, this.PosY, this.PosX + this.sizeX, this.PosY + 15.0f, 5);
        roatrage.drawString("N", this.PosX + 3.0f, this.PosY, None.getColor().getRGB());
        roatrage.drawString("one", this.PosX + 3.0f + (float)roatrage.getStringWidth("N"), this.PosY, -1);
        roatrage.drawString("C", this.PosX + 3.0f + (float)roatrage.getStringWidth("None"), this.PosY, None.getColor().getRGB());
        roatrage.drawString("ode", this.PosX + 3.0f + (float)roatrage.getStringWidth("NoneC"), this.PosY, -1);
        float i = this.PosY + 20.0f;
        for (Category category : Category.values()) {
            this.drawCat(category, this.PosX + 2.0f, i, mouseX, mouseY);
            i += 25.0f;
        }
        if (this.currentcat != null) {
            float x = this.PosX + 55.0f;
            float y = this.PosY + 20.0f;
            for (Module module : None.moduleManager.getModules(this.currentcat)) {
                this.drawMod(module, x, y, mouseX, mouseY);
                if (!((x += 80.0f) + 80.0f >= this.sizeX + this.PosX)) continue;
                x = this.PosX + 55.0f;
                y += 40.0f;
            }
        }
        if (this.currentmod != null) {
            RenderUtils.drawShadowRect(this.PosX + this.sizeX - this.sizeX / 3.0f, this.PosY, this.PosX + this.sizeX, this.PosY + this.sizeY, 3);
            icon.drawString("I", this.PosX + this.sizeX - 10.0f, this.PosY + 2.0f, Color.red.getRGB());
            if (this.ishover(this.PosX + this.sizeX - 10.0f, this.PosY, this.PosX + this.sizeX, this.PosY + 10.0f, mouseX, mouseY) && this.click && this.but == 0) {
                this.currentmod = null;
            }
            if (this.ishover(this.PosX, this.PosY, this.PosX + this.sizeX - this.sizeX / 3.0f, this.PosY + this.sizeY, mouseX, mouseY) && this.click) {
                this.currentmod = null;
            }
        }
        if (this.currentmod != null) {
            fr.drawCenteredString(this.currentmod.getName(), this.PosX + this.sizeX - this.sizeX / 3.0f / 2.0f, this.PosY + 2.0f, -1);
            float y = this.PosY + 15.0f;
            for (Setting setting : None.settingManager.getSettings(this.currentmod)) {
                if (!setting.isVisible()) continue;
                if (setting instanceof BooleanSetting) {
                    this.drawCheck((BooleanSetting)setting, this.PosX + this.sizeX - this.sizeX / 3.0f + 2.0f, y, mouseX, mouseY);
                    y += 17.0f;
                }
                if (setting instanceof FloatSetting) {
                    this.drawSlider((FloatSetting)setting, this.PosX + this.sizeX - this.sizeX / 3.0f + 2.0f, y, mouseX, mouseY);
                    y += 23.0f;
                }
                if (!(setting instanceof ModeSetting)) continue;
                this.drawMode((ModeSetting)setting, this.PosX + this.sizeX - this.sizeX / 3.0f + 2.0f, y, mouseX, mouseY);
                y += 23.0f;
            }
        }
        GL11.glPopMatrix();
        this.click = false;
    }

    public void drawCheck(BooleanSetting setting, float x, float y, int mouseX, int mouseY) {
        RenderUtils.drawRect(x, y, x + this.setting_size, y + 15.0f, new Color(30, 30, 30).getRGB());
        fr.drawCenteredString(setting.getName(), x + this.setting_size / 2.0f, y + 7.0f - (float)(fr.getHeight() / 2), -1);
        icon.drawString(setting.getVal() != false ? "H" : "I", x + 3.0f, y + 5.0f, setting.getVal() != false ? Color.green.getRGB() : Color.red.getRGB());
        if (this.ishover(x, y, x + this.setting_size, y + 20.0f, mouseX, mouseY) && this.click && this.but == 0) {
            setting.setVal(setting.getVal() == false);
        }
    }

    public void drawSlider(FloatSetting setting, float x, float y, int mouseX, int mouseY) {
        RenderUtils.drawRect(x, y, x + this.setting_size, y + 20.0f, new Color(30, 30, 30).getRGB());
        fr.drawCenteredString(setting.getName(), x + this.setting_size / 2.0f, y + 5.0f - (float)(fr.getHeight() / 2), -1);
        fr.drawString(setting.getVal() + "", x + this.setting_size - (float)fr.getStringWidth(setting.getVal() + ""), y + 5.0f - (float)(fr.getHeight() / 2), -1);
        if (this.ishover(x, y, x + this.setting_size, y + 20.0f, mouseX, mouseY) && Mouse.isButtonDown((int)0)) {
            setting.setVal((float)this.round((float)((double)((float)mouseX - x) * (double)(setting.getMax() - setting.getMin()) / (double)this.setting_size + (double)setting.getMin()), 0.1));
            if (setting.getVal() > setting.getMax()) {
                setting.setVal(setting.getMax());
            } else if (setting.getVal() < setting.getMin()) {
                setting.setVal(setting.getMin());
            }
        }
        double optionValue = this.round(setting.getVal(), 0.1);
        double renderPerc = (double)this.setting_size / (double)(setting.getMax() - setting.getMin());
        double barWidth = renderPerc * optionValue - renderPerc * (double)setting.getMin();
        RenderUtils.drawRect((double)x, (double)(y + 10.0f), (double)x + barWidth, (double)(y + 13.0f), None.getColor().getRGB());
    }

    private double round(float num, double increment) {
        double v = (double)Math.round((double)num / increment) * increment;
        BigDecimal bd = new BigDecimal(v);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void drawMode(ModeSetting setting, float x, float y, int mouseX, int mouseY) {
        RenderUtils.drawRect(x, y, x + this.setting_size, y + 20.0f, new Color(30, 30, 30).getRGB());
        fr.drawCenteredString(setting.getName(), x + this.setting_size / 2.0f, y + 5.0f - (float)(fr.getHeight() / 2), -1);
        fr.drawCenteredString(setting.getVal(), x + this.setting_size / 2.0f, y + 15.0f - (float)(fr.getHeight() / 2), None.getColor().getRGB());
        if (this.ishover(x, y, x + this.setting_size, y + 20.0f, mouseX, mouseY) && this.click && this.but == 0) {
            for (int i = 0; i < setting.getStrings().size(); ++i) {
                String s = setting.getStrings().get(i);
                if (!s.equalsIgnoreCase(setting.getVal())) continue;
                if (i != setting.getStrings().size() - 1) {
                    setting.setVal(setting.getStrings().get(i + 1));
                } else {
                    setting.setVal(setting.getStrings().get(0));
                }
                return;
            }
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.click = true;
        this.but = mouseButton;
    }

    public void drawCat(Category category, float x, float y, int mouseX, int mouseY) {
        if (category == this.currentcat) {
            RenderUtils.drawShadowRect(x, y, x + 50.0f, y + 20.0f, 5);
        } else {
            RenderUtils.drawRect(x, y, x + 50.0f, y + 20.0f, new Color(30, 30, 30, 150).getRGB());
        }
        String ic = "";
        if (category == Category.Combat) {
            ic = "D";
        }
        if (category == Category.Player) {
            ic = "B";
        }
        if (category == Category.Misc) {
            ic = "F";
        }
        if (category == Category.Move) {
            ic = "A";
        }
        if (category == Category.Hud) {
            ic = "M";
        }
        if (category == Category.Render) {
            ic = "C";
        }
        if (category == this.currentcat) {
            icon.drawString(ic, x + 1.0f, y + 6.0f - (float)(icon.getHeight() / 2), None.getColor().getRGB());
            fr.drawString(category.name(), x + 1.0f, y + 15.0f - (float)(icon.getHeight() / 2), None.getColor().getRGB());
        } else {
            icon.drawString(ic, x + 1.0f, y + 6.0f - (float)(icon.getHeight() / 2), -1);
            fr.drawString(category.name(), x + 1.0f, y + 15.0f - (float)(icon.getHeight() / 2), -1);
        }
        if (this.ishover(x, y, x + 50.0f, y + 20.0f, mouseX, mouseY) && this.click && this.but == 0) {
            this.currentcat = category;
        }
    }

    public void drawMod(Module module, float x, float y, int mouseX, int mouseY) {
        if (module == this.currentmod) {
            RenderUtils.drawShadowRect(x, y, x + 75.0f, y + 35.0f, 5);
        } else {
            RenderUtils.drawRect(x, y, x + 75.0f, y + 35.0f, new Color(30, 30, 30, 150).getRGB());
        }
        fr.drawCenteredString(module.getName(), x + 37.0f, y + 4.0f, -1);
        RenderUtils.drawShadowRect(x + 60.0f, y + 20.0f, x + 70.0f, y + 30.0f, 2);
        if (module.isEnable()) {
            icon.drawCenteredString("H", x + 65.0f, y + 26.0f - (float)(icon.getHeight() / 2), Color.green.getRGB());
        } else {
            icon.drawCenteredString("I", x + 65.0f, y + 26.0f - (float)(icon.getHeight() / 2), Color.red.getRGB());
        }
        String bind = Keyboard.getKeyName((int)module.getKey());
        RenderUtils.drawShadowRect(x + 3.0f, y + 20.0f, x + 3.0f + (float)fr.getStringWidth(bind), y + 30.0f, 2);
        fr.drawCenteredString(bind, x + 3.0f + (float)(fr.getStringWidth(bind) / 2), y + 26.0f - (float)(icon.getHeight() / 2), -1);
        if (this.ishover(x, y, x + 75.0f, y + 35.0f, mouseX, mouseY) && this.click && this.currentmod == null) {
            if (this.but == 0) {
                module.toggle();
            }
            if (this.but == 1) {
                this.currentmod = module;
                this.click = false;
            }
            if (this.but == 2) {
                if (this.binding) {
                    this.binding = false;
                } else {
                    this.mod_to_bind = module;
                    this.binding = true;
                }
            }
        }
    }

    public boolean ishover(float x1, float y1, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x1 && (float)mouseX <= x2 && (float)mouseY >= y1 && (float)mouseY <= y2;
    }
}

