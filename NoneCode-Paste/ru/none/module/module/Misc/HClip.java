/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none.module.module.Misc;

import net.minecraft.client.Minecraft;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.FloatSetting;

public class HClip
extends Module {
    FloatSetting val = new FloatSetting("Blocks", this, 0.0f, 50.0f, 1.0f);

    public HClip() {
        super("HClip", Category.Misc);
        None.settingManager.add(this.val);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.mc.player.setPosition(this.mc.player.posX + Math.sin(Math.toRadians(Minecraft.getMinecraft().player.rotationYaw)) * (double)this.val.getVal(), this.mc.player.posY, this.mc.player.posZ + Math.cos(Math.toRadians(Minecraft.getMinecraft().player.rotationYaw)) * (double)this.val.getVal());
        this.setEnable(false);
    }
}

