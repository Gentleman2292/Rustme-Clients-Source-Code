/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 */
package ru.none.hooks;

import net.minecraft.network.Packet;

public class SendPacket {
    Packet packet;
    boolean cancelled = false;

    public SendPacket(Packet packetIn) {
        this.packet = packetIn;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

