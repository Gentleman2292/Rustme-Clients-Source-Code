/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.Display
 */
package ru.none;

import java.awt.Color;
import org.lwjgl.opengl.Display;
import ru.none.clickguiWhite.ClickWhite;
import ru.none.command.CommandManager;
import ru.none.config.cfg;
import ru.none.hooks.PlayerHook;
import ru.none.hooks.ReceivePacket;
import ru.none.hooks.SendPacket;
import ru.none.hudEditor.HudEditor;
import ru.none.macros.MacrosManager;
import ru.none.module.Module;
import ru.none.module.ModuleManager;
import ru.none.module.module.Hud.Client;
import ru.none.module.setting.SettingManager;
import ru.none.musicgui.MusicGui;
import ru.none.utils.ColorUtils;
import ru.none.utils.notification.NotificationManager;

public class None {
    public static SettingManager settingManager;
    public static ModuleManager moduleManager;
    public static HudEditor hudEditor;
    public static ClickWhite guiWhite;
    public static MusicGui musicGui;
    public static CommandManager commandManager;
    public static MacrosManager macrosManager;
    private static None gate;
    public static int color;

    public static void init() {
        settingManager = new SettingManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        macrosManager = new MacrosManager();
        guiWhite = new ClickWhite();
        musicGui = new MusicGui();
        hudEditor = new HudEditor();
        cfg.init();
        cfg.load();
    }

    public static Color getColor() {
        try {
            switch (Client.mode.getVal()) {
                case "XuyClient": {
                    return ColorUtils.TwoColoreffect(new Color(255, 48, 0), new Color(246, 186, 55), (double)Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998);
                }
                case "Astolfo": {
                    return new Color(ColorUtils.astolfoColors((int)((double)Math.abs(System.currentTimeMillis() / 10L) / 100.0 + 0.16999999999999998), 5));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new Color(30, 30, 30, 150);
    }

    public static None getGate() {
        return gate;
    }

    public static void onUpdate(PlayerHook playerHook) {
        if (!Display.getTitle().equalsIgnoreCase("balls protection")) {
            Display.setTitle((String)"XuyClient by rtyu");
        }
        for (Module module : None.moduleManager.modules) {
            if (!module.isEnable()) continue;
            module.onUpdate(playerHook);
        }
    }

    public static void onUpdatePre(PlayerHook playerHook) {
        for (Module module : None.moduleManager.modules) {
            if (!module.isEnable()) continue;
            module.onUpdatePre(playerHook);
        }
    }

    public static void onSendPacket(SendPacket hook) {
        for (Module module : None.moduleManager.modules) {
            if (!module.isEnable()) continue;
            module.onSendPacket(hook);
        }
    }

    public static void afetSendPacket() {
        for (Module module : None.moduleManager.modules) {
            if (!module.isEnable()) continue;
            module.afetSendPacket();
        }
    }

    public static void onReceivePacket(ReceivePacket hook) {
        for (Module module : None.moduleManager.modules) {
            if (!module.isEnable()) continue;
            module.onReceivePacket(hook);
        }
    }

    public static void onRender2D() {
        NotificationManager.render();
        for (Module module : None.moduleManager.modules) {
            if (!module.isEnable()) continue;
            module.onRender2D();
        }
    }

    public static void onRender3D() {
        for (Module module : None.moduleManager.modules) {
            if (!module.isEnable()) continue;
            module.onRender3D();
        }
    }

    public static void onKey(int key2) {
        macrosManager.onKey(key2);
        for (Module module : None.moduleManager.modules) {
            if (module.getKey() != key2) continue;
            module.toggle();
        }
    }

    static {
        color = new Color(105, 55, 255).getRGB();
    }
}

