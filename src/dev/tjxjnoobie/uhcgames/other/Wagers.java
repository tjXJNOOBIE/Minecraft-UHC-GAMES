package dev.tjxjnoobie.uhcgames.other;

import com.sun.org.apache.regexp.internal.RE;
import info.techwizmatt.ServerCore.API.CoinAPI;
import info.techwizmatt.ServerCore.Rank.Rank;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by TJ on 1/7/2018.
 */
public class Wagers {

    public static Map<Player, Integer> wager = new HashMap<>();
    public static int pot = 50;
    public static int currentpot = 0;
    public static ArrayList<Player> donors = new ArrayList<>();


    public static int getWager(Player player) {
        if (!wager.containsKey(player)) {
            return 0;
        }
        return wager.get(player);
    }

    public static int getMaxPot() {
        return pot;
    }

    public static int getCurrentpot() {
        return currentpot;
    }

    public static boolean inPot(Player player) {
        return wager.containsKey(player);
    }

    public static void setWager(int wager) {
        currentpot = wager;
    }

    public static int addWager(int WagerToAdd, Player player) {
        if (!inPot(player)) {
            wager.put(player, WagerToAdd);
        } else {
            player.sendMessage(Main.prefix + "§cYou are already in the pot");
            return 0;
        }
        int addedwager = getCurrentpot() + WagerToAdd;
        currentpot = addedwager + currentpot;
        player.sendMessage(Main.prefix + "You've added §a" + WagerToAdd + " §7coins to the pot");
        CoinAPI.subtractTokens(player, WagerToAdd);
        Bukkit.broadcastMessage(Main.prefix + player.getDisplayName() + " §7Added §a" + WagerToAdd + "  §7coins to the pot for a total of §a" + currentpot + "§8/§a" + pot);

        return addedwager;
    }

    public static int removeWager(int RemovedWager, Player player) {
        if (Main.getInstance().arenas.get(0).watching.contains(player)) {
            return 0;
        }
        if (Arena.joinable && !inPot(player)) {
            return 0;
        }
        if (Arena.joinable && inPot(player)) {
            wager.remove(player);
            currentpot = currentpot - RemovedWager;
            CoinAPI.AddTokens(player, RemovedWager);
        } else if (!Arena.joinable && inPot(player)) {
            return 0;
        } else if (!Arena.joinable && !inPot(player)) {
            return 0;
        }

        return RemovedWager;
    }

    public static void giveWager(Player player, int Wager) {
        if(getCurrentpot() <= 0){
            Bukkit.getLogger().log(Level.INFO,"No pot collected");
            return;
        }
        if (!wager.containsKey(player)) {
            player.sendMessage(Main.prefix + "§cYou didn't win the wager since you didn't put any coins into the pot.");
            return;
        } else {
            Bukkit.broadcastMessage(Main.prefix + player.getDisplayName() + " §7has won a wager of §e" + currentpot + "§7!");
            player.sendMessage(Main.prefix + "§aYou won a wager of §e" + currentpot + "§a for winning the game!");
            CoinAPI.AddTokens(player, currentpot);
        }

    }

    public static void runPotIncrease(Player p) {

            if (Rank.getRankLevel(p) >= 2) {
                donors.add(p);
                Bukkit.getLogger().log(Level.INFO,"Currently in pot: " + donors.toString());
            }
            if (donors.size() > 1) {
                int increased = getMaxPot() * donors.size();
                pot = increased;
            }
        }



    public static void runPotDecrease(Player player) {
    if(donors.contains(player)){
        donors.remove(player);
        int decreased = getMaxPot() * donors.size();
        pot = decreased;
    }
    }
}
