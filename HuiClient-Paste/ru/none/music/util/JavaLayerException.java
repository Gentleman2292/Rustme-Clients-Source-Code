/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music.util;

import java.io.PrintStream;

public class JavaLayerException
extends Exception {
    private Throwable exception;

    public JavaLayerException() {
    }

    public JavaLayerException(String msg) {
        super(msg);
    }

    public JavaLayerException(String msg, Throwable t) {
        super(msg);
        this.exception = t;
    }

    public Throwable getException() {
        return this.exception;
    }

    @Override
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintStream ps) {
        if (this.exception == null) {
            super.printStackTrace(ps);
        } else {
            this.exception.printStackTrace();
        }
    }
}

