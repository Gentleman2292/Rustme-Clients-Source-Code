/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.math.Vec2f
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package ru.none.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.config.cfg;
import ru.none.gui.Component;
import ru.none.gui.shit.GuiCategoty;
import ru.none.gui.shit.GuiCheck;
import ru.none.gui.shit.GuiCombo;
import ru.none.gui.shit.GuiModule;
import ru.none.gui.shit.GuiObject;
import ru.none.gui.shit.GuiSlider;
import ru.none.module.Category;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.TimerUtils;

public class ClickGuiScreen2
extends GuiScreen {
    public static GuiCategoty categoty = null;
    public static GuiModule module = null;
    TimerUtils timer;
    float catAnim = 0.0f;
    float catAnim2 = 0.0f;
    int x = 100;
    int y = 100;
    float anim = 0.0f;
    int dragX;
    int dragY;
    boolean draging = false;
    ArrayList<GuiObject> objects = new ArrayList();
    ArrayList<GuiCategoty> categoties = new ArrayList();

    public ClickGuiScreen2() {
        for (Category category : Category.values()) {
            this.categoties.add(new GuiCategoty(category));
        }
        this.timer = new TimerUtils();
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        cfg.save();
    }

    public void initGui() {
        super.initGui();
        this.anim = 0.2f;
        TimerUtils.reset();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (this.draging) {
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
        }
        if (!Mouse.isButtonDown((int)0)) {
            this.draging = false;
        }
        this.anim = MathUtils.lerp(this.anim, 1.0f, 0.1f);
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(this.x, this.y, 450.0f, 250.0f, this.anim);
        RenderUtils.drawShadowRect(this.x, this.y, this.x + 450, this.y + 250, 2);
        RenderUtils.drawRect(this.x, this.y, this.x + 450, this.y + 250, new Color(60, 60, 60).getRGB());
        RenderUtils.drawLine(this.x, this.y + 20, this.x + 450, this.y + 20, new Color(30, 30, 30).getRGB());
        RenderUtils.drawRect(this.x, this.y + 22, this.x + 450, this.y + 250, new Color(30, 30, 30).getRGB());
        ArrayList<Object> vec2fs = new ArrayList<Vec2f>();
        RenderUtils.drawRect(this.x - 5, this.y - 5, this.x - 1, this.y + 50, new Color(60, 60, 60).getRGB());
        vec2fs.clear();
        vec2fs.add((Object)new Vec2f((float)(this.x - 5), (float)(this.y - 7)));
        vec2fs.add((Object)new Vec2f((float)(this.x + 50), (float)(this.y - 7)));
        vec2fs.add((Object)new Vec2f((float)(this.x + 60), (float)(this.y - 3)));
        vec2fs.add((Object)new Vec2f((float)(this.x + 60), (float)(this.y - 1)));
        vec2fs.add((Object)new Vec2f((float)(this.x - 1), (float)(this.y - 1)));
        RenderUtils.drawSome2(vec2fs, new Color(60, 60, 60).getRGB());
        RenderUtils.drawRect(this.x, this.y + 252, this.x + 60, this.y + 254, new Color(60, 60, 60).getRGB());
        RenderUtils.drawRect(this.x + 450 - 40, this.y - 4, this.x + 450, this.y - 2, new Color(60, 60, 60).getRGB());
        RenderUtils.drawRect(this.x + 280, this.y + 252, this.x + 320, this.y + 254, new Color(60, 60, 60).getRGB());
        vec2fs.clear();
        int i = this.x + 15;
        for (GuiCategoty cat : this.categoties) {
            if (cat == categoty) {
                this.catAnim = MathUtils.lerp(this.catAnim, i - this.x, 0.5f);
                this.catAnim2 = Math.abs(this.catAnim - (float)(i - this.x)) >= 20.0f ? MathUtils.lerp(this.catAnim2, -2.0f, 0.1f) : MathUtils.lerp(this.catAnim2, 0.0f, 0.1f);
                vec2fs = new ArrayList();
                vec2fs.add((Object)new Vec2f((float)this.x, (float)(this.y + 16) + this.catAnim2));
                vec2fs.add((Object)new Vec2f((float)this.x + this.catAnim - 5.0f, (float)(this.y + 16) + this.catAnim2));
                vec2fs.add((Object)new Vec2f((float)this.x + this.catAnim - 1.0f, (float)(this.y + 2) + this.catAnim2));
                vec2fs.add((Object)new Vec2f((float)this.x + this.catAnim + cat.getWidth() + 3.0f, (float)(this.y + 2) + this.catAnim2));
                vec2fs.add((Object)new Vec2f((float)this.x + this.catAnim + cat.getWidth(), (float)(this.y + 18) + this.catAnim2));
                vec2fs.add((Object)new Vec2f((float)this.x, (float)(this.y + 18) + this.catAnim2));
                RenderUtils.drawSome(vec2fs, new Color(None.getColor().getRed(), None.getColor().getGreen(), None.getColor().getBlue(), 200).getRGB());
                vec2fs.clear();
                vec2fs.add((Object)new Vec2f((float)this.x + this.catAnim - 5.0f, (float)(this.y + 18) + this.catAnim2));
                vec2fs.add((Object)new Vec2f((float)this.x + this.catAnim - 1.0f, (float)(this.y + 2) + this.catAnim2));
                vec2fs.add((Object)new Vec2f((float)this.x + this.catAnim + cat.getWidth() + 3.0f, (float)(this.y + 2) + this.catAnim2));
                vec2fs.add((Object)new Vec2f((float)this.x + this.catAnim + cat.getWidth(), (float)(this.y + 18) + this.catAnim2));
                RenderUtils.drawSome2(vec2fs, new Color(None.getColor().getRed(), None.getColor().getGreen(), None.getColor().getBlue(), 150).getRGB());
            }
            cat.drawScreen(i, this.y, mouseX, mouseY, partialTicks);
            i = (int)((float)i + (cat.getWidth() + 10.0f));
        }
        if (categoty != null) {
            i = this.y + 30;
            for (Component component : categoty.getModules()) {
                component.drawScreen(this.x, i, mouseX, mouseY, partialTicks);
                i = (int)((float)i + component.getHeight());
            }
        }
        RenderUtils.drawLine(this.x + 152, this.y + 30, this.x + 152, this.y + 250, new Color(60, 60, 60).getRGB());
        RenderUtils.drawLine(this.x + 152, this.y + 30, this.x + 450, this.y + 30, new Color(60, 60, 60).getRGB());
        if (module != null) {
            i = this.y + 45;
            for (Component component : module.getSettings()) {
                Component b;
                if (component instanceof GuiCheck && ((GuiCheck)(b = (GuiCheck)component)).getSetting().isVisible()) {
                    component.drawScreen(this.x + 155, i, mouseX, mouseY, partialTicks);
                    i = (int)((float)i + (component.getHeight() + 2.0f));
                }
                if (component instanceof GuiSlider && ((GuiSlider)(b = (GuiSlider)component)).getSetting().isVisible()) {
                    component.drawScreen(this.x + 155, i, mouseX, mouseY, partialTicks);
                    i = (int)((float)i + (component.getHeight() + 2.0f));
                }
                if (!(component instanceof GuiCombo) || !((GuiCombo)(b = (GuiCombo)component)).getSetting().isVisible()) continue;
                component.drawScreen(this.x + 155, i, mouseX, mouseY, partialTicks);
                i = (int)((float)i + (component.getHeight() + 2.0f));
            }
        }
        GL11.glPopMatrix();
    }

    public boolean ishover(float x1, float y1, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x1 && (float)mouseX <= x2 && (float)mouseY >= y1 && (float)mouseY <= y2;
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        int i = this.x + 15;
        for (GuiCategoty cat : this.categoties) {
            cat.mouseClicked(i, this.y, mouseX, mouseY, mouseButton);
            i = (int)((float)i + (cat.getWidth() + 10.0f));
        }
        if (this.ishover(i, this.y, this.x + 450, this.y + 20, mouseX, mouseY)) {
            this.draging = !this.draging;
            this.dragX = mouseX - this.x;
            this.dragY = mouseY - this.y;
        }
        if (categoty != null) {
            i = this.y + 30;
            for (Component component : categoty.getModules()) {
                component.mouseClicked(this.x, i, mouseX, mouseY, mouseButton);
                i = (int)((float)i + component.getHeight());
            }
        }
        if (module != null) {
            i = this.y + 45;
            for (Component component : module.getSettings()) {
                Component b;
                if (component instanceof GuiCheck && ((GuiCheck)(b = (GuiCheck)component)).getSetting().isVisible()) {
                    component.mouseClicked(this.x + 155, i, mouseX, mouseY, mouseButton);
                    i = (int)((float)i + (component.getHeight() + 2.0f));
                }
                if (component instanceof GuiSlider && ((GuiSlider)(b = (GuiSlider)component)).getSetting().isVisible()) {
                    component.mouseClicked(this.x + 155, i, mouseX, mouseY, mouseButton);
                    i = (int)((float)i + (component.getHeight() + 2.0f));
                }
                if (!(component instanceof GuiCombo) || !((GuiCombo)(b = (GuiCombo)component)).getSetting().isVisible()) continue;
                component.mouseClicked(this.x + 155, i, mouseX, mouseY, mouseButton);
                i = (int)((float)i + (component.getHeight() + 2.0f));
            }
        }
    }
}

