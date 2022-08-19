/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.macros;

import net.minecraft.client.Minecraft;

public class Macros {
    String name;
    String command;
    int key;

    public Macros(String name, String command, int key2) {
        this.command = command;
        this.key = key2;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getKey() {
        return this.key;
    }

    public String getCommand() {
        return this.command;
    }

    public void use() {
        Minecraft.getMinecraft();
        Minecraft.player.sendChatMessage(this.command);
    }
}

