/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class JumpCircles
extends Module {
    static Color jumpCircleColor = None.getColor();
    static final int TYPE = 0;
    static final byte MAX_JC_TIME = 20;
    static List<Circle> circles = new ArrayList<Circle>();
    static float pt;

    public JumpCircles() {
        super("JumpCircles", Category.Render);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (Minecraft.player.motionY == 0.33319999363422365) {
            JumpCircles.handleEntityJump((Entity)Minecraft.player);
        }
        JumpCircles.onLocalPlayerUpdate(Minecraft.player);
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        EntityPlayerSP client = Minecraft.player;
        double ix = -(client.lastTickPosX + (client.posX - client.lastTickPosX) * (double)mc.getRenderPartialTicks());
        double iy = -(client.lastTickPosY + (client.posY - client.lastTickPosY) * (double)mc.getRenderPartialTicks());
        double iz = -(client.lastTickPosZ + (client.posZ - client.lastTickPosZ) * (double)mc.getRenderPartialTicks());
        GL11.glPushMatrix();
        GL11.glTranslated((double)ix, (double)iy, (double)iz);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)3008);
        GL11.glDisable((int)2929);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2896);
        GL11.glShadeModel((int)7425);
        Collections.reverse(circles);
        try {
            for (Circle c : circles) {
                float k = (float)c.existed / 20.0f;
                double x = c.position().xCoord;
                double y = c.position().yCoord - (double)k * 0.5;
                double z = c.position().zCoord;
                float end = k + 1.0f - k;
                GL11.glBegin((int)8);
                for (int i = 0; i <= 360; i += 5) {
                    GL11.glColor4f((float)((float)c.color().xCoord), (float)((float)c.color().yCoord), (float)((float)c.color().zCoord), (float)(0.2f * (1.0f - (float)c.existed / 20.0f)));
                    GL11.glVertex3d((double)(x + Math.cos(Math.toRadians(i * 4)) * (double)k), (double)y, (double)(z + Math.sin(Math.toRadians(i * 4)) * (double)k));
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.01f * (1.0f - (float)c.existed / 20.0f)));
                    GL11.glVertex3d((double)(x + Math.cos(Math.toRadians(i)) * (double)end), (double)(y + Math.sin(k * 8.0f) * 0.5), (double)(z + Math.sin(Math.toRadians(i) * (double)end)));
                }
                GL11.glEnd();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        Collections.reverse(circles);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3008);
        GL11.glPopMatrix();
    }

    public static void onLocalPlayerUpdate(EntityPlayerSP instance) {
        circles.removeIf(Circle::update);
    }

    public static void handleEntityJump(Entity entity) {
        int red = (int)((float)(jumpCircleColor.getRed() >> 16 & 0xFF) / 100.0f);
        int green = (int)((float)(jumpCircleColor.getGreen() >> 8 & 0xFF) / 100.0f);
        int blue = (int)((float)(jumpCircleColor.getBlue() & 0xFF) / 100.0f);
        Vec3d color = new Vec3d((double)red, (double)green, (double)blue);
        circles.add(new Circle(entity.getPositionVector(), color));
    }

    static class Circle {
        private final Vec3d vec;
        private final Vec3d color;
        byte existed;

        Circle(Vec3d vec, Vec3d color) {
            this.vec = vec;
            this.color = color;
        }

        Vec3d position() {
            return this.vec;
        }

        Vec3d color() {
            return this.color;
        }

        boolean update() {
            this.existed = (byte)(this.existed + 1);
            return this.existed > 20;
        }
    }
}

