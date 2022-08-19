/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec2f
 *  org.lwjgl.opengl.GL11
 */
package ru.none.clickguiWhite;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.clickguiWhite.ClickCat;
import ru.none.clickguiWhite.MusicGui;
import ru.none.config.cfg;
import ru.none.module.Category;
import ru.none.module.module.Hud.ClickGui;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class ClickWhite
extends GuiScreen {
    public static CustomFontRenderer fr2 = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/roadrage.ttf"), 30.0f, 0), true, true);
    float anim = 0.0f;
    int x;
    int y;
    public static MusicGui musicGui;
    float animeAnim = 0.0f;
    ArrayList<Float> d = new ArrayList();
    ArrayList<ClickCat> cats = new ArrayList();

    public ClickWhite() {
        int x = 5;
        for (int i = 0; i < Category.values().length; ++i) {
            this.cats.add(new ClickCat(Category.values()[i], x, 20));
            if (Category.values().length / 2 == i + 1) {
                x += 300;
                continue;
            }
            x += 120;
        }
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
        musicGui = new MusicGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.anim = MathUtils.lerp(this.anim, 100.0f, 0.05f);
        ArrayList<Vec2f> vec2fs = new ArrayList<Vec2f>();
        musicGui.drawScreen(this.x, this.y, mouseX, mouseY, partialTicks);
        int x = GuiScreen.width / 2 - 10;
        int y = 50;
        if (ClickGui.anime.getVal().equalsIgnoreCase("putin")) {
            this.animeAnim = MathUtils.lerp(this.animeAnim, GuiScreen.height - 300, 0.05f);
            RenderUtils.drawImage(new ResourceLocation("none/gui/putin1.png"), GuiScreen.width - 300, (int)this.animeAnim, 400, 300);
        }
        if (ClickGui.anime.getVal().equalsIgnoreCase("negr")) {
            this.animeAnim = MathUtils.lerp(this.animeAnim, GuiScreen.height - 300, 0.05f);
            RenderUtils.drawImage(new ResourceLocation("none/gui/negr.png"), GuiScreen.width - 300, (int)this.animeAnim, 400, 300);
        }
        if (this.anim >= 99.0f) {
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
                if (i != 2) continue;
                this.text("y", i, size);
                if (!((double)size > 0.9)) continue;
                this.d.set(i + 1, Float.valueOf(MathUtils.lerp(this.d.get(i + 1).floatValue(), 1.0f, 0.1f)));
            }
        }
        vec2fs.add(new Vec2f((float)x, (float)y));
        if (this.anim >= 60.0f) {
            vec2fs.add(new Vec2f((float)x, (float)(y + 60)));
            vec2fs.add(new Vec2f((float)x + this.anim - 60.0f, (float)(y + 60)));
        } else {
            vec2fs.add(new Vec2f((float)x, (float)y + this.anim));
        }
        RenderUtils.drawSome(vec2fs, None.getColor().getRGB());
        vec2fs.clear();
        vec2fs.add(new Vec2f((float)(x + 20), (float)(y + 60)));
        if (this.anim >= 60.0f) {
            vec2fs.add(new Vec2f((float)(x + 20), (float)y));
            vec2fs.add(new Vec2f((float)(x + 20) - (this.anim - 60.0f), (float)y));
        } else {
            vec2fs.add(new Vec2f((float)(x + 20), (float)(y + 60) - this.anim));
        }
        RenderUtils.drawSome(vec2fs, None.getColor().getRGB());
        for (ClickCat cat : this.cats) {
            cat.drawScreen(0, 0, mouseX, mouseY, partialTicks);
        }
    }

    public void text(String test, int i, float size) {
        int x = GuiScreen.width / 2 - 10;
        int y = 50;
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(x - 10, y + fr2.getHeight() * i, 20.0f, fr2.getHeight(), size);
        fr2.drawString(test, x + 10 - fr2.getStringWidth("X") / 2, y + fr2.getHeight() * i, i == 0 ? None.getColor().getRGB() : -1);
        GL11.glPopMatrix();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (ClickCat cat : this.cats) {
            cat.mouseClicked(0, 0, mouseX, mouseY, mouseButton);
        }
        musicGui.mouseClicked(this.x, this.y, mouseX, mouseY, mouseButton);
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        cfg.save();
    }

    public void initGui() {
        super.initGui();
        this.anim = 0.0f;
        this.d.clear();
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
        this.d.add(Float.valueOf(0.0f));
        for (ClickCat cat : this.cats) {
            cat.initGui();
        }
        musicGui.initGui();
    }
}

