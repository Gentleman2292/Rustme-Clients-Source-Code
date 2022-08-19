/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Render;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.RenderUtils;

public class ChinaHat
extends Module {
    public ChinaHat() {
        super("ChinaHat", Category.Render);
    }

    @Override
    public void onRender3D() {
        double height;
        super.onRender3D();
        double d = height = Minecraft.player.isSneaking() ? -0.1 : 0.12;
        if (ChinaHat.mc.gameSettings.thirdPersonView == 1 || ChinaHat.mc.gameSettings.thirdPersonView == 2) {
            Color color;
            GlStateManager.pushMatrix();
            GL11.glBlendFunc((int)770, (int)771);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            RenderUtils.enableSmoothLine(2.5f);
            GL11.glShadeModel((int)7425);
            GL11.glDisable((int)2884);
            GL11.glEnable((int)3042);
            GL11.glEnable((int)2929);
            GL11.glTranslatef((float)0.0f, (float)((float)((double)Minecraft.player.height + height)), (float)0.0f);
            GL11.glRotatef((float)(-Minecraft.player.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
            Color color2 = Color.WHITE;
            color2 = new Color(105, 55, 255);
            GL11.glBegin((int)6);
            RenderUtils.glColor(color2, 255.0f);
            GL11.glVertex3d((double)0.0, (double)0.3, (double)0.0);
            float i = 0.0f;
            while ((double)i < 360.5) {
                color = None.getColor();
                RenderUtils.glColor(color, 255.0f);
                GL11.glVertex3d((double)(Math.cos((double)i * Math.PI / 180.0) * 0.66), (double)0.0, (double)(Math.sin((double)i * Math.PI / 180.0) * 0.66));
                i += 1.0f;
            }
            GL11.glVertex3d((double)0.0, (double)0.3, (double)0.0);
            GL11.glEnd();
            GL11.glBegin((int)2);
            i = 0.0f;
            while ((double)i < 360.5) {
                color = None.getColor();
                RenderUtils.glColor(color, 255.0f);
                GL11.glVertex3d((double)(Math.cos((double)i * Math.PI / 180.0) * 0.66), (double)0.0, (double)(Math.sin((double)i * Math.PI / 180.0) * 0.66));
                i += 1.0f;
            }
            GL11.glEnd();
            GlStateManager.enableAlpha();
            RenderUtils.disableSmoothLine();
            GL11.glShadeModel((int)7424);
            GL11.glEnable((int)2884);
            GL11.glDisable((int)3042);
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            GlStateManager.resetColor();
            GlStateManager.popMatrix();
        }
    }
}

