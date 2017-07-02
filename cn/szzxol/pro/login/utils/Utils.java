package cn.szzxol.pro.login.utils;

import static cn.szzxol.pro.login.utils.Configuration.getConfiguration;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import org.bukkit.entity.Player;

/**
 *
 * @author I_promise
 */
public class Utils {

    public static boolean isLogin(Player player) {
        return getConfiguration("/players/" + player.getName()).getBoolean("isLogin");
    }
    
    public static boolean isReg(Player player) {
        return getConfiguration("/players/" + player.getName()).getBoolean("isReg");
    }

    public static List<String> AllAllowedCommandBeforeLogin = new ArrayList<>(asList("l", "login", "reg", "register"));

    public static boolean isAllow(String cmd) {
        return AllAllowedCommandBeforeLogin.stream().anyMatch((command) -> (cmd.startsWith("/" + command + " ")));
    }
    
    public static boolean isRightPassword(Player player,String password){
        return (password.equals(getConfiguration("/players/" + player.getName()).getString("password")));
    }
}
