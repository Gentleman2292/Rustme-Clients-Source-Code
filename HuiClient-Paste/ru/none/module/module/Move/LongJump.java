/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.module.module.Move;

import net.minecraft.client.Minecraft;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;

public class LongJump
extends Module {
    private final FloatSetting boost = new FloatSetting("Boost", this, 0.03f, 1.0f, 0.07f);
    private int jumps = 0;

    public LongJump() {
        super("LongJump", Category.Move);
        None.settingManager.add(this.boost);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.jumps = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (Minecraft.player.hurtTime > 0) {
            if (Minecraft.player.onGround) {
                Minecraft.player.jump();
            }
        }
        if (!Minecraft.player.onGround) {
            if (Minecraft.player.hurtTime > 0) {
                LongJump.mc.timer.field_194148_c = 5.0f;
                return;
            }
        }
        Minecraft.player.speedInAir = 0.02f;
    }
}

