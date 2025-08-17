package Commands;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.Stats;

import dev.tjxjnoobie.uhcgames.other.Grade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCMD implements CommandExecutor{

	
	
	
	
	  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){

	        Player p = (Player)sender;

	  if (cmd.getName().equalsIgnoreCase("stats")){
	  if (args.length == 0) {
		      String playerusername = p.getName();
		      String playeruuid = p.getUniqueId().toString();
		  sender.sendMessage(Main.prefix + p.getDisplayName() + " §7's §7Records");
		  if(Grade.getPlacementMatches(playeruuid,"UUID") > 0) {
			  sender.sendMessage(Main.prefix + "Grade§8: §7You have §a" + Grade.getPlacementMatches(playeruuid, "UUID") + "§7 placements");
		  }else{
			  sender.sendMessage(Main.prefix+"Grade§8: " +Grade.getGrade(playeruuid,"UUID"));
		  }
		  sender.sendMessage(Main.prefix  + "Games§8:§f (§aWins§8/§cLosses§8/§7Rate§f)§8:§a " + Stats.getWins(playerusername, playeruuid, "UUID") + "§8/§c" + Stats.getLosses(playerusername, playeruuid, "UUID") + "§8/§7" + Stats.getWinRate(playerusername, playeruuid, "UUID") + "%");
		  sender.sendMessage(Main.prefix  + "Bow Shots§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getBowHits(playerusername, playeruuid, "UUID") + "§8/§c" + Stats.getBowMisses(playerusername, playeruuid, "UUID") + "§8/§7" + Stats.getBowAccuracy(playerusername, playeruuid, "UUID") + "%");
		  sender.sendMessage(Main.prefix  + "Swings§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getSwingHits(playerusername, playeruuid, "UUID") + "§8/§c" + Stats.getSwingsMisses(playerusername,playeruuid, "UUID") + "§8/§7" + Stats.getSwingAccuracy(playerusername, playeruuid, "UUID") + "%");
		  sender.sendMessage(Main.prefix  + "Kills§8:§f (§aKills§8/§cDeaths§8/§7KDR§f)§8:§a " + +Stats.getKills(playerusername, playeruuid, "UUID") + "§7/§c" + Stats.getDeaths(playerusername, playeruuid, "UUID") + "§7/§7" + Stats.getKills(playerusername, playeruuid, "UUID"));
			return true;
	  }


		  if (args.length == 1) {
			  Player tp = Bukkit.getPlayer(args[0]);
			  if (tp != null) {
				  String tplayerusername = args[0];
				  String tplayeruuid = tp.getUniqueId().toString();
				  sender.sendMessage(Main.prefix  + tp.getDisplayName() + " §7's §7Records");
				  if(Grade.getPlacementMatches(tplayeruuid,"UUID") > 0) {
					  sender.sendMessage(Main.prefix + "Grade§8: §a This user has " + Grade.getPlacementMatches(tplayeruuid, "UUID") + "§7 placements");
				  }else{
					  sender.sendMessage(Main.prefix+"Grade§8: " +Grade.getGrade(tplayeruuid,"UUID"));
				  }

				  sender.sendMessage(Main.prefix  + "Games§8:§f (§aWins§8/§cLosses§8/§7Rate§f)§8:§a " + Stats.getWins(tplayerusername, tplayeruuid, "UUID") + "§8/§c" + Stats.getLosses(tplayerusername, tplayeruuid, "UUID") + "§8/§7" + Stats.getWinRate(tplayerusername, tplayeruuid, "UUID") + "%");
				  sender.sendMessage(Main.prefix  + "Bow Shots§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getBowHits(tplayerusername, tplayeruuid, "UUID") + "§8/§c" + Stats.getBowMisses(tplayerusername, tplayeruuid, "UUID") + "§8/§7" + Stats.getBowAccuracy(tplayerusername, tplayeruuid, "UUID") + "%");
				  sender.sendMessage(Main.prefix  + "Swings§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getSwingHits(tplayerusername, tplayeruuid, "UUID") + "§8/§c" + Stats.getSwingsMisses(tplayerusername, tplayeruuid, "UUID") + "§8/§7" + Stats.getSwingAccuracy(tplayerusername, tplayeruuid, "UUID") + "%");
				  sender.sendMessage(Main.prefix  + "Kills§8:§f (§aKills§8/§cDeaths§8/§7KDR§f)§8:§a " + +Stats.getKills(tplayerusername, tplayeruuid, "UUID") + "§7/§c" + Stats.getDeaths(tplayerusername, tplayeruuid, "UUID") + "§7/§7" + Stats.getKills(tplayerusername, tplayeruuid, "UUID"));

			  } else if(!Stats.OfflinePlayerExists(args[0])){
				  sender.sendMessage(Main.prefix + ChatColor.RED+"That player has never played §5Sonder UHCGames");
				  return true;
			  } else if(Stats.OfflinePlayerExists(args[0])){
				  sender.sendMessage(Main.prefix + args[0] + " §7's §7Records");
				  if(Grade.getPlacementMatches(args[0],"USERNAME") > 0) {
					  sender.sendMessage(Main.prefix + "Grade§8: §a This user has " + Grade.getPlacementMatches(args[0], "USERNAME") + "§7 placements");
				  }else{
					  sender.sendMessage(Main.prefix+"Grade§8: " +Grade.getGrade(args[0],"USERNAME"));
				  }
				  sender.sendMessage(Main.prefix + "Games§8:§f (§aWins§8/§cLosses§8/§7Rate§f)§8:§a " + Stats.getWins(args[0], args[0], "USERNAME") + "§8/§c" + Stats.getLosses(args[0], args[0], "USERNAME") + "§8/§7" + Stats.getWinRate(args[0], args[0], "USERNAME") + "%");
				  sender.sendMessage(Main.prefix + "Bow Shots§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getBowHits(args[0], args[0], "USERNAME") + "§8/§c" + Stats.getBowMisses(args[0], args[0], "USERNAME") + "§8/§7" + Stats.getBowAccuracy(args[0], args[0], "USERNAME") + "%");
				  sender.sendMessage(Main.prefix + "Swings§8:§f (§aHits§8/§cMisses§8/§7Accuracy§f)§8:§a " + Stats.getSwingHits(args[0], args[0], "USERNAME") + "§8/§c" + Stats.getSwingsMisses(args[0], args[0], "USERNAME") + "§8/§7" + Stats.getSwingAccuracy(args[0], args[0], "USERNAME") + "%");
				  sender.sendMessage(Main.prefix + "Kills§8:§f (§aKills§8/§cDeaths§8/§7KDR§f)§8:§a " + +Stats.getKills(args[0], args[0], "USERNAME") + "§7/§c" + Stats.getDeaths(args[0], args[0], "USERNAME") + "§7/§7" + Stats.getKills(args[0], args[0], "USERNAME"));

			  }
		  }

	  

	  
	  
	return false;
	  
	  }
	return false;
	  
	  
	  }
}


