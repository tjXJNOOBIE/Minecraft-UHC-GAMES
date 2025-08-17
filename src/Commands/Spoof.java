package Commands;

import info.techwizmatt.ServerCore.Rank.Rank;
import dev.tjxjnoobie.uhcgames.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by TJ on 1/7/2018.
 */
public class Spoof implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if(Rank.getRankLevel((Player)sender) == 11){
            Main.getInstance().arenas.get(0).ingame.add((Player) sender);
            sender.sendMessage("spoofed a player for testing");
        }
        return false;
    }
}
