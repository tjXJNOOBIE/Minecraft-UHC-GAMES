package Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by TJ on 9/27/2017.
 */
public class Speed implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

        if ((cmd.getName().equalsIgnoreCase("speed")) && (sender.isOp()) && (args.length > 0)) {
            float speed = Float.valueOf(args[0]).floatValue() / 10.0F;
            Player player = (Player) sender;
            player.setFlySpeed(speed);
            player.sendMessage(ChatColor.GREEN + "You've set your fly speed to " + speed + "!");
        }
        return false;
    }
}
