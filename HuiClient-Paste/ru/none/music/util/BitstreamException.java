/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.util;

import ru.none.music.util.BitstreamErrors;
import ru.none.music.util.JavaLayerException;

public class BitstreamException
extends JavaLayerException
implements BitstreamErrors {
    private int errorcode = 256;

    public BitstreamException(String msg, Throwable t) {
        super(msg, t);
    }

    public BitstreamException(int errorcode, Throwable t) {
        this(BitstreamException.getErrorString(errorcode), t);
        this.errorcode = errorcode;
    }

    public int getErrorCode() {
        return this.errorcode;
    }

    public static String getErrorString(int errorcode) {
        return "Bitstream errorcode " + Integer.toHexString(errorcode);
    }
}

