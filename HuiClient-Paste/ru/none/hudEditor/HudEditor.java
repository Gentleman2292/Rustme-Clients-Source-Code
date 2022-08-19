/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package ru.none.hudEditor;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import ru.none.None;
import ru.none.hudEditor.HudComponent;
import ru.none.module.Module;

public class HudEditor
extends GuiScreen {
    ArrayList<HudComponent> hudComponents = new ArrayList();

    public HudEditor() {
        for (Module module : None.moduleManager.modules) {
            if (!module.isHud()) continue;
            this.hudComponents.add(new HudComponent(module));
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (HudComponent hudComponent : this.hudComponents) {
            if (!hudComponent.module.isEnable()) continue;
            hudComponent.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (HudComponent hudComponent : this.hudComponents) {
            if (!hudComponent.module.isEnable()) continue;
            hudComponent.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }
}

