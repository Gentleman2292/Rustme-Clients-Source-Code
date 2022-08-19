/*
 * Decompiled with CFR 0.150.
 */
package ru.none.utils;

public class TimerUtils {
    static long MC;

    public TimerUtils() {
        MC = System.currentTimeMillis();
    }

    public boolean isDeley(long time) {
        return System.currentTimeMillis() - MC >= time;
    }

    public static void reset() {
        MC = System.currentTimeMillis();
    }

    public long getTime() {
        return System.currentTimeMillis() - MC;
    }
}

