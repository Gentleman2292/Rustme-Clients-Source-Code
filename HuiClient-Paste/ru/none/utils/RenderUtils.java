/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.Vec2f
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package ru.none.utils;

import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public final class RenderUtils {
    public static Minecraft mc = Minecraft.getMinecraft();
    private static final Frustum frustum = new Frustum();

    public static void setColor(Color c) {
        GL11.glColor4d((double)((float)c.getRed() / 255.0f), (double)((float)c.getGreen() / 255.0f), (double)((float)c.getBlue() / 255.0f), (double)((float)c.getAlpha() / 255.0f));
    }

    public static void smoothAngleRectProof(float xPos, float yPos, float x2Pos, float y2Pos, int color) {
        RenderUtils.drawRect(xPos + 3.0f, yPos - 3.0f, x2Pos - 3.0f, yPos - 2.5f, color);
        RenderUtils.drawRect(xPos + 2.0f, yPos - 2.5f, x2Pos - 2.0f, yPos - 2.0f, color);
        RenderUtils.drawRect(xPos + 1.5f, yPos - 2.0f, x2Pos - 1.5f, yPos - 1.5f, color);
        RenderUtils.drawRect(xPos + 1.0f, yPos - 1.5f, x2Pos - 1.0f, yPos - 1.0f, color);
        RenderUtils.drawRect(xPos + 0.5f, yPos - 1.0f, x2Pos - 0.5f, yPos, color);
        RenderUtils.drawRect(xPos, yPos, x2Pos, y2Pos, color);
        RenderUtils.drawRect(xPos + 2.0f, y2Pos + 2.5f, x2Pos - 2.0f, y2Pos + 2.0f, color);
        RenderUtils.drawRect(xPos + 1.5f, y2Pos + 2.0f, x2Pos - 1.5f, y2Pos + 1.5f, color);
        RenderUtils.drawRect(xPos + 1.0f, y2Pos + 1.5f, x2Pos - 1.0f, y2Pos + 1.0f, color);
        RenderUtils.drawRect(xPos + 0.5f, y2Pos + 1.0f, x2Pos - 0.5f, y2Pos, color);
        RenderUtils.drawRect(xPos + 3.0f, y2Pos + 3.0f, x2Pos - 3.0f, y2Pos + 2.5f, color);
    }

    public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
        Tessellator ts = Tessellator.getInstance();
        BufferBuilder buffer = ts.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        buffer.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
    }

    public static void scissorRect(float x, float y, float width, double height) {
        ScaledResolution sr = new ScaledResolution(mc);
        int factor = sr.getScaleFactor();
        GL11.glScissor((int)((int)(x * (float)factor)), (int)((int)(((double)sr.getScaledHeight() - height) * (double)factor)), (int)((int)((width - x) * (float)factor)), (int)((int)((height - (double)y) * (double)factor)));
    }

    public static boolean isInViewFrustum(Entity entity) {
        return RenderUtils.isInViewFrustum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }

    private static boolean isInViewFrustum(AxisAlignedBB bb) {
        Entity current = mc.getRenderViewEntity();
        frustum.setPosition(current.posX, current.posY, current.posZ);
        return frustum.isBoundingBoxInFrustum(bb);
    }

    public static double interpolate(double old, double current, double partialTicks) {
        return old + (current - old) * partialTicks;
    }

    public static void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 0xFF) / 255.0f;
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GlStateManager.color((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void glColor(Color color) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        float alpha = (float)color.getAlpha() / 255.0f;
        GlStateManager.color((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void glColor(Color color, float alpha) {
        float red = (float)color.getRed() / 255.0f;
        float green = (float)color.getGreen() / 255.0f;
        float blue = (float)color.getBlue() / 255.0f;
        GlStateManager.color((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void drawLine(float startX, float startY, float endX, float endY, int color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        RenderUtils.glColor(color);
        GL11.glLineWidth((float)1.5f);
        GL11.glBegin((int)3);
        GL11.glVertex2f((float)startX, (float)startY);
        GL11.glVertex2f((float)endX, (float)endY);
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void drawSome(ArrayList<Vec2f> pos, int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glLineWidth((float)2.0f);
        GL11.glBegin((int)3);
        for (Vec2f vec2f : pos) {
            GL11.glVertex2f((float)vec2f.x, (float)vec2f.y);
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void drawSome2(ArrayList<Vec2f> pos, int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)9);
        for (Vec2f vec2f : pos) {
            GL11.glVertex2f((float)vec2f.x, (float)vec2f.y);
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void drawRect(float startX, float startY, float endX, float endY, int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)startX, (double)startY);
        GL11.glVertex2d((double)endX, (double)startY);
        GL11.glVertex2d((double)endX, (double)endY);
        GL11.glVertex2d((double)startX, (double)endY);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void drawRect(double startX, double startY, double endX, double endY, int color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        RenderUtils.glColor(color);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)endX, (double)startY);
        GL11.glVertex2d((double)startX, (double)startY);
        GL11.glVertex2d((double)startX, (double)endY);
        GL11.glVertex2d((double)endX, (double)endY);
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void drawRoundedUpRect(float startX, float startY, float endX, float endY, float radius, int color) {
        float x1 = startX + radius;
        float y1 = startY + radius;
        float x2 = endX - radius;
        RenderUtils.drawRect(startX, y1, endX, endY, color);
        RenderUtils.drawRect(x1, startY, x2, y1, color);
        RenderUtils.drawSector(x2, y1, radius, 90, 180, color);
        RenderUtils.drawSector(x1, y1, radius, 180, 270, color);
    }

    public static void drawRoundedDownRect(float startX, float startY, float endX, float endY, float radius, int color) {
        float x1 = startX + radius;
        float x2 = endX - radius;
        float y2 = endY - radius;
        RenderUtils.drawRect(startX, startY, endX, y2, color);
        RenderUtils.drawRect(x1, y2, x2, endY, color);
        RenderUtils.drawSector(x2, y2, radius, 0, 90, color);
        RenderUtils.drawSector(x1, y2, radius, 270, 360, color);
    }

    public static void drawRoundedRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, float radius, int color) {
        float x1 = paramXStart + radius;
        float y1 = paramYStart + radius;
        float x2 = paramXEnd - radius;
        float y2 = paramYEnd - radius;
        RenderUtils.drawRect(x1, y1, x2, y2, color);
        RenderUtils.drawRect(x1, paramYStart, x2, y1, color);
        RenderUtils.drawRect(x1, y2, x2, paramYEnd, color);
        RenderUtils.drawRect(paramXStart, y1, x1, y2, color);
        RenderUtils.drawRect(x2, y1, paramXEnd, y2, color);
        RenderUtils.drawSector(x2, y2, radius, 0, 90, color);
        RenderUtils.drawSector(x2, y1, radius, 90, 180, color);
        RenderUtils.drawSector(x1, y1, radius, 180, 270, color);
        RenderUtils.drawSector(x1, y2, radius, 270, 360, color);
    }

    public static void drawUpShadow(float startX, float startY, float endX, float endY) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        GL11.glBegin((int)7);
        RenderUtils.glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)startX, (double)startY);
        RenderUtils.glColor(new Color(0, 0, 0, 0).getRGB());
        GL11.glVertex2d((double)startX, (double)endY);
        GL11.glVertex2d((double)endX, (double)endY);
        RenderUtils.glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)endX, (double)startY);
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel((int)7424);
    }

    public static void drawDownShadow(float startX, float startY, float endX, float endY) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        GL11.glBegin((int)7);
        RenderUtils.glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)endX, (double)endY);
        RenderUtils.glColor(new Color(0, 0, 0, 0).getRGB());
        GL11.glVertex2d((double)endX, (double)startY);
        GL11.glVertex2d((double)startX, (double)startY);
        RenderUtils.glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)startX, (double)endY);
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel((int)7424);
    }

    public static void drawSector(float paramX, float paramY, float radius, int angleStart, int angleEnd, int color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        RenderUtils.glColor(color);
        GL11.glBegin((int)6);
        GL11.glVertex2d((double)paramX, (double)paramY);
        for (int i = angleStart; i <= angleEnd; ++i) {
            GL11.glVertex2d((double)((double)paramX + Math.sin((double)i * Math.PI / 180.0) * (double)radius), (double)((double)paramY + Math.cos((double)i * Math.PI / 180.0) * (double)radius));
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void renderItem(ItemStack itemStack, int x, int y) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        RenderUtils.mc.getRenderItem().zLevel = -100.0f;
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
        mc.getRenderItem().renderItemOverlays(RenderUtils.mc.fontRendererObj, itemStack, x, y);
        RenderUtils.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.disableDepth();
    }

    public static void drawGradientRect(double startX, double startY, double endX, double endY, boolean sideways, int startColor, int endColor) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        RenderUtils.glColor(startColor);
        GL11.glBegin((int)7);
        if (sideways) {
            GL11.glVertex2d((double)startX, (double)startY);
            GL11.glVertex2d((double)startX, (double)endY);
            RenderUtils.glColor(endColor);
            GL11.glVertex2d((double)endX, (double)endY);
            GL11.glVertex2d((double)endX, (double)startY);
        } else {
            GL11.glVertex2d((double)startX, (double)startY);
            RenderUtils.glColor(endColor);
            GL11.glVertex2d((double)startX, (double)endY);
            GL11.glVertex2d((double)endX, (double)endY);
            RenderUtils.glColor(startColor);
            GL11.glVertex2d((double)endX, (double)startY);
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel((int)7424);
    }

    public static void drawCircle(float x, float y, float radius, int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        boolean flag = GL11.glIsEnabled((int)3042);
        boolean flag1 = GL11.glIsEnabled((int)2848);
        boolean flag2 = GL11.glIsEnabled((int)3553);
        if (!flag) {
            GL11.glEnable((int)3042);
        }
        if (!flag1) {
            GL11.glEnable((int)2848);
        }
        if (flag2) {
            GL11.glDisable((int)3553);
        }
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)9);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d((double)((double)x + Math.sin((double)i * Math.PI / 180.0) * (double)radius), (double)((double)y + Math.cos((double)i * Math.PI / 180.0) * (double)radius));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        if (flag2) {
            GL11.glEnable((int)3553);
        }
        if (!flag1) {
            GL11.glDisable((int)2848);
        }
        if (!flag) {
            GL11.glDisable((int)3042);
        }
    }

    public static void drawRect228(float startX, float startY, float endX, float endY, int color) {
        ArrayList<Vec2f> vec2fs = new ArrayList<Vec2f>();
        vec2fs.add(new Vec2f(startX + 5.0f, startY));
        vec2fs.add(new Vec2f(endX, startY));
        vec2fs.add(new Vec2f(endX, endY - 5.0f));
        vec2fs.add(new Vec2f(endX - 5.0f, endY));
        vec2fs.add(new Vec2f(startX, endY));
        vec2fs.add(new Vec2f(startX, startY + 5.0f));
        vec2fs.add(new Vec2f(startX + 5.0f, startY));
        RenderUtils.drawSome2(vec2fs, color);
    }

    public static void drawRect228(float startX, float startY, float endX, float endY, int colorIn, int colorBord) {
        ArrayList<Vec2f> vec2fs = new ArrayList<Vec2f>();
        vec2fs.add(new Vec2f(startX + 5.0f, startY));
        vec2fs.add(new Vec2f(endX, startY));
        vec2fs.add(new Vec2f(endX, endY - 5.0f));
        vec2fs.add(new Vec2f(endX - 5.0f, endY));
        vec2fs.add(new Vec2f(startX, endY));
        vec2fs.add(new Vec2f(startX, startY + 5.0f));
        vec2fs.add(new Vec2f(startX + 5.0f, startY));
        RenderUtils.drawSome2(vec2fs, colorIn);
        RenderUtils.drawSome(vec2fs, colorBord);
    }

    public static void customScaledObject2D(float oXpos, float oYpos, float oWidth, float oHeight, float oScale) {
        GL11.glTranslated((double)(oWidth / 2.0f), (double)(oHeight / 2.0f), (double)1.0);
        GL11.glTranslated((double)(-oXpos * oScale + oXpos + oWidth / 2.0f * -oScale), (double)(-oYpos * oScale + oYpos + oHeight / 2.0f * -oScale), (double)1.0);
        GL11.glScaled((double)oScale, (double)oScale, (double)0.0);
    }

    public static void drawCircle228(float x, float y, float radius, int color, int xz_kak_nazvat) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        boolean flag = GL11.glIsEnabled((int)3042);
        boolean flag1 = GL11.glIsEnabled((int)2848);
        boolean flag2 = GL11.glIsEnabled((int)3553);
        if (!flag) {
            GL11.glEnable((int)3042);
        }
        if (!flag1) {
            GL11.glEnable((int)2848);
        }
        if (flag2) {
            GL11.glDisable((int)3553);
        }
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glLineWidth((float)2.5f);
        GL11.glBegin((int)3);
        for (int i = 0; i <= xz_kak_nazvat; ++i) {
            GL11.glVertex2d((double)((double)x + Math.sin((double)i * Math.PI / 180.0) * (double)radius), (double)((double)y + Math.cos((double)i * Math.PI / 180.0) * (double)radius));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        if (flag2) {
            GL11.glEnable((int)3553);
        }
        if (!flag1) {
            GL11.glDisable((int)2848);
        }
        if (!flag) {
            GL11.glDisable((int)3042);
        }
    }

    public static void enableSmoothLine(float width) {
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        GL11.glLineWidth((float)width);
    }

    public static void disableSmoothLine() {
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glDepthMask((boolean)true);
        GL11.glCullFace((int)1029);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void drawShadowRect(double startX, double startY, double endX, double endY, int radius) {
        RenderUtils.drawRect(startX, startY, endX, endY, new Color(0, 0, 0, 100).getRGB());
        RenderUtils.drawGradientRect(startX, startY - (double)radius, endX, startY, false, true, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());
        RenderUtils.drawGradientRect(startX, endY, endX, endY + (double)radius, false, false, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());
        RenderUtils.drawSector2(endX, endY, 0, 90, radius);
        RenderUtils.drawSector2(endX, startY, 90, 180, radius);
        RenderUtils.drawSector2(startX, startY, 180, 270, radius);
        RenderUtils.drawSector2(startX, endY, 270, 360, radius);
        RenderUtils.drawGradientRect(startX - (double)radius, startY, startX, endY, true, true, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());
        RenderUtils.drawGradientRect(endX, startY, endX + (double)radius, endY, true, false, new Color(0, 0, 0, 100).getRGB(), new Color(0, 0, 0, 0).getRGB());
    }

    public static Vec3d getRenderPos(double x, double y, double z) {
        return new Vec3d(x -= RenderUtils.mc.getRenderManager().viewerPosX, y -= RenderUtils.mc.getRenderManager().viewerPosY, z -= RenderUtils.mc.getRenderManager().viewerPosZ);
    }

    public static void putVertex3d(Vec3d vec) {
        GL11.glVertex3d((double)vec.xCoord, (double)vec.yCoord, (double)vec.zCoord);
    }

    public static void setupColor(Color color, int alpha) {
        GL11.glColor4d((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f), (double)((float)alpha / 255.0f));
    }

    public static void drawShadowRect(double startX, double startY, double endX, double endY, int color, int radius) {
        Color color1 = new Color(color);
        RenderUtils.drawRect(startX, startY, endX, endY, new Color(color1.getRed(), color1.getGreen(), color1.getBlue()).getRGB());
        RenderUtils.drawGradientRect(startX, startY - (double)radius, endX, startY, false, true, new Color(color1.getRed(), color1.getGreen(), color1.getBlue()).getRGB(), new Color(0, 0, 0, 0).getRGB());
        RenderUtils.drawGradientRect(startX, endY, endX, endY + (double)radius, false, false, new Color(color1.getRed(), color1.getGreen(), color1.getBlue()).getRGB(), new Color(0, 0, 0, 0).getRGB());
        RenderUtils.drawSector2(endX, endY, 0, 90, radius);
        RenderUtils.drawSector2(endX, startY, 90, 180, radius);
        RenderUtils.drawSector2(startX, startY, 180, 270, radius);
        RenderUtils.drawSector2(startX, endY, 270, 360, radius);
        RenderUtils.drawGradientRect(startX - (double)radius, startY, startX, endY, true, true, new Color(color1.getRed(), color1.getGreen(), color1.getBlue()).getRGB(), new Color(0, 0, 0, 0).getRGB());
        RenderUtils.drawGradientRect(endX, startY, endX + (double)radius, endY, true, false, new Color(color1.getRed(), color1.getGreen(), color1.getBlue()).getRGB(), new Color(0, 0, 0, 0).getRGB());
    }

    public static void drawSector2(double x, double y, int startAngle, int endAngle, int radius) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        GL11.glBegin((int)6);
        RenderUtils.glColor(new Color(0, 0, 0, 100).getRGB());
        GL11.glVertex2d((double)x, (double)y);
        RenderUtils.glColor(new Color(0, 0, 0, 0).getRGB());
        for (int i = startAngle; i <= endAngle; ++i) {
            GL11.glVertex2d((double)(x + Math.sin((double)i * Math.PI / 180.0) * (double)radius), (double)(y + Math.cos((double)i * Math.PI / 180.0) * (double)radius));
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel((int)7424);
    }

    public static void drawGradientRect(double startX, double startY, double endX, double endY, boolean sideways, boolean reversed, int startColor, int endColor) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        GL11.glBegin((int)7);
        RenderUtils.glColor(startColor);
        if (sideways) {
            if (reversed) {
                GL11.glVertex2d((double)endX, (double)endY);
                GL11.glVertex2d((double)endX, (double)startY);
                RenderUtils.glColor(endColor);
                GL11.glVertex2d((double)startX, (double)startY);
                GL11.glVertex2d((double)startX, (double)endY);
            } else {
                GL11.glVertex2d((double)startX, (double)startY);
                GL11.glVertex2d((double)startX, (double)endY);
                RenderUtils.glColor(endColor);
                GL11.glVertex2d((double)endX, (double)endY);
                GL11.glVertex2d((double)endX, (double)startY);
            }
        } else if (reversed) {
            GL11.glVertex2d((double)endX, (double)endY);
            RenderUtils.glColor(endColor);
            GL11.glVertex2d((double)endX, (double)startY);
            GL11.glVertex2d((double)startX, (double)startY);
            RenderUtils.glColor(startColor);
            GL11.glVertex2d((double)startX, (double)endY);
        } else {
            GL11.glVertex2d((double)startX, (double)startY);
            RenderUtils.glColor(endColor);
            GL11.glVertex2d((double)startX, (double)endY);
            GL11.glVertex2d((double)endX, (double)endY);
            RenderUtils.glColor(startColor);
            GL11.glVertex2d((double)endX, (double)startY);
        }
        GL11.glEnd();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel((int)7424);
    }

    public static void drawBorderedRect(double left, double top, double right, double bottom, double borderWidth, int insideColor, int borderColor, boolean borderIncludedInBounds) {
        RenderUtils.drawRect(left - (!borderIncludedInBounds ? borderWidth : 0.0), top - (!borderIncludedInBounds ? borderWidth : 0.0), right + (!borderIncludedInBounds ? borderWidth : 0.0), bottom + (!borderIncludedInBounds ? borderWidth : 0.0), borderColor);
        RenderUtils.drawRect(left + (borderIncludedInBounds ? borderWidth : 0.0), top + (borderIncludedInBounds ? borderWidth : 0.0), right - (borderIncludedInBounds ? borderWidth : 0.0), bottom - (borderIncludedInBounds ? borderWidth : 0.0), insideColor);
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height, int color) {
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        RenderUtils.glColor(color);
        mc.getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, (float)0.0f, (float)0.0f, (int)width, (int)height, (float)width, (float)height);
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
    }

    public static void enableGL2D() {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static void disableGL2D() {
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height) {
        GL11.glEnable((int)2848);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, (float)0.0f, (float)0.0f, (int)width, (int)height, (float)width, (float)height);
        GL11.glPopMatrix();
    }

    public static void drawCircle3D(Entity entity, double radius, float partialTicks, int points, float width, int color) {
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glDisable((int)2929);
        GL11.glLineWidth((float)width);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2929);
        GL11.glBegin((int)3);
        double x = RenderUtils.interpolate(entity.lastTickPosX, entity.posX, partialTicks) - RenderUtils.mc.getRenderManager().viewerPosX;
        double y = RenderUtils.interpolate(entity.lastTickPosY, entity.posY, partialTicks) - RenderUtils.mc.getRenderManager().viewerPosY;
        double z = RenderUtils.interpolate(entity.lastTickPosZ, entity.posZ, partialTicks) - RenderUtils.mc.getRenderManager().viewerPosZ;
        RenderUtils.glColor(color);
        for (int i = 0; i <= points; ++i) {
            GL11.glVertex3d((double)(x + radius * Math.cos((float)i * ((float)Math.PI * 2) / (float)points)), (double)y, (double)(z + radius * Math.sin((float)i * ((float)Math.PI * 2) / (float)points)));
        }
        GL11.glEnd();
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
    }
}

