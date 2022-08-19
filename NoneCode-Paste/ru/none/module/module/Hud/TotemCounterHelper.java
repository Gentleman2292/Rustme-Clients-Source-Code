/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package ru.none.module.module.Hud;

import net.minecraft.entity.Entity;

public class TotemCounterHelper {
    Entity entity;
    int count;
    long time;

    public TotemCounterHelper(Entity entity, int count) {
        this.count = count;
        this.entity = entity;
        this.time = System.currentTimeMillis();
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return this.time;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

