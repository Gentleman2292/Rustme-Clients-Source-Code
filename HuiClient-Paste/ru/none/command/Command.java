/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.command;

import net.minecraft.client.Minecraft;
import ru.none.utils.Utils;

public class Command {
    protected Minecraft mc = Minecraft.getMinecraft();
    String name;
    int params;
    String desc;

    public Command(String name, int params, String desc) {
        this.desc = desc;
        this.params = params;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getParams() {
        return this.params;
    }

    public String getDesc() {
        return this.desc;
    }

    public void use(String ... args) {
    }

    public void syntaxError() {
        Utils.clientMessage("SyntaxError, current use :" + this.desc);
    }
}

