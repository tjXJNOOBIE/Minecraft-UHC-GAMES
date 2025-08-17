package Commands;

import info.techwizmatt.ServerCore.API.CoinAPI;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.other.Wagers;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by TJ on 1/7/2018.
 */
public class Wager implements CommandExecutor {


    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String arg, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            sender.sendMessage(Main.prefix + "Usage: §c/wager §a<amount>");
            return true;
        }

        if (!Arena.joinable) {
          sender.sendMessage(Main.prefix+"You can only set wagers in lobby.");
          return true;
        }

        int i = Integer.parseInt(args[0]);

        if(i > CoinAPI.GetTokens(player)){
            sender.sendMessage(Main.prefix+"§cYou can't afford this! " + "You only have §a" + CoinAPI.GetTokens(player) + "§c coins§8!");
            return true;

        }
        if (args.length == 1) {
            if (Integer.parseInt(args[0]) < 10) {
                sender.sendMessage(Main.prefix + "§cThe minimum wager is 10 coins§8!");
                return false;
            }
            if (Integer.parseInt(args[0]) <= 0) {
                sender.sendMessage(Main.prefix + "§cCan't set wagers below 0§8!");
                return false;
            }
            if(i>= Wagers.getMaxPot()){
                int needed = Wagers.getMaxPot() - Wagers.getCurrentpot();
                sender.sendMessage(Main.prefix+"Please select a lower amount!" + "Pot is §a" + Wagers.getCurrentpot() + "§8/§a"+Wagers.getMaxPot());
                sender.sendMessage(Main.prefix+"The pot has room for §a" + needed +"§7 more coins");
                return true;
            }
            if(Wagers.getCurrentpot() >= Wagers.getMaxPot()){
                sender.sendMessage(Main.prefix+"§cThe pot is full§8!");
                return true;
            }
            int Added = i + Wagers.getCurrentpot()-1;
            if(Added >= Wagers.getMaxPot()){
                int needed = Wagers.getMaxPot() - Wagers.getCurrentpot();
                sender.sendMessage(Main.prefix+"Please select a lower amount!" + "Pot is §a" + Wagers.getCurrentpot() + "§8/§a"+Wagers.getMaxPot());
                sender.sendMessage(Main.prefix+"The pot has room for §a" + needed +"§7 more coins");
                return true;
            }
            Wagers.addWager(i,player);
        }else{
            sender.sendMessage(Main.prefix+"§cToo many arguments!");

        }
        return false;
    }
}
