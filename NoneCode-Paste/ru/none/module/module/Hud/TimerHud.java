/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Hud;

import java.awt.Color;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.utils.MoveUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.TimerUtils;

public class TimerHud
extends Module {
    int level = 100;
    TimerUtils timer = new TimerUtils();
    BooleanSetting autoDisable = new BooleanSetting("AutoDisable", (Module)this, true);

    public TimerHud() {
        super("TimerHud", Category.Hud, 100, 30);
        None.settingManager.add(this.autoDisable);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        boolean b = MoveUtils.isMoving();
        if (this.timer.isDeley(10L) && None.moduleManager.getModule("Timer").isEnable() && b) {
            this.timer.reset();
            this.level = (int)((float)this.level - this.mc.timer.field_194147_b * 4.0f);
            if (this.level <= 0) {
                this.level = 0;
            }
        }
        if (!b && this.timer.isDeley(250L) && None.moduleManager.getModule("Timer").isEnable() || this.timer.isDeley(250L) && !None.moduleManager.getModule("Timer").isEnable()) {
            this.timer.reset();
            this.level = b ? (this.level += 2) : (this.level += 4);
            if (this.level >= 100) {
                this.level = 100;
            }
        }
        if (this.level <= 0 && this.autoDisable.getVal().booleanValue() && None.moduleManager.getModule("Timer").isEnable()) {
            None.moduleManager.getModule("Timer").setEnable(false);
        }
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int x2 = this.getPosX();
        int x = this.getPosX() + 50;
        int y = this.getPosY();
        RenderUtils.drawRect(x - 30, y, x + 30, y + 10, new Color(30, 30, 30, 200).getRGB());
        this.fr.drawCenteredString(this.getName(), x, y + 3, -1);
        RenderUtils.drawGradientRect(x2, y + 10, x2 + this.level, y + 30, true, new Color(30, 30, 30, 200).getRGB(), None.getColor().getRGB());
        RenderUtils.drawGradientRect(x2 + this.level, y + 10, x2 + 100, y + 30, true, None.getColor().getRGB(), new Color(30, 30, 30, 200).getRGB());
        this.fr.drawCenteredString(this.level + "", x, y + 15, -1);
    }
}

