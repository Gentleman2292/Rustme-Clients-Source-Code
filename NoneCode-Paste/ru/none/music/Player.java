/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import ru.none.music.player.MP3Player;

public class Player {
    public static MP3Player player;
    public static boolean isPlaying;
    public static float vol;
    public static boolean paused;
    public static boolean playerStopped;
    public static boolean playerPlaying;
    public static String MusicName;

    public static void resume() {
        if (player != null) {
            Player.setVolume(100.0f);
            player.play();
            isPlaying = true;
            paused = false;
        }
    }

    public static void play(String url) {
        Player.stop();
        try {
            URL u = new URL(url);
            player = new MP3Player(u);
        }
        catch (MalformedURLException malformedURLException) {
            // empty catch block
        }
        player.setRepeat(false);
        Player.setVolume(100.0f);
        new Thread(){

            @Override
            public void run() {
                try {
                    player.play();
                    isPlaying = true;
                    paused = false;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }.start();
    }

    public static void play(String url, String name) {
        MusicName = name;
        Player.stop();
        try {
            URL u = new URL(url);
            player = new MP3Player(u);
        }
        catch (MalformedURLException malformedURLException) {
            // empty catch block
        }
        player.setRepeat(false);
        Player.setVolume(100.0f);
        new Thread(){

            @Override
            public void run() {
                try {
                    player.play();
                    isPlaying = true;
                    paused = false;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }.start();
    }

    public static void play(String url, boolean add) {
        Player.stop();
        try {
            URL u = new URL(url);
            player = new MP3Player(u);
        }
        catch (MalformedURLException malformedURLException) {
            // empty catch block
        }
        player.setRepeat(false);
        Player.setVolume(100.0f);
        new Thread(){

            @Override
            public void run() {
                try {
                    player.play();
                    isPlaying = true;
                    paused = false;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }.start();
    }

    public static void playFile(File f) {
        Player.stop();
        player = new MP3Player(f);
        player.setRepeat(false);
        Player.setVolume(100.0f);
        new Thread(){

            @Override
            public void run() {
                try {
                    player.play();
                    isPlaying = true;
                    paused = false;
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }.start();
    }

    public static void pause() {
        if (player != null) {
            player.pause();
            isPlaying = false;
            paused = true;
        }
    }

    public static boolean isPlaying() {
        return playerPlaying;
    }

    public static boolean isStopped() {
        return playerStopped;
    }

    public static void stop() {
        if (player != null) {
            player.stop();
            player = null;
            isPlaying = false;
            paused = false;
        }
    }

    public static void setVolume(float volume) {
        if (player != null) {
            player.setVolume((int)volume);
        }
    }

    static {
        isPlaying = false;
        playerStopped = true;
        MusicName = "";
    }
}

