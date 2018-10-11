package de.kleindev.broadcaster.commands;

import de.kleindev.broadcaster.Main;
import de.kleindev.broadcaster.tools.Downloader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * ===============================
 * Broadcaster
 * Created by KleinDev
 * 2017 (Recoded at 2018)
 * ==============================
 */

public class Setup implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission(Main.config.getPermission("Reload"))
                    || sender.hasPermission(Main.config.getPermission("Update"))) {
                sender.sendMessage(Main.config.getMessage("Usage", true));
            } else sender.sendMessage(Main.config.getMessage("NoPermission", true));
            return true;
        } else if (args.length == 1) {
            switch (args[0]) {
                case "reload":
                    if (sender.hasPermission(Main.config.getPermission("Reload"))) {
                        Main.config.reload();
                        sender.sendMessage(Main.config.getMessage("ReloadCompleted", true));
                    } else sender.sendMessage(Main.config.getMessage("NoPermission", true));
                    return true;
                case "update":
                    if (sender.hasPermission(Main.config.getPermission("Update"))) {
                        Downloader.downloadUpdate("https://files.kleindev.de/spigot/public/broadcaster/Broadcaster.jar", sender);
                    } else sender.sendMessage(Main.config.getMessage("NoPermission", true));
                    return true;
                case "help":
                    if (sender.hasPermission(Main.config.getPermission("Reload"))
                            || sender.hasPermission(Main.config.getPermission("Update"))) {
                        sender.sendMessage(Main.config.getMessage("Usage", true));
                    } else sender.sendMessage(Main.config.getMessage("NoPermission", true));
                    return true;
                default:
                    break;
            }
        }
        return false;
    }
}
