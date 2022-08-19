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
        if (GuiWalk.mc.currentScreen != null && !(GuiWalk.mc.currentScreen instanceof GuiChat)) {
            GuiWalk.mc.gameSettings.keyBindJump.pressed = Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindJump.getKeyCode());
            GuiWalk.mc.gameSettings.keyBindForward.pressed = Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindForward.getKeyCode());
            GuiWalk.mc.gameSettings.keyBindBack.pressed = Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindBack.getKeyCode());
            GuiWalk.mc.gameSettings.keyBindLeft.pressed = Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindLeft.getKeyCode());
            GuiWalk.mc.gameSettings.keyBindRight.pressed = Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindRight.getKeyCode());
            if (this.sneak.getVal().booleanValue()) {
                GuiWalk.mc.gameSettings.keyBindSneak.pressed = Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindSneak.getKeyCode());
            }
            GuiWalk.mc.gameSettings.keyBindSprint.pressed = Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindSprint.getKeyCode());
        }
    }
}

