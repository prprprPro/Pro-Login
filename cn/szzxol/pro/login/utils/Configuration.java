package cn.szzxol.pro.login.utils;

import cn.szzxol.pro.login.Login;
import static cn.szzxol.pro.login.Login.folder;
import java.io.File;
import java.io.IOException;
import static org.bukkit.Bukkit.getLogger;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author I_promise
 */
public class Configuration {

    public static YamlConfiguration getConfiguration(String FileName) {
        File Config = new File(folder, FileName + ".yml");
        return YamlConfiguration.loadConfiguration(Config);
    }

    public static void saveConfiguration(YamlConfiguration YamlConfig, String FileName) {
        File Config = new File(folder, FileName + ".yml");
        try {
            YamlConfig.save(Config);
        } catch (IOException ex) {
        }
    }

    public static void saveDefaultYaml(String FileName) {
        File file = new File(folder, FileName + ".yml");
        if (file.exists()) {
            getLogger().info("[pro-Login] 检测到文件：" + FileName + ".yml");
        } else {
            Login.instance.saveResource(FileName + ".yml", false);
        }
    }
}
