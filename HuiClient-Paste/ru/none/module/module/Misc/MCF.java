/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.input.Mouse
 */
package ru.none.module.module.Misc;

import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Mouse;
import ru.none.FriendManager;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;

public class MCF
extends Module {
    public boolean onFriend = true;

    public MCF() {
        super("MCF", Category.Misc);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        super.onUpdate(hook);
        if (Mouse.isButtonDown((int)2) && MCF.mc.pointedEntity != null && MCF.mc.pointedEntity instanceof EntityLivingBase && this.onFriend) {
            this.onFriend = false;
            if (FriendManager.isFriend(MCF.mc.objectMouseOver.entityHit.getName())) {
                FriendManager.remove(MCF.mc.objectMouseOver.entityHit.getName());
            } else {
                FriendManager.add(MCF.mc.objectMouseOver.entityHit.getName());
            }
        }
        if (!Mouse.isButtonDown((int)2)) {
            this.onFriend = true;
        }
    }
}

