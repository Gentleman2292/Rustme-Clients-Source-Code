/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.util.text.TextFormatting
 */
package ru.none.module.module.Move;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextFormatting;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;

public class NoSlowDown
extends Module {
    public static FloatSetting percentage;

    public NoSlowDown() {
        super("NoSlowDown", Category.Move);
        percentage = new FloatSetting("Percentage", this, 0.0f, 100.0f, 1.0f);
        None.settingManager.add(percentage);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        this.setDisplayName(this.getName() + " " + (Object)TextFormatting.GRAY + percentage.getVal());
        if (this.mc.player.isHandActive()) {
            if (this.mc.player.onGround && !this.mc.gameSettings.keyBindJump.isKeyDown()) {
                if (this.mc.player.ticksExisted % 2 == 0) {
                    EntityPlayerSP var10000 = this.mc.player;
                    var10000.motionX *= 0.46;
                    var10000 = this.mc.player;
                    var10000.motionZ *= 0.46;
                }
            } else if ((double)this.mc.player.fallDistance > 0.2) {
                EntityPlayerSP var10000 = this.mc.player;
                var10000.motionX *= (double)0.91f;
                var10000 = this.mc.player;
                var10000.motionZ *= (double)0.91f;
            }
        }
    }
}

