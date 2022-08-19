/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Render;

import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.RenderUtils;
import ru.none.utils.TimerUtils;

public class Trails
extends Module {
    ArrayList<trail> trails = new ArrayList();

    public Trails() {
        super("Trails", Category.Render);
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)3553);
        RenderUtils.enableSmoothLine(2.5f);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)2.5f);
        RenderUtils.setColor(None.getColor());
        GL11.glBegin((int)7);
        for (int i = 0; i < this.trails.size(); ++i) {
            trail bc = this.trails.get(i);
            if (bc.getTimer().isDeley(1000L)) {
                this.trails.remove(bc);
            }
            RenderUtils.putVertex3d(RenderUtils.getRenderPos(bc.getX(), bc.getY(), bc.getZ()));
            RenderUtils.putVertex3d(RenderUtils.getRenderPos(bc.getX(), bc.getY() + (double)this.mc.player.height, bc.getZ()));
        }
        GL11.glEnd();
        GL11.glDisable((int)3042);
        RenderUtils.disableSmoothLine();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2848);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        this.trails.add(new trail(this.mc.player.lastTickPosX, this.mc.player.lastTickPosY, this.mc.player.lastTickPosZ));
    }

    public static class trail {
        double x;
        double y;
        double z;
        TimerUtils timer;

        public trail(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.timer = new TimerUtils();
        }

        public TimerUtils getTimer() {
            return this.timer;
        }

        public double getZ() {
            return this.z;
        }

        public double getY() {
            return this.y;
        }

        public double getX() {
            return this.x;
        }
    }
}

