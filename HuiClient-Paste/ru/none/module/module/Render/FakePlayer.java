/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package ru.none.module.module.Render;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import ru.none.module.Category;
import ru.none.module.Module;

public class FakePlayer
extends Module {
    EntityOtherPlayerMP fakePlayer;

    public FakePlayer() {
        super("FakePlayer", Category.Render);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (this.fakePlayer != null && FakePlayer.mc.world != null) {
            FakePlayer.mc.world.removeEntityFromWorld(-7777);
            this.fakePlayer = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onEnable() {
        super.onEnable();
        if (FakePlayer.mc.world != null) {
            if (Minecraft.player != null) {
                this.fakePlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("6714531a-1c69-438e-b7d6-d6d41ca6838b"), "Akrien_0ne_love"));
                this.fakePlayer.copyLocationAndAnglesFrom((Entity)Minecraft.player);
                this.fakePlayer.inventory.copyInventory(Minecraft.player.inventory);
                FakePlayer.mc.world.addEntityToWorld(-7777, (Entity)this.fakePlayer);
                return;
            }
        }
        this.setEnable(false);
    }
}

