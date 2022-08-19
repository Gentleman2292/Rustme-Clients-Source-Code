/*
 * Decompiled with CFR 0.150.
 */
package ru.none.utils.notification;

public class Notification {
    String name;
    String message;
    int color;
    long time;
    float x = 0.0f;

    public Notification(String name, String message, int color) {
        this.color = color;
        this.message = message;
        this.name = name;
        this.time = System.currentTimeMillis();
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public long getTime() {
        return this.time;
    }

    public String getName() {
        return this.name;
    }

    public int getColor() {
        return this.color;
    }

    public String getMessage() {
        return this.message;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

