/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.util;

import java.io.IOException;
import java.io.InputStream;
import ru.none.music.util.Source;

public class InputStreamSource
implements Source {
    private final InputStream in;

    public InputStreamSource(InputStream in) {
        if (in == null) {
            throw new NullPointerException("in");
        }
        this.in = in;
    }

    @Override
    public int read(byte[] b, int offs, int len) throws IOException {
        int read = this.in.read(b, offs, len);
        return read;
    }

    @Override
    public boolean willReadBlock() {
        return true;
    }

    @Override
    public boolean isSeekable() {
        return false;
    }

    @Override
    public long tell() {
        return -1L;
    }

    @Override
    public long seek(long to) {
        return -1L;
    }

    @Override
    public long length() {
        return -1L;
    }
}

