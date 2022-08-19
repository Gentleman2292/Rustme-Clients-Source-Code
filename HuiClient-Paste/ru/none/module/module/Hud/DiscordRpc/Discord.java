/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 */
package ru.none.module.module.Hud.DiscordRpc;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import ru.none.RPC;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.module.Hud.DiscordRpc.DiscordEventHandlers;
import ru.none.module.module.Hud.DiscordRpc.DiscordRPC;
import ru.none.module.module.Hud.DiscordRpc.DiscordRichPresence;

public class Discord
extends Module {
    public static final DiscordRPC INSTANCE = new DiscordRPC(){

        @Override
        public void Discord_Initialize(@Nonnull String var1, @Nullable DiscordEventHandlers var2, boolean var3, @Nullable String var4) {
        }

        @Override
        public void Discord_Shutdown() {
        }

        @Override
        public void Discord_RunCallbacks() {
        }

        @Override
        public void Discord_UpdateConnection() {
        }

        @Override
        public void Discord_UpdatePresence(@Nullable DiscordRichPresence var1) {
        }

        @Override
        public void Discord_ClearPresence() {
        }

        @Override
        public void Discord_Respond(@Nonnull String var1, int var2) {
        }

        @Override
        public void Discord_UpdateHandlers(@Nullable DiscordEventHandlers var1) {
        }

        @Override
        public void Discord_Register(String var1, String var2) {
        }

        @Override
        public void Discord_RegisterSteamGame(String var1, String var2) {
        }
    };

    public Discord() {
        super("DiscordRPC", Category.Hud);
    }

    @Override
    public void onEnable() {
        RPC.startRPC();
        super.onEnable();
    }
}

