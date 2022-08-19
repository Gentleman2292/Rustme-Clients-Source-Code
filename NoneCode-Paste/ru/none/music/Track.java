/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music;

public class Track {
    String name;
    long maxLeight;
    String url;
    String author;

    public Track(String name, long maxLeight, String url, String author) {
        this.maxLeight = maxLeight;
        this.url = url;
        this.name = name;
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getName() {
        return this.name;
    }

    public long getMaxLeight() {
        return this.maxLeight;
    }

    public String getUrl() {
        return this.url;
    }
}

