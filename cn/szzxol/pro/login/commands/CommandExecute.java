package cn.szzxol.pro.login.commands;

import static cn.szzxol.pro.login.commands.CommandLogin.CommandLogin;
import static cn.szzxol.pro.login.commands.CommandRegister.CommandRegister;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 *
 * @author I_promise
 */
public class CommandExecute {

    public static boolean CommandExecute(CommandSender sender, Command cmd, String label, String[] args) {
        String command = cmd.getName().toLowerCase();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (command) {
                case "l":
                case "login":
                    return CommandLogin(player, cmd, label, args);
                case "reg":
                case "register":
                    return CommandRegister(player, cmd, label, args);
                default:
                    return true;
            }
        }
        return true;
    }
}
