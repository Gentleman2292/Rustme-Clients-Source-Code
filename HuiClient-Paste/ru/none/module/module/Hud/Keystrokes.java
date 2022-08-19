/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Hud;

import java.awt.Color;
import java.util.ArrayList;
import java.util.function.Supplier;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.MathUtils;
import ru.none.utils.RenderUtils;
import ru.none.utils.font.CustomFontRenderer;

public class Keystrokes
extends Module {
    ArrayList<key> keys = new ArrayList();

    public Keystrokes() {
        super("Keystrokes", Category.Hud, 100, 70);
        for (KeyType keyType : KeyType.values()) {
            if (keyType == KeyType.UP) {
                this.keys.add(new key(() -> Keyboard.getKeyName((int)Keystrokes.mc.gameSettings.keyBindForward.getKeyCode()), keyType, () -> this.getPosX() + 35, () -> this.getPosY(), Keystrokes.mc.gameSettings.keyBindForward));
            }
            if (keyType == KeyType.DOWN) {
                this.keys.add(new key(() -> Keyboard.getKeyName((int)Keystrokes.mc.gameSettings.keyBindBack.getKeyCode()), keyType, () -> this.getPosX() + 35, () -> this.getPosY() + 35, Keystrokes.mc.gameSettings.keyBindBack));
            }
            if (keyType == KeyType.LEFT) {
                this.keys.add(new key(() -> Keyboard.getKeyName((int)Keystrokes.mc.gameSettings.keyBindLeft.getKeyCode()), keyType, () -> this.getPosX(), () -> this.getPosY() + 35, Keystrokes.mc.gameSettings.keyBindLeft));
            }
            if (keyType != KeyType.RIGHT) continue;
            this.keys.add(new key(() -> Keyboard.getKeyName((int)Keystrokes.mc.gameSettings.keyBindRight.getKeyCode()), keyType, () -> this.getPosX() + 70, () -> this.getPosY() + 35, Keystrokes.mc.gameSettings.keyBindRight));
        }
    }

    @Override
    public void onRender2D() {
        super.onRender2D();
        for (key key2 : this.keys) {
            key2.render(this.fr);
        }
    }

    public static enum KeyType {
        UP,
        DOWN,
        LEFT,
        RIGHT;

    }

    public static class key {
        Supplier<String> name;
        KeyType keyType;
        Supplier<Integer> x;
        Supplier<Integer> y;
        KeyBinding key;
        float anim = 0.0f;

        public key(Supplier<String> name, KeyType keyType, Supplier<Integer> x, Supplier<Integer> y, KeyBinding key2) {
            this.name = name;
            this.keyType = keyType;
            this.x = x;
            this.y = y;
            this.key = key2;
        }

        public KeyType getKeyType() {
            return this.keyType;
        }

        public Supplier<Integer> getX() {
            return this.x;
        }

        public Supplier<Integer> getY() {
            return this.y;
        }

        public Supplier<String> getName() {
            return this.name;
        }

        public void render(CustomFontRenderer fr) {
            GL11.glEnable((int)3089);
            RenderUtils.scissorRect(this.x.get().intValue(), this.y.get().intValue(), this.x.get() + 30, this.y.get() + 30);
            RenderUtils.drawRect(this.x.get().intValue(), this.y.get().intValue(), this.x.get() + 30, this.y.get() + 30, new Color(200, 200, 200, 150).getRGB());
            this.anim = Keyboard.isKeyDown((int)this.key.getKeyCode()) ? MathUtils.lerp(this.anim, 50.0f, 0.1f) : 0.0f;
            RenderUtils.drawCircle(this.x.get() + 15, this.y.get() + 15, this.anim, new Color(255, 255, 255, 200).getRGB());
            GL11.glDisable((int)3089);
            fr.drawCenteredString(Keyboard.getKeyName((int)this.key.getKeyCode()), this.x.get() + 15, this.y.get() + 15 - fr.getHeight() / 2, new Color(0, 0, 0).getRGB());
        }
    }
}

