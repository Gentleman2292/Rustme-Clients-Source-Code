/*
 * Decompiled with CFR 0.150.
 */
package ru.none.command.commands;

import ru.none.None;
import ru.none.command.Command;

public class MacrosCommand
extends Command {
    public MacrosCommand() {
        super("macros", 4, "macros [add/remove] [Name] [Command] [KeyBind]");
    }

    @Override
    public void use(String ... args) {
        super.use(args);
        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("add")) {
                if (args.length >= 5) {
                    String g = String.join((CharSequence)" ", args);
                    g = g.substring(g.indexOf(args[4]));
                    None.macrosManager.add(args[2], g, args[3]);
                } else {
                    this.syntaxError();
                }
            }
            if (args[1].equalsIgnoreCase("remove")) {
                if (args.length == 3) {
                    None.macrosManager.remove(args[2]);
                } else {
                    this.syntaxError();
                }
            }
        } else {
            this.syntaxError();
        }
    }
}

