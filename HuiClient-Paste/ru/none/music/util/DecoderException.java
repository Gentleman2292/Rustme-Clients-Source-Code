/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.util;

import ru.none.music.util.DecoderErrors;
import ru.none.music.util.JavaLayerException;

public class DecoderException
extends JavaLayerException
implements DecoderErrors {
    private int errorcode = 512;

    public DecoderException(String msg, Throwable t) {
        super(msg, t);
    }

    public DecoderException(int errorcode, Throwable t) {
        this(DecoderException.getErrorString(errorcode), t);
        this.errorcode = errorcode;
    }

    public int getErrorCode() {
        return this.errorcode;
    }

    public static String getErrorString(int errorcode) {
        return "Decoder errorcode " + Integer.toHexString(errorcode);
    }
}

