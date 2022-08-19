/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package ru.none.utils.notification;

import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;
import ru.none.utils.notification.Notification;

public class NotificationManager {
    protected static CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/main.ttf"), 20.0f, 0), true, true);
    static ArrayList<Notification> notifications = new ArrayList();

    public static void add(Notification notification) {
        notifications.add(notification);
    }

    public static void render() {
        Notification notification;
        int i;
        for (i = 0; i < notifications.size(); ++i) {
            notification = notifications.get(i);
            if (System.currentTimeMillis() - notification.getTime() <= 2000L) continue;
            notifications.remove(i);
        }
        for (i = 0; i < notifications.size(); ++i) {
            notification = notifications.get(i);
            NotificationManager.drawNotification(notification, i);
        }
    }

    public static void drawNotification(Notification notification, int i) {
        if (System.currentTimeMillis() - notification.getTime() >= 1500L) {
            notification.setX(MathUtils.lerp(notification.getX(), 0.0f, 0.2f));
        } else {
            notification.setX(MathUtils.lerp(notification.getX(), 1.0f, 0.2f));
        }
        GL11.glPushMatrix();
        float x = GuiScreen.width - 10 - fr.getStringWidth(notification.getMessage());
        float y = GuiScreen.height - 25 - i * 30;
        float oXpos = x;
        float oYpos = y;
        float oWidth = fr.getStringWidth(notification.getMessage());
        float oHeight = 20.0f;
        GL11.glTranslated((double)oWidth, (double)oHeight, (double)1.0);
        GL11.glTranslated((double)(-oXpos * notification.getX() + oXpos + oWidth * -notification.getX()), (double)(-oYpos * 1.0f + oYpos + oHeight * -1.0f), (double)1.0);
        GL11.glScaled((double)notification.getX(), (double)1.0, (double)0.0);
        RenderUtils.drawShadowRect(x - 5.0f, y, x + oWidth, y + 20.0f, 5);
        RenderUtils.drawRect(x, y, x + oWidth, y + 20.0f, new Color(255, 255, 255).getRGB());
        RenderUtils.drawRect(x - 5.0f, y, x - 1.0f, y + 20.0f, notification.getColor());
        fr.drawString(notification.getName(), x, y + 2.0f, new Color(30, 30, 30).getRGB());
        fr.drawString(notification.getMessage(), x, y + 13.0f, new Color(30, 30, 30).getRGB());
        GL11.glPopMatrix();
    }
}

