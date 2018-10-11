package de.kleindev.broadcaster;

import de.kleindev.broadcaster.commands.Broadcast;
import de.kleindev.broadcaster.commands.Setup;
import de.kleindev.broadcaster.listener.PlayerJoin;
import de.kleindev.broadcaster.tools.Config;
import de.kleindev.broadcaster.tools.DevTweaks;
import de.kleindev.broadcaster.tools.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ===============================
 * Broadcaster
 * Created by KleinDev
 * 2017 (Recoded at 2018)
 * ==============================
 */
public class Main extends JavaPlugin {
    public static Config config;

    @Override
    public void onEnable() {
        if (DevTweaks.isFirstStart()) {
            //Save config.yml to plugins datafolder
            saveDefaultConfig();

            //Save README.txt to plugins datafolder
            DevTweaks.copyFromResource("README.txt");

            //Update check on start
            UpdateChecker.checkUpdateonStart();
        }
        preSetup();
        config = new Config();
    }

    @Override
    public void onDisable() {

    }

    private void preSetup() {
        //Register commands
        this.getCommand("broadcaster").setExecutor(new Broadcast());
        this.getCommand("bcsetup").setExecutor(new Setup());

        //Register listeners
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
    }


}
