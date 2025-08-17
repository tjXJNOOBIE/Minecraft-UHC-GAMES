package dev.tjxjnoobie.uhcgames.commands;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.other.Storage;
import dev.tjxjnoobie.uhcgames.other.Wagers;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by TJ on 3/24/2017.
 */
public class Debug implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Storage storage = new Storage();
        Player player = (Player)sender;
        int swingpercent = (int)(((int)storage.getSwingHits(player) * 100.0f) / (int)storage.getSwings(player));
        int shotpercent = (int)(((int)storage.getBowHits(player) * 100.0f) / (int)storage.getBowMisses(player));
        int winrate = (int)(((int)storage.getWins(player) * 100.0f) / (int)storage.getPlayed(player));

        sender.sendMessage(ChatColor.ITALIC+"Information for current local game...");
        sender.sendMessage(Main.prefix+"Kills: " + storage.getKills(player));
        sender.sendMessage(Main.prefix+"KillStreak: " + storage.getStreak(player));
        sender.sendMessage(Main.prefix+"Deaths: " + storage.getDeaths(player));
        sender.sendMessage(Main.prefix+"Played: " + storage.getPlayed(player));
        sender.sendMessage(Main.prefix+"Won: " + storage.getWins(player));
        sender.sendMessage(Main.prefix + "Bow Shots: " + storage.getBowMisses(player));
        sender.sendMessage(Main.prefix + "Bow Hits: " + storage.getBowHits(player));
        sender.sendMessage(Main.prefix + "Bow Accuracy: " + shotpercent);
        sender.sendMessage(Main.prefix + "Sword Swings: " + storage.getSwings(player));
        sender.sendMessage(Main.prefix + "Sword Hits: " + storage.getSwingHits(player));
        sender.sendMessage(Main.prefix + "Hit Accuracy: " + swingpercent);
        sender.sendMessage(Main.prefix + "Damage Taken: " + storage.getDmgTaken(player));
        sender.sendMessage(Main.prefix + "Damage Given: " + storage.getDmgGiven(player));
        sender.sendMessage(Main.prefix + "Armour Tier: " + storage.getArcherTier(player));

        sender.sendMessage(Main.prefix + "Archer Tier: " + storage.getArcherTier(player));
        sender.sendMessage(Main.prefix + "Rusher Tier: " + storage.getRusherTier(player));
        sender.sendMessage(Main.prefix + "Healer Tier: " + storage.getDmgTaken(player));
        sender.sendMessage(Main.prefix + "Welder Tier: " + storage.getBruteTier(player));
        sender.sendMessage(Main.prefix + "Pot Max  " + Wagers.getMaxPot());
        sender.sendMessage(Main.prefix + "Current Pot: " + Wagers.getCurrentpot());

        sender.sendMessage(Main.prefix + "Map Rotation: " + Main.maplist);
        sender.sendMessage(Main.prefix +"Maps: " + Main.getInstance().getConfig().getStringList("maps"));
        return false;
    }

    }
