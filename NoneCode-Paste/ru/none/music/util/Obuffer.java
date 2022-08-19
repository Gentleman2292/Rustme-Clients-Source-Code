/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.util;

public abstract class Obuffer {
    public static final int OBUFFERSIZE = 2304;
    public static final int MAXCHANNELS = 2;

    public abstract void append(int var1, short var2);

    public void appendSamples(int channel, float[] f) {
        int i = 0;
        while (i < 32) {
            short s = this.clip(f[i++]);
            this.append(channel, s);
        }
    }

    private final short clip(float sample) {
        return (short)(sample > 32767.0f ? 32767 : (short)(sample < -32768.0f ? -32768 : (short)sample));
    }

    public abstract void write_buffer(int var1);

    public abstract void close();

    public abstract void clear_buffer();

    public abstract void set_stop_flag();
}

