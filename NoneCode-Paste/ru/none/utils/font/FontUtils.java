/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package ru.none.utils.font;

import java.awt.Font;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import ru.none.utils.font.CustomFontRenderer;

public final class FontUtils {
    public static CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/latoregular.ttf"), 20.0f, 0), true, true);

    public static Font getFontFromTTF(ResourceLocation loc, float fontSize, int fontType) {
        try {
            Font output = Font.createFont(fontType, Minecraft.getMinecraft().getResourceManager().getResource(loc).getInputStream());
            output = output.deriveFont(fontSize);
            return output;
        }
        catch (Exception var5) {
            return null;
        }
    }
}

