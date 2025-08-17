package dev.tjxjnoobie.uhcgames.commands;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import dev.tjxjnoobie.uhcgames.managers.PointManager;
import dev.tjxjnoobie.uhcgames.other.MapList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Commands
  implements CommandExecutor{
  public HashMap<String, String> cmddesc = new HashMap();
  public HashMap<Player, Player> confirm2 = new HashMap();
  public HashMap<Player, Integer> confirm = new HashMap();
  
  private String removeLastChar(String str)
  {
    return str.substring(0, str.length() - 1);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args)
  {
	  final Player player = (Player)sender;
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

    if ((cmd.getName().equalsIgnoreCase("setkills")) && 
      (sender.isOp()))
    {
      Player player1 = Bukkit.getPlayer(args[0]);
      PointManager manager = new PointManager();
      manager.setKills(player1, Integer.parseInt(args[1]));
    }
    if ((cmd.getName().equalsIgnoreCase("settotal")) && 
      (sender.isOp()))
    {
      Player player1 = Bukkit.getPlayer(args[0]);
      PointManager manager = new PointManager();
      manager.setTotal(player1, Integer.parseInt(args[1]));
    }
    if ((cmd.getName().equalsIgnoreCase("setwins")) && 
      (sender.isOp()))
    {
      Player player1 = Bukkit.getPlayer(args[0]);
      PointManager manager = new PointManager();
      manager.setWins(player1, Integer.parseInt(args[1]));
    }

    if ((cmd.getName().equalsIgnoreCase("spec")) && 
      (args.length > 0))
    {
      //final Player player = (Player)sender;
      for (Arena arena : Main.getInstance().arenas) {
        if (arena.watching.contains(player))
        {
          Player target = Bukkit.getServer().getPlayer(args[0]);
          if (arena.getIngame().contains(target))
          {
            player.teleport(target);
            player.addPotionEffect(
              new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 100, true), false);
            player.addPotionEffect(
              new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1000, true), false);
            player.setAllowFlight(true);
            player.setFlying(true);
            new BukkitRunnable()
            {
              public void run()
              {
                if (!player.getGameMode().equals(GameMode.CREATIVE)) {
                  player.setGameMode(GameMode.CREATIVE);
                }
              }
            }.runTaskTimer(Main.getInstance(), 5L, 5L);
          }
        }
      }
    }
    if ((cmd.getName().equalsIgnoreCase("vote")) || (cmd.getName().equalsIgnoreCase("v"))) {
      if (args.length > 0 && Arena.voting == true){
        Main.voting.vote((Player)sender, Integer.parseInt(args[0]));
        return false;
  		} else if (args.length == 0){
		MapList.getMapList(player);
      		return true;
      		}

    }
 
    
	return false;
  }
}
