/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Render;

import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;

public class ESP
extends Module {
    public static ModeSetting mode;

    public ESP() {
        super("ESP", Category.Render);
        ArrayList<String> modes = new ArrayList<String>();
        modes.add("2D");
        modes.add("Shader");
        mode = new ModeSetting("Mode", this, modes, "2D");
        None.settingManager.add(mode);
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        if (mode.getVal().equalsIgnoreCase("2D")) {
            for (Entity e : ESP.mc.world.loadedEntityList) {
                if (!(e instanceof EntityPlayer)) continue;
                if (e == Minecraft.player) {
                    if (ESP.mc.gameSettings.thirdPersonView != 1) continue;
                }
                double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)mc.getRenderPartialTicks() - ESP.mc.getRenderManager().viewerPosX;
                double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)mc.getRenderPartialTicks() - ESP.mc.getRenderManager().viewerPosY;
                double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)mc.getRenderPartialTicks() - ESP.mc.getRenderManager().viewerPosZ;
                ESP.mc.entityRenderer.setupCameraTransform(mc.getRenderPartialTicks(), 0);
                GL11.glPushMatrix();
                GL11.glDisable((int)2929);
                GL11.glDisable((int)3553);
                GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
                GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-ESP.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)ESP.mc.getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glLineWidth((float)3.0f);
                GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
                GL11.glBegin((int)3);
                GL11.glVertex3d((double)((double)e.width + 0.1), (double)-0.4, (double)0.0);
                GL11.glVertex3d((double)((double)e.width + 0.1), (double)((double)e.height + 0.4), (double)0.0);
                GL11.glVertex3d((double)((double)(-e.width) - 0.1), (double)((double)e.height + 0.4), (double)0.0);
                GL11.glVertex3d((double)((double)(-e.width) - 0.1), (double)-0.4, (double)0.0);
                GL11.glVertex3d((double)((double)e.width + 0.1), (double)-0.4, (double)0.0);
                GL11.glEnd();
                GL11.glLineWidth((float)0.7f);
                GL11.glColor3f((float)255.0f, (float)255.0f, (float)255.0f);
                GL11.glBegin((int)3);
                GL11.glVertex3d((double)((double)e.width + 0.1), (double)-0.4, (double)0.0);
                GL11.glVertex3d((double)((double)e.width + 0.1), (double)((double)e.height + 0.4), (double)0.0);
                GL11.glVertex3d((double)((double)(-e.width) - 0.1), (double)((double)e.height + 0.4), (double)0.0);
                GL11.glVertex3d((double)((double)(-e.width) - 0.1), (double)-0.4, (double)0.0);
                GL11.glVertex3d((double)((double)e.width + 0.1), (double)-0.4, (double)0.0);
                GL11.glEnd();
                GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
                GL11.glLineWidth((float)3.0f);
                GL11.glBegin((int)3);
                GL11.glVertex3d((double)((double)e.width + 0.4), (double)-0.4, (double)0.0);
                GL11.glVertex3d((double)((double)e.width + 0.4), (double)((double)e.height + 0.4), (double)0.0);
                GL11.glEnd();
                GL11.glColor3f((float)Color.GRAY.getRed(), (float)Color.GRAY.getGreen(), (float)Color.GRAY.getBlue());
                GL11.glLineWidth((float)0.7f);
                GL11.glBegin((int)3);
                GL11.glVertex3d((double)((double)e.width + 0.4), (double)-0.4, (double)0.0);
                GL11.glVertex3d((double)((double)e.width + 0.4), (double)((double)e.height + 0.4), (double)0.0);
                GL11.glEnd();
                GL11.glColor3f((float)0.0f, (float)255.0f, (float)0.0f);
                GL11.glBegin((int)3);
                GL11.glVertex3d((double)((double)e.width + 0.4), (double)-0.4, (double)0.0);
                GL11.glVertex3d((double)((double)e.width + 0.4), (double)((double)(((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth()) * ((double)e.height + 0.4)), (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)3);
                GL11.glEnd();
                GL11.glEnable((int)2929);
                GL11.glEnable((int)3553);
                GL11.glColor3f((float)255.0f, (float)255.0f, (float)255.0f);
                GL11.glPopMatrix();
            }
        }
    }
}

