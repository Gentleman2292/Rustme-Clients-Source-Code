/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.item.Item
 */
package ru.none.module.module.Misc;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.utils.TimerUtils;

public class ChestStealer
extends Module {
    public TimerUtils timer = new TimerUtils();
    FloatSetting speed = new FloatSetting("Speed", this, 0.0f, 1000.0f, 100.0f);

    public ChestStealer() {
        super("ChestStealer", Category.Misc);
        None.settingManager.add(this.speed);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (Minecraft.player.openContainer != null) {
            if (Minecraft.player.openContainer instanceof ContainerChest) {
                ContainerChest container = (ContainerChest)Minecraft.player.openContainer;
                for (int i = 0; i < container.inventorySlots.size(); ++i) {
                    if (container.getLowerChestInventory().getStackInSlot(i).getItem() != Item.getItemById((int)0) && this.timer.isDeley((long)this.speed.getVal())) {
                        ChestStealer.mc.playerController.windowClick(container.windowId, i, 0, ClickType.QUICK_MOVE, (EntityPlayer)Minecraft.player);
                        TimerUtils.reset();
                        continue;
                    }
                    if (!this.empty((Container)container)) continue;
                    Minecraft.player.closeScreen();
                }
            }
        }
    }

    public boolean empty(Container container) {
        boolean voll = true;
        int slotAmount = container.inventorySlots.size() == 90 ? 54 : 27;
        int n = slotAmount;
        for (int i = 0; i < slotAmount; ++i) {
            if (!container.getSlot(i).getHasStack()) continue;
            voll = false;
        }
        return voll;
    }
}

