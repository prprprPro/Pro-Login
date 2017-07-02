package cn.szzxol.pro.login.commands;

import static cn.szzxol.pro.login.messages.Msg.MsgErrorArgs;
import static cn.szzxol.pro.login.messages.Msg.MsgLoginSuccessful;
import static cn.szzxol.pro.login.messages.Msg.MsgNotRegistered;
import static cn.szzxol.pro.login.messages.Msg.MsgRepeatLogin;
import static cn.szzxol.pro.login.messages.Msg.MsgWrongPassword;
import static cn.szzxol.pro.login.utils.Configuration.getConfiguration;
import static cn.szzxol.pro.login.utils.Configuration.saveConfiguration;
import static cn.szzxol.pro.login.utils.Utils.isLogin;
import static cn.szzxol.pro.login.utils.Utils.isReg;
import static cn.szzxol.pro.login.utils.Utils.isRightPassword;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author I_promise
 */
public class CommandLogin {

    public static boolean CommandLogin(Player player, Command cmd, String label, String[] args) {
        if (!isReg(player)) {
            MsgNotRegistered(player);
            return true;
        }
        if (isLogin(player)) {
            MsgRepeatLogin(player);
            return true;
        }
        if (args.length != 1) {
            MsgErrorArgs(player);
            return true;
        }
        if (isRightPassword(player, args[0])) {
            YamlConfiguration config = getConfiguration("/players/" + player.getName());
            config.set("isLogin", true);
            saveConfiguration(config, "/players/" + player.getName());
            MsgLoginSuccessful(player);
            return true;
        } else {
            MsgWrongPassword(player);
            return true;
        }
    }

}
