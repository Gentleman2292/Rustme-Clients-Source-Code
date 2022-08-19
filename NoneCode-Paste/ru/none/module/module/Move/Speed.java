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

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        this.setDisplayName(this.getName() + " " + (Object)TextFormatting.GRAY + this.mode.getVal());
        switch (this.mode.getVal()) {
            case "Bow": {
                int b = 0;
                if (this.mc.player.hurtTime == 0 && this.timer.isDeley(2000L)) {
                    for (int i = 0; i < 9; ++i) {
                        if (!(this.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBow)) continue;
                        b = this.mc.player.inventory.currentItem;
                        this.mc.player.inventory.currentItem = i;
                        float gg = (float)(this.mc.player.motionX + this.mc.player.motionZ);
                        if (gg == 0.0f) {
                            hook.setPitch(-90.0f);
                        } else {
                            this.mc.player.jump();
                            hook.setPitch(60.0f);
                        }
                        this.mc.gameSettings.keyBindUseItem.pressed = true;
                        this.timer.reset();
                    }
                }
                if (this.mc.player.hurtTime == 0 && this.timer.isDeley(100L)) {
                    this.mc.gameSettings.keyBindUseItem.pressed = false;
                    this.mc.player.inventory.currentItem = b;
                }
                if (this.mc.player.hurtTime > 0) {
                    MoveUtils.setSpeed(0.8);
                }
            }
            case "StormHVH": 
            case "Matrix": {
                if (this.mc.player.isOnLadder() || this.mc.player.isInWater()) {
                    return;
                }
                if (this.mc.player.fallDistance >= 1.2f) {
                    return;
                }
                double x = this.mc.player.posX;
                double y = this.mc.player.posY;
                double z = this.mc.player.posZ;
                double yaw = (double)this.mc.player.rotationYaw * 0.017453292;
                if (this.mc.player.onGround) {
                    this.ticks = 11.0f;
                } else if (this.ticks < 11.0f) {
                    this.ticks += 1.0f;
                }
                if (this.mc.player.motionY == -0.4448259643949201) {
                    EntityPlayerSP var10000 = this.mc.player;
                    var10000.motionX *= (double)1.8f;
                    var10000 = this.mc.player;
                    var10000.motionZ *= (double)1.8f;
                    Minecraft.getMinecraft().player.setPosition(x - Math.sin(yaw) * 0.003, y, z + Math.cos(yaw) * 0.003);
                }
                this.ticks = 0.0f;
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.player.speedInAir = 0.02f;
        this.mc.player.capabilities.allowFlying = false;
    }
}

