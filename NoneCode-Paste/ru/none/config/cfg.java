/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.google.gson.JsonPrimitive
 *  net.minecraft.client.Minecraft
 */
package ru.none.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import ru.none.None;
import ru.none.module.Module;
import ru.none.module.setting.Setting;
import ru.none.module.setting.settings.BooleanSetting;
import ru.none.module.setting.settings.FloatSetting;
import ru.none.module.setting.settings.ModeSetting;

public class cfg {
    public static File file;
    public static final File CLIENT_FOLDER;
    public static final File CONFIG_FOLDER;

    public static void init() {
        CLIENT_FOLDER.mkdir();
        CONFIG_FOLDER.mkdir();
    }

    public static void save() {
        JsonObject presetJson = new JsonObject();
        JsonArray moduleArray = new JsonArray();
        for (Module module : None.moduleManager.modules) {
            JsonObject moduleObject = new JsonObject();
            moduleObject.addProperty("name", module.getName());
            moduleObject.addProperty("enabled", Boolean.valueOf(module.isEnable()));
            moduleObject.addProperty("keybind", (Number)module.getKey());
            moduleObject.addProperty("PosX", (Number)module.getPosX());
            moduleObject.addProperty("PosY", (Number)module.getPosY());
            JsonArray settingsArray = new JsonArray();
            for (Setting setting : None.settingManager.getSettings(module)) {
                JsonObject settingObject = new JsonObject();
                if (setting instanceof BooleanSetting) {
                    settingObject.addProperty(setting.getName(), ((BooleanSetting)setting).getVal());
                } else if (setting instanceof ModeSetting) {
                    settingObject.addProperty(setting.getName(), ((ModeSetting)setting).getVal());
                } else if (setting instanceof FloatSetting) {
                    settingObject.addProperty(setting.getName(), (Number)Float.valueOf(((FloatSetting)setting).getVal()));
                }
                settingsArray.add((JsonElement)settingObject);
            }
            moduleObject.add("settings", (JsonElement)settingsArray);
            moduleArray.add((JsonElement)moduleObject);
        }
        presetJson.add("modules", (JsonElement)moduleArray);
        try {
            FileWriter writer = new FileWriter(new File(CONFIG_FOLDER, "default.json"));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson((JsonElement)presetJson));
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        JsonParser parser = new JsonParser();
        try {
            JsonObject object = parser.parse((Reader)new FileReader(new File(CONFIG_FOLDER, "default.json"))).getAsJsonObject();
            JsonElement autoSave = object.get("auto save");
            JsonArray moduleArray = object.getAsJsonArray("modules");
            for (JsonElement element : moduleArray) {
                if (!(element instanceof JsonObject)) continue;
                JsonObject moduleObject = (JsonObject)element;
                Set moduleSet = moduleObject.entrySet();
                for (Module module : None.moduleManager.modules) {
                    if (!cfg.contains(moduleSet, "name", (JsonElement)new JsonPrimitive(module.getName()))) continue;
                    for (Map.Entry value : moduleSet) {
                        String key2 = (String)value.getKey();
                        JsonElement val = (JsonElement)value.getValue();
                        try {
                            if (key2.equals("enabled")) {
                                module.setEnable(val.getAsBoolean());
                                continue;
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (key2.equals("keybind")) {
                            module.setKey(val.getAsInt());
                            continue;
                        }
                        if (key2.equals("PosX")) {
                            module.setPosX(val.getAsInt());
                            continue;
                        }
                        if (key2.equals("PosY")) {
                            module.setPosY(val.getAsInt());
                            continue;
                        }
                        if (!key2.equals("settings")) continue;
                        for (JsonElement element1 : val.getAsJsonArray()) {
                            if (!(element1 instanceof JsonObject)) continue;
                            JsonObject settingObject = (JsonObject)element1;
                            Set settingSet = settingObject.entrySet();
                            for (Setting setting : None.settingManager.getSettings(module)) {
                                for (Map.Entry value1 : settingSet) {
                                    String settingKey = (String)value1.getKey();
                                    JsonElement settingVal = (JsonElement)value1.getValue();
                                    if (setting instanceof BooleanSetting) {
                                        if (!settingKey.equals(setting.getName())) continue;
                                        ((BooleanSetting)setting).setVal(settingVal.getAsBoolean());
                                        continue;
                                    }
                                    if (setting instanceof ModeSetting) {
                                        if (!settingKey.equals(setting.getName())) continue;
                                        ((ModeSetting)setting).setVal(settingVal.getAsString());
                                        continue;
                                    }
                                    if (!(setting instanceof FloatSetting) || !settingKey.equals(setting.getName())) continue;
                                    ((FloatSetting)setting).setVal(settingVal.getAsFloat());
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean contains(Set<Map.Entry<String, JsonElement>> set, String key2, JsonElement value) {
        for (Map.Entry<String, JsonElement> entry : set) {
            if (!entry.getKey().equals(key2) || !entry.getValue().equals((Object)value)) continue;
            return true;
        }
        return false;
    }

    static {
        CLIENT_FOLDER = new File(Minecraft.getMinecraft().mcDataDir, "None Code");
        CONFIG_FOLDER = new File(CLIENT_FOLDER, "config");
    }
}

