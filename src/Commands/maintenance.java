package Commands;

import info.techwizmatt.ServerCore.Rank.Rank;
import dev.tjxjnoobie.uhcgames.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class maintenance implements CommandExecutor{

	
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
			Player player = (Player) sender;
		if ((sender.isOp()|| Rank.getRankLevel(player)<9)) {
			if (!Main.maintance.contains(player)) {
				Main.maintance.add(player);
				sender.sendMessage(ChatColor.RED + "You turned maintenance mode on!");
			} else {
				Main.maintance.remove((Player) sender);
				sender.sendMessage(ChatColor.GREEN + "You turned maintenance mode off!");
			}
		}
		return true;
	}
}
