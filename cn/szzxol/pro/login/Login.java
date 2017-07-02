package cn.szzxol.pro.login;

import cn.szzxol.pro.login.listener.PlayListener;
import static cn.szzxol.pro.login.commands.CommandExecute.CommandExecute;
import static cn.szzxol.pro.login.messages.Msg.MsgJoinTip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

/**
 *
 * @author I_promise
 */
public class Login extends JavaPlugin {

    public static File folder;
    public static FileConfiguration DefaultConfig;
    public static String version;

    @Override
    public void onEnable() {
        getLogger().info("插件正在加载...");
        getServer().getPluginManager().registerEvents(new PlayListener(), this);
        this.saveDefaultConfig();
        folder = this.getDataFolder();
        DefaultConfig = this.getConfig();
        version = DefaultConfig.getString("Version");
        getLogger().info("插件加载完成...");
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> {
            List<Player> AllPlayers = new ArrayList<>();
            AllPlayers.addAll(Bukkit.getServer().getOnlinePlayers());
            for (Player target : AllPlayers) {
                MsgJoinTip(target);
            }
        }, 0L, 3 * 20L);
    }

    @Override
    public void onDisable() {
        getLogger().info("插件卸载完成...");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandExecute(sender, cmd, label, args);
        return true;
    }

    public static Login instance;

    public Login() {
        instance = this;
    }

}
