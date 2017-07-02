package cn.szzxol.pro.login.commands;

import static cn.szzxol.pro.login.messages.Msg.MsgErrorArgs;
import static cn.szzxol.pro.login.messages.Msg.MsgNotSamePassword;
import static cn.szzxol.pro.login.messages.Msg.MsgRegSuccessful;
import static cn.szzxol.pro.login.messages.Msg.MsgRepeatRegister;
import static cn.szzxol.pro.login.utils.Configuration.getConfiguration;
import static cn.szzxol.pro.login.utils.Configuration.saveConfiguration;
import static cn.szzxol.pro.login.utils.Utils.isReg;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 *
 * @author I_promise
 */
public class CommandRegister {

    public static boolean CommandRegister(Player player, Command cmd, String label, String[] args) {
        if (isReg(player)) {
            MsgRepeatRegister(player);
            return true;
        }
        if (args.length != 2) {
            MsgErrorArgs(player);
            return true;
        }
        if (!args[0].equals(args[1])) {
            MsgNotSamePassword(player);
            return true;
        }
        YamlConfiguration config = getConfiguration("/players/" + player.getName());
        config.set("isReg", true);
        config.set("isLogin", true);
        config.set("password", args[0]);
        saveConfiguration(config, "/players/" + player.getName());
        MsgRegSuccessful(player);
        return true;
    }

}
