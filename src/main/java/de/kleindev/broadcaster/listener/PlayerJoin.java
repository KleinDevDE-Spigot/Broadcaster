package de.kleindev.broadcaster.listener;

import de.kleindev.broadcaster.Main;
import de.kleindev.broadcaster.tools.UpdateChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * ===============================
 * Broadcaster
 * Created by KleinDev
 * 2017 (Recoded at 2018)
 * ==============================
 */

public class PlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (Boolean.getBoolean(Main.config.getConfiguration("NotifyUpdate"))) {
            if (e.getPlayer().hasPermission(Main.config.getPermission("NotifyUpdate")))
                UpdateChecker.checkUpdate(e.getPlayer());
        }
    }
}
