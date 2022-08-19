/*
 * Decompiled with CFR 0.150.
 */
package ru.none.utils;

public class TimerUtils {
    long MC = System.currentTimeMillis();

    public boolean isDeley(long time) {
        return System.currentTimeMillis() - this.MC >= time;
    }

    public void reset() {
        this.MC = System.currentTimeMillis();
    }

    public long getTime() {
        return System.currentTimeMillis() - this.MC;
    }
}

