/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 */
package ru.none.module.module.Combat;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.ModeSetting;
import ru.none.utils.notification.Notification;
import ru.none.utils.notification.NotificationManager;

public class AntiBot
extends Module {
    ModeSetting mode;
    ArrayList<String> modes = new ArrayList();

    public AntiBot() {
        super("AntiBot", Category.Combat);
        this.modes.add("Matrix");
        this.modes.add("Dev");
        this.mode = new ModeSetting("Mode", this, this.modes, "Matrix");
        None.settingManager.add(this.mode);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        switch (this.mode.getVal()) {
            case "Matrix": {
                for (Entity \u0435\u043d\u0442\u0438\u0442\u0438 : this.mc.world.loadedEntityList) {
                    if (!(\u0435\u043d\u0442\u0438\u0442\u0438 instanceof EntityPlayer) || \u0435\u043d\u0442\u0438\u0442\u0438.ticksExisted > 5 || ((EntityPlayer)\u0435\u043d\u0442\u0438\u0442\u0438).hurtTime <= 0 || !(this.mc.player.getDistanceToEntity(\u0435\u043d\u0442\u0438\u0442\u0438) <= 25.0f)) continue;
                    this.mc.world.removeEntity(\u0435\u043d\u0442\u0438\u0442\u0438);
                    NotificationManager.add(new Notification("AntiBot", "Bot removed", None.getColor().getRGB()));
                }
            }
            case "Dev": {
                for (Entity entity : this.mc.world.loadedEntityList) {
                    if (!(entity instanceof EntityPlayer) || entity.ticksExisted > 5 || entity == this.mc.player || this.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY - 0.5, entity.posZ)).getBlock() != Blocks.AIR) continue;
                    this.mc.world.removeEntity(entity);
                    NotificationManager.add(new Notification("AntiBot", "Bot removed", None.getColor().getRGB()));
                }
                break;
            }
        }
    }
}

