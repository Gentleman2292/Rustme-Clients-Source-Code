/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemEnderPearl
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.util.EnumHand
 *  org.lwjgl.input.Mouse
 */
package ru.none.module.module.Misc;

import net.minecraft.item.ItemEnderPearl;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Mouse;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class MiddleClickPearl
extends Module {
    public MiddleClickPearl() {
        super("MiddleClickPearl", Category.Misc);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (Mouse.isButtonDown((int)2)) {
            for (int i = 0; i < 9; ++i) {
                if (!(this.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemEnderPearl)) continue;
                this.mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(i));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                this.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.mc.player.inventory.currentItem));
                return;
            }
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}

