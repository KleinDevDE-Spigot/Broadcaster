package de.kleindev.broadcaster.tools;

import de.kleindev.broadcaster.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * ===============================
 * Broadcaster
 * Created by KleinDev
 * 2017 (Recoded at 2018)
 * ==============================
 */
public class UpdateChecker {
    private static String prefix = "§8[§3Broadcaster§8] ";
    private static int resource = 45524;
    private static String newVersion = "";

    public static void checkUpdate(Player p) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.getOutputStream()
                    .write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + resource)
                            .getBytes(StandardCharsets.UTF_8));
            newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (!newVersion.equalsIgnoreCase(Main.getPlugin(Main.class).getDescription().getVersion())) {
                if ((Main.getPlugin(Main.class).getConfig().getBoolean("NotifyUpdate")) &&
                        (p.hasPermission(Main.getPlugin(Main.class).getConfig().getString("PermissionNotifyUpdate")))) {
                    p.sendMessage(Main.getPlugin(Main.class).getConfig().getString("MessageNotifyUpdate").replaceAll("&", "$").replaceAll("%version%", newVersion));
                }
            } else {
                p.sendMessage(prefix + " §fYou're running the newest plugin version!");
            }
        } catch (Exception ex) {
            p.sendMessage(prefix + " §cFailed to check for updates on spigotmc.org");
        }
    }

    public static void checkUpdateonStart() {
        System.out.println(prefix + "Checking for updates...");
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.getOutputStream()
                    .write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + resource)
                            .getBytes(StandardCharsets.UTF_8));
            newVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (!newVersion.equalsIgnoreCase(Main.getPlugin(Main.class).getDescription().getVersion())) {
                System.out.print("[Broadcaster] A new Update is available! Version " + newVersion);
            } else {
                System.out.println("[Broadcaster] You're running the newest plugin version!");
            }
        } catch (Exception ex) {
            System.err.println("[Broadcaster] " + ChatColor.RED + "Failed to check for updates on spigotmc.org");
        }
    }
}
