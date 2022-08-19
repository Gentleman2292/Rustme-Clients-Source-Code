/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Hud;

import java.awt.Color;
import org.lwjgl.opengl.GL11;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.music.Player;
import ru.none.utils.RenderUtils;

public class PlayerHud
extends Module {
    public PlayerHud() {
        super("MusicHud", Category.Hud, 100, 20);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int x = this.getPosX();
        int y = this.getPosY();
        if (Player.player == null) {
            return;
        }
        this.setSizeX(this.fr.getStringWidth(Player.MusicName));
        RenderUtils.drawShadowRect(x, y, x + this.getSizeX(), y + 20, 5);
        RenderUtils.drawRect(x, y, x + this.getSizeX(), y + 20, new Color(30, 30, 30).getRGB());
        GL11.glEnable((int)3089);
        RenderUtils.scissorRect(x, y, x + this.getSizeX(), y + 20);
        this.fr.drawCenteredString(Player.MusicName, x + this.getSizeX() / 2, y + 5 - this.fr.getHeight() / 2, -1);
        GL11.glDisable((int)3089);
    }
}

