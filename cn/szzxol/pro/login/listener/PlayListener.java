package cn.szzxol.pro.login.listener;

import cn.szzxol.pro.login.Login;
import static cn.szzxol.pro.login.messages.Msg.MsgAskForLogin;
import static cn.szzxol.pro.login.messages.Msg.MsgAskForRegister;
import static cn.szzxol.pro.login.utils.Configuration.getConfiguration;
import static cn.szzxol.pro.login.utils.Configuration.saveConfiguration;
import static cn.szzxol.pro.login.utils.Utils.isAllow;
import static cn.szzxol.pro.login.utils.Utils.isLogin;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 *
 * @author I_promise
 */
public class PlayListener implements Listener {

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (!isLogin(event.getPlayer()) && !isAllow(event.getMessage().toLowerCase())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String Name = player.getName();
        File checkfile = new File(Login.instance.getDataFolder() + "/players/", Name + ".yml");
        if (!checkfile.getParentFile().exists()) {
            checkfile.getParentFile().mkdirs();
        }
        if (!checkfile.exists()) {
            try {
                checkfile.createNewFile();
                YamlConfiguration config = getConfiguration("/players/" + Name);
                config.set("Name", Name);
                config.set("isReg", false);
                saveConfiguration(config, "/players/" + Name);
                MsgAskForRegister(player);
            } catch (IOException e) {
            }
        } else {
            MsgAskForLogin(player);
        }
        YamlConfiguration config = getConfiguration("/players/" + Name);
        config.set("isLogin", false);
        saveConfiguration(config, "/players/" + Name);
        Bukkit.broadcastMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("【信号站】").append(ChatColor.WHITE).append("服务器捕捉到新信号...正在接入...").toString());
        event.setJoinMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("【信号站】").append(ChatColor.GOLD).append(Name).append(ChatColor.WHITE).append(" 已接入...").toString());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!isLogin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!isLogin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!isLogin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (!isLogin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!isLogin(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (!isLogin(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (!isLogin(player)) {
                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!isLogin(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        YamlConfiguration config = getConfiguration("/players/" + player.getName());
        config.set("isLogin", false);
        saveConfiguration(config, "/players/" + player.getName());
    }

}
