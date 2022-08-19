/*
 * Decompiled with CFR 0.150.
 */
package ru.none.module;

import java.util.ArrayList;
import ru.none.module.Category;
import ru.none.module.Module;
import ru.none.module.module.Combat.AntiBot;
import ru.none.module.module.Combat.AutoGapple;
import ru.none.module.module.Combat.AutoTotem;
import ru.none.module.module.Combat.KillAura;
import ru.none.module.module.Combat.Velocity;
import ru.none.module.module.Hud.Armor;
import ru.none.module.module.Hud.ClickGui;
import ru.none.module.module.Hud.ClickMusicGui;
import ru.none.module.module.Hud.Client;
import ru.none.module.module.Hud.GappelHud;
import ru.none.module.module.Hud.HudEditor;
import ru.none.module.module.Hud.Keystrokes;
import ru.none.module.module.Hud.ModuleList;
import ru.none.module.module.Hud.PlayerHud;
import ru.none.module.module.Hud.Potion;
import ru.none.module.module.Hud.TargetHud;
import ru.none.module.module.Hud.TimerHud;
import ru.none.module.module.Hud.TotemPopCounter;
import ru.none.module.module.Hud.WaterMark;
import ru.none.module.module.Misc.AutoArmor;
import ru.none.module.module.Misc.ChestStealer;
import ru.none.module.module.Misc.Disabler;
import ru.none.module.module.Misc.FastPlace;
import ru.none.module.module.Misc.HClip;
import ru.none.module.module.Misc.MCF;
import ru.none.module.module.Misc.MiddleClickPearl;
import ru.none.module.module.Misc.NoClip;
import ru.none.module.module.Misc.NoPush;
import ru.none.module.module.Misc.Nofall;
import ru.none.module.module.Misc.SafeWalk;
import ru.none.module.module.Move.AirJump;
import ru.none.module.module.Move.AutoSprint;
import ru.none.module.module.Move.Flight;
import ru.none.module.module.Move.GuiWalk;
import ru.none.module.module.Move.Jesus;
import ru.none.module.module.Move.LongJump;
import ru.none.module.module.Move.NoSlowDown;
import ru.none.module.module.Move.Speed;
import ru.none.module.module.Move.Spider;
import ru.none.module.module.Move.Strafe;
import ru.none.module.module.Move.TargetStrafe;
import ru.none.module.module.Player.Timer;
import ru.none.module.module.Render.AnimationAttack;
import ru.none.module.module.Render.ChinaHat;
import ru.none.module.module.Render.ESP;
import ru.none.module.module.Render.FakePlayer;
import ru.none.module.module.Render.FullBright;
import ru.none.module.module.Render.JumpCircles;
import ru.none.module.module.Render.NameProtect;
import ru.none.module.module.Render.NameTags;
import ru.none.module.module.Render.NoHurtCam;
import ru.none.module.module.Render.Particalse;
import ru.none.module.module.Render.Tracers;
import ru.none.module.module.Render.Trails;
import ru.none.module.module.Render.WallHack;

public class ModuleManager {
    public ArrayList<Module> modules = new ArrayList();

    public ModuleManager() {
        this.modules.add(new JumpCircles());
        this.modules.add(new ChinaHat());
        this.modules.add(new WaterMark());
        this.modules.add(new ClickGui());
        this.modules.add(new AntiBot());
        this.modules.add(new AutoGapple());
        this.modules.add(new ModuleList());
        this.modules.add(new GuiWalk());
        this.modules.add(new AutoSprint());
        this.modules.add(new Tracers());
        this.modules.add(new NameTags());
        this.modules.add(new ESP());
        this.modules.add(new KillAura());
        this.modules.add(new TargetStrafe());
        this.modules.add(new NoHurtCam());
        this.modules.add(new TargetHud());
        this.modules.add(new Velocity());
        this.modules.add(new AutoTotem());
        this.modules.add(new Timer());
        this.modules.add(new MCF());
        this.modules.add(new Jesus());
        this.modules.add(new Spider());
        this.modules.add(new LongJump());
        this.modules.add(new ChestStealer());
        this.modules.add(new AutoArmor());
        this.modules.add(new SafeWalk());
        this.modules.add(new FastPlace());
        this.modules.add(new Flight());
        this.modules.add(new Potion());
        this.modules.add(new NoClip());
        this.modules.add(new TotemPopCounter());
        this.modules.add(new Speed());
        this.modules.add(new Nofall());
        this.modules.add(new Disabler());
        this.modules.add(new Strafe());
        this.modules.add(new NoSlowDown());
        this.modules.add(new Client());
        this.modules.add(new TimerHud());
        this.modules.add(new GappelHud());
        this.modules.add(new Armor());
        this.modules.add(new NoPush());
        this.modules.add(new NameProtect());
        this.modules.add(new Trails());
        this.modules.add(new FakePlayer());
        this.modules.add(new HClip());
        this.modules.add(new Particalse());
        this.modules.add(new ClickMusicGui());
        this.modules.add(new FullBright());
        this.modules.add(new HudEditor());
        this.modules.add(new Keystrokes());
        this.modules.add(new AnimationAttack());
        this.modules.add(new AirJump());
        this.modules.add(new MiddleClickPearl());
        this.modules.add(new PlayerHud());
        this.modules.add(new WallHack());
    }

    public ArrayList<Module> getModules(Category category) {
        ArrayList<Module> returns = new ArrayList<Module>();
        for (Module module : this.modules) {
            if (module.getCategory() != category) continue;
            returns.add(module);
        }
        return returns;
    }

    public Module getModule(String name) {
        for (Module module : this.modules) {
            if (module.getName() != name) continue;
            return module;
        }
        return null;
    }
}

