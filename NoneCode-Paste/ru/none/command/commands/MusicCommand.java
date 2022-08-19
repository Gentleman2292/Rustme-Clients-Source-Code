/*
 * Decompiled with CFR 0.150.
 */
package ru.none.command.commands;

import ru.none.command.Command;
import ru.none.music.Player;
import ru.none.music.WebUtils;
import ru.none.utils.Utils;

public class MusicCommand
extends Command {
    public MusicCommand() {
        super("music", 2, "music [play/stop/vol/pause/resume/searh] [val]");
    }

    @Override
    public void use(String ... args) {
        super.use(args);
        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("play") && args.length == 3) {
                Player.play(args[2]);
                Utils.clientMessage("Start playing");
                return;
            }
            if (args[1].equalsIgnoreCase("vol") && args.length == 3) {
                Player.player.setVolume(Integer.parseInt(args[2]));
                return;
            }
            if (args[1].equalsIgnoreCase("search") && args.length >= 3) {
                String g = String.join((CharSequence)"+", args);
                g = g.substring(g.indexOf(args[2]), g.length());
                System.out.println("https://ru.hitmotop.com/search?q=" + g);
                String ret = WebUtils.visitSite("https://ru.hitmotop.com/search?q=" + g);
                String[] rets = ret.split("data-musmeta");
                boolean first = true;
                for (String st : rets) {
                    if (!first) {
                        String name = st.substring(st.indexOf("title") + 8, st.indexOf("url") - 3);
                        String url = st.substring(st.indexOf("url") + 6, st.indexOf("img") - 3);
                        url = url.replaceAll("\\\\", "");
                        System.out.println(name + "  " + url);
                    }
                    first = false;
                }
                return;
            }
            if (args[1].equalsIgnoreCase("stop")) {
                Player.stop();
                return;
            }
            if (args[1].equalsIgnoreCase("pause")) {
                Player.pause();
                return;
            }
            if (args[1].equalsIgnoreCase("resume")) {
                Player.resume();
                return;
            }
        }
        this.syntaxError();
    }
}

