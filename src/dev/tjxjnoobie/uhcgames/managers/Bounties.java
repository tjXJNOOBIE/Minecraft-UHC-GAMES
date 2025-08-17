package dev.tjxjnoobie.uhcgames.managers;

import java.util.HashMap;
import java.util.Map;

import info.techwizmatt.ServerCore.API.CoinAPI;
import dev.tjxjnoobie.uhcgames.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Bounties
{
  public static Map<Player, Integer> bounties = new HashMap();
  
  public static int getBounty(Player player)
  {
    return bounties.get(player);
  }
  
  public static boolean hasBounty(Player player)
  {
    return bounties.containsKey(player);
  }
  
  public static void setBounty(Player player, Integer amount, Player by)
  {
    bounties.put(player, amount);
    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
      p.sendMessage(Main.prefix + by.getDisplayName() + " §7has set a bounty on " + player.getDisplayName() + " §7for §8{§a"+amount+"§8) §7coins§8!");
    }
  }
  
  public static void claimBounty(Player player, Player killer) {
    if (bounties.containsKey(player)) {
      CoinAPI.AddTokens(killer,bounties.get(player));
      for(Player ap : Bukkit.getOnlinePlayers()) {
        ap.sendMessage(Main.prefix + killer.getDisplayName() + " §7has claimed a bounty on " + player.getDisplayName() + " §7for §8(§a" + Bounties.getBounty(player) + "§8) §7coins§8!");
      }
    }
  }

  public static void claimWinnerBounty(Player player) {
    if (bounties.containsKey(player)) {
      CoinAPI.AddTokens(player,bounties.get(player));
      for(Player ap : Bukkit.getOnlinePlayers()) {
        ap.sendMessage(Main.prefix + player.getDisplayName() + " §7has claimed their own bounty of "+"§8(§a" + Bounties.getBounty(player) + "§8) §7coins§8!");
      }
      player.sendMessage(Main.prefix+"You've claimed your own bounty of §a"+getBounty(player)+" §7for winning the match and having a bounty");
    }
  }
}

