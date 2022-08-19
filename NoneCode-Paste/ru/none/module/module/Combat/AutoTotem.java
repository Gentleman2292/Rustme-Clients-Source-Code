/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.util.text.TextFormatting
 */
package ru.none.module.module.Combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.text.TextFormatting;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.TimerUtils;

public class AutoTotem
extends Module {
    FloatSetting health = new FloatSetting("Health", this, 0.0f, 20.0f, 15.0f);
    TimerUtils timer;

    public AutoTotem() {
        super("AutoTotem", Category.Combat);
        None.settingManager.add(this.health);
        this.timer = new TimerUtils();
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        this.setDisplayName(this.getName() + " " + (Object)TextFormatting.GRAY + this.health.getVal());
        if (this.mc.player.getHealth() <= this.health.getVal() && Item.getIdFromItem((Item)this.mc.player.inventoryContainer.getSlot(45).getStack().getItem()) != 449) {
            this.mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.OPEN_INVENTORY));
            for (Slot slot : this.mc.player.inventoryContainer.inventorySlots) {
                if (Item.getIdFromItem((Item)slot.getStack().getItem()) != 449) continue;
                this.mc.playerController.windowClick(this.mc.player.inventoryContainer.windowId, slot.slotNumber, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
                if (!this.timer.isDeley(MathUtils.getRandomInRange(500, 166))) continue;
                this.mc.playerController.windowClick(this.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)this.mc.player);
                this.timer.reset();
            }
        }
    }
}

