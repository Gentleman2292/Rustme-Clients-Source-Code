/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StringUtils
 */
package ru.none.utils.font;

import java.awt.Font;
import net.minecraft.util.StringUtils;
import ru.none.utils.font.CustomFontRenderer;

public class FontUtil {
    private static CustomFontRenderer fontRenderer;

    public static void setupFontUtils() {
        fontRenderer = new CustomFontRenderer(new Font("Arial", 0, 17), true, true);
    }

    public static double getStringWidth(String text) {
        return fontRenderer.getStringWidth(StringUtils.stripControlCodes((String)text));
    }

    public static int getFontHeight() {
        return fontRenderer.getHeight();
    }

    public static void drawString(String text, int x, double y, int color) {
        fontRenderer.drawString(text, x, (int)y, color);
    }

    public static void drawStringWithShadow(String text, double x, double y, int color) {
        fontRenderer.drawStringWithShadow(text, (float)x, (float)y, color);
    }

    public static void drawCenteredString(String text, double x, double y, int color) {
        FontUtil.drawString(text, (int)(x - (double)(fontRenderer.getStringWidth(text) / 2)), y, color);
    }

    public static void drawCenteredStringWithShadow(String text, double x, double y, int color) {
        FontUtil.drawStringWithShadow(text, x - (double)(fontRenderer.getStringWidth(text) / 2), y, color);
    }

    public static void drawTotalCenteredString(String text, double x, double y, int color) {
        FontUtil.drawString(text, (int)(x - (double)(fontRenderer.getStringWidth(text) / 2)), y - (double)(fontRenderer.getHeight() / 2), color);
    }

    public static void drawTotalCenteredStringWithShadow(String text, double x, double y, int color) {
        FontUtil.drawStringWithShadow(text, x - (double)(fontRenderer.getStringWidth(text) / 2), y - (double)((float)fontRenderer.getHeight() / 2.0f), color);
    }
}

