/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Move;

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
        if (MoveUtils.isMoving() && this.mc.player.isCollidedHorizontally && this.timer.isDeley(200L)) {
            hook.setOnGround(true);
            this.mc.player.onGround = true;
            this.mc.player.isCollidedVertically = true;
            this.mc.player.isCollidedHorizontally = true;
            this.mc.player.isAirBorne = true;
            this.mc.player.jump();
            this.timer.reset();
        }
    }
}

