package Commands;

import java.util.ArrayList;

import dev.tjxjnoobie.uhcgames.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerList implements CommandExecutor{
	
	
	  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){
	  int i;
	    if ((cmd.getName().equalsIgnoreCase("who")) || (cmd.getName().equalsIgnoreCase("list")) || 
	      (cmd.getName().equalsIgnoreCase("l")))
	    {
	      ArrayList<String> oplayers = new ArrayList();
	      ArrayList<String> ingamearray = new ArrayList();
	      ArrayList<String> watchingarray = new ArrayList();
	      for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
	    	  String display = p2.getDisplayName();
	        oplayers.add(display);
	      }
	      for(Player ingame : Main.getInstance().arenas.get(0).ingame) {
	    		  String display2 = ingame.getDisplayName();
	    	  ingamearray.add(display2);
	    	  }
	      
	      for(Player watching : Main.getInstance().arenas.get(0).watching) {
	    		  String display3 = watching.getDisplayName();
	    	  watchingarray.add(display3);
	      }
	      int online = Main.getInstance().arenas.get(0).ingame.size() + Main.getInstance().arenas.get(0).watching.size();
	      sender.sendMessage(Main.getInstance().prefix + ChatColor.WHITE + "There are " + ChatColor.DARK_GRAY + "[" + 
	        ChatColor.GOLD + online+ ChatColor.DARK_GRAY + "/" + ChatColor.GOLD +
	        Bukkit.getMaxPlayers() + ChatColor.DARK_GRAY + "]" + ChatColor.WHITE + " players online" + 
	        ChatColor.DARK_GRAY + ".");
	      String s = ChatColor.translateAlternateColorCodes('&', "&8&m-&r &f&lInGame&8: ");
	      String ws = ChatColor.translateAlternateColorCodes('&', "&8&m-&r &f&lWatching&8: ");
	      for (i = 0; i < Main.getInstance().arenas.get(0).ingame.size(); i++) {
	        s = s + ingamearray.get(i) + ChatColor.DARK_GRAY + ", ";
	      }
	      for (i = 0; i < Main.getInstance().arenas.get(0).watching.size(); i++) {
	          ws = ws + watchingarray.get(i) + ChatColor.DARK_GRAY + ", ";
	        }
	      s = s.substring(0, s.length() - 4);
	      ws = ws.substring(0, ws.length() - 4);
	      sender.sendMessage(s);
	      sender.sendMessage(ws);
	    }
		return false;
	  }
}
