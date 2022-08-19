/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Hud;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;

public class ModuleList
extends Module {
    BooleanSetting onlyBinded;
    ArrayList<SubModule> modules = new ArrayList();

    public ModuleList() {
        super("ModuleList", Category.Hud);
        this.onlyBinded = new BooleanSetting("OnlyBinded", (Module)this, false);
        None.settingManager.add(this.onlyBinded);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        int x = GuiScreen.width - 3;
        this.modules.clear();
        for (Module module : None.moduleManager.modules) {
            this.modules.add(new SubModule(module));
        }
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        int x = GuiScreen.width - 3;
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        int x = GuiScreen.width - 8;
        int y = Minecraft.player.getActivePotionEffects().size() == 0 ? 8 : 30;
        for (SubModule module : this.modules) {
            if (module.getModule().isEnable()) {
                module.setAnim(MathUtils.lerp(module.getAnim(), 1.0f, 0.1f));
                module.setY((int)MathUtils.lerp(module.getY(), this.fr.getHeight() + 10, 0.1f));
                continue;
            }
            module.setAnim(MathUtils.lerp(module.getAnim(), 0.0f, 0.1f));
            module.setY((int)MathUtils.lerp(module.getY(), 0.0f, 0.1f));
        }
        this.modules.sort(new Comparator<SubModule>(){

            @Override
            public int compare(SubModule o1, SubModule o2) {
                return ModuleList.this.fr.getStringWidth(o2.getModule().getDisplayName()) - ModuleList.this.fr.getStringWidth(o1.getModule().getDisplayName());
            }
        });
        int i = 0;
        for (SubModule module : this.modules) {
            GL11.glPushMatrix();
            float oXpos = x - this.fr.getStringWidth(module.getModule().getDisplayName());
            float oYpos = y + i;
            float oWidth = this.fr.getStringWidth(module.getModule().getDisplayName());
            float oHeight = 10.0f;
            GL11.glTranslated((double)oWidth, (double)oHeight, (double)1.0);
            GL11.glTranslated((double)(-oXpos * module.getAnim() + oXpos + oWidth * -module.getAnim()), (double)(-oYpos * 1.0f + oYpos + oHeight * -1.0f), (double)1.0);
            GL11.glScaled((double)module.getAnim(), (double)1.0, (double)0.0);
            RenderUtils.drawShadowRect(x - this.fr.getStringWidth(module.getModule().getDisplayName()), y + i, x, y + i + 10, 2);
            RenderUtils.drawRect(x - this.fr.getStringWidth(module.getModule().getDisplayName()), y + i, x, y + i + 9, new Color(30, 30, 30, 200).getRGB());
            this.fr.drawString(module.getModule().getDisplayName(), x - this.fr.getStringWidth(module.getModule().getDisplayName()), y + i, None.getColor().getRGB());
            GL11.glPopMatrix();
            i += module.getY();
        }
    }

    static class SubModule {
        Module module;
        int y = 0;
        float anim = 0.0f;

        public SubModule(Module module) {
            this.module = module;
        }

        public float getAnim() {
            return this.anim;
        }

        public void setAnim(float anim) {
            this.anim = anim;
        }

        public Module getModule() {
            return this.module;
        }

        public int getY() {
            return this.y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}

