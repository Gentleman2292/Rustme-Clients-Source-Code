/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module.module.Hud;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import ru.none.hooks.ReceivePacket;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.module.Hud.TotemCounterHelper;
import ru.none.utils.RenderUtils;

public class TotemPopCounter
extends Module {
    public static ArrayList<TotemCounterHelper> pops = new ArrayList();

    public TotemPopCounter() {
        super("TotemPopCounter", Category.Hud, 100, 80);
    }

    @Override
    public void onReceivePacket(ReceivePacket hook) {
        super.onReceivePacket(hook);
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        float x = this.getPosX();
        float y = this.getPosY();
        for (int i = 0; i < pops.size(); ++i) {
            if (i <= 4) continue;
            pops.remove(i);
        }
        pops.sort(new Comparator<TotemCounterHelper>(){

            @Override
            public int compare(TotemCounterHelper o1, TotemCounterHelper o2) {
                if (o1.getTime() < o2.getTime()) {
                    return -1;
                }
                return 1;
            }
        });
        for (TotemCounterHelper totem : pops) {
            RenderUtils.drawShadowRect(x + 50.0f - (float)(this.fr.getStringWidth(totem.getEntity().getName()) / 2), y, x + 50.0f + (float)(this.fr.getStringWidth(totem.getEntity().getName()) / 2), y + 15.0f, 3);
            this.fr.drawCenteredString(totem.getEntity().getName(), x + 50.0f, y + 1.0f, -1);
            this.fr.drawCenteredString("-" + totem.getCount(), x + 50.0f, y + 10.0f, Color.red.getRGB());
            y += 20.0f;
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        pops.clear();
    }
}

