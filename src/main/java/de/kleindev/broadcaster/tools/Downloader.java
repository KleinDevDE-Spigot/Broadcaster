package de.kleindev.broadcaster.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * ===============================
 * Broadcaster
 * Created by KleinDev
 * 2017 (Recoded at 2018)
 * ==============================
 */

public class Downloader {

    public static void downloadUpdate(String url, CommandSender sender) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        String pluginPath = "plugins/Broadcaster-LATEST.jar";
        URLConnection uc;

        if (url != null) {
            try {
                sender.sendMessage("§8[§9Updater§8] §fDownloading Update...");
                URL site = new URL(url);
                uc = site.openConnection();
                uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                uc.addRequestProperty("ServerID", Bukkit.getServerId());
                uc.addRequestProperty("ServerName", Bukkit.getServerName());
                uc.connect();
                bis = new BufferedInputStream(uc.getInputStream());
                fos = new FileOutputStream(pluginPath);

                int count = -1;
                int read;
                while ((read = bis.read()) != -1) {
                    fos.write((byte) read);
                    count++;
                }
                sender.sendMessage("§8[§9Broadcaster§8] §fUpdate downloaded §e(" + count / 1024 + "KB)");
                sender.sendMessage("§8[§9Broadcaster§8] §fPlease Reload the Server to apply changes");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}