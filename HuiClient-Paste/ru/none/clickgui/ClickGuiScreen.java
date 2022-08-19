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
package ru.none.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.clickgui.Panel;
import ru.none.config.cfg;
import ru.none.module.Category;
import ru.none.module.module.Hud.ClickGui;
import ru.none.utils.ColorUtils;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.TimerUtils;
import ru.none.utils.Utils;

public class ClickGuiScreen
extends GuiScreen {
    ArrayList<Panel> panels = new ArrayList();
    int color = new Color(0, 0, 0, 0).getRGB();
    float animeAnim = 0.0f;
    TimerUtils timer;
    ArrayList<partical> particals = new ArrayList();

    public ClickGuiScreen() {
        int i = 10;
        for (Category category : Category.values()) {
            this.panels.add(new Panel(category, i, 10));
            i += 160;
        }
    }

    public void initGui() {
        super.initGui();
        for (Panel panel : this.panels) {
            panel.initGui();
        }
        this.timer = new TimerUtils();
        this.color = new Color(0, 0, 0, 0).getRGB();
        this.animeAnim = GuiScreen.height;
        Utils.playSound("cat.wav");
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        RenderUtils.drawGradientRect(0.0, GuiScreen.height / 4, GuiScreen.width, GuiScreen.height, false, new Color(0, 0, 0, 0).getRGB(), this.color);
        this.color = ColorUtils.TwoColoreffect(None.getColor(), new Color(this.color), 0.05f).getRGB();
        GL11.glPushMatrix();
        GL11.glRotated((double)5.0, (double)0.0, (double)0.0, (double)0.0);
        if (this.particals.size() < 100) {
            this.particals.add(new partical(MathUtils.getRandomInRange((float)GuiScreen.width, 0.0f), (float)GuiScreen.height + MathUtils.getRandomInRange((float)GuiScreen.height, 0.0f), MathUtils.getRandomInRange(0.5f, 0.05f)));
        }
        if (ClickGui.anime.getVal().equalsIgnoreCase("NSFW1")) {
            this.animeAnim = MathUtils.lerp(this.animeAnim, GuiScreen.height - 300, 0.05f);
            RenderUtils.drawImage(new ResourceLocation("none/gui/NSFW1.png"), GuiScreen.width - 300, (int)this.animeAnim, 400, 300);
        }
        if (ClickGui.anime.getVal().equalsIgnoreCase("NSFW2")) {
            this.animeAnim = MathUtils.lerp(this.animeAnim, GuiScreen.height - 300, 0.05f);
            RenderUtils.drawImage(new ResourceLocation("none/gui/NSFW2.png"), GuiScreen.width - 300, (int)this.animeAnim, 400, 300);
        }
        if (ClickGui.anime.getVal().equalsIgnoreCase("megumin")) {
            this.animeAnim = MathUtils.lerp(this.animeAnim, GuiScreen.height - 300, 0.05f);
            RenderUtils.drawImage(new ResourceLocation("none/gui/megumin.png"), GuiScreen.width - 300, (int)this.animeAnim, 400, 300);
        }
        if (ClickGui.anime.getVal().equalsIgnoreCase("babidgon")) {
            this.animeAnim = MathUtils.lerp(this.animeAnim, GuiScreen.height - 300, 0.05f);
            RenderUtils.drawImage(new ResourceLocation("none/gui/bp.png"), GuiScreen.width - 400, (int)this.animeAnim, 400, 300);
        }
        GL11.glPopMatrix();
        for (partical partical2 : this.particals) {
            partical2.render();
            partical2.setY(partical2.getY() - partical2.speed);
            if (!(Math.random() <= (double)0.1f)) continue;
            partical2.setX(partical2.getX() + MathUtils.getRandomInRange(-0.1f, 0.1f));
        }
        for (int i = 0; i < this.particals.size(); ++i) {
            if (!(this.particals.get(i).getY() <= 0.0f)) continue;
            this.particals.remove(i);
        }
        for (Panel panel : this.panels) {
            panel.drawScreen(mouseX, mouseY, partialTicks);
        }
        int scrollWheel = Mouse.getDWheel();
        for (Panel panel : this.panels) {
            if (Keyboard.isKeyDown((int)42)) {
                if (scrollWheel > 0) {
                    panel.setX(panel.getX() + 15);
                }
                if (scrollWheel >= 0) continue;
                panel.setX(panel.getX() - 15);
                continue;
            }
            if (scrollWheel > 0) {
                panel.setY(panel.getY() + 15);
            }
            if (scrollWheel >= 0) continue;
            panel.setY(panel.getY() - 15);
        }
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        cfg.save();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (Panel panel : this.panels) {
            panel.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        for (Panel panel : this.panels) {
            panel.mouseReleased(mouseX, mouseY, state);
        }
    }

    public static class partical {
        float speed;
        float x;
        float y;

        public partical(float x, float y, float speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        public float getX() {
            return this.x;
        }

        public float getY() {
            return this.y;
        }

        public void setX(float x) {
            this.x = x;
        }

        public void setY(float y) {
            this.y = y;
        }

        public void render() {
            RenderUtils.drawCircle(this.x, this.y, this.y / (float)GuiScreen.height * 3.0f, None.getColor().getRGB());
        }
    }
}

