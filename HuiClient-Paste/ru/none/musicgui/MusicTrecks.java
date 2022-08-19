/*
 * Decompiled with CFR 0.150.
 */
package ru.none.musicgui;

import java.awt.Color;
import java.io.IOException;
import ru.none.music.Player;
import ru.none.music.Track;
import ru.none.musicgui.MusicButton;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.FontUtils;

public class MusicTrecks
extends MusicButton {
    Track track;

    public MusicTrecks(Track track) {
        this.track = track;
    }

    public void play() {
        Player.play(this.track.getUrl(), this.track.getName());
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        RenderUtils.drawRect(x, y, x + 290, y + 20, new Color(200, 200, 200).getRGB());
        FontUtils.fr.drawString("" + new String(this.track.getName()), x + 1, y + 1, new Color(30, 30, 30).getRGB());
        FontUtils.fr.drawString("" + new String(this.track.getAuthor()), x + 1, y + 11, new Color(60, 60, 60).getRGB());
    }

    @Override
    public void initGui() {
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.ishover(x, y, x + 290, y + 20, mouseX, mouseY)) {
            this.play();
        }
    }
}

