/*
 * Decompiled with CFR 0.150.
 */
package ru.none.utils;

public class rain {
    float x;
    float y;
    float z;
    float radius;

    public rain(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = 0.0f;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setY(float y) {
        this.y = y;
    }
}

