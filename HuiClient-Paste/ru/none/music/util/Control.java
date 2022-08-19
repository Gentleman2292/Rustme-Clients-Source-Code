/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.util;

public interface Control {
    public void start();

    public void stop();

    public boolean isPlaying();

    public void pause();

    public boolean isRandomAccess();

    public double getPosition();

    public void setPosition(double var1);
}

