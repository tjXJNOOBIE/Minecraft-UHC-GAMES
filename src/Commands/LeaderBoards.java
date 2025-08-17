package Commands;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.Stats;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LeaderBoards implements CommandExecutor{

    public static HashMap<Integer, String> wins = new HashMap();
	public static HashMap<Integer, String> winrate = new HashMap();
	public static HashMap<Integer, String> kills = new HashMap();
	public static HashMap<String, Integer> bowaccuracy = new HashMap();



	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){
		Player player = (Player) sender;
		if(args.length == 0){
			sender.sendMessage(Main.prefix+ ChatColor.RED+"You must select wins/winrate/kills");
			return true;
		}
		if(args.length==1){
			if(args[0].equalsIgnoreCase("wins")){
				sender.sendMessage(ChatColor.DARK_AQUA+ "-----------------------------------------------------");
				sender.sendMessage("§6Now viewing§7: §7§lSurvival Games LeaderBoards §7(§5Wins§7)");
				sender.sendMessage(ChatColor.DARK_AQUA+ "-----------------------------------------------------");

				sender.sendMessage("§a#1 §7▏ §9"+wins.get(0) + " §8- §a" + Stats.getWins(wins.get(0),wins.get(0),"USERNAME") + " Wins");
				sender.sendMessage("§e#2 §7▏  §9"+wins.get(1) + " §8- §a " + Stats.getWins(wins.get(1),wins.get(1),"USERNAME") + " Wins");
				sender.sendMessage("§c#3  §7▏ §9"+wins.get(2) +  " §8- §a" + Stats.getWins(wins.get(2),wins.get(2),"USERNAME") + " Wins");
				sender.sendMessage("§c#4  §7▏ §9"+wins.get(3) +  " §8- §a" + Stats.getWins(wins.get(3),wins.get(3),"USERNAME") + " Wins");
				sender.sendMessage("§c#5  §7▏ §9"+wins.get(4) +  " §8- §a" + Stats.getWins(wins.get(4),wins.get(4),"USERNAME") + " Wins");
				sender.sendMessage("§c#6  §7▏ §9"+wins.get(5) +  " §8- §a" + Stats.getWins(wins.get(5),wins.get(5),"USERNAME") + " Wins");
				sender.sendMessage("§c#7  §7▏ §9"+wins.get(6) +  " §8- §a" + Stats.getWins(wins.get(6),wins.get(6),"USERNAME") + " Wins");
				sender.sendMessage("§c#8  §7▏ §9"+wins.get(7) +  " §8- §a" + Stats.getWins(wins.get(7),wins.get(7),"USERNAME") + " Wins");
				sender.sendMessage("§c#9  §7▏ §9"+wins.get(8) +  " §8- §a" + Stats.getWins(wins.get(8),wins.get(8),"USERNAME") + " Wins");
				sender.sendMessage("§4#10  §7▏ §9"+wins.get(9) +  " §8- §a" + Stats.getWins(wins.get(9),wins.get(9),"USERNAME") + " Wins");

			}
			if(args[0].equalsIgnoreCase("kills")){
				sender.sendMessage(ChatColor.DARK_AQUA+ "-----------------------------------------------------");
				sender.sendMessage("§6Now viewing§7: §7§lSurvival Games LeaderBoards §7(§5Kills§7)");
				sender.sendMessage(ChatColor.DARK_AQUA+ "-----------------------------------------------------");
				sender.sendMessage("§a#1 §7▏ §9"+kills.get(0) + " §8- §a" + Stats.getKills(kills.get(0),kills.get(0),"USERNAME") + " Kills");
				sender.sendMessage("§e#2 §7▏  §9"+kills.get(1) + " §8- §a" + Stats.getKills(kills.get(1),kills.get(1),"USERNAME") + " Kills");
				sender.sendMessage("§c#3  §7▏ §9"+kills.get(2) +  " §8- §a" + Stats.getKills(kills.get(2),kills.get(2),"USERNAME") + " Kills");
				sender.sendMessage("§c#4  §7▏ §9"+kills.get(3) +  " §8- §a" + Stats.getKills(kills.get(3),kills.get(3),"USERNAME") + " Kills");
				sender.sendMessage("§c#5  §7▏ §9"+kills.get(4) +  " §8- §a" + Stats.getKills(kills.get(4),kills.get(4),"USERNAME") + " Kills");
				sender.sendMessage("§c#6  §7▏ §9"+kills.get(5) +  " §8- §a" + Stats.getKills(kills.get(5),kills.get(5),"USERNAME") + " Kills");
				sender.sendMessage("§c#7  §7▏ §9"+kills.get(6) +  " §8- §a" + Stats.getKills(kills.get(6),kills.get(6),"USERNAME") + " Kills");
				sender.sendMessage("§c#8  §7▏ §9"+kills.get(7) +  " §8- §a" + Stats.getKills(kills.get(7),kills.get(7),"USERNAME") + " Kills");
				sender.sendMessage("§c#9  §7▏ §9"+kills.get(8) +  " §8- §a" + Stats.getKills(kills.get(8),kills.get(8),"USERNAME") + " Kills");
				sender.sendMessage("§4#10  §7▏ §9"+kills.get(9) +  " §8- §a" + Stats.getKills(kills.get(9),kills.get(9),"USERNAME") + " Kills");

			}
			if(args[0].equalsIgnoreCase("winrate")){
				sender.sendMessage(ChatColor.DARK_AQUA+ "-----------------------------------------------------");
				sender.sendMessage("§6Now viewing§7: §7§lSurvival Games LeaderBoards §7(§5Win Rate§7)");
				sender.sendMessage(ChatColor.DARK_AQUA+ "-----------------------------------------------------");

				sender.sendMessage("§a#1 §7▏ §9"+winrate.get(0) + " §8- §a" + Stats.getWinRate(winrate.get(0),winrate.get(0),"USERNAME") + "% Win rate");
				sender.sendMessage("§e#2 §7▏  §9"+winrate.get(1) + " §8- §a" + Stats.getWinRate(winrate.get(1),winrate.get(1),"USERNAME") + "% Win rate");
				sender.sendMessage("§c#3  §7▏ §9"+winrate.get(2) +  " §8- §a" + Stats.getWinRate(winrate.get(2),winrate.get(2),"USERNAME") + "% Win rate");
				sender.sendMessage("§c#4  §7▏ §9"+winrate.get(3) +  " §8- §a" + Stats.getWinRate(winrate.get(3),winrate.get(3),"USERNAME") + "% Win rate");
				sender.sendMessage("§c#5  §7▏ §9"+winrate.get(4) +  " §8- §a" + Stats.getWinRate(winrate.get(4),winrate.get(4),"USERNAME") + "% Win rate");
				sender.sendMessage("§c#6  §7▏ §9"+winrate.get(5) +  " §8- §a" + Stats.getWinRate(winrate.get(5),winrate.get(5),"USERNAME") + "% Win rate");
				sender.sendMessage("§c#7  §7▏ §9"+winrate.get(6) +  " §8- §a" + Stats.getWinRate(winrate.get(6),winrate.get(6),"USERNAME") + "% Win rate");
				sender.sendMessage("§c#8  §7▏ §9"+winrate.get(7) +  " §8- §a" + Stats.getWinRate(winrate.get(7),winrate.get(7),"USERNAME") + "% Win rate");
				sender.sendMessage("§c#9  §7▏ §9"+winrate.get(8) +  " §8- §a" + Stats.getWinRate(winrate.get(8),winrate.get(8),"USERNAME") + "% Win rate");
				sender.sendMessage("§4#10  §7▏ §9"+winrate.get(9) +  " §8- §a" + Stats.getWinRate(winrate.get(9),winrate.get(9),"USERNAME") + "% Win rate");

			}
			if(args.length>=2){
				sender.sendMessage(Main.prefix + "§cToo many arguments");
				return true;
			}
		}else{
			sender.sendMessage(Main.prefix + "§cToo many arguments");

		}
	    return false;
	  }
	    
}
