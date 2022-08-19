/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package ru.none.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.clickgui.Mod;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class Panel {
    int x;
    int y;
    float anim = 0.0f;
    Category category;
    boolean open = false;
    ArrayList<Mod> mods = new ArrayList();
    boolean draging = false;
    int dragX = 0;
    int dragY = 0;
    protected CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/roadrage.ttf"), 25.0f, 0), true, true);

    public Panel(Category category, int x, int y) {
        this.x = x;
        this.y = y;
        this.category = category;
        ArrayList<Module> modules = new ArrayList<Module>(None.moduleManager.getModules(category));
        modules.sort(new Comparator<Module>(){

            @Override
            public int compare(Module o1, Module o2) {
                return Panel.this.fr.getStringWidth(o2.getName()) - Panel.this.fr.getStringWidth(o1.getName());
            }
        });
        for (Module module : modules) {
            this.mods.add(new Mod(module));
        }
    }

    public boolean ishover(float x1, float y1, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x1 && (float)mouseX <= x2 && (float)mouseY >= y1 && (float)mouseY <= y2;
    }

    public boolean isOpen() {
        return this.open;
    }

    public ArrayList<Mod> getMods() {
        return this.mods;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void initGui() {
        this.anim = 0.0f;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.anim = MathUtils.lerp(this.anim, 1.0f, 0.2f);
        int b = 18;
        for (Mod mod : this.mods) {
            b += mod.getHeight();
        }
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(this.x, this.y, 150.0f, b, this.anim);
        if (!Mouse.isButtonDown((int)0)) {
            this.draging = false;
        }
        if (this.draging) {
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
        }
        if (this.open) {
            RenderUtils.drawShadowRect(this.x, this.y, this.x + 150, this.y + b, 3);
            RenderUtils.drawRect(this.x, this.y, this.x + 150, this.y + b, new Color(30, 30, 30).getRGB());
            RenderUtils.drawShadowRect(this.x, this.y + b, this.x + 150, this.y + b + 2, None.getColor().getRGB(), 3);
        }
        RenderUtils.drawShadowRect(this.x, this.y, this.x + 150, this.y + 15, 2);
        this.fr.drawCenteredString(this.category.name(), this.x + 75, this.y + 1, None.getColor().getRGB());
        if (this.open) {
            int i = this.y + 16;
            for (Mod mod : this.mods) {
                mod.drawScreen(this.x + 2, i, mouseX, mouseY, partialTicks);
                i += mod.getHeight();
            }
        }
        GL11.glPopMatrix();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.ishover(this.x, this.y, this.x + 150, this.y + 15, mouseX, mouseY)) {
            if (mouseButton == 0) {
                boolean bl = this.draging = !this.draging;
                if (this.draging) {
                    this.dragX = mouseX - this.x;
                    this.dragY = mouseY - this.y;
                }
            }
            if (mouseButton == 1) {
                boolean bl = this.open = !this.open;
            }
        }
        if (this.open) {
            int i = this.y + 16;
            for (Mod mod : this.mods) {
                if (this.ishover(this.x, i, this.x + 150, i + mod.getHeight() + 1, mouseX, mouseY)) {
                    mod.mouseClicked(this.x + 2, i, mouseX, mouseY, mouseButton);
                }
                i += mod.getHeight();
            }
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.open) {
            int i = this.y + 16;
            for (Mod mod : this.mods) {
                mod.mouseReleased(this.x + 2, i, mouseX, mouseY, state);
                i += mod.getHeight();
            }
        }
    }
}

