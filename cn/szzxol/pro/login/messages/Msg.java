package cn.szzxol.pro.login.messages;

import static cn.szzxol.pro.login.utils.Utils.isLogin;
import static cn.szzxol.pro.login.utils.Utils.isReg;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 *
 * @author I_promise
 */
public class Msg {

    public static void MsgNoPermission(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("你没有权限执行这个指令").toString());
    }

    public static void MsgJoinTip(Player player) {
        if (!isLogin(player)) {
            if (isReg(player)) {
                MsgAskForLogin(player);
            } else {
                MsgAskForRegister(player);
            }
        }
    }

    public static void MsgErrorArgs(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("参数错误").toString());
    }

    public static void MsgPlayerNotFound(Player player, String TargetName) {
        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("玩家 ").append(TargetName).append(" 不在线或不存在").toString());
    }

    public static void MsgWrongPassword(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("密码错误").toString());
    }

    public static void MsgNotSamePassword(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("两次密码不一致").toString());
    }

    public static void MsgRepeatLogin(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("你已经登录过了").toString());
    }

    public static void MsgRepeatRegister(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("你已经注册过了").toString());
    }

    public static void MsgAskForRegister(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("输入 ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("/register <密码> <重复密码>").append(ChatColor.AQUA).append(ChatColor.BOLD).append("注册").toString());
    }

    public static void MsgAskForLogin(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("输入 ").append(ChatColor.WHITE).append(ChatColor.BOLD).append("/login <密码>").append(ChatColor.AQUA).append(ChatColor.BOLD).append(" 登录").toString());
    }

    public static void MsgNotRegistered(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("你还没有注册").toString());
    }

    public static void MsgRegSuccessful(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("注册成功").toString());
    }

    public static void MsgLoginSuccessful(Player player) {
        player.sendMessage((new StringBuilder()).append(ChatColor.AQUA).append(ChatColor.BOLD).append("登陆成功").toString());
    }

}
