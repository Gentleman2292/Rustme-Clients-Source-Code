/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package ru.none.clickguiWhite;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.clickguiWhite.ClickMod;
import ru.none.clickguiWhite.ClickObject;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class ClickCat
extends ClickObject {
    boolean open;
    int posx;
    int posy;
    int dragX;
    int dragY;
    boolean draging = false;
    float animOpen = 0.0f;
    float scroll = 0.0f;
    ArrayList<ClickMod> mods = new ArrayList();
    Category category;
    protected CustomFontRenderer fr2 = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/latoregular.ttf"), 25.0f, 0), true, true);

    public ClickCat(Category category, int x, int y) {
        this.posx = x;
        this.posy = y;
        this.category = category;
        for (Module module : None.moduleManager.getModules(category)) {
            this.mods.add(new ClickMod(module));
        }
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (this.open) {
            this.mods.get((int)0).anim = MathUtils.lerp(this.mods.get((int)0).anim, 1.0f, 0.3f);
            for (int i = 0; i < this.mods.size(); ++i) {
                if (i > this.mods.size() - 2 || !((double)this.mods.get((int)i).anim >= 0.9)) continue;
                this.mods.get((int)(i + 1)).anim = MathUtils.lerp(this.mods.get((int)(i + 1)).anim, 1.0f, 0.3f);
            }
        } else {
            for (ClickMod clickMod : this.mods) {
                clickMod.resetAnim();
            }
        }
        if (!Mouse.isButtonDown((int)0)) {
            this.draging = false;
        }
        if (this.draging) {
            this.posx = mouseX - this.dragX;
            this.posy = mouseY - this.dragY;
        }
        RenderUtils.drawRect(this.posx, this.posy, (float)this.posx + this.getWidth(), (float)this.posy + this.getHeight(), new Color(220, 220, 220).getRGB());
        this.fr2.drawString(this.category.name(), this.posx + 10, this.posy + 10 - this.fr2.getHeight() / 2, new Color(0, 0, 0).getRGB());
        int dlinna = 0;
        for (ClickMod clickMod : this.mods) {
            dlinna = (int)((float)dlinna + clickMod.getHeight2());
        }
        if (dlinna > 250) {
            dlinna = 250;
        }
        this.animOpen = this.open ? MathUtils.lerp(this.animOpen, dlinna, 0.4f) : MathUtils.lerp(this.animOpen, 0.0f, 0.4f);
        GL11.glEnable((int)3089);
        RenderUtils.scissorRect(this.posx, (float)this.posy + this.getHeight(), (float)this.posx + this.getWidth(), (float)this.posy + this.getHeight() + this.animOpen);
        RenderUtils.drawRect(this.posx, (float)this.posy + this.getHeight(), (float)this.posx + this.getWidth(), (float)this.posy + this.getHeight() + (float)dlinna, -1);
        RenderUtils.drawUpShadow(this.posx, (float)this.posy + this.getHeight(), (float)this.posx + this.getWidth(), (float)this.posy + this.getHeight() + 5.0f);
        int n = (int)this.scroll;
        for (ClickMod mod : this.mods) {
            int n2;
            mod.drawScreen(this.posx, (int)((float)(n2 + this.posy) + this.getHeight()), mouseX, mouseY, partialTicks);
            n2 = (int)((float)n2 + mod.getHeight2());
        }
        GL11.glDisable((int)3089);
        if (this.ishover(this.posx, this.posy, (float)this.posx + this.getWidth(), (float)this.posy + this.getHeight() + this.animOpen, mouseX, mouseY) && this.open && dlinna == 250) {
            int n3 = Mouse.getDWheel();
            if (n3 > 0) {
                this.scroll += 3.0f;
            } else if (n3 < 0) {
                this.scroll -= 3.0f;
            }
        }
        if (dlinna != 250) {
            this.scroll = 0.0f;
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(this.posx, this.posy, (float)this.posx + this.getWidth(), (float)this.posy + this.getHeight(), mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.draging = true;
                this.dragX = mouseX - this.posx;
                this.dragY = mouseY - this.posy;
                System.out.println("work");
            }
            if (mouseButton == 1) {
                this.open = !this.open;
            }
        }
        int b = (int)this.scroll;
        if (this.open) {
            for (ClickMod mod : this.mods) {
                mod.mouseClicked(this.posx, (int)((float)(b + this.posy) + this.getHeight()), mouseX, mouseY, mouseButton);
                b = (int)((float)b + mod.getHeight2());
            }
        }
    }

    @Override
    public float getHeight() {
        return 20.0f;
    }

    @Override
    public float getWidth() {
        return 100.0f;
    }
}

