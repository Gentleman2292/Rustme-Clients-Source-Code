/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec2f
 *  org.lwjgl.opengl.GL11
 */
package ru.none.gui.shit;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.gui.Component;
import ru.none.utils.RenderUtils;

public class GuiObject
extends Component {
    ArrayList<Vec2f> vec2fs;
    int x;
    int y;

    public GuiObject(ArrayList<Vec2f> vec2fs, int x, int y) {
        this.vec2fs = vec2fs;
        this.x = x;
        this.y = y;
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)0.0f);
        RenderUtils.drawSome2(this.vec2fs, None.getColor().getRGB());
        GL11.glPopMatrix();
    }

    @Override
    protected void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public ArrayList<Vec2f> getVec2fs() {
        return this.vec2fs;
    }
}

