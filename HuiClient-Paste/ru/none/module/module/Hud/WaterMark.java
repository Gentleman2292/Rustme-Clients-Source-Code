/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.TextFormatting
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Hud;

import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.TimerUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class WaterMark
extends Module {
    ModeSetting mode;
    float anim = 0.0f;
    ArrayList<Float> d = new ArrayList();
    TimerUtils timer;
    public static CustomFontRenderer icon = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/icons.ttf"), 20.0f, 0), true, true);
    public static CustomFontRenderer fr2 = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/roadrage.ttf"), 30.0f, 0), true, true);

    public WaterMark() {
        super("WaterMark", Category.Hud, 80, 20);
        this.toggle();
        this.timer = new TimerUtils();
        ArrayList<String> modes = new ArrayList<String>();
        modes.add("XuyClient");
        modes.add("Rise");
        modes.add("WexSide");
        this.mode = new ModeSetting("Mode", this, modes, "XuyClient");
        None.settingManager.add(this.mode);
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
    }

    public void text(String test, int i, float size) {
        int x = this.getPosX();
        int y = this.getPosY();
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(x + fr2.getStringWidth("  ") * i, y, x + fr2.getStringWidth("  ") * i + 10, fr2.getHeight(), size);
        fr2.drawString(test, x + fr2.getStringWidth("  ") * i + 10 - fr2.getStringWidth("X") / 2, y, i == 0 || i == 4 ? None.getColor().getRGB() : -1);
        GL11.glPopMatrix();
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        this.setDisplayName(this.getName() + " " + (Object)TextFormatting.GRAY + this.mode.getVal());
        if (this.mode.getVal().equalsIgnoreCase("XuyClient")) {
            if (this.timer.isDeley(5000L)) {
                this.d.clear();
                this.d.add(Float.valueOf(0.0f));
                this.d.add(Float.valueOf(0.0f));
                this.d.add(Float.valueOf(0.0f));
                this.d.add(Float.valueOf(0.0f));
                this.d.add(Float.valueOf(0.0f));
                this.d.add(Float.valueOf(0.0f));
                this.d.add(Float.valueOf(0.0f));
                this.d.add(Float.valueOf(0.0f));
                TimerUtils.reset();
            }
            for (int i = 0; i < this.d.size(); ++i) {
                float size = this.d.get(i).floatValue();
                if (i == 0) {
                    this.text("X", i, size);
                    this.d.set(i, Float.valueOf(MathUtils.lerp(size, 1.0f, 0.1f)));
                    if ((double)size > 0.9) {
                        this.d.set(i + 1, Float.valueOf(MathUtils.lerp(this.d.get(i + 1).floatValue(), 1.0f, 0.1f)));
                    }
                }
                if (i == 1) {
                    this.text("u", i, size);
                    if ((double)size > 0.9) {
                        this.d.set(i + 1, Float.valueOf(MathUtils.lerp(this.d.get(i + 1).floatValue(), 1.0f, 0.1f)));
                    }
                }
                if (i == 2) {
                    this.text("y", i, size);
                    if ((double)size > 0.9) {
                        this.d.set(i + 1, Float.valueOf(MathUtils.lerp(this.d.get(i + 1).floatValue(), 1.0f, 0.1f)));
                    }
                }
                if (i == 4) {
                    this.text("C", i, size);
                    if ((double)size > 0.9) {
                        this.d.set(i + 1, Float.valueOf(MathUtils.lerp(this.d.get(i + 1).floatValue(), 1.0f, 0.1f)));
                    }
                }
                if (i == 5) {
                    this.text("l", i, size);
                    if ((double)size > 0.9) {
                        this.d.set(i + 1, Float.valueOf(MathUtils.lerp(this.d.get(i + 1).floatValue(), 1.0f, 0.1f)));
                    }
                }
                if (i == 6) {
                    this.text("i", i, size);
                    if ((double)size > 0.9) {
                        this.d.set(i + 1, Float.valueOf(MathUtils.lerp(this.d.get(i + 1).floatValue(), 1.0f, 0.1f)));
                    }
                }
                if (i == 7) {
                    this.text("e", i, size);
                }
                if (i == 7) {
                    this.text("n", i, size);
                }
                if (i != 7) continue;
                this.text("t", i, size);
            }
            this.setSizeX(50);
            this.setSizeY(20);
        } else if (this.mode.getVal().equalsIgnoreCase("Rise")) {
            int x = this.getPosX();
            int y = this.getPosY();
            RenderUtils.drawRect(x, y, x + this.fr.getStringWidth("by rtyu") + 20, y + 20, new Color(30, 30, 30).getRGB());
            RenderUtils.drawRect(x, y, x + 2, y + 20, None.getColor().getRGB());
            this.fr.drawString("XuyClient", x + 4, y + 1, -1);
            this.fr.drawString("by rtyu    ", x + 4, y + 10, -1);
            this.setSizeX(100);
            this.setSizeY(40);
        } else if (this.mode.getVal().equalsIgnoreCase("WexSide")) {
            int x = this.getPosX();
            int y = this.getPosY();
            String text = "WexSuicide.xuy  |  " + System.getProperty("user.name") + "  |  " + Minecraft.getDebugFPS() + " \u0447fps";
            RenderUtils.drawShadowRect(x, y, x + this.fr.getStringWidth(text) + 10, y + 10, 3);
            icon.drawString("a", x, y + 5 - icon.getHeight() / 2, -1);
            this.fr.drawString(text, x + icon.getStringWidth("A "), y + 5 - this.fr.getHeight() / 2, -1);
            this.setSizeY(10);
            this.setSizeX(this.fr.getStringWidth(text) + 10);
        }
    }
}

