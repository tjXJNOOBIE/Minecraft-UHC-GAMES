package dev.tjxjnoobie.uhcgames.commands;

import info.techwizmatt.ServerCore.Rank.Rank;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Start implements CommandExecutor{
	
	
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		String playername = player.getName();
		if (args.length == 0 && Rank.getRankLevel(player) >=10 && Arena.joinable == true) {
			new BukkitRunnable(){
				@Override
				public void run() {
					Arena.minPlayers = 1;
				}
			}.runTaskLater(Main.getInstance(),20*2);
			Arena.prelobby = 10;
			Bukkit.broadcastMessage(Main.prefix + "The game has been force started by " + ((Player) sender).getDisplayName());
		}
		if(args.length == 0 && Rank.getRankLevel(player)<10) {
			sender.sendMessage(Main.prefix+ ChatColor.RED+"You don't have permission to use this command");
		}
		if(args.length > 0 && Rank.getRankLevel(player) >=10){
			sender.sendMessage(Main.prefix + ChatColor.RED + "Too many arguments!");
		}
		if(args.length == 0 && (Arena.joinable == false)){
			sender.sendMessage(Main.prefix + ChatColor.RED + "You may not start the game while it's running");
		}


			return true;
		}
				}


	
	
	
	


