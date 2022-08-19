/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.module.module.Misc;

import net.minecraft.client.Minecraft;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class Nofall
extends Module {
    public Nofall() {
        super("Nofall", Category.Misc);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (Minecraft.player.fallDistance > 10.0f) {
            if (Minecraft.player.isSneaking()) {
                Minecraft.player.setPosition(Minecraft.player.lastTickPosX, Minecraft.player.lastTickPosY, Minecraft.player.lastTickPosZ);
            }
        }
    }
}

