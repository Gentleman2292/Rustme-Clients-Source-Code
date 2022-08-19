/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.network.Packet
 */
package ru.none.hooks;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Packet;

public class ReceivePacket {
    ChannelHandlerContext p_channelRead01;
    Packet<?> p_channelRead02;
    boolean cancelled;

    public ReceivePacket(ChannelHandlerContext p_channelRead01, Packet<?> p_channelRead02) {
        this.p_channelRead01 = p_channelRead01;
        this.p_channelRead02 = p_channelRead02;
        this.cancelled = false;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public ChannelHandlerContext getP_channelRead01() {
        return this.p_channelRead01;
    }

    public Packet<?> getP_channelRead02() {
        return this.p_channelRead02;
    }

    public void setP_channelRead01(ChannelHandlerContext p_channelRead01) {
        this.p_channelRead01 = p_channelRead01;
    }

    public void setP_channelRead02(Packet<?> p_channelRead02) {
        this.p_channelRead02 = p_channelRead02;
    }
}

