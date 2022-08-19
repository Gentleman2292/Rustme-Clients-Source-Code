/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 */
package ru.none.musicgui;

import java.awt.Color;
import java.io.IOException;
import org.lwjgl.input.Mouse;
import ru.none.None;
import ru.none.music.Player;
import ru.none.musicgui.MusicButton;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.FontUtils;

public class MusicVol
extends MusicButton {
    boolean draging = false;

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (Player.player == null) {
            return;
        }
        if (!Mouse.isButtonDown((int)0)) {
            this.draging = false;
        }
        if (this.draging) {
            int b = (int)((float)(mouseX - x) / (this.getWidth() / 100.0f));
            if (b > 100) {
                Player.player.setVolume(100);
            } else if (b < 0) {
                Player.player.setVolume(0);
            } else {
                Player.setVolume(b);
            }
        }
        RenderUtils.drawRect(x, y + 5, (float)x + this.getWidth() / 100.0f * (float)Player.player.getVolume(), y + 6, None.getColor().getRGB());
        RenderUtils.drawCircle((float)x + this.getWidth() / 100.0f * (float)Player.player.getVolume(), y + 6, 3.0f, None.getColor().getRGB());
        FontUtils.fr.drawString(Player.player.getVolume() + "", (float)(x + 3) + this.getWidth() / 100.0f * (float)Player.player.getVolume(), (float)y + this.getHeight() / 2.0f - (float)(FontUtils.fr.getHeight() / 2), new Color(30, 30, 30).getRGB());
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (Player.player == null) {
            return;
        }
        if (this.ishover(x, y, (float)x + this.getWidth() + 5.0f, (float)y + this.getHeight(), mouseX, mouseY) && mouseButton == 0) {
            this.draging = true;
        }
    }

    @Override
    public float getHeight() {
        return 10.0f;
    }

    @Override
    public float getWidth() {
        return 100.0f;
    }
}

