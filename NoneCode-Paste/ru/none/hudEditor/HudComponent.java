/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 */
package ru.none.hudEditor;

import java.awt.Color;
import java.io.IOException;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import ru.none.module.Module;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class HudComponent {
    boolean draging = false;
    int dragX = 0;
    int dragY = 0;
    Module module;
    protected CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/main.ttf"), 20.0f, 0), true, true);

    public Module getModule() {
        return this.module;
    }

    public boolean ishover(float x1, float y1, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x1 && (float)mouseX <= x2 && (float)mouseY >= y1 && (float)mouseY <= y2;
    }

    public HudComponent(Module module) {
        this.module = module;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtils.drawRect(this.module.getPosX(), this.module.getPosY(), this.module.getPosX() + this.module.getSizeX(), this.module.getPosY() + this.module.getSizeY(), new Color(30, 30, 30, 200).getRGB());
        this.fr.drawCenteredString(this.module.getName(), this.module.getPosX() + this.module.getSizeX() / 2, this.module.getPosY() + 2, -1);
        if (this.draging) {
            this.module.setPosX(mouseX - this.dragX);
            this.module.setPosY(mouseY - this.dragY);
        }
        if (!Mouse.isButtonDown((int)0)) {
            this.draging = false;
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.ishover(this.module.getPosX(), this.module.getPosY(), this.module.getPosX() + this.module.getSizeX(), this.module.getPosY() + this.module.getSizeY(), mouseX, mouseY) && mouseButton == 0) {
            this.draging = !this.draging;
            this.dragX = mouseX - this.module.getPosX();
            this.dragY = mouseY - this.module.getPosY();
        }
    }
}

