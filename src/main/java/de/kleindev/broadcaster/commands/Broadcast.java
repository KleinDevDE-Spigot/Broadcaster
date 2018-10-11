package de.kleindev.broadcaster.commands;

import de.kleindev.broadcaster.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ===============================
 * Broadcaster
 * Created by KleinDev
 * 2017 (Recoded at 2018)
 * ==============================
 */

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission(Main.config.getPermission(""))) {
            if (args.length == 0) {
                sender.sendMessage(Main.config.getMessage("Usage", true));
            } else {
                StringBuilder sentence = new StringBuilder();
                for (String s : args) {
                    if (sentence.toString().equals(""))
                        sentence.append(s);
                    else sentence.append(" ").append(s);
                }

                String format = Main.config.getConfiguration("Format");
                for (String s : Main.config.getTimeFormats().keySet()) {
                    format = format.replaceAll(s, new SimpleDateFormat(Main.config.getTimeFormats().get(s)).format(new Date()));
                }
                format = format.replaceAll("%message%", sentence.toString());
                format = format.replaceAll("%sender%", sender.getName());

                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', format));
                }
            }
        } else sender.sendMessage(Main.config.getMessage("NoPermission", true));
        return false;
    }
}
