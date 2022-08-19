/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Hud;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import ru.none.FriendManager;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class TargetHud
extends Module {
    Entity targetez;
    float anim = 0.0f;
    float healph = 0.0f;
    ArrayList<particle> particles = new ArrayList();

    public TargetHud() {
        super("TargetHud", Category.Hud, 133, 35);
    }

    public Entity getTarget() {
        ArrayList<Entity> players = new ArrayList<Entity>(this.mc.world.loadedEntityList);
        players.sort(new Comparator<Entity>(){

            @Override
            public int compare(Entity o1, Entity o2) {
                if (((TargetHud)TargetHud.this).mc.player.getDistanceToEntity(o1) > ((TargetHud)TargetHud.this).mc.player.getDistanceToEntity(o2)) {
                    return 1;
                }
                if (((TargetHud)TargetHud.this).mc.player.getDistanceToEntity(o1) < ((TargetHud)TargetHud.this).mc.player.getDistanceToEntity(o2)) {
                    return -1;
                }
                return 0;
            }
        });
        for (Entity entity : players) {
            if (!(entity instanceof EntityPlayer) || !(((EntityPlayer)entity).getHealth() > 0.0f) || this.mc.player == entity || !(this.mc.player.getDistanceToEntity(entity) <= 6.0f) || FriendManager.isFriend(entity.getName())) continue;
            return entity;
        }
        return null;
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        EntityPlayer target = (EntityPlayer)this.getTarget();
        if (target == null) {
            if (this.targetez == null) {
                return;
            }
            this.particles.clear();
            target = (EntityPlayer)this.targetez;
            this.anim = MathUtils.lerp(this.anim, 0.0f, 0.1f);
            if (this.anim <= 0.8f) {
                return;
            }
            GL11.glPushMatrix();
            int x = this.getPosX();
            int y = this.getPosY();
            RenderUtils.customScaledObject2D(x, y, 115.0f, 35.0f, this.anim);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderUtils.drawRect(x, y, x + 113, y + 35, new Color(30, 30, 30).getRGB());
            try {
                this.mc.getTextureManager().bindTexture(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(target.getUniqueID()).getLocationSkin());
                Gui.drawScaledCustomSizeModalRect((int)(x + 3), (int)(y + 3), (float)8.0f, (float)8.0f, (int)8, (int)8, (int)20, (int)20, (float)64.0f, (float)64.0f);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            RenderUtils.drawShadowRect(x, y, x + 113, y + 35, 3);
            RenderUtils.drawRect(x + 115, y, x + 133, y + 35, new Color(30, 30, 30).getRGB());
            RenderUtils.drawShadowRect(x + 115, y, x + 133, y + 35, 3);
            this.healph = MathUtils.lerp(this.healph, 0.0f, 0.1f);
            float h = 107.0f / target.getMaxHealth();
            if (target.hurtTime > 0) {
                RenderUtils.drawRect(x + 3, y + 27, (float)(x - 5) + h * this.healph, y + 29, None.getColor().getRGB());
                RenderUtils.drawCircle((float)x + h * this.healph, y + 28, 3.0f, None.getColor().getRGB());
            } else {
                RenderUtils.drawRect(x + 3, y + 27, (float)(x - 3) + h * this.healph, y + 29, None.getColor().getRGB());
                RenderUtils.drawCircle((float)x + h * this.healph, y + 28, 2.0f, None.getColor().getRGB());
            }
            this.fr.drawString(target.getName(), x + 25, y + 3, -1);
            this.fr.drawString(MathUtils.round(target.getHealth(), 1) + " / " + target.getMaxHealth(), x + 25, y + 6 + this.fr.getHeight(), -1);
            RenderUtils.renderItem(target.getHeldItem(EnumHand.MAIN_HAND), x + 116, y);
            RenderUtils.renderItem(target.getHeldItem(EnumHand.OFF_HAND), x + 116, y + 16);
            GL11.glPopMatrix();
        } else {
            this.targetez = target;
            GL11.glPushMatrix();
            this.anim = MathUtils.lerp(this.anim, 1.0f, 0.1f);
            int x = this.getPosX();
            int y = this.getPosY();
            if (target.hurtTime > 0) {
                this.particles.add(new particle(x + 14, y + 14));
            }
            RenderUtils.customScaledObject2D(x, y, 115.0f, 35.0f, this.anim);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderUtils.drawRect(x, y, x + 113, y + 35, new Color(30, 30, 30).getRGB());
            try {
                this.mc.getTextureManager().bindTexture(Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(target.getUniqueID()).getLocationSkin());
                Gui.drawScaledCustomSizeModalRect((int)(x + 3), (int)(y + 3), (float)8.0f, (float)8.0f, (int)8, (int)8, (int)20, (int)20, (float)64.0f, (float)64.0f);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            for (particle particle2 : this.particles) {
                particle2.update();
            }
            for (int i = 0; i < this.particles.size(); ++i) {
                float f2;
                float f = (float)(x + 14) - this.particles.get(i).getX();
                if (!(MathHelper.sqrt((float)(f * f + (f2 = (float)(y + 14) - this.particles.get(i).getY()) * f2)) > 25.0f)) continue;
                this.particles.remove(i);
            }
            RenderUtils.drawShadowRect(x, y, x + 113, y + 35, 3);
            RenderUtils.drawRect(x + 115, y, x + 133, y + 35, new Color(30, 30, 30).getRGB());
            RenderUtils.drawShadowRect(x + 115, y, x + 133, y + 35, 3);
            this.healph = MathUtils.lerp(this.healph, target.getHealth(), 0.1f);
            float h = 107.0f / target.getMaxHealth();
            if (target.hurtTime > 0) {
                RenderUtils.drawRect(x + 3, y + 27, (float)(x - 5) + h * this.healph, y + 29, None.getColor().getRGB());
                RenderUtils.drawCircle((float)x + h * this.healph, y + 28, 3.0f, None.getColor().getRGB());
            } else {
                RenderUtils.drawRect(x + 3, y + 27, (float)(x - 3) + h * this.healph, y + 29, None.getColor().getRGB());
                RenderUtils.drawCircle((float)x + h * this.healph, y + 28, 2.0f, None.getColor().getRGB());
            }
            GL11.glEnable((int)3089);
            RenderUtils.scissorRect(x + 25, y + 2, x + 112, y + 15);
            this.fr.drawString(target.getName(), x + 25, y + 3, -1);
            GL11.glDisable((int)3089);
            this.fr.drawString(MathUtils.round(target.getHealth(), 1) + " / " + target.getMaxHealth(), x + 25, y + 6 + this.fr.getHeight(), -1);
            RenderUtils.renderItem(target.getHeldItem(EnumHand.MAIN_HAND), x + 116, y);
            RenderUtils.renderItem(target.getHeldItem(EnumHand.OFF_HAND), x + 116, y + 16);
            GL11.glPopMatrix();
        }
    }

    static class particle {
        float x;
        float y;
        float motionX;
        float motionY;

        public particle(float x, float y) {
            this.x = x;
            this.y = y;
            this.motionX = MathUtils.getRandomInRange(0.5f, -0.5f);
            this.motionY = MathUtils.getRandomInRange(0.5f, -0.5f);
        }

        public float getX() {
            return this.x;
        }

        public void update() {
            this.x += this.motionX;
            this.y += this.motionY;
            this.motionX = MathUtils.lerp(this.motionX, 0.0f, 0.001f);
            this.motionY = MathUtils.lerp(this.motionY, 0.0f, 0.001f);
            float f = this.x + 14.0f - this.getX();
            float f2 = this.y + 14.0f - this.getY();
            int color = new Color(None.getColor().getRed(), None.getColor().getGreen(), None.getColor().getBlue(), Math.round(200.0f - MathHelper.sqrt((float)(f * f + f2 * f2)))).getRGB();
            RenderUtils.drawCircle(this.x, this.y, 1.0f, color);
        }

        public void setY(float y) {
            this.y = y;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return this.y;
        }
    }
}

