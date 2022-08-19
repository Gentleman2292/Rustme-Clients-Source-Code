/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.ItemAppleGold
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.text.TextFormatting
 */
package ru.none.module.module.Combat;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.utils.TimerUtils;

public class AutoGapple
extends Module {
    TimerUtils timer = new TimerUtils();
    FloatSetting onheal = new FloatSetting("OnHeal", this, 0.0f, 20.0f, 15.0f);
    BooleanSetting realyworld;
    private boolean isActive;
    public static boolean eat = false;

    public AutoGapple() {
        super("AutoGapple", Category.Combat);
        None.settingManager.add(this.onheal);
        this.realyworld = new BooleanSetting("RealyWorld", (Module)this, true);
        None.settingManager.add(this.realyworld);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        AutoGapple.mc.gameSettings.keyBindUseItem.pressed = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onUpdate(PlayerHook hook) {
        block5: {
            block6: {
                super.onUpdate(hook);
                this.setDisplayName(this.getName() + " " + (Object)TextFormatting.GRAY + this.onheal.getVal());
                if (eat) {
                    eat = false;
                    TimerUtils.reset();
                }
                if (!(Minecraft.player.getHealth() <= this.onheal.getVal())) break block5;
                if (Minecraft.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAppleGold) break block6;
                if (!(Minecraft.player.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAppleGold)) break block5;
            }
            if (this.realyworld.getVal().booleanValue() && this.timer.isDeley(2250L) || !this.realyworld.getVal().booleanValue()) {
                this.isActive = true;
                AutoGapple.mc.gameSettings.keyBindUseItem.pressed = true;
                return;
            }
        }
        if (!this.isActive) return;
        AutoGapple.mc.gameSettings.keyBindUseItem.pressed = false;
        this.isActive = false;
    }
}

