/*
 * Decompiled with CFR 0.150.
 */
package ru.none.music;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import ru.none.music.Player;

public class WebUtils {
    public static String agent1 = "User-Agent";
    public static String agent2 = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36";

    public static String visitSiteThreaded(final String urly) {
        final ArrayList lines = new ArrayList();
        String stuff = "";
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    String line;
                    URL url = new URL(urly);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.addRequestProperty(agent1, agent2);
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while ((line = in.readLine()) != null) {
                        lines.add(line);
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }).start();
        for (String s : lines) {
            stuff = stuff + s;
        }
        return stuff;
    }

    public static void playMusicLink(final String urly) {
        ArrayList lines = new ArrayList();
        String stuff = "";
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    System.out.println("Attempting to play video with ID " + urly);
                    String s = WebUtils.visitSite("https://www.convertmp3.io/fetch/?format=text&video=https://www.youtube.com/watch?v=" + urly);
                    System.out.println(s);
                    if (s.contains("meta")) {
                        System.out.println("Sorry, this video has not yet been converted & cached. It is being converted now, come back later and it will be ready.");
                    }
                    URL url = new URL(s.split("Link: ")[1]);
                    String length = s.split("Length: ")[1];
                    String realLength = length.split(" <br")[0];
                    System.out.println("Length: " + realLength);
                    System.out.println("Successfully got virtual storage location");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.addRequestProperty(agent1, agent2);
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String actualSongURL = connection.getURL().toString();
                    System.out.println("Successfully got physical location of song");
                    Player.play(actualSongURL);
                    System.out.println("Now Playing");
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }).start();
    }

    public static List<String> visitSiteThreadedFriends(final String urly) {
        final ArrayList<String> lines = new ArrayList<String>();
        try {
            new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        String line;
                        URL url = new URL(urly);
                        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                        connection.addRequestProperty(agent1, agent2);
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((line = in.readLine()) != null) {
                            if (line.isEmpty() || line.equals(" ") || line.equals("   ")) continue;
                            lines.add(line.contains(" ") ? line.replace(" ", "") : line);
                        }
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }).start();
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
        return lines;
    }

    public static String visitSite(String urly) {
        ArrayList<String> lines = new ArrayList<String>();
        String stuff = "";
        try {
            String line;
            URL url = new URL(urly);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty(agent1, agent2);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        for (String s : lines) {
            stuff = stuff + s;
        }
        return stuff;
    }
}

