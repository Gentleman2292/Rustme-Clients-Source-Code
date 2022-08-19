/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import ru.none.FriendManager;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.utils.GCDFix;
import ru.none.utils.MathUtils;

public class TargetStrafe
extends Module {
    private double direction = 0.0;
    private double strafe = 1.0;
    private FloatSetting range = new FloatSetting("Range", this, 0.1f, 7.0f, 3.6f);
    private FloatSetting motion = new FloatSetting("Speed", this, 0.01f, 2.0f, 0.2f);
    private BooleanSetting damageboost = new BooleanSetting("Damage Boost", (Module)this, false);
    private FloatSetting damagemotion = new FloatSetting("Damage Motion", this, 0.1f, 2.0f, 0.2f, () -> this.damageboost.getVal());

    public TargetStrafe() {
        super("TargetStrafe", Category.Move);
        None.settingManager.add(this.range);
        None.settingManager.add(this.motion);
        None.settingManager.add(this.damageboost);
        None.settingManager.add(this.damagemotion);
    }

    public void doStrafeAtSpeed(double d) {
        boolean autoSneak = false;
        if (!Minecraft.player.isDead) {
            boolean bl = true;
            Entity entity = this.getTarget();
            if (entity != null) {
                if (Minecraft.player.onGround) {
                    Minecraft.player.jump();
                }
                float[] arrf = this.getNeededRotations(entity.posX, entity.posY, entity.posZ);
                Minecraft.getMinecraft();
                if ((double)Minecraft.player.getDistanceToEntity(entity) <= (double)this.range.getVal()) {
                    Minecraft.player.renderYawOffset = arrf[0];
                    Minecraft.player.rotationYawHead = arrf[0];
                    this.setSpeed(d - 0.05, arrf[0], this.direction, 0.0);
                } else {
                    this.setSpeed(d - 0.05, arrf[0], this.direction, 1.0);
                    Minecraft.player.renderYawOffset = arrf[0];
                    Minecraft.player.rotationYawHead = arrf[0];
                }
                Minecraft.getMinecraft();
                if ((double)Minecraft.player.getDistanceToEntity(entity) <= 7.0) {
                    // empty if block
                }
                KeyBinding.setKeyBindState((int)TargetStrafe.mc.gameSettings.keyBindSneak.getKeyCode(), (boolean)false);
            }
        }
    }

    private void invertStrafe() {
        this.direction = -this.direction;
    }

    public void setSpeed(double d, float f, double d2, double d3) {
        double d4 = d3;
        double d5 = d2;
        float f2 = f;
        if (d4 == 0.0 && d5 == 0.0) {
            Minecraft.player.motionZ = 0.0;
            Minecraft.player.motionX = 0.0;
        } else {
            if (d4 != 0.0) {
                if (d5 > 0.0) {
                    f2 += (float)(d4 > 0.0 ? -45 : 45);
                } else if (d5 < 0.0) {
                    f2 += (float)(d4 > 0.0 ? 45 : -45);
                }
                d5 = 0.0;
                if (d4 > 0.0) {
                    d4 = 1.0;
                } else if (d4 < 0.0) {
                    d4 = -1.0;
                }
            }
            double d6 = Math.cos(Math.toRadians(f2 + 90.0f));
            double d7 = Math.sin(Math.toRadians(f2 + 90.0f));
            Minecraft.player.motionX = d4 * d * d6 + d5 * d * d7;
            Minecraft.player.motionZ = d4 * d * d7 - d5 * d * d6;
        }
    }

    public Entity getTarget() {
        ArrayList<Entity> players = new ArrayList<Entity>(TargetStrafe.mc.world.loadedEntityList);
        players.sort(new Comparator<Entity>(){

            @Override
            public int compare(Entity o1, Entity o2) {
                mc;
                float f = Minecraft.player.getDistanceToEntity(o1);
                mc;
                if (f > Minecraft.player.getDistanceToEntity(o2)) {
                    return 1;
                }
                mc;
                float f2 = Minecraft.player.getDistanceToEntity(o1);
                mc;
                if (f2 < Minecraft.player.getDistanceToEntity(o2)) {
                    return -1;
                }
                return 0;
            }
        });
        for (Entity entity : players) {
            if (!(entity instanceof EntityPlayer) || !(((EntityPlayer)entity).getHealth() > 0.0f)) continue;
            if (Minecraft.player == entity) continue;
            if (!(Minecraft.player.getDistanceToEntity(entity) <= 60.0f) || FriendManager.isFriend(entity.getName())) continue;
            return entity;
        }
        return null;
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        this.setDisplayName(this.getName() + " " + (Object)TextFormatting.GRAY + this.motion.getVal());
        if (TargetStrafe.mc.gameSettings.keyBindLeft.isKeyDown()) {
            this.direction = 1.0;
        } else if (TargetStrafe.mc.gameSettings.keyBindRight.isKeyDown()) {
            this.direction = -1.0;
        }
        Entity target = this.getTarget();
        if (target != null) {
            if (Minecraft.player.isCollidedHorizontally) {
                this.invertStrafe();
            }
            Minecraft.player.movementInput.field_192832_b = 0.0f;
            double d = this.getMovementSpeed();
            float boost = this.damagemotion.getVal();
            if (Minecraft.player.hurtTime > 0) {
                this.doStrafeAtSpeed(d *= (double)boost);
            } else {
                this.doStrafeAtSpeed(d);
            }
        }
    }

    public double getMovementSpeed() {
        boolean damageBoost = this.damageboost.getVal();
        boolean boosted = false;
        double d = this.motion.getVal();
        return d;
    }

    private void switchDirection() {
        this.direction = this.direction == 1.0 ? -1.0 : 1.0;
    }

    public float[] getNeededRotations(double x, double y, double z) {
        double diffX = x - Minecraft.player.posX;
        double diffZ = z - Minecraft.player.posZ;
        double diffY = y + (double)Minecraft.player.getEyeHeight() - (Minecraft.player.posY + (double)Minecraft.player.getEyeHeight()) - 0.4;
        double dist = MathHelper.sqrt((double)(diffX * diffX + diffZ * diffZ));
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / Math.PI - 90.0) + MathUtils.getRandomInRange(-1.5f, 1.5f);
        float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / Math.PI)) + MathUtils.getRandomInRange(-1.0f, 1.0f);
        yaw = Minecraft.player.rotationYaw + GCDFix.getFixedRotation(MathHelper.wrapDegrees((float)(yaw - Minecraft.player.rotationYaw)));
        pitch = Minecraft.player.rotationPitch + GCDFix.getFixedRotation(MathHelper.wrapDegrees((float)(pitch - Minecraft.player.rotationPitch)));
        pitch = MathHelper.clamp((float)pitch, (float)-90.0f, (float)90.0f);
        return new float[]{yaw, pitch};
    }
}

