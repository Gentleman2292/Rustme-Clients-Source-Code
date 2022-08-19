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
        Minecraft.getMinecraft();
        double d = x - Minecraft.player.posX;
        Minecraft.getMinecraft();
        double d2 = z - Minecraft.player.posZ;
        Minecraft.getMinecraft();
        double d3 = y + (double)Minecraft.player.getEyeHeight();
        Minecraft.getMinecraft();
        Minecraft.getMinecraft();
        Minecraft.getMinecraft();
        double d32 = d3 - (Minecraft.player.getEntityBoundingBox().minY + (Minecraft.player.getEntityBoundingBox().maxY - Minecraft.player.getEntityBoundingBox().minY));
        double d4 = MathHelper.sqrt((double)(d * d + d2 * d2));
        float f = (float)(MathHelper.atan2((double)d2, (double)d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(MathHelper.atan2((double)d32, (double)d4) * 180.0 / Math.PI));
        return new float[]{f + MathUtils.getRandomInRange(yaw_r, -yaw_r), f2 + MathUtils.getRandomInRange(pitch_r, -pitch_r)};
    }

    public static float[] getNeededRotations2(Entity entityLivingBase) {
        Minecraft.getMinecraft();
        double d = Minecraft.player.posX + Math.random() / 10.0;
        Minecraft.getMinecraft();
        Minecraft.getMinecraft();
        double d2 = Minecraft.player.posY + (double)Minecraft.player.getEyeHeight();
        Minecraft.getMinecraft();
        Vec3d eyesPos = new Vec3d(d, d2, Minecraft.player.posZ + Math.random() / 10.0);
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
        if (!(Minecraft.player.getDistanceToEntity(target) <= this.range.getVal())) {
            return;
        }
        if (this.dobbleTap.getVal().booleanValue()) {
            this.tap = true;
            TimerUtils.reset();
        }
        Minecraft.player.setSprinting(false);
        Minecraft.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Minecraft.player, CPacketEntityAction.Action.STOP_SPRINTING));
        KillAura.mc.gameSettings.keyBindSprint.pressed = false;
        if (this.onlycrit.getVal().booleanValue()) {
            Minecraft.player.onGround = false;
        }
        System.out.println(Minecraft.player.getCooledAttackStrength(2.25f));
        int slot = Minecraft.player.inventory.currentItem;
        if (this.BreakerMode.getVal().equalsIgnoreCase("OnHit") && this.shildBreaker.getVal().booleanValue() && target instanceof EntityPlayer && ((EntityPlayer)target).getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem((Item)((EntityPlayer)target).getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
            for (int i = 0; i < 9; ++i) {
                if (!(Minecraft.player.inventory.getStackInSlot(i).getItem() instanceof ItemAxe)) continue;
                if (this.BreakerSwithMode.getVal().equalsIgnoreCase("Silent")) {
                    mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(i));
                    break;
                }
                Minecraft.player.inventory.currentItem = i;
                break;
            }
        }
        KillAura.mc.playerController.attackEntity((EntityPlayer)Minecraft.player, target);
        Minecraft.player.swingArm(EnumHand.MAIN_HAND);
        if (this.BreakerMode.getVal().equalsIgnoreCase("OnHit") && this.shildBreaker.getVal().booleanValue() && target instanceof EntityPlayer && ((EntityPlayer)target).getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem((Item)((EntityPlayer)target).getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
            if (this.BreakerSwithMode.getVal().equalsIgnoreCase("Silent")) {
                mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(Minecraft.player.inventory.currentItem));
            } else {
                Minecraft.player.inventory.currentItem = slot;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        KillAura.mc.gameSettings.keyBindUseItem.pressed = false;
    }

    @Override
    public void onUpdatePre(PlayerHook playerHook) {
        block28: {
            EntityPlayer target;
            block25: {
                Block upBlock;
                Block inBlock;
                block26: {
                    block27: {
                        super.onUpdatePre(playerHook);
                        target = (EntityPlayer)this.getTarget();
                        if (target == null) {
                            return;
                        }
                        inBlock = KillAura.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY, Minecraft.player.posZ)).getBlock();
                        Block downBlock = KillAura.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY - (double)0.4f, Minecraft.player.posZ)).getBlock();
                        upBlock = KillAura.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, Minecraft.player.posY + 1.5, Minecraft.player.posZ)).getBlock();
                        if (!this.cooldown.getVal().booleanValue()) break block25;
                        if (this.dobbleTap.getVal().booleanValue() && target.getHealth() <= this.dobbleTapHealph.getVal() && this.tap && this.timer.isDeley(MathUtils.getRandomInRange(300, 150))) {
                            KillAura.mc.playerController.attackEntity((EntityPlayer)Minecraft.player, (Entity)target);
                            Minecraft.player.swingArm(EnumHand.MAIN_HAND);
                            this.tap = false;
                        }
                        if (this.BreakerMode.getVal().equalsIgnoreCase("Always") && this.timer.isDeley(1000 / MathUtils.getRandomInRange(2, 3))) {
                            boolean click = false;
                            if (this.shildBreaker.getVal().booleanValue() && target instanceof EntityPlayer && target.getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem((Item)target.getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
                                for (int i = 0; i < 9; ++i) {
                                    if (!(Minecraft.player.inventory.getStackInSlot(i).getItem() instanceof ItemAxe)) continue;
                                    mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(i));
                                    Minecraft.player.swingArm(EnumHand.MAIN_HAND);
                                    click = true;
                                    break;
                                }
                            }
                            if (click) {
                                KillAura.mc.playerController.attackEntity((EntityPlayer)Minecraft.player, (Entity)target);
                            }
                            if (this.shildBreaker.getVal().booleanValue() && target instanceof EntityPlayer && target.getActiveHand() == EnumHand.OFF_HAND && Item.getIdFromItem((Item)target.getHeldItem(EnumHand.OFF_HAND).getItem()) == 442) {
                                mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(Minecraft.player.inventory.currentItem));
                            }
                            TimerUtils.reset();
                        }
                        if (!this.shildBlocker.getVal().booleanValue()) break block26;
                        if (Minecraft.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.SHIELD) break block27;
                        if (Minecraft.player.getHeldItem(EnumHand.OFF_HAND).getItem() != Items.SHIELD) break block26;
                    }
                    if (Minecraft.player.getCooledAttackStrength(0.0f) <= 0.78f) {
                        KillAura.mc.gameSettings.keyBindUseItem.pressed = true;
                    } else if (!Mouse.isButtonDown((int)KillAura.mc.gameSettings.keyBindUseItem.getKeyCode())) {
                        KillAura.mc.gameSettings.keyBindUseItem.pressed = false;
                    }
                }
                if (this.onlycrit.getVal().booleanValue()) {
                    if (inBlock instanceof BlockLiquid) {
                        if (Minecraft.player.getCooledAttackStrength(2.25f) >= 1.0f) {
                            this.attack((Entity)target);
                            return;
                        }
                    }
                    if (inBlock == Blocks.WEB) {
                        Minecraft.player.setPosition(Minecraft.player.posX, Minecraft.player.posY + 0.1, Minecraft.player.posZ);
                        Minecraft.player.motionY = 0.05f;
                    }
                    if (upBlock != Blocks.AIR) {
                        if (Minecraft.player.getCooledAttackStrength(2.25f) >= 1.0f) {
                            this.attack((Entity)target);
                            return;
                        }
                    }
                    if (Minecraft.player.fallDistance >= 0.15f) {
                        if ((double)Minecraft.player.getCooledAttackStrength(2.25f) >= 0.8) {
                            this.attack((Entity)target);
                            return;
                        }
                    }
                } else if (Minecraft.player.getCooledAttackStrength(0.0f) >= 1.0f) {
                    this.attack((Entity)target);
                }
                break block28;
            }
            if (this.timer.isDeley((long)(1000.0f / MathUtils.getRandomInRange(this.maxCPS.getVal(), this.minCPS.getVal())))) {
                this.attack((Entity)target);
                TimerUtils.reset();
            }
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
        AxisAlignedBB axisalignedbb = Minecraft.player.getEntityBoundingBox();
        if (KillAura.mc.world.rayTraceBlocks(new Vec3d(Minecraft.player.posX, Minecraft.player.posY + (double)Minecraft.player.getEyeHeight(), Minecraft.player.posZ), new Vec3d(target.posX, target.posY + (double)Minecraft.player.getEyeHeight(), target.posZ), false, true, false) != null) {
            block0: for (float y = Minecraft.player.getEyeHeight(); y > 0.0f; y -= 0.1f) {
                float x = Minecraft.player.width / 2.0f;
                while (true) {
                    if (!(x > -(Minecraft.player.width / 2.0f))) continue block0;
                    float z = Minecraft.player.width / 2.0f;
                    while (true) {
                        if (!(z > -(Minecraft.player.width / 2.0f))) break;
                        if (KillAura.mc.world.rayTraceBlocks(new Vec3d(Minecraft.player.posX, Minecraft.player.posY + (double)Minecraft.player.getEyeHeight(), Minecraft.player.posZ), new Vec3d(target.posX + (double)x, target.posY + (double)y, target.posZ + (double)z), false, true, false) == null) {
                            poss.add(new Vec3d(target.posX + (double)x, target.posY + (double)y, target.posZ + (double)z));
                        }
                        z -= 0.1f;
                    }
                    x -= 0.1f;
                }
            }
        } else {
            poss.add(new Vec3d(target.posX, target.posY + (double)Minecraft.player.getEyeHeight(), target.posZ));
        }
        poss.sort(new Comparator<Vec3d>(){

            @Override
            public int compare(Vec3d o1, Vec3d o2) {
                mc;
                double d = Minecraft.player.getDistance(o2.xCoord, o2.yCoord, o2.zCoord);
                mc;
                return (int)(d - Minecraft.player.getDistance(o1.xCoord, o1.yCoord, o1.zCoord));
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
            Minecraft.player.renderYawOffset = rotation[0];
            Minecraft.player.rotationYawHead = rotation[0];
            Minecraft.player.rotationPitchHead = rotation[1];
        } else {
            Minecraft.player.rotationYaw = rotation[0];
            Minecraft.player.rotationPitch = rotation[1];
        }
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
    }

    public float[] getNeededRotations(double x, double y, double z, float yaw_r, float pitch_r) {
        double d = Minecraft.player.posX - Minecraft.player.lastTickPosX;
        double d2 = Minecraft.player.posY - Minecraft.player.lastTickPosY;
        double d3 = Minecraft.player.posZ - Minecraft.player.lastTickPosZ;
        double diffX = x - Minecraft.player.posX;
        double diffZ = z - Minecraft.player.posZ;
        double diffY = y - (Minecraft.player.posY + d2 * (double)0.3f);
        diffY = y - (Minecraft.player.posY + (double)Minecraft.player.getEyeHeight());
        double dist = MathHelper.sqrt((double)(diffX * diffX + diffZ * diffZ));
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / Math.PI - 90.0) + MathUtils.getRandomInRange(-yaw_r, yaw_r);
        float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / Math.PI)) + MathUtils.getRandomInRange(-pitch_r, pitch_r);
        yaw = Minecraft.player.rotationYaw + GCDFix.getFixedRotation(MathHelper.wrapDegrees((float)(yaw - Minecraft.player.rotationYaw)));
        pitch = Minecraft.player.rotationPitch + GCDFix.getFixedRotation(MathHelper.wrapDegrees((float)(pitch - Minecraft.player.rotationPitch)));
        pitch = MathHelper.clamp((float)pitch, (float)-90.0f, (float)90.0f);
        return new float[]{yaw, pitch};
    }

    public Entity getTarget() {
        ArrayList<Entity> players = new ArrayList<Entity>(KillAura.mc.world.loadedEntityList);
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
            if (!(Minecraft.player.getDistanceToEntity(entity) <= this.range.getVal() + 1.5f) || FriendManager.isFriend(entity.getName())) continue;
            return entity;
        }
        return null;
    }
}

