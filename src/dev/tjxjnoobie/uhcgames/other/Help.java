package dev.tjxjnoobie.uhcgames.other;

import dev.tjxjnoobie.uhcgames.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help {

	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(label.equalsIgnoreCase("help")) {
			if(args.length == 0) {
			sender.sendMessage("      §7§lPlayer Help Menu      ");
			sender.sendMessage(" ");
			sender.sendMessage(Main.prefix + "§8§l§7/hub §8- §dSends you to a hub");
			sender.sendMessage(Main.prefix + "§8§l>> §7/msg <player> §8- §dMessage a player");
			sender.sendMessage(Main.prefix + "§8§l>> §7/server [name] §8- §dTeleport between servers");
			sender.sendMessage(Main.prefix + "§8§l>> §7/sping §8- §dPing the CURRENT server your on");
			sender.sendMessage(Main.prefix + "§8§l>> §7/ping §8- §dPing to the overrall network");
			sender.sendMessage("                                                               ");
			return true;
			}
		}
		
		if(args[0].equalsIgnoreCase("staff")) {
	
			if(sender.hasPermission("blimp.staff")) {
				if(args.length == 1) {
				if((sender instanceof Player)) {
			sender.sendMessage("      §7§lStaff Help Menu     ");
			sender.sendMessage(" ");
			sender.sendMessage(Main.prefix + "§8§l>> §7/ban <player> §8- §dBans a player");
			sender.sendMessage(Main.prefix + "§8§l>> §7/unban <player> §8- §dUnbans a player");
			sender.sendMessage(Main.prefix + "§8§l>> §7/tempban <time> <player> §8- §dTemp bans a player");
			sender.sendMessage(Main.prefix + "§8§l>> §7/set<rank> <player> §8- §dSets a player rank");
			sender.sendMessage(Main.prefix + "§8§l>> §7/kill <player> §8- §dMurder's a player.");
			sender.sendMessage(Main.prefix + "§8§l>> §7/tp [player] §8- §dTeleport a player or teleport to a player");
			sender.sendMessage(Main.prefix + "§8§l>> §7/gmc [player] §8- §dSet's gamemode to creative");
			sender.sendMessage(Main.prefix + "§8§l>> §7/gms [player] §8- §dSet's gamemdoe to survival");
			sender.sendMessage(Main.prefix + "§8§l>> §7/heal [player] §8- §dHeal's you");
			sender.sendMessage(Main.prefix + "§8§l>> §7/playerinfo <player> §8- §d Show most nesscary information about a player");
			sender.sendMessage(Main.prefix + "§8§l>> §7/cc §8- §dClears chat");
			sender.sendMessage(Main.prefix + "§8§l>> §7/clear [player] §8- §d Clear's a player inventroy");
			sender.sendMessage(" ");
			sender.sendMessage(" ");
			sender.sendMessage(" ");

			sender.sendMessage("§c§lNOTE§f: §c§l[player] §c is optional. As §c§l<player> §cis need for the command to run");
			
			
		}
			}
		}
		return false;
	}
		return false;

}
}

