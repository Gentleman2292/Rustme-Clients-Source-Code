/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.input.Mouse
 */
package ru.none.module.module.Combat;

import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.input.Mouse;
import ru.none.FriendManager;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.GCDFix;
import ru.none.utils.MathUtils;
import ru.none.utils.TimerUtils;

public class KillAura
extends Module {
    float predict = -1.0f;
    TimerUtils timer;
    FloatSetting range = new FloatSetting("Range", this, 0.0f, 8.0f, 3.6f);
    BooleanSetting Silent;
    BooleanSetting cooldown;
    FloatSetting minCPS;
    FloatSetting maxCPS;
    BooleanSetting onlycrit;
    BooleanSetting shildBreaker;
    ModeSetting BreakerMode;
    ModeSetting BreakerSwithMode;
    BooleanSetting dobbleTap;
    FloatSetting dobbleTapHealph;
    boolean tap = false;
    BooleanSetting shildBlocker;
    float oldHp = 0.0f;

    public KillAura() {
        super("KillAura", Category.Combat);
        None.settingManager.add(this.range);
        this.Silent = new BooleanSetting("Silent", (Module)this, true);
        None.settingManager.add(this.Silent);
        this.cooldown = new BooleanSetting("Cooldown", (Module)this, true);
        None.settingManager.add(this.cooldown);
        this.minCPS = new FloatSetting("MinCps", this, 0.0f, 20.0f, 8.0f, () -> this.cooldown.getVal() == false);
        None.settingManager.add(this.minCPS);
        this.maxCPS = new FloatSetting("MaxCps", this, 0.0f, 20.0f, 14.0f, () -> this.cooldown.getVal() == false);
        None.settingManager.add(this.maxCPS);
        this.onlycrit = new BooleanSetting("OnlyCrit", this, true, () -> this.cooldown.getVal());
        None.settingManager.add(this.onlycrit);
        this.shildBreaker = new BooleanSetting("ShieldBreaker", this, true, () -> this.cooldown.getVal());
        None.settingManager.add(this.shildBreaker);
        ArrayList<String> modes = new ArrayList<String>();
        modes.add("OnHit");
        modes.add("Always");
        this.BreakerMode = new ModeSetting("BreakerMode", this, modes, "OnHit", () -> this.shildBreaker.getVal());
        None.settingManager.add(this.BreakerMode);
        ArrayList<String> modes2 = new ArrayList<String>();
        modes2.add("Client");
        modes2.add("Silent");
        this.BreakerSwithMode = new ModeSetting("BreakerSwithMode", this, modes2, "Silent", () -> this.shildBreaker.getVal());
        None.settingManager.add(this.BreakerSwithMode);
        this.shildBlocker = new BooleanSetting("ShieldBlocker", this, true, () -> this.cooldown.getVal());
        None.settingManager.add(this.shildBlocker);
        this.dobbleTap = new BooleanSetting("DobbleTap", this, false, () -> this.cooldown.getVal());
        None.settingManager.add(this.dobbleTap);
        this.dobbleTapHealph = new FloatSetting("DobbleTapHealph", this, 0.0f, 20.0f, 5.0f, () -> this.dobbleTap.getVal() != false && this.cooldown.getVal() != false);
        None.settingManager.add(this.dobbleTapHealph);
        this.timer = new TimerUtils();
    }

    public static float[] getRotaion(double x, double y, double z, float yaw_r, float pitch_r) {
        double d = x - Minecraft.getMinecraft().player.posX;
        double d2 = z - Minecraft.getMinecraft().player.posZ;
        double d3 = y + (double)Minecraft.getMinecraft().player.getEyeHeight() - (Minecraft.getMinecraft().player.getEntityBoundingBox().minY + (Minecraft.getMinecraft().player.getEntityBoundingBox().maxY - Minecraft.getMinecraft().player.getEntityBoundingBox().minY));
        double d4 = MathHelper.sqrt((double)(d * d + d2 * d2));
        float f = (float)(MathHelper.atan2((double)d2, (double)d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(MathHelper.atan2((double)d3, (double)d4) * 180.0 / Math.PI));
        return new float[]{f + MathUtils.getRandomInRange(yaw_r, -yaw_r), f2 + MathUtils.getRandomInRange(pitch_r, -pitch_r)};
    }

    public static float[] getNeededRotations2(Entity entityLivingBase) {
        Vec3d eyesPos = new Vec3d(Minecraft.getMinecraft().player.posX + Math.random() / 10.0, Minecraft.getMinecraft().player.posY + (double)Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().player.posZ + Math.random() / 10.0);
        double diffX = entityLivingBase.getPositionVector().xCoord - eyesPos.xCoord;
        double diffY = entityLivingBase.getPositionVector().yCoord - eyesPos.yCoord;
        double diffZ = entityLivingBase.getPositionVector().zCoord - eyesPos.zCoord;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = MathHelper.wrapDegrees((float)((float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f));
        float pitch = MathHelper.wrapDegrees((float)((float)(-Math.toDegrees(Math.atan2(diffY, diffXZ))) - 10.0f));
        float f = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6f + 0.2f;
        float gcd = f * f * f * 1.2f;
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new float[]{yaw, pitch};
    }

    public void attack(Entity target) {
        if (!(this.mc.player.getDistanceToEntity(target) <= this.range.getVal())) {
            return;
        }
        if (this.dobbleTap.getVal().booleanValue()) {
            this.tap = true;
            this.timer.reset();
        }
        this.mc.player.setSprinting(false);
        this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
        this.mc.gameSettings.keyBindSprint.pressed = false;
        if (this.onlycrit.getVal().booleanValue()) {
            this.mc.player.onGround = false;
        }
        System.out.println(this.mc.player.getCooledAttackStrength(2.25f));
        int slot = this.mc.player.inventory.currentItem;
        if (this.BreakerMode.getVal().equalsIgnoreCase("OnHit") && this.shildBreaker.getVal().booleanValue() && target instanceof EntityPlayer && ((EntityPlayer)target).getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem((Item)((EntityPlayer)target).getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
            for (int i = 0; i < 9; ++i) {
                if (!(this.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemAxe)) continue;
                if (this.BreakerSwithMode.getVal().equalsIgnoreCase("Silent")) {
                    this.mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(i));
                    break;
                }
                this.mc.player.inventory.currentItem = i;
                break;
            }
        }
        this.mc.playerController.attackEntity((EntityPlayer)this.mc.player, target);
        this.mc.player.swingArm(EnumHand.MAIN_HAND);
        if (this.BreakerMode.getVal().equalsIgnoreCase("OnHit") && this.shildBreaker.getVal().booleanValue() && target instanceof EntityPlayer && ((EntityPlayer)target).getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem((Item)((EntityPlayer)target).getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
            if (this.BreakerSwithMode.getVal().equalsIgnoreCase("Silent")) {
                this.mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(this.mc.player.inventory.currentItem));
            } else {
                this.mc.player.inventory.currentItem = slot;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.gameSettings.keyBindUseItem.pressed = false;
    }

    @Override
    public void onUpdatePre(PlayerHook playerHook) {
        super.onUpdatePre(playerHook);
        EntityPlayer target = (EntityPlayer)this.getTarget();
        if (target == null) {
            return;
        }
        Block inBlock = this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ)).getBlock();
        Block downBlock = this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY - (double)0.4f, this.mc.player.posZ)).getBlock();
        Block upBlock = this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, this.mc.player.posY + 1.5, this.mc.player.posZ)).getBlock();
        if (this.cooldown.getVal().booleanValue()) {
            if (this.dobbleTap.getVal().booleanValue() && target.getHealth() <= this.dobbleTapHealph.getVal() && this.tap && this.timer.isDeley(MathUtils.getRandomInRange(300, 150))) {
                this.mc.playerController.attackEntity((EntityPlayer)this.mc.player, (Entity)target);
                this.mc.player.swingArm(EnumHand.MAIN_HAND);
                this.tap = false;
            }
            if (this.BreakerMode.getVal().equalsIgnoreCase("Always") && this.timer.isDeley(1000 / MathUtils.getRandomInRange(2, 3))) {
                boolean click = false;
                if (this.shildBreaker.getVal().booleanValue() && target instanceof EntityPlayer && target.getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem((Item)target.getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
                    for (int i = 0; i < 9; ++i) {
                        if (!(this.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemAxe)) continue;
                        this.mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(i));
                        this.mc.player.swingArm(EnumHand.MAIN_HAND);
                        click = true;
                        break;
                    }
                }
                if (click) {
                    this.mc.playerController.attackEntity((EntityPlayer)this.mc.player, (Entity)target);
                }
                if (this.shildBreaker.getVal().booleanValue() && target instanceof EntityPlayer && target.getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem((Item)target.getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
                    this.mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(this.mc.player.inventory.currentItem));
                }
                this.timer.reset();
            }
            if (this.shildBlocker.getVal().booleanValue() && (this.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.SHIELD || this.mc.player.getHeldItem(EnumHand.OFF_HAND).getItem() == Items.SHIELD)) {
                if (this.mc.player.getCooledAttackStrength(0.0f) <= 0.78f) {
                    this.mc.gameSettings.keyBindUseItem.pressed = true;
                } else if (!Mouse.isButtonDown((int)this.mc.gameSettings.keyBindUseItem.getKeyCode())) {
                    this.mc.gameSettings.keyBindUseItem.pressed = false;
                }
            }
            if (this.onlycrit.getVal().booleanValue()) {
                if (inBlock instanceof BlockLiquid && this.mc.player.getCooledAttackStrength(2.25f) >= 1.0f) {
                    this.attack((Entity)target);
                    return;
                }
                if (inBlock == Blocks.WEB) {
                    this.mc.player.setPosition(this.mc.player.posX, this.mc.player.posY + 0.1, this.mc.player.posZ);
                    this.mc.player.motionY = 0.05f;
                }
                if (upBlock != Blocks.AIR && this.mc.player.getCooledAttackStrength(2.25f) >= 1.0f) {
                    this.attack((Entity)target);
                    return;
                }
                if (this.mc.player.fallDistance >= 0.15f && (double)this.mc.player.getCooledAttackStrength(2.25f) >= 0.8) {
                    this.attack((Entity)target);
                    return;
                }
            } else if (this.mc.player.getCooledAttackStrength(0.0f) >= 1.0f) {
                this.attack((Entity)target);
            }
        } else if (this.timer.isDeley((long)(1000.0f / MathUtils.getRandomInRange(this.maxCPS.getVal(), this.minCPS.getVal())))) {
            this.attack((Entity)target);
            this.timer.reset();
        }
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        EntityPlayer target = (EntityPlayer)this.getTarget();
        if (target == null) {
            return;
        }
        ArrayList<Vec3d> poss = new ArrayList<Vec3d>();
        AxisAlignedBB axisalignedbb = this.mc.player.getEntityBoundingBox();
        if (this.mc.world.rayTraceBlocks(new Vec3d(this.mc.player.posX, this.mc.player.posY + (double)this.mc.player.getEyeHeight(), this.mc.player.posZ), new Vec3d(target.posX, target.posY + (double)this.mc.player.getEyeHeight(), target.posZ), false, true, false) != null) {
            for (float y = this.mc.player.getEyeHeight(); y > 0.0f; y -= 0.1f) {
                for (float x = this.mc.player.width / 2.0f; x > -(this.mc.player.width / 2.0f); x -= 0.1f) {
                    for (float z = this.mc.player.width / 2.0f; z > -(this.mc.player.width / 2.0f); z -= 0.1f) {
                        if (this.mc.world.rayTraceBlocks(new Vec3d(this.mc.player.posX, this.mc.player.posY + (double)this.mc.player.getEyeHeight(), this.mc.player.posZ), new Vec3d(target.posX + (double)x, target.posY + (double)y, target.posZ + (double)z), false, true, false) != null) continue;
                        poss.add(new Vec3d(target.posX + (double)x, target.posY + (double)y, target.posZ + (double)z));
                    }
                }
            }
        } else {
            poss.add(new Vec3d(target.posX, target.posY + (double)this.mc.player.getEyeHeight(), target.posZ));
        }
        poss.sort(new Comparator<Vec3d>(){

            @Override
            public int compare(Vec3d o1, Vec3d o2) {
                return (int)(((KillAura)KillAura.this).mc.player.getDistance(o2.xCoord, o2.yCoord, o2.zCoord) - ((KillAura)KillAura.this).mc.player.getDistance(o1.xCoord, o1.yCoord, o1.zCoord));
            }
        });
        if (poss.size() == 0) {
            return;
        }
        double diffX = target.posX - target.lastTickPosX;
        double diffY = target.posY - target.lastTickPosY;
        double diffZ = target.posZ - target.lastTickPosZ;
        float speed = (float)Math.sqrt(diffX * diffX + diffZ * diffZ) * 2.0f;
        float[] rotation = this.getNeededRotations(((Vec3d)poss.get((int)0)).xCoord + diffX * (double)speed, ((Vec3d)poss.get((int)0)).yCoord, ((Vec3d)poss.get((int)0)).zCoord + diffZ * (double)speed, 1.5f, 0.0f);
        if (this.Silent.getVal().booleanValue()) {
            hook.setYaw(rotation[0]);
            hook.setPitch(rotation[1]);
            this.mc.player.renderYawOffset = rotation[0];
            this.mc.player.rotationYawHead = rotation[0];
            this.mc.player.rotationPitchHead = rotation[1];
        } else {
            this.mc.player.rotationYaw = rotation[0];
            this.mc.player.rotationPitch = rotation[1];
        }
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
    }

    public float[] getNeededRotations(double x, double y, double z, float yaw_r, float pitch_r) {
        double d = this.mc.player.posX - this.mc.player.lastTickPosX;
        double d2 = this.mc.player.posY - this.mc.player.lastTickPosY;
        double d3 = this.mc.player.posZ - this.mc.player.lastTickPosZ;
        double diffX = x - this.mc.player.posX;
        double diffZ = z - this.mc.player.posZ;
        double diffY = y - (this.mc.player.posY + d2 * (double)0.3f);
        diffY = y - (this.mc.player.posY + (double)this.mc.player.getEyeHeight());
        double dist = MathHelper.sqrt((double)(diffX * diffX + diffZ * diffZ));
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / Math.PI - 90.0) + MathUtils.getRandomInRange(-yaw_r, yaw_r);
        float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / Math.PI)) + MathUtils.getRandomInRange(-pitch_r, pitch_r);
        yaw = this.mc.player.rotationYaw + GCDFix.getFixedRotation(MathHelper.wrapDegrees((float)(yaw - this.mc.player.rotationYaw)));
        pitch = this.mc.player.rotationPitch + GCDFix.getFixedRotation(MathHelper.wrapDegrees((float)(pitch - this.mc.player.rotationPitch)));
        pitch = MathHelper.clamp((float)pitch, (float)-90.0f, (float)90.0f);
        return new float[]{yaw, pitch};
    }

    public Entity getTarget() {
        ArrayList<Entity> players = new ArrayList<Entity>(this.mc.world.loadedEntityList);
        players.sort(new Comparator<Entity>(){

            @Override
            public int compare(Entity o1, Entity o2) {
                if (((KillAura)KillAura.this).mc.player.getDistanceToEntity(o1) > ((KillAura)KillAura.this).mc.player.getDistanceToEntity(o2)) {
                    return 1;
                }
                if (((KillAura)KillAura.this).mc.player.getDistanceToEntity(o1) < ((KillAura)KillAura.this).mc.player.getDistanceToEntity(o2)) {
                    return -1;
                }
                return 0;
            }
        });
        for (Entity entity : players) {
            if (!(entity instanceof EntityPlayer) || !(((EntityPlayer)entity).getHealth() > 0.0f) || this.mc.player == entity || !(this.mc.player.getDistanceToEntity(entity) <= this.range.getVal() + 1.5f) || FriendManager.isFriend(entity.getName())) continue;
            return entity;
        }
        return null;
    }
}

