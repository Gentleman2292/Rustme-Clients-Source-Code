/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package ru.none.module;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import ru.none.hooks.PlayerHook;
import ru.none.hooks.ReceivePacket;
import ru.none.hooks.SendPacket;
import ru.none.module.Category;
import ru.none.utils.font.CustomFontRenderer;
import ru.none.utils.font.FontUtils;
import ru.none.utils.notification.Notification;
import ru.none.utils.notification.NotificationManager;

public class Module {
    String name;
    Category category;
    int key;
    int posX = 100;
    int posY = 100;
    int sizeX = 0;
    int sizeY = 0;
    boolean enable;
    String displayName;
    protected static Minecraft mc = Minecraft.getMinecraft();
    protected CustomFontRenderer fr = new CustomFontRenderer(FontUtils.getFontFromTTF(new ResourceLocation("fonts/latoregular.ttf"), 20.0f, 0), true, true);

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enable = false;
        this.displayName = name;
    }

    public Module(String name, Category category, int sizeX, int sizeY) {
        this.name = name;
        this.category = category;
        this.enable = false;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.displayName = name;
    }

    public boolean isHud() {
        return this.sizeY != 0 || this.sizeX != 0;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getName() {
        return this.name;
    }

    public int getKey() {
        return this.key;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setKey(int key2) {
        this.key = key2;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        if (enable) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    public void toggle() {
        this.setEnable(!this.isEnable());
    }

    public void onEnable() {
        this.displayName = this.name;
        if (this.name.equalsIgnoreCase("ClickGui") || this.name.equalsIgnoreCase("ClickMusicGui") || this.name.equalsIgnoreCase("HudEditor")) {
            NotificationManager.add(new Notification(this.name, "Was be opened", Color.YELLOW.getRGB()));
        } else {
            NotificationManager.add(new Notification(this.name, "Was be enabled", Color.GREEN.getRGB()));
        }
    }

    public void onDisable() {
        this.displayName = this.name;
        if (!(this.name.equalsIgnoreCase("ClickGui") || this.name.equalsIgnoreCase("ClickMusicGui") || this.name.equalsIgnoreCase("HudEditor"))) {
            NotificationManager.add(new Notification(this.name, "Was be disabled", Color.red.getRGB()));
        }
    }

    public void onRender2D() {
    }

    public void onRender3D() {
    }

    public void onUpdate(PlayerHook hook) {
    }

    public void onReceivePacket(ReceivePacket hook) {
    }

    public void onSendPacket(SendPacket hook) {
    }

    public void afetSendPacket() {
    }

    public void onUpdatePre(PlayerHook playerHook) {
    }
}

