/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Render;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import ru.none.FriendManager;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.RenderUtils;

public class Tracers
extends Module {
    public Tracers() {
        super("Tracers", Category.Render);
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        Tracers.mc.gameSettings.viewBobbing = false;
        GL11.glPushMatrix();
        GL11.glEnable((int)2848);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)1.0E-6f);
        for (Entity entity : Tracers.mc.world.loadedEntityList) {
            if (entity == Minecraft.player || !(entity instanceof EntityPlayer)) continue;
            assert (mc.getRenderViewEntity() != null);
            Minecraft.player.getDistanceToEntity(entity);
            double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - Tracers.mc.getRenderManager().viewerPosX;
            double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - Tracers.mc.getRenderManager().viewerPosY;
            double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - Tracers.mc.getRenderManager().viewerPosZ;
            Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
            vec3d = vec3d.rotatePitch(-((float)Math.toRadians(Minecraft.player.rotationPitch)));
            Vec3d vec3d2 = vec3d.rotateYaw(-((float)Math.toRadians(Minecraft.player.rotationYaw)));
            GL11.glBegin((int)2);
            if (FriendManager.isFriend(entity.getName())) {
                RenderUtils.setColor(None.getColor());
            } else {
                RenderUtils.setColor(Color.white);
            }
            GL11.glVertex3d((double)vec3d2.xCoord, (double)((double)Minecraft.player.getEyeHeight() + vec3d2.yCoord), (double)vec3d2.zCoord);
            GL11.glVertex3d((double)d, (double)(d2 + 1.1), (double)d3);
            GL11.glEnd();
        }
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }
}

