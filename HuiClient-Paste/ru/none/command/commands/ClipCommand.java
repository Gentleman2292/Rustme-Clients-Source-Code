/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 */
package ru.none.command.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import ru.none.command.Command;
import ru.none.utils.Utils;

public class ClipCommand
extends Command {
    public ClipCommand() {
        super("clip", 1, "clip [up/down]");
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void use(String ... args) {
        int i;
        block13: {
            block12: {
                block11: {
                    block10: {
                        block9: {
                            super.use(args);
                            if (args.length != 2) {
                                this.syntaxError();
                                return;
                            }
                            if (!args[1].contains("up")) break block9;
                            if (Minecraft.player.hurtTime <= 0) break block9;
                            break block10;
                        }
                        Utils.clientMessage("What?");
                        break block11;
                    }
                    for (i = (int)Minecraft.player.posY + 1; i < 256; ++i) {
                        if (this.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, (double)i, Minecraft.player.posZ)).getBlock() != Blocks.AIR) continue;
                        if (this.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, (double)(i + 1), Minecraft.player.posZ)).getBlock() != Blocks.AIR) continue;
                        if (this.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, (double)(i - 1), Minecraft.player.posZ)).getBlock() == Blocks.AIR) continue;
                        Minecraft.player.setPosition(Minecraft.player.posX, (double)i, Minecraft.player.posZ);
                        return;
                    }
                }
                if (!args[1].contains("down")) break block12;
                if (Minecraft.player.hurtTime > 0) break block13;
            }
            Utils.clientMessage("What?");
            return;
        }
        i = (int)Minecraft.player.posY - 1;
        while (i > 0) {
            if (this.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, (double)i, Minecraft.player.posZ)).getBlock() == Blocks.AIR) {
                if (this.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, (double)(i + 1), Minecraft.player.posZ)).getBlock() == Blocks.AIR) {
                    if (this.mc.world.getBlockState(new BlockPos(Minecraft.player.posX, (double)(i - 1), Minecraft.player.posZ)).getBlock() != Blocks.AIR) {
                        Minecraft.player.setPosition(Minecraft.player.posX, (double)i, Minecraft.player.posZ);
                        return;
                    }
                }
            }
            --i;
        }
    }
}

