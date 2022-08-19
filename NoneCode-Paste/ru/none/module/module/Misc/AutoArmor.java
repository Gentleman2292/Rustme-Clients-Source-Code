/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.InventoryEffectRenderer
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Enchantments
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 */
package ru.none.module.module.Misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import ru.none.hooks.PlayerHook;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.TimerUtils;

public class AutoArmor
extends Module {
    public TimerUtils timer = new TimerUtils();

    public AutoArmor() {
        super("AutoArmor", Category.Misc);
    }

    @Override
    public void onUpdate(PlayerHook hook) {
        ItemArmor item;
        ItemStack stack;
        super.onUpdate(hook);
        if (this.mc.currentScreen instanceof GuiContainer && !(this.mc.currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        InventoryPlayer inventory = this.mc.player.inventory;
        if (this.mc.player.movementInput.field_192832_b == 0.0f) {
            if (this.mc.player.movementInput.moveStrafe != 0.0f) {
                return;
            }
        } else {
            return;
        }
        int[] bestArmorSlots = new int[4];
        int[] bestArmorValues = new int[4];
        for (int type = 0; type < 4; ++type) {
            bestArmorSlots[type] = -1;
            stack = inventory.armorItemInSlot(type);
            if (AutoArmor.isNullOrEmpty(stack) || !(stack.getItem() instanceof ItemArmor)) continue;
            item = (ItemArmor)stack.getItem();
            bestArmorValues[type] = this.getArmorValue(item, stack);
        }
        for (int slot = 0; slot < 36; ++slot) {
            stack = inventory.getStackInSlot(slot);
            if (AutoArmor.isNullOrEmpty(stack) || !(stack.getItem() instanceof ItemArmor)) continue;
            item = (ItemArmor)stack.getItem();
            int armorType = item.armorType.getIndex();
            int armorValue = this.getArmorValue(item, stack);
            if (armorValue <= bestArmorValues[armorType]) continue;
            bestArmorSlots[armorType] = slot;
            bestArmorValues[armorType] = armorValue;
        }
        ArrayList<Integer> types = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3));
        Collections.shuffle(types);
        for (int i : types) {
            ItemStack oldArmor;
            int j = bestArmorSlots[i];
            if (j == -1 || !AutoArmor.isNullOrEmpty(oldArmor = inventory.armorItemInSlot(i)) && inventory.getFirstEmptyStack() == -1) continue;
            if (j < 9) {
                j += 36;
            }
            if (!AutoArmor.isNullOrEmpty(oldArmor)) {
                this.mc.playerController.windowClick(0, 8 - i, 0, ClickType.QUICK_MOVE, (EntityPlayer)this.mc.player);
            }
            this.mc.playerController.windowClick(0, j, 0, ClickType.QUICK_MOVE, (EntityPlayer)this.mc.player);
            break;
        }
    }

    int getArmorValue(ItemArmor item, ItemStack stack) {
        int armorPoints = item.damageReduceAmount;
        int prtPoints = 0;
        int armorToughness = (int)item.toughness;
        int armorType = item.getArmorMaterial().getDamageReductionAmount(EntityEquipmentSlot.LEGS);
        Enchantment protection = Enchantments.PROTECTION;
        int prtLvl = EnchantmentHelper.getEnchantmentLevel((Enchantment)protection, (ItemStack)stack);
        EntityPlayerSP player = this.mc.player;
        DamageSource dmgSource = DamageSource.causePlayerDamage((EntityPlayer)player);
        prtPoints = protection.calcModifierDamage(prtLvl, dmgSource);
        return armorPoints * 5 + prtPoints * 3 + armorToughness + armorType;
    }

    public static boolean isNullOrEmpty(ItemStack stack) {
        return stack == null || stack.func_190926_b();
    }
}

