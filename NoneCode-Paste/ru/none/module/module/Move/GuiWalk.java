/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  org.lwjgl.input.Keyboard
 */
package ru.none.module.module.Move;

import net.minecraft.client.gui.GuiChat;
import org.lwjgl.input.Keyboard;
import ru.none.None;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.setting.settings.BooleanSetting;

public class GuiWalk
extends Module {
    BooleanSetting sneak = new BooleanSetting("Sneak", (Module)this, false);

    public GuiWalk() {
        super("GuiWalk", Category.Move);
        None.settingManager.add(this.sneak);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (this.mc.currentScreen != null && !(this.mc.currentScreen instanceof GuiChat)) {
            this.mc.gameSettings.keyBindJump.pressed = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindJump.getKeyCode());
            this.mc.gameSettings.keyBindForward.pressed = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindForward.getKeyCode());
            this.mc.gameSettings.keyBindBack.pressed = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindBack.getKeyCode());
            this.mc.gameSettings.keyBindLeft.pressed = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindLeft.getKeyCode());
            this.mc.gameSettings.keyBindRight.pressed = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindRight.getKeyCode());
            if (this.sneak.getVal().booleanValue()) {
                this.mc.gameSettings.keyBindSneak.pressed = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindSneak.getKeyCode());
            }
            this.mc.gameSettings.keyBindSprint.pressed = Keyboard.isKeyDown((int)this.mc.gameSettings.keyBindSprint.getKeyCode());
        }
    }
}

