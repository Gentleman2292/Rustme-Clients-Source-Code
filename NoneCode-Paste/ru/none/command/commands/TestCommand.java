/*
 * Decompiled with CFR 0.150.
 */
package ru.none.command.commands;

import ru.none.command.Command;
import ru.none.utils.Utils;

public class TestCommand
extends Command {
    public TestCommand() {
        super("test", 2, "test [to] [message]");
    }

    @Override
    public void use(String ... args) {
        super.use(args);
        if (args.length == 3) {
            Utils.clientMessage("You send massage to: " + args[1] + ", and your massege: " + args[2]);
        } else {
            this.syntaxError();
        }
    }
}

