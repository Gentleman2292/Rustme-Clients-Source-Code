/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.item.ItemBow
 *  net.minecraft.util.text.TextFormatting
 */
package ru.none.module.module.Move;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemBow;
import net.minecraft.util.text.TextFormatting;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.MoveUtils;
import ru.none.utils.TimerUtils;

public class Speed
extends Module {
    private float ticks = 35.0f;
    TimerUtils timer;
    ModeSetting mode;
    FloatSetting speed;
    ArrayList<String> modes = new ArrayList();

    public Speed() {
        super("Speed", Category.Move);
        this.modes.add("Bow");
        this.modes.add("StormHVH");
        this.modes.add("Matrix");
        this.timer = new TimerUtils();
        this.mode = new ModeSetting("Mode", this, this.modes, "Bow");
        None.settingManager.add(this.mode);
        this.speed = new FloatSetting("Speed", this, 0.0f, 10.0f, 1.0f);
        None.settingManager.add(this.speed);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        this.setDisplayName(this.getName() + " " + (Object)TextFormatting.GRAY + this.mode.getVal());
        switch (this.mode.getVal()) {
            case "Bow": {
                int b = 0;
                if (Minecraft.player.hurtTime == 0 && this.timer.isDeley(2000L)) {
                    for (int i = 0; i < 9; ++i) {
                        if (!(Minecraft.player.inventory.getStackInSlot(i).getItem() instanceof ItemBow)) continue;
                        b = Minecraft.player.inventory.currentItem;
                        Minecraft.player.inventory.currentItem = i;
                        float gg = (float)(Minecraft.player.motionX + Minecraft.player.motionZ);
                        if (gg == 0.0f) {
                            hook.setPitch(-90.0f);
                        } else {
                            Minecraft.player.jump();
                            hook.setPitch(60.0f);
                        }
                        Speed.mc.gameSettings.keyBindUseItem.pressed = true;
                        TimerUtils.reset();
                    }
                }
                if (Minecraft.player.hurtTime == 0 && this.timer.isDeley(100L)) {
                    Speed.mc.gameSettings.keyBindUseItem.pressed = false;
                    Minecraft.player.inventory.currentItem = b;
                }
                if (Minecraft.player.hurtTime > 0) {
                    MoveUtils.setSpeed(0.8);
                }
            }
            case "StormHVH": 
            case "Matrix": {
                if (Minecraft.player.isOnLadder()) return;
                if (Minecraft.player.isInWater()) {
                    return;
                }
                if (Minecraft.player.fallDistance >= 1.2f) {
                    return;
                }
                double x = Minecraft.player.posX;
                double y = Minecraft.player.posY;
                double z = Minecraft.player.posZ;
                double yaw = (double)Minecraft.player.rotationYaw * 0.017453292;
                if (Minecraft.player.onGround) {
                    this.ticks = 11.0f;
                } else if (this.ticks < 11.0f) {
                    this.ticks += 1.0f;
                }
                if (Minecraft.player.motionY == -0.4448259643949201) {
                    EntityPlayerSP var10000 = Minecraft.player;
                    var10000.motionX *= (double)1.8f;
                    var10000 = Minecraft.player;
                    var10000.motionZ *= (double)1.8f;
                    Minecraft.getMinecraft();
                    Minecraft.player.setPosition(x - Math.sin(yaw) * 0.003, y, z + Math.cos(yaw) * 0.003);
                }
                this.ticks = 0.0f;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Minecraft.player.speedInAir = 0.02f;
        Minecraft.player.capabilities.allowFlying = false;
    }
}

