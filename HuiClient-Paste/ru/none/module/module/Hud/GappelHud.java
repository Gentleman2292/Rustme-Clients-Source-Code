/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Hud;

import java.awt.Color;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.RenderUtils;
import ru.none.utils.TimerUtils;

public class GappelHud
extends Module {
    public static boolean eat = false;
    public static TimerUtils timer;

    public GappelHud() {
        super("GappelHud", Category.Hud, 50, 50);
        timer = new TimerUtils();
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        if (eat) {
            eat = false;
            TimerUtils.reset();
        }
        if (timer.isDeley(2500L)) {
            RenderUtils.drawCircle228(this.getPosX() + 25, this.getPosY() + 25, 20.0f, Color.green.getRGB(), 360);
        } else {
            RenderUtils.drawCircle228(this.getPosX() + 25, this.getPosY() + 25, 20.0f, None.getColor().getRGB(), (int)(timer.getTime() / 6L));
        }
        GL11.glPushMatrix();
        RenderUtils.customScaledObject2D(this.getPosX(), this.getPosY(), 50.0f, 50.0f, 2.0f);
        RenderUtils.renderItem(new ItemStack(Items.GOLDEN_APPLE), this.getPosX() + 25 - 8, this.getPosY() + 25 - 8);
        GL11.glPopMatrix();
    }
}

