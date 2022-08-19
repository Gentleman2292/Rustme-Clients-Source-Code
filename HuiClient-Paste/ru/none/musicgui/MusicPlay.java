/*
 * Decompiled with CFR 0.150.
 */
package ru.none.musicgui;

import java.io.IOException;
import ru.none.None;
import ru.none.music.Player;
import ru.none.musicgui.MusicButton;
import ru.none.utils.font.FontUtils;

public class MusicPlay
extends MusicButton {
    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        if (Player.player == null) {
            return;
        }
        if (Player.player.isPaused()) {
            FontUtils.fr.drawCenteredString(">", x + 12, y + 12 - FontUtils.fr.getHeight() / 2, None.getColor().getRGB());
        } else {
            FontUtils.fr.drawCenteredString("||", x + 12, y + 12 - FontUtils.fr.getHeight() / 2, None.getColor().getRGB());
        }
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (Player.player == null) {
            return;
        }
        if (this.ishover(x, y, x + 25, y + 25, mouseX, mouseY)) {
            if (Player.player.isPaused()) {
                Player.resume();
            } else if (Player.isPlaying()) {
                Player.pause();
            }
        }
    }
}

