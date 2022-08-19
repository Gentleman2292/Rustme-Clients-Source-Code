/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.none;

import net.minecraft.client.Minecraft;
import ru.none.None;
import ru.none.module.Module;
import ru.none.module.module.Hud.DiscordRpc.DiscordEventHandlers;
import ru.none.module.module.Hud.DiscordRpc.DiscordRPC;
import ru.none.module.module.Hud.DiscordRpc.DiscordRichPresence;

public class RPC {
    private static final DiscordRichPresence discordRichPresence = new DiscordRichPresence();
    private static final DiscordRPC discordRPC = DiscordRPC.INSTANCE;

    public static void startRPC() {
        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = (var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2);
        String discordID = "1004337174734446663";
        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
        RPC.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        RPC.discordRichPresence.details = "Null";
        RPC.discordRichPresence.largeImageKey = "main";
        RPC.discordRichPresence.largeImageText = "XuyClient";
        discordRPC.Discord_UpdatePresence(discordRichPresence);
        new Thread(() -> {
            while (true) {
                int use = 0;
                None.getGate();
                int max = None.moduleManager.modules.size();
                None.getGate();
                for (Module module : None.moduleManager.modules) {
                    if (!module.isEnable()) continue;
                    ++use;
                }
                RPC.discordRichPresence.state = "UID: 1";
                discordRPC.Discord_UpdatePresence(discordRichPresence);
                RPC.discordRichPresence.details = "Hacks [" + use + "/" + max + ']';
                RPC.discordRichPresence.details = Minecraft.getMinecraft().world != null ? (Minecraft.getMinecraft().isSingleplayer() ? "\u0434\u0435\u0434 \u0438\u043d\u0441\u0430\u0439\u0434" : "\u0432 \u0433\u043e\u0441\u0442\u044f\u0445 \u0443: " + Minecraft.getMinecraft().getCurrentServerData().serverIP) : "\u043c\u0435\u043d\u044e";
                discordRPC.Discord_RunCallbacks();
                try {
                    Thread.sleep(5000L);
                    continue;
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                break;
            }
        }).start();
    }

    public static void stopRPC() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}

