/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package ru.none.gui.shit;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.util.ResourceLocation;
import ru.none.None;
import ru.none.gui.ClickGuiScreen2;
import ru.none.gui.Component;
import ru.none.gui.shit.GuiModule;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.Utils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;

public class GuiCategoty
extends Component {
    public static CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/roadrage.ttf"), 25.0f, 0), true, true);
    ArrayList<Component> modules = new ArrayList();
    Category category;

    public GuiCategoty(Category category) {
        this.category = category;
        for (Module module : None.moduleManager.getModules(category)) {
            this.modules.add(new GuiModule(module));
        }
    }

    public Category getCategory() {
        return this.category;
    }

    @Override
    public void drawScreen(int x, int y, int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(x, y, mouseX, mouseY, partialTicks);
        fr.drawString(this.category.name(), x, y + 10 - fr.getHeight() / 2, -1);
    }

    @Override
    public void mouseClicked(int x, int y, int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(x, y, mouseX, mouseY, mouseButton);
        if (this.ishover(x, y, (float)x + this.getWidth(), (float)y + this.getHeight(), mouseX, mouseY)) {
            ClickGuiScreen2.categoty = this;
            Utils.playSound("cat.wav");
        }
    }

    @Override
    public float getHeight() {
        return 20.0f;
    }

    @Override
    public float getWidth() {
        return fr.getStringWidth(this.category.name()) + 5;
    }

    public ArrayList<Component> getModules() {
        return this.modules;
    }
}

