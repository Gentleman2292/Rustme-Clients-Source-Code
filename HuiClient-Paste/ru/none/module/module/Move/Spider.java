/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.module.module.Move;

import net.minecraft.client.Minecraft;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.MoveUtils;
import ru.none.utils.TimerUtils;

public class Spider
extends Module {
    TimerUtils timer = new TimerUtils();

    public Spider() {
        super("Spider", Category.Move);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (MoveUtils.isMoving()) {
            if (Minecraft.player.isCollidedHorizontally && this.timer.isDeley(200L)) {
                hook.setOnGround(true);
                Minecraft.player.onGround = true;
                Minecraft.player.isCollidedVertically = true;
                Minecraft.player.isCollidedHorizontally = true;
                Minecraft.player.isAirBorne = true;
                Minecraft.player.jump();
                TimerUtils.reset();
            }
        }
    }
}

