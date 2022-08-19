/*
 * Decompiled with CFR 0.150.
 */
package ru.none;

import java.util.ArrayList;

public class FriendManager {
    public static ArrayList<String> friends = new ArrayList();

    public static void add(String name) {
        if (!friends.contains(name)) {
            friends.add(name);
        }
    }

    public static boolean isFriend(String name) {
        return friends.contains(name);
    }

    public static void remove(String name) {
        if (friends.contains(name)) {
            friends.remove(name);
        }
    }
}

