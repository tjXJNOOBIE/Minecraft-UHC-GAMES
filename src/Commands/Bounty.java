package Commands;

import info.techwizmatt.ServerCore.API.CoinAPI;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.managers.Bounties;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bounty implements CommandExecutor{
	
	
	  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){
		Player player = (Player)sender;
		if(Main.getInstance().arenas.get(0).ingame.contains(player)){
			sender.sendMessage(Main.prefix+"§cYou must be dead to set bounties on players.");
		}
	  if ((cmd.getName().equalsIgnoreCase("bounty")) && 
		      (args.length > 1) && 
		      (Bukkit.getPlayer(args[0]) != null)) {
		      for (Arena arena : Main.getInstance().arenas) {
		        if ((arena.ingame.contains(Bukkit.getPlayer(args[0]))) && 
		          (arena.watching.contains(sender)))
		        {
		          int i = Integer.parseInt(args[1]);
		          if(i <= 0) {
		          	sender.sendMessage(
		                      Main.prefix + ChatColor.GREEN + "You can't bounty below 0!");
		          	return true;
		          }
		          if(i <= -1) {
		            	sender.sendMessage(
		                        Main.prefix + ChatColor.GREEN + "You can't bounty below 0!");
		            	return true;
		            }
		          if (CoinAPI.GetTokens(player) >= i){
		            CoinAPI.subtractTokens(player,i);
		            Bounties.setBounty(Bukkit.getPlayer(args[0]), i, player);
		            sender.sendMessage(Main.prefix + ChatColor.GREEN +
		              "You have set a bounty on: " + ChatColor.DARK_GREEN + 
		              Bukkit.getPlayer(args[0]).getDisplayName() + ChatColor.GRAY + "!");
		            return true;
		      
		          }
		          if (CoinAPI.GetTokens(player) > i){
		        	  sender.sendMessage(Main.prefix +ChatColor.RED+"You can't afford this");
		        	  return true;
		          }
		          if(Arena.joinable == true) {
		        	  sender.sendMessage(Main.prefix + ChatColor.RED+"You may not bounty at this time.");
		        	  return true;
		          }
		          if(Bukkit.getPlayer(args[0]) == null){
		          	sender.sendMessage(Main.prefix + ChatColor.RED + "User is not online.");
				  }
		        }
		      }
		    }
	return false;
	  }

}
