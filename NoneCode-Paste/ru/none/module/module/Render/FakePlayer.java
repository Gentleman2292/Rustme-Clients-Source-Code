/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package ru.none.module.module.Render;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
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
        if (this.fakePlayer != null && this.mc.world != null) {
            this.mc.world.removeEntityFromWorld(-7777);
            this.fakePlayer = null;
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (this.mc.world != null && this.mc.player != null) {
            this.fakePlayer = new EntityOtherPlayerMP((World)this.mc.world, new GameProfile(UUID.fromString("6714531a-1c69-438e-b7d6-d6d41ca6838b"), "Akrien_0ne_love"));
            this.fakePlayer.copyLocationAndAnglesFrom((Entity)this.mc.player);
            this.fakePlayer.inventory.copyInventory(this.mc.player.inventory);
            this.mc.world.addEntityToWorld(-7777, (Entity)this.fakePlayer);
        } else {
            this.setEnable(false);
        }
    }
}

