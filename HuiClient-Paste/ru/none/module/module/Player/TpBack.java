/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 */
package ru.none.module.module.Player;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.TimerUtils;

public class TpBack
extends Module {
    private long lastMS;

    public TpBack() {
        super("TpBack", Category.Player);
    }

    @Override
    public void onEnable() {
        int x = Minecraft.player.getPosition().getX();
        int y = Minecraft.player.getPosition().getY();
        int z = Minecraft.player.getPosition().getZ();
        super.onEnable();
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        Minecraft.player.onGround = true;
        Minecraft.player.setSprinting(false);
        Minecraft.player.onGround = false;
        if (!Minecraft.player.isSneaking()) {
            return;
        }
        if (Minecraft.player.ticksExisted % 5 == 0) {
            // empty if block
        }
        if (Minecraft.player.onGround) {
            Minecraft.player.jump();
        }
        if (this.hasReached(30.0)) {
            Minecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Minecraft.player.posX, 200.0, Minecraft.player.posZ, true));
            TimerUtils.reset();
        }
    }

    @Override
    public void onDisable() {
        Minecraft.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Minecraft.player.posX, Minecraft.player.posY + 5.0, Minecraft.player.posZ, true));
        super.onDisable();
    }

    public boolean hasReached(double milliseconds) {
        return (double)(System.currentTimeMillis() - this.lastMS) >= milliseconds;
    }
}

