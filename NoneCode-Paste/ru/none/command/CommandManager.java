/*
 * Decompiled with CFR 0.150.
 */
package ru.none.command;

import java.util.ArrayList;
import ru.none.command.Command;
import ru.none.command.commands.ClipCommand;
import ru.none.command.commands.MacrosCommand;
import ru.none.command.commands.MusicCommand;
import ru.none.command.commands.TestCommand;

public class CommandManager {
    ArrayList<Command> commands = new ArrayList();

    public CommandManager() {
        this.commands.add(new TestCommand());
        this.commands.add(new MacrosCommand());
        this.commands.add(new ClipCommand());
        this.commands.add(new MusicCommand());
    }

    public void useCommand(String message) {
        message = message.substring(1, message.length());
        String[] bebras = message.split(" ");
        for (String string : bebras) {
        }
        for (Command command : this.commands) {
            if (!bebras[0].contains(command.getName())) continue;
            System.out.println(bebras[0]);
            command.use(bebras);
        }
    }
}

