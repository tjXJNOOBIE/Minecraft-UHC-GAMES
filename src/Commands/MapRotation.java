package Commands;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Voting;
import dev.tjxjnoobie.uhcgames.other.MapList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * Created by TJ on 3/27/2017.
 */
public class MapRotation implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){
        Player player = (Player)sender;
        int i = 10;
        Random rand = new Random();
        rand.nextInt(i);
        if(sender.hasPermission("blimp.admin")){
            if(args.length == 0) {
              sender.sendMessage(Main.prefix +ChatColor.RED+"Too few arguments!");
            }
        }
        if(args.length > 0) {
            if (Integer.parseInt(args[0]) <= 0) {
                sender.sendMessage(Main.prefix + "To select a map rotation the integer must be above 0!");
                return false;
            }
            if (Integer.parseInt(args[0]) > 10) {
                sender.sendMessage(Main.prefix + ChatColor.RED+"Please select 1-10");
                return false;
            }
            if (Integer.parseInt(args[0]) <= 10) {
                Main.maplist = Integer.parseInt(args[0]);
                Bukkit.broadcastMessage(Main.prefix + "Map rotation was forcefully set to " + Integer.parseInt(args[0]) + " by " + ((Player) sender).getDisplayName());
                Voting.i1=0;
                Voting.i2=0;
                Voting.i3=0;
                Voting.i4=0;
                Voting.i5=0;
                return false;
            }
            if (args[0].equalsIgnoreCase("random")) {
                MapList.selectRandomMapList();
                Bukkit.broadcastMessage(Main.prefix + "Map rotation was changed by random to " + Main.maplist + "" + "by " + ((Player) sender).getDisplayName());
                Voting.i1=0;
                Voting.i2=0;
                Voting.i3=0;
                Voting.i4=0;
                Voting.i5=0;
                return false;
            }
        }

        return false;
    }

    }
