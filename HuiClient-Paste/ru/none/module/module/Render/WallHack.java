/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 */
package ru.none.module.module.Render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import ru.none.FriendManager;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.BooleanSetting;

public class WallHack
extends Module {
    private BooleanSetting friends = new BooleanSetting("Friends", (Module)this, false);

    public WallHack() {
        super("WallHack", Category.Render);
        None.settingManager.add(this.friends);
    }

    void render(Entity entity, float ticks) {
        try {
            block7: {
                block6: {
                    if (entity == null) break block6;
                    if (entity != Minecraft.player) break block7;
                }
                return;
            }
            if (entity == mc.getRenderViewEntity() && WallHack.mc.gameSettings.thirdPersonView == 0) {
                return;
            }
            WallHack.mc.entityRenderer.disableLightmap();
            mc.getRenderManager().renderEntityStatic(entity, ticks, false);
            WallHack.mc.entityRenderer.enableLightmap();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        GlStateManager.clear((int)256);
        RenderHelper.enableStandardItemLighting();
        for (Entity entity : WallHack.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityPlayer) || entity == mc.getRenderViewEntity()) continue;
            if (FriendManager.isFriend(entity.getName()) && this.friends.getVal().booleanValue()) {
                this.render(entity, mc.getRenderPartialTicks());
                continue;
            }
            this.render(entity, mc.getRenderPartialTicks());
        }
    }
}

