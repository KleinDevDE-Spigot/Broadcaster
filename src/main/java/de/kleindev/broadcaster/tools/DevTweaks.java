package de.kleindev.broadcaster.tools;

import de.kleindev.broadcaster.Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ===============================
 * Broadcaster
 * Created by KleinDev
 * 2017 (Recoded at 2018)
 * ==============================
 */

public class DevTweaks {

    public static void copyFromResource(String File) {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        try {
            String pluginPath = Main.getPlugin(Main.class).getDataFolder() + java.io.File.separator + File;
            bis = new BufferedInputStream(Main.getPlugin(Main.class).getResource(File));
            fos = new FileOutputStream(pluginPath);

            int count = -1;
            int read;
            while ((read = bis.read()) != -1) {
                fos.write((byte) read);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException ignored) {
            }
        }

        try {
            bis.close();
        } catch (IOException ignored) {
        }
    }

    public static boolean isFirstStart() {
        return new File(Main.getPlugin(Main.class).getDataFolder().getAbsolutePath() + "/config.yml").exists();
    }
}
