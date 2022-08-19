/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.ItemStack
 */
package ru.none.module.module.Hud;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.RenderUtils;

public class Armor
extends Module {
    public Armor() {
        super("Armor", Category.Hud, 20, 80);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int x = this.getPosX();
        int y = this.getPosY();
        RenderUtils.drawShadowRect(x, y, x + 20, y + 80, 2);
        int i = this.getPosY();
        for (ItemStack item : Minecraft.player.getArmorInventoryList()) {
            RenderUtils.renderItem(item, x + 2, i + 2);
            i += 20;
        }
    }
}

