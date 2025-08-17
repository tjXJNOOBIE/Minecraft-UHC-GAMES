package Commands;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.managers.MapManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class SG implements CommandExecutor{
	  public HashMap<String, String> cmddesc = new HashMap();

	
	  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args){
		  Player player = (Player) sender;
		  int i;
		    this.cmddesc.put(ChatColor.RED + "/" + ChatColor.GOLD + cmd.getName() + " createarena " + ChatColor.RED + "[arena]", 
		    	      "Create an arena");
		    	    
		    	    this.cmddesc.put(ChatColor.RED + "/" + ChatColor.GOLD + cmd.getName() + " addspawn " + ChatColor.RED + "[arena]", 
		    	      "Add a spawn point to an arena");
		    	    this.cmddesc.put(ChatColor.RED + "/" + ChatColor.GOLD + cmd.getName() + " teleport " + ChatColor.RED + 
		    	      "[arena] [spawn_id]", "Teleport to a certain spawn");
		    	    this.cmddesc.put(ChatColor.RED + "/" + ChatColor.GOLD + cmd.getName() + " join " + ChatColor.RED + "[game_id]", 
		    	      "Join a certain game");
		    	    this.cmddesc.put(
		    	      ChatColor.RED + "/" + ChatColor.GOLD + cmd.getName() + " setby " + ChatColor.RED + "[game_id] [name]", 
		    	      "Set a made by");
		    	    this.cmddesc.put(ChatColor.RED + "/" + ChatColor.GOLD + cmd.getName() + " sethub", "Set the hub");
		    	    this.cmddesc.put(ChatColor.RED + "/" + ChatColor.GOLD + cmd.getName() + " setlobby " + ChatColor.RED + "[arena]", 
		    	      "Set the lobby for a certain arena");
		    	    this.cmddesc.put(ChatColor.RED + "/" + ChatColor.GOLD + cmd.getName() + " arenalist", "See all the arenas");
	  if (((cmd.getName().equalsIgnoreCase("sg")) ||
		      (cmd.getName().equalsIgnoreCase("survivalgames"))) &&
		      (sender.hasPermission("sg.use")))
		    {
		      Player p = (Player)sender;
		      if (args.length == 0)
		      {
		        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0F, 3.0F);
		        sender.sendMessage("   ");
		        
		        sender.sendMessage(ChatColor.GOLD + "Plugin Commands are shown below");
		        sender.sendMessage("   ");
		        for (String name : this.cmddesc.keySet())
		        {
		          sender.sendMessage(getCmdMSG(name, (String)this.cmddesc.get(name)));
		          sender.sendMessage("   ");
		        }

		      }
		      else if (args[0].equalsIgnoreCase("createarena"))
		      {
		        if (args.length > 1)
		        {
		          String name = args[1];
		          WorldEditPlugin worldEditPlugin = null;
		          worldEditPlugin = (WorldEditPlugin)Bukkit.getServer().getPluginManager()
		            .getPlugin("WorldEdit");
		          //Player player = (Player)sender;
		          Main.getInstance().getConfig().set("arena." + name + ".pos1", 
		            worldEditPlugin.getSelection(player).getMinimumPoint());
		          Main.getInstance().getConfig().set("arena." + name + ".pos2", 
		            worldEditPlugin.getSelection(player).getMaximumPoint());
		          List<String> maps = Main.getInstance().getConfig().getStringList("maps");
		          maps.add(name);
		          Main.getInstance().getConfig().set("maps", maps);
		          Main.getInstance().saveConfig();
		          sender.sendMessage(
		            Main.getInstance().prefix + ChatColor.GOLD + "You've created a arena with the name: " + 
		            ChatColor.RED + name + ChatColor.GOLD + "!");
		        }
		      }
		      else if (args[0].equalsIgnoreCase("addspawn"))
		      {
		        if (args.length > 1)
		        {
		          MapManager manager2 = new MapManager(args[1]);
		          //Player player = (Player)sender;
		          manager2.addSpawn(player.getLocation());
		          sender.sendMessage(Main.getInstance().prefix + ChatColor.GOLD + "You've added a spawn to: " + 
		            ChatColor.RED + args[1] + ChatColor.GOLD + ", with the ID: " + ChatColor.RED +
		            manager2.spawns.size() + ChatColor.GOLD + "!");
		        }
		      }
		      else
		      {
		      
		        if (args[0].equalsIgnoreCase("teleport"))
		        {
		          if (args.length > 2)
		          {
		        
		            i = Integer.parseInt(args[2]);
		            String arena = args[1];
		            MapManager manager2 = new MapManager(arena);
		            manager2.loadSpawns();
		            player.teleport(manager2.getSpawn(i - 1));
		          }
		        }
		        else if (args[0].equalsIgnoreCase("join"))
		        {
		          if (args.length > 1)
		          {
		            Arena arena = (Arena)Main.getInstance().arenas.get(Integer.parseInt(args[1]));
		          }
		        }
		        else if (args[0].equalsIgnoreCase("arenalist"))
		        {
		          sender.sendMessage("    ");
		          sender.sendMessage(ChatColor.GOLD + "Games are shown below");
		          for (Arena arenas : Main.getInstance().arenas)
		          {
		            sender.sendMessage("    ");
		            sender.sendMessage(ChatColor.GOLD + "Map: " + ChatColor.RED + arenas.getMap().MAP_NAME + 
		              ChatColor.GOLD + " Joinable: " + ChatColor.RED + arenas.joinable + ChatColor.GOLD + 
		              " Players: " + ChatColor.RED + arenas.getIngame().size() + ChatColor.GOLD + 
		              " Min players: " + ChatColor.RED + arenas.minPlayers);
		          }
		          sender.sendMessage("    ");
		        }
		        else
		        {
		          Location location;
		          if (args[0].equalsIgnoreCase("setlobby"))
		          {
		            if (args.length > 1)
		            {
		            
		              location = player.getLocation();
		              Main.getInstance().getConfig().set("arena." + args[1] + ".lobby", location);
		              Main.getInstance().saveConfig();
		              player.sendMessage(ChatColor.GOLD + "You've set the lobby location for: " + ChatColor.RED + 
		                args[1] + ChatColor.GOLD + ".");
		            }
		          }
		          else if (args[0].equalsIgnoreCase("sethub"))
		          {
		         
		            Main.getInstance().getConfig().set("locations.lobby", player.getLocation());
		            Main.getInstance().saveConfig();
		            player.sendMessage(ChatColor.GOLD + "You've set the hub location!");
		          }
		          else if ((args[0].equalsIgnoreCase("setby")) && 
		            (args.length > 2))
		          {
		            for (Arena arena : Main.getInstance().arenas) {
		              if (arena.getID() == Integer.parseInt(args[1])) {
		                arena.setBy(args[2]);
		              }
		            }
		      
		          }
		        }
		      }
		      return false;
		  }
		    

			return false;
		  }
public String getCmdMSG(String cmd, String desc){
  return ChatColor.GOLD + cmd + ChatColor.RED + " : " + ChatColor.GOLD + desc;
}
}

