package Commands;

import dev.tjxjnoobie.uhcgames.managers.Sponsor;
import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SponsorCMD implements CommandExecutor {

    static boolean disabled = true;

    public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sponsor")) {

            for (Arena arenas : Main.getInstance().arenas) {
                if ((arenas.watching.contains(sender)) &&
                        (arenas.ingame.contains(Bukkit.getPlayer(args[0])))) {
                    Sponsor.players.put((Player) sender, Bukkit.getPlayer(args[0]));
                    Sponsor.openMenu((Player) sender);
                    sender.sendMessage(Main.prefix + "Sponsor menu for " + ChatColor.RED + Bukkit.getPlayer(args[0]).getDisplayName() + ChatColor.GRAY + " opened");

                }
            }

        }
        return false;
    }
}

