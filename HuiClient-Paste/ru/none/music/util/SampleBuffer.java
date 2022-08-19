/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.util;

import ru.none.music.util.Obuffer;

public class SampleBuffer
extends Obuffer {
    private short[] buffer = new short[2304];
    private int[] bufferp = new int[2];
    private int channels;
    private int frequency;

    public SampleBuffer(int sample_frequency, int number_of_channels) {
        this.channels = number_of_channels;
        this.frequency = sample_frequency;
        for (int i = 0; i < number_of_channels; ++i) {
            this.bufferp[i] = (short)i;
        }
    }

    public int getChannelCount() {
        return this.channels;
    }

    public int getSampleFrequency() {
        return this.frequency;
    }

    public short[] getBuffer() {
        return this.buffer;
    }

    public int getBufferLength() {
        return this.bufferp[0];
    }

    @Override
    public void append(int channel, short value) {
        this.buffer[this.bufferp[channel]] = value;
        int n = channel;
        this.bufferp[n] = this.bufferp[n] + this.channels;
    }

    @Override
    public void appendSamples(int channel, float[] f) {
        int pos = this.bufferp[channel];
        int i = 0;
        while (i < 32) {
            short s;
            float fs;
            fs = (fs = f[i++]) > 32767.0f ? 32767.0f : (fs < -32767.0f ? -32767.0f : fs);
            this.buffer[pos] = s = (short)fs;
            pos += this.channels;
        }
        this.bufferp[channel] = pos;
    }

    @Override
    public void write_buffer(int val) {
    }

    @Override
    public void close() {
    }

    @Override
    public void clear_buffer() {
        for (int i = 0; i < this.channels; ++i) {
            this.bufferp[i] = (short)i;
        }
    }

    @Override
    public void set_stop_flag() {
    }
}

