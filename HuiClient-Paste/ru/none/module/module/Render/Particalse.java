/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Render;

import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.ColorUtils;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class Particalse
extends Module {
    ArrayList<partical> particals = new ArrayList();

    public Particalse() {
        super("Particalse", Category.Render);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        for (Entity entity : Particalse.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityPlayer)) continue;
            EntityPlayer player = (EntityPlayer)entity;
            if (player.hurtTime <= 0) continue;
            for (int i = 0; i < 2; ++i) {
                this.particals.add(new partical(player.posX, MathUtils.getRandomInRange(player.posY + (double)player.height, player.posY), player.posZ));
            }
        }
        for (int i = 0; i < this.particals.size(); ++i) {
            if (System.currentTimeMillis() - this.particals.get(i).getTime() < 10000L) continue;
            this.particals.remove(i);
        }
        for (partical partical2 : this.particals) {
            partical2.update();
        }
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        for (partical partical2 : this.particals) {
            partical2.render();
        }
    }

    public static class partical {
        double x;
        double y;
        double z;
        float motionX;
        float motionY;
        float motionZ;
        long time;

        public partical(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.motionX = MathUtils.getRandomInRange(-0.1f, 0.1f);
            this.motionY = -0.01f;
            this.motionZ = MathUtils.getRandomInRange(-0.1f, 0.1f);
            this.time = System.currentTimeMillis();
        }

        public long getTime() {
            return this.time;
        }

        public void update() {
            this.x += (double)this.motionX;
            if (Minecraft.getMinecraft().world.getBlockState(new BlockPos(this.x, this.y - (double)0.01f, this.z)).getBlock() != Blocks.AIR) {
                if (Minecraft.getMinecraft().world.getBlockState(new BlockPos(this.x, this.y + (double)0.1f, this.z)).getBlock() != Blocks.AIR) {
                    this.motionX = -this.motionX;
                    this.motionZ = -this.motionZ;
                }
            } else {
                this.y += (double)this.motionY;
                if (Minecraft.getMinecraft().world.getBlockState(new BlockPos(this.x, this.y, this.z)).getBlock() != Blocks.AIR) {
                    this.motionX = -this.motionX;
                    this.motionZ = -this.motionZ;
                }
            }
            this.z += (double)this.motionZ;
            if (System.currentTimeMillis() - this.time >= 8500L) {
                this.motionZ = MathUtils.lerp(this.motionZ, 0.0f, 0.001f);
                this.motionY = MathUtils.lerp(this.motionY, 0.0f, 0.001f);
                this.motionX = MathUtils.lerp(this.motionX, 0.0f, 0.001f);
            }
        }

        public void render() {
            GL11.glPushMatrix();
            RenderUtils.enableGL2D();
            GL11.glDisable((int)3553);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            double posX = RenderUtils.getRenderPos((double)this.x, (double)this.y, (double)this.z).xCoord;
            double posY = RenderUtils.getRenderPos((double)this.x, (double)this.y, (double)this.z).yCoord;
            double posZ = RenderUtils.getRenderPos((double)this.x, (double)this.y, (double)this.z).zCoord;
            double tau = Math.PI * 2;
            double fans = 45.0;
            int color = ColorUtils.TwoColoreffect(255, 48, 0, 246, 186, 55, (double)Math.abs(System.currentTimeMillis() / 4L) / 100.1275 * 0.5);
            float alpha = 130.0f;
            GL11.glPointSize((float)7.0f);
            GL11.glEnable((int)2832);
            GL11.glBegin((int)0);
            RenderUtils.setupColor(new Color(color), (int)alpha);
            GL11.glVertex3d((double)posX, (double)posY, (double)posZ);
            GL11.glEnd();
            GL11.glPointSize((float)18.0f);
            GL11.glEnable((int)2832);
            GL11.glBegin((int)0);
            RenderUtils.setupColor(new Color(color), (int)(alpha / 4.0f));
            GL11.glVertex3d((double)posX, (double)posY, (double)posZ);
            GL11.glEnd();
            GL11.glDisable((int)3042);
            GL11.glDisable((int)2848);
            GL11.glEnable((int)3553);
            RenderUtils.disableGL2D();
            GL11.glEnable((int)2848);
            GL11.glDisable((int)2929);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glDepthMask((boolean)false);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)3042);
            GL11.glShadeModel((int)7425);
            Minecraft.getMinecraft().entityRenderer.disableLightmap();
            GlStateManager.disableDepth();
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3008);
            GL11.glBegin((int)8);
            GL11.glDisable((int)2884);
            RenderUtils.setupColor(new Color(color), (int)alpha);
            GL11.glVertex3d((double)posX, (double)(posY + (double)this.motionY + 0.01), (double)posZ);
            GL11.glVertex3d((double)posX, (double)(posY - 0.01), (double)posZ);
            RenderUtils.setupColor(new Color(color), 0);
            GL11.glVertex3d((double)(posX - (double)(this.motionX * 20.0f)), (double)(posY + (double)(this.motionY * 2.0f)), (double)(posZ - (double)(this.motionZ * 20.0f)));
            GL11.glVertex3d((double)(posX - (double)(this.motionX * 20.0f)), (double)(posY - (double)(this.motionY * 18.0f)), (double)(posZ - (double)(this.motionZ * 20.0f)));
            GL11.glEnable((int)2884);
            GL11.glEnd();
            GL11.glEnable((int)2884);
            GL11.glEnable((int)3008);
            GL11.glShadeModel((int)7424);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2929);
            GL11.glDisable((int)2848);
            GlStateManager.resetColor();
            GlStateManager.popMatrix();
        }
    }
}

