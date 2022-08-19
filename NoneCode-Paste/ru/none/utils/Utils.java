/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextFormatting
 */
package ru.none.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import ru.none.None;

public class Utils {
    public static void clientMessage(String message) {
        message = (Object)TextFormatting.GRAY + "[" + (Object)TextFormatting.fromColorIndex((int)None.getColor().getRGB()) + "NoneCode" + (Object)TextFormatting.GRAY + "] " + (Object)TextFormatting.WHITE + (Object)TextFormatting.RESET + message;
        Minecraft.getMinecraft().player.addChatMessage((ITextComponent)new TextComponentString(message));
    }

    public static void playSound(String url) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(None.class.getResourceAsStream("/assets/minecraft/none/sounds/" + url));
                clip.open(inputStream);
                clip.start();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }).start();
    }
}

