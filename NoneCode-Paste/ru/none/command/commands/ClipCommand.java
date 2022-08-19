/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 */
package ru.none.command.commands;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import ru.none.command.Command;
import ru.none.utils.Utils;

public class ClipCommand
extends Command {
    public ClipCommand() {
        super("clip", 1, "clip [up/down]");
    }

    @Override
    public void use(String ... args) {
        super.use(args);
        if (args.length == 2) {
            int i;
            if (args[1].contains("up") && this.mc.player.hurtTime > 0) {
                for (i = (int)this.mc.player.posY + 1; i < 256; ++i) {
                    if (this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, (double)i, this.mc.player.posZ)).getBlock() != Blocks.AIR || this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, (double)(i + 1), this.mc.player.posZ)).getBlock() != Blocks.AIR || this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, (double)(i - 1), this.mc.player.posZ)).getBlock() == Blocks.AIR) continue;
                    this.mc.player.setPosition(this.mc.player.posX, (double)i, this.mc.player.posZ);
                    return;
                }
            } else {
                Utils.clientMessage("What?");
            }
            if (args[1].contains("down") && this.mc.player.hurtTime > 0) {
                for (i = (int)this.mc.player.posY - 1; i > 0; --i) {
                    if (this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, (double)i, this.mc.player.posZ)).getBlock() != Blocks.AIR || this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, (double)(i + 1), this.mc.player.posZ)).getBlock() != Blocks.AIR || this.mc.world.getBlockState(new BlockPos(this.mc.player.posX, (double)(i - 1), this.mc.player.posZ)).getBlock() == Blocks.AIR) continue;
                    this.mc.player.setPosition(this.mc.player.posX, (double)i, this.mc.player.posZ);
                    return;
                }
            } else {
                Utils.clientMessage("What?");
            }
        } else {
            this.syntaxError();
        }
    }
}

