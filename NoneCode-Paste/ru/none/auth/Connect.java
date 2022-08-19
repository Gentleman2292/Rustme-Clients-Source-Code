/*
 * Decompiled with CFR 0.150.
 */
package ru.none.auth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.MessageDigest;

public class Connect {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 1337);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String word = Connect.getHWID();
            out.write(word + "\n");
            out.flush();
            Thread.sleep(1000L);
            String string = in.readLine();
            System.out.println(string);
            if (!string.equalsIgnoreCase("yes")) {
                System.exit(228);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getHWID() {
        try {
            byte[] byteData;
            String toEncrypt = System.getenv("COMPUTERNAME") + System.getProperty("user.name") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_LEVEL");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toEncrypt.getBytes());
            StringBuffer hexString = new StringBuffer();
            for (byte aByteData : byteData = md.digest()) {
                String hex = Integer.toHexString(0xFF & aByteData);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}

