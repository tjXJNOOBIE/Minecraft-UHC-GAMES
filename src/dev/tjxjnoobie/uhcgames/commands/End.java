package dev.tjxjnoobie.uhcgames.commands;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class End implements CommandExecutor{
  
  	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
  	  if(!sender.hasPermission("game.start")){
  	    sender.sendMessage(Main.prefix+ ChatColor.RED+"You don't have permission");
  	    return true;
  	  }
  	  if(sender.hasPermission("game.start")){
  	    sender.sendMessage(Main.prefix + "You've ended the game with " + Main.getInstance().arenas.get(0).ingame.size() + " players remaining.");
  	    for(Arena arena : Main.getInstance().arenas){
  	    	arena.stopGame();
		}
  	  }
  	  return false;
  	}
  	
  
}