package de.kleindev.broadcaster.tools;

import de.kleindev.broadcaster.Main;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {
    private HashMap<String, String> messages = getMessages();
    private HashMap<String, String> permissions = getPermissions();
    private HashMap<String, String> configuration = getConfiguration();
    private HashMap<String, String> timeFormats = getSimpleDateFormat();

    private HashMap<String, String> getMessages() {
        HashMap<String, String> a = new HashMap<>();
        for (String s : Main.getPlugin(Main.class).getConfig().getConfigurationSection("Messages").getKeys(false)) {
            a.put(s, Main.getPlugin(Main.class).getConfig().getString("Messages." + s));
        }
        return a;
    }

    private HashMap<String, String> getPermissions() {
        HashMap<String, String> a = new HashMap<>();
        for (String s : Main.getPlugin(Main.class).getConfig().getConfigurationSection("Permissions").getKeys(false)) {
            a.put(s, Main.getPlugin(Main.class).getConfig().getString("Permissions." + s));
        }
        return a;
    }

    private HashMap<String, String> getConfiguration() {
        HashMap<String, String> a = new HashMap<>();
        for (String s : Main.getPlugin(Main.class).getConfig().getConfigurationSection("Configuration").getKeys(false)) {
            a.put(s, Main.getPlugin(Main.class).getConfig().getString("Configuration." + s));
        }
        return a;
    }

    private HashMap<String, String> getSimpleDateFormat() {
        HashMap<String, String> a = new HashMap<>();
        Pattern p = Pattern.compile("%time_[^%]+%");
        Matcher m = p.matcher(configuration.get("Format"));
        while (m.find())
            a.put(m.group(), m.group().substring("%time_".length(), m.group().length() - 1));
        return a;
    }

    public HashMap<String, String> getTimeFormats() {
        return timeFormats;
    }

    public String getMessage(String key, boolean withColor) {
        if (withColor)
            return ChatColor.translateAlternateColorCodes('&', messages.get(key));
        else return messages.get(key);
    }

    public Permission getPermission(String key) {
        return new Permission(permissions.get(key));
    }

    public String getConfiguration(String key) {
        return configuration.get(key);
    }

    public void reload() {
        Main.getPlugin(Main.class).reloadConfig();
        messages = getMessages();
        permissions = getPermissions();
        configuration = getConfiguration();
    }
}
