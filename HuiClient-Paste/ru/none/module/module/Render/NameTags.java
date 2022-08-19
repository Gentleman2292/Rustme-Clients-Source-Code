/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemAir
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.text.TextFormatting
 *  org.lwjgl.opengl.GL11
 */
package ru.none.module.module.Render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;
import ru.none.FriendManager;
import ru.none.None;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.utils.RenderUtils;

public class NameTags
extends Module {
    public NameTags() {
        super("NameTags", Category.Render);
    }

    @Override
    public void onRender3D() {
        super.onRender3D();
        for (Entity e : NameTags.mc.world.loadedEntityList) {
            if (!(e instanceof EntityPlayer)) continue;
            if (e == Minecraft.player) continue;
            double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosX;
            double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosY;
            double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)mc.getRenderPartialTicks() - NameTags.mc.getRenderManager().viewerPosZ;
            GL11.glPushMatrix();
            GL11.glDisable((int)2929);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            float size = Math.min(Math.max(1.2f * (Minecraft.player.getDistanceToEntity(e) * 0.15f), 1.25f), 6.0f) * 0.015f;
            GL11.glTranslatef((float)((float)x), (float)((float)y + e.height + 0.6f), (float)((float)z));
            GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)(-NameTags.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)NameTags.mc.getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)(-size), (float)(-size), (float)(-size));
            String name = e.getDisplayName().getUnformattedText() + (Object)TextFormatting.GREEN + " HP: " + Math.round(((EntityPlayer)e).getHealth());
            float wight = this.fr.getStringWidth(name) + 10;
            if (FriendManager.isFriend(e.getName())) {
                RenderUtils.drawShadowRect(-(wight / 2.0f), 0.0, wight / 2.0f, 15.0, None.getColor().getRGB(), 3);
            } else {
                RenderUtils.drawShadowRect(-(wight / 2.0f), 0.0, wight / 2.0f, 15.0, 3);
            }
            RenderUtils.drawRect(-(wight / 2.0f), 0.0f, wight / 2.0f, 15.0f, new Color(30, 30, 30).getRGB());
            int b = (int)(wight - 4.0f);
            RenderUtils.drawShadowRect(-(wight / 2.0f) + 2.0f, 11.0, -(wight / 2.0f) + 2.0f + (float)b / ((EntityPlayer)e).getMaxHealth() * ((EntityPlayer)e).getHealth(), 13.0, None.getColor().getRGB(), 1);
            RenderUtils.drawRect(-(wight / 2.0f) + 2.0f, 11.0f, -(wight / 2.0f) + 2.0f + (float)b / ((EntityPlayer)e).getMaxHealth() * ((EntityPlayer)e).getHealth(), 13.0f, None.getColor().getRGB());
            this.fr.drawCenteredString(name, 0.0f, 5 - this.fr.getHeight() / 2, -1);
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            if (!(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemAir)) {
                items.add(((EntityPlayer)e).getHeldItem(EnumHand.MAIN_HAND));
            }
            for (ItemStack itemStack : e.getArmorInventoryList()) {
                if (itemStack.getItem() instanceof ItemAir) continue;
                items.add(itemStack);
            }
            if (!(((EntityPlayer)e).getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemAir)) {
                items.add(((EntityPlayer)e).getHeldItem(EnumHand.OFF_HAND));
            }
            int i = -(items.size() * 16 / 2);
            for (ItemStack itemStack : items) {
                RenderItem renderItem = mc.getRenderItem();
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                RenderHelper.enableStandardItemLighting();
                renderItem.zLevel = -100.0f;
                renderItem.renderItemIntoGUI(itemStack, i, -20);
                renderItem.zLevel = 0.0f;
                RenderHelper.disableStandardItemLighting();
                GlStateManager.enableAlpha();
                GlStateManager.disableBlend();
                GlStateManager.disableLighting();
                GlStateManager.popMatrix();
                GlStateManager.pushMatrix();
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                GlStateManager.popMatrix();
                if (itemStack.getItem() instanceof ItemArmor) {
                    int posY = -30;
                    Map enchantments = EnchantmentHelper.getEnchantments((ItemStack)itemStack);
                    for (Enchantment enchantment : enchantments.keySet()) {
                        int level = EnchantmentHelper.getEnchantmentLevel((Enchantment)enchantment, (ItemStack)itemStack);
                        this.fr.drawCenteredString(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level, i + 8, posY, -1);
                        posY -= 12;
                    }
                }
                i += 16;
            }
            GL11.glEnable((int)2929);
            GL11.glColor3f((float)255.0f, (float)255.0f, (float)255.0f);
            GL11.glEnable((int)2929);
            GL11.glPopMatrix();
        }
    }

    public int getcenter(String text) {
        return NameTags.mc.fontRendererObj.getStringWidth(text) / 2;
    }

    public int getcenter(int text) {
        return NameTags.mc.fontRendererObj.getStringWidth(String.valueOf(text)) / 2;
    }
}

