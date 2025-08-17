package dev.tjxjnoobie.uhcgames.managers;

import dev.tjxjnoobie.uhcgames.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SpectatorManager {
    public void makeSpec(final Player livingEntity) {
        Main.getInstance().arenas.get(0).watching.add(livingEntity);
        livingEntity.setAllowFlight(true);
        livingEntity.setGameMode(GameMode.CREATIVE);
        livingEntity.setHealth(20);
        livingEntity.setFoodLevel(40);
        livingEntity.setFlying(true);
        livingEntity.setSaturation(200);
        for (Player p : Main.getInstance().arenas.get(0).getIngame()) {
            p.hidePlayer(livingEntity);
        }
        for (Player p : Main.getInstance().arenas.get(0).watching) {
            p.hidePlayer(livingEntity);
        }
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.hidePlayer(livingEntity);
        }
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 100, true),
                false);
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 100, true),
                false);
        livingEntity.addPotionEffect(
                new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1000, true), false);
        giveItmes(livingEntity);

        new BukkitRunnable() {
            public void run() {
                if (Arena.cleanuptime <= 30) {
                    livingEntity.removePotionEffect(PotionEffectType.INVISIBILITY);
                    livingEntity.removePotionEffect(PotionEffectType.WEAKNESS);
                    cancel();

                    livingEntity.setGameMode(GameMode.CREATIVE);
                    for (Arena arena : Main.getInstance().arenas) {
                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 100, true),
                                false);
                        livingEntity.addPotionEffect(
                                new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 1000, true), false);
                        livingEntity.setAllowFlight(true);
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 5L, 5L);

    }


    public static void giveItmes(Player player) {
        // Give Spectator items
        final ItemStack compass = new ItemStack(Material.COMPASS);
        final int remain = Main.getInstance().arenas.get(0).ingame.size();
        final ItemMeta compassmeta = compass.getItemMeta();
        compassmeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + remain + " Tributes Remain");
        final List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Teleport to the other " + remain + " player remaining");
        compassmeta.setLore(lore);
        compass.setItemMeta(compassmeta);
        final ItemStack eye = new ItemStack(Material.EYE_OF_ENDER);
        final ItemMeta eyemeta = eye.getItemMeta();
        List<String> eyelore = new ArrayList<String>();
        eyemeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Back to Lobby");
        eyelore.add("");
        eyemeta.setLore(eyelore);
        eye.setItemMeta(eyemeta);
        final ItemStack book = new ItemStack(Material.BOOK);
        final ItemMeta bookmeta = book.getItemMeta();
        List<String> booklore = new ArrayList<String>();
        bookmeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Play another game");
        booklore.add(ChatColor.DARK_AQUA + "Click to open the Survival Games menu");
        book.setItemMeta(bookmeta);

        compassmeta.setDisplayName(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + remain + " Tributes Remain");
        new BukkitRunnable() {
            public void run() {
                player.getInventory().setItem(4, compass);
                player.getInventory().setItem(8, eye);
                player.getInventory().setItem(0, book);
                book.setItemMeta(bookmeta);
                eye.setItemMeta(eyemeta);
                compass.setItemMeta(compassmeta);
                for (Player p : Main.getInstance().arenas.get(0).ingame) {
                    p.hidePlayer(player);
                }
                for (Player p : Main.getInstance().arenas.get(0).watching) {
                    p.hidePlayer(player);
                }

            }
        }.runTaskTimer(Main.getInstance(), 20 * 5, 20 * 5);
    }
}


  

