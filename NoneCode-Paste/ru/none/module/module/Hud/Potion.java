/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package ru.none.module.module.Hud;

import net.minecraft.client.resources.I18n;
import net.minecraft.potion.PotionEffect;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.RenderUtils;

public class Potion
extends Module {
    public Potion() {
        super("Potion", Category.Hud, 100, 100);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int posY = this.getPosY();
        for (PotionEffect potionEffect : this.mc.player.getActivePotionEffects()) {
            String name = I18n.format((String)potionEffect.getPotion().getName(), (Object[])new Object[0]);
            String level = "";
            switch (potionEffect.getAmplifier()) {
                case 1: {
                    level = level + " " + I18n.format((String)"enchantment.level.2", (Object[])new Object[0]);
                    break;
                }
                case 2: {
                    level = level + " " + I18n.format((String)"enchantment.level.3", (Object[])new Object[0]);
                    break;
                }
                case 3: {
                    level = level + " " + I18n.format((String)"enchantment.level.4", (Object[])new Object[0]);
                }
            }
            String time = net.minecraft.potion.Potion.getPotionDurationString((PotionEffect)potionEffect, (float)1.0f);
            if (this.fr.getStringWidth(name) > this.fr.getStringWidth(level + " " + time)) {
                RenderUtils.drawShadowRect(this.getPosX(), posY, this.getPosX() + this.fr.getStringWidth(name), posY + 20, 5);
            } else {
                RenderUtils.drawShadowRect(this.getPosX(), posY, this.getPosX() + this.fr.getStringWidth(level + " " + time), posY + 20, 5);
            }
            this.fr.drawStringWithShadow(name, this.getPosX(), posY + 2, -1);
            this.fr.drawStringWithShadow(level + " " + time, this.getPosX(), posY + 18 - this.fr.getHeight(), None.getColor().getRGB());
            posY += 30;
        }
    }
}

