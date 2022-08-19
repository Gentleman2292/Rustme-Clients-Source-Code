/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec2f
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package ru.none.musicgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.music.Player;
import ru.none.music.Track;
import ru.none.music.WebUtils;
import ru.none.musicgui.MusicPlay;
import ru.none.musicgui.MusicTrecks;
import ru.none.musicgui.MusicVol;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class MusicGui
extends GuiScreen {
    private GuiTextField searchField;
    protected CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/latoregular.ttf"), 20.0f, 0), true, true);
    protected CustomFontRenderer fr2 = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/roadrage.ttf"), 30.0f, 0), true, true);
    int scroll = 0;
    int x;
    int y;
    int wight = 300;
    int height = 200;
    boolean draging = false;
    int dragX;
    int dragY;
    static ArrayList<MusicTrecks> trecks = new ArrayList();
    MusicPlay musicPlay = new MusicPlay();
    MusicVol musicVol = new MusicVol();

    public static void addTreck(MusicTrecks treck) {
        trecks.add(treck);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.searchField.xPosition = this.x + 5;
        this.searchField.yPosition = this.y + 5;
        ArrayList<Vec2f> vec2fs = new ArrayList<Vec2f>();
        vec2fs.add(new Vec2f((float)(this.x + 100), (float)this.y));
        vec2fs.add(new Vec2f((float)(this.x + this.wight), (float)this.y));
        vec2fs.add(new Vec2f((float)(this.x + this.wight), (float)(this.y + this.height)));
        vec2fs.add(new Vec2f((float)this.x, (float)(this.y + this.height)));
        vec2fs.add(new Vec2f((float)this.x, (float)(this.y + 30)));
        vec2fs.add(new Vec2f((float)(this.x + 90), (float)(this.y + 30)));
        vec2fs.add(new Vec2f((float)(this.x + 100), (float)(this.y + 20)));
        vec2fs.add(new Vec2f((float)(this.x + 100), (float)this.y));
        RenderUtils.drawShadowRect(this.x, this.y, this.x + this.wight, this.y + this.height, 5);
        RenderUtils.drawRect(this.x, this.y, this.x + this.wight, this.y + this.height, -1);
        RenderUtils.drawRect(this.x, this.y + this.height - 25, this.x + this.wight, this.y + this.height, new Color(200, 200, 200).getRGB());
        RenderUtils.drawSome(vec2fs, None.getColor().getRGB());
        vec2fs.clear();
        vec2fs.add(new Vec2f((float)this.x, (float)this.y));
        vec2fs.add(new Vec2f((float)(this.x + 100), (float)this.y));
        vec2fs.add(new Vec2f((float)(this.x + 100), (float)(this.y + 20)));
        vec2fs.add(new Vec2f((float)(this.x + 90), (float)(this.y + 30)));
        vec2fs.add(new Vec2f((float)this.x, (float)(this.y + 30)));
        vec2fs.add(new Vec2f((float)(this.x + 100), (float)this.y));
        this.musicPlay.drawScreen(this.x, this.y + this.height - 25, mouseX, mouseY, partialTicks);
        this.musicVol.drawScreen(this.x + 25, this.y + this.height - 15, mouseX, mouseY, partialTicks);
        if (Player.player != null) {
            this.fr.drawString(Player.MusicName, this.x + 160, this.y + this.height - 15, new Color(60, 60, 60).getRGB());
            this.fr.drawString("" + MathUtils.round((float)Player.player.getPosition() / 1000.0f / 60.0f, 0.01f) + "", this.x + this.wight - this.fr.getStringWidth(" " + MathUtils.round((float)Player.player.getPosition() / 1000.0f / 60.0f, 0.01f) + ""), this.y + this.height - 10, new Color(0, 0, 0).getRGB());
            RenderUtils.drawRect(this.x + 30, this.y + this.height - 20 + 2, (float)(this.x + 30) + 260.0f, this.y + this.height - 20 + 3, -1);
            RenderUtils.drawRect(this.x + 30, this.y + this.height - 20 + 2, (float)(this.x + 30) + 7.8E-4f * (float)Player.player.getPosition(), this.y + this.height - 20 + 3, None.getColor().getRGB());
        }
        if (!Mouse.isButtonDown((int)0)) {
            this.draging = false;
        }
        if (this.draging) {
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
        }
        if (this.ishover(this.x, this.y, this.x + this.wight, this.y + this.height - 25, mouseX, mouseY)) {
            int scrollWheel = Mouse.getDWheel();
            if (scrollWheel > 0) {
                this.scroll += 7;
            } else if (scrollWheel < 0) {
                this.scroll -= 7;
            }
        }
        GL11.glEnable((int)3089);
        RenderUtils.scissorRect(this.x + 5, this.y, this.x + this.wight - 5, this.y + this.height - 25);
        int i = 0;
        for (MusicTrecks treck : trecks) {
            treck.drawScreen(this.x + 5, this.y + i + this.scroll, mouseX, mouseY, partialTicks);
            i += 23;
        }
        GL11.glDisable((int)3089);
        RenderUtils.drawSome2(vec2fs, -1);
        this.searchField.drawTextBox();
        RenderUtils.drawUpShadow(this.x + 100, this.y, this.x + this.wight, this.y + 5);
        RenderUtils.drawDownShadow(this.x, this.y + this.height - 23, this.x + this.wight, this.y + this.height - 20);
    }

    protected void keyTyped(char typedChar, int keyCode) {
        this.searchField.setFocused(true);
        this.searchField.textboxKeyTyped(typedChar, keyCode);
        if (typedChar == '\t') {
            if (!this.searchField.isFocused()) {
                this.searchField.setFocused(true);
            } else {
                this.searchField.setFocused(false);
            }
        }
        if (keyCode == 1) {
            Minecraft.getMinecraft().displayGuiScreen(null);
        } else if (keyCode == 28) {
            this.scroll = 0;
            trecks.clear();
            CharSequence[] massive = this.searchField.getText().split(" ");
            String g = String.join((CharSequence)"+", massive);
            System.out.println("https://ru.hitmotop.com/search?q=" + g);
            String ret = WebUtils.visitSite("https://ru.hitmotop.com/search?q=" + g);
            String[] rets = ret.split("data-musmeta");
            boolean first = true;
            int i = 0;
            for (String st : rets) {
                if (!first && i < rets.length) {
                    System.out.println(st);
                    String name = st.substring(st.indexOf("title") + 8, st.indexOf("url") - 3);
                    String aurhor = st.substring(st.indexOf("artist") + 9, st.indexOf("title") - 3);
                    String url = st.substring(st.indexOf("url") + 6, st.indexOf("img") - 3);
                    url = url.replaceAll("\\\\", "");
                    System.out.println(name + "  " + url);
                    trecks.add(new MusicTrecks(new Track(name, 1000L, url, aurhor)));
                }
                ++i;
                first = false;
            }
        }
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents((boolean)false);
    }

    public void updateScreen() {
        this.searchField.updateCursorCounter();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.musicPlay.mouseClicked(this.x, this.y + this.height - 25, mouseX, mouseY, mouseButton);
        this.musicVol.mouseClicked(this.x + 25, this.y + this.height - 15, mouseX, mouseY, mouseButton);
        if (this.ishover(this.x, this.y, this.x + 100, this.y + 30, mouseX, mouseY) && mouseButton == 0) {
            this.draging = true;
            this.dragX = mouseX - this.x;
            this.dragY = mouseY - this.y;
        }
        int i = 0;
        for (MusicTrecks treck : trecks) {
            if (this.y + i + this.scroll + 20 < this.y + this.height - 20) {
                treck.mouseClicked(this.x + 5, this.y + i + this.scroll, mouseX, mouseY, mouseButton);
            }
            i += 23;
        }
        this.searchField.mouseClicked(this.x, this.y, mouseButton);
    }

    public boolean ishover(float x1, float y1, float x2, float y2, int mouseX, int mouseY) {
        return (float)mouseX >= x1 && (float)mouseX <= x2 && (float)mouseY >= y1 && (float)mouseY <= y2;
    }

    public void initGui() {
        super.initGui();
        this.searchField = new GuiTextField(40, this.fontRendererObj, this.x + 5, this.y + 5, 90, 17);
        Keyboard.enableRepeatEvents((boolean)true);
    }
}

