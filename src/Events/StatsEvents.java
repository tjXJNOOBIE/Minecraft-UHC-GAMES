package Events;

import dev.tjxjnoobie.uhcgames.other.DamageApi;
import dev.tjxjnoobie.uhcgames.other.Storage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.logging.Level;

/**
 * Created by TJ on 4/1/2017.
 */
public class StatsEvents implements Listener {





    @EventHandler
    public void onMiss(ProjectileLaunchEvent e) {
        Player shooter = (Player) e.getEntity().getShooter();
        Storage storage = new Storage();
        if (shooter.getItemInHand().getType() == Material.BOW && e.getEntity() instanceof Arrow) {
            storage.addBowMiss(shooter);
        } else {

            return;
        }

    }

    @EventHandler
    public void onDmgHit(EntityDamageByEntityEvent e) {


        Storage storage = new Storage();
        if (e.getDamager() instanceof Player) {
            Bukkit.getLogger().log(Level.INFO, "DMAAGED");

            Player player = (Player) e.getDamager();
            Bukkit.getLogger().log(Level.INFO, DamageApi.calculateDamageAddArmor(player, EntityDamageEvent.DamageCause.ENTITY_ATTACK, player.getLastDamage()) + " DAMAGED LOGGED");
            Bukkit.getLogger().log(Level.INFO, DamageApi.calculateDamageRemoveArmor(player, EntityDamageEvent.DamageCause.ENTITY_ATTACK, player.getLastDamage()) + " DAMAGED LOGGED NO ARMOR");

            if (player.getItemInHand().getType() == Material.WOOD_AXE || player.getItemInHand().getType() == Material.WOOD_SWORD) {
                storage.addSwingHit(player);
                return;
            }
            if (player.getItemInHand().getType() == Material.STONE_AXE || player.getItemInHand().getType() == Material.STONE_SWORD) {
                storage.addSwingHit(player);
                return;
            }
            if (player.getItemInHand().getType() == Material.IRON_AXE || player.getItemInHand().getType() == Material.IRON_SWORD) {
                storage.addSwingHit(player);
                return;
            }
            if (player.getItemInHand().getType() == Material.DIAMOND_AXE || player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                storage.addSwingHit(player);
                return;
            }
        }
            if ((e.getDamager() instanceof Arrow)) {
                Arrow a = (Arrow) e.getDamager();
                if ((a.getShooter() instanceof Player)) {
                    a.getShooter();
                    Player p = (Player) a.getShooter();

                    Damageable dp = (Damageable) e.getEntity();
                    if ((dp instanceof Player)) {
                        Player v = (Player) dp;
                        double ptviev = dp.getHealth();
                        Integer damage = Integer.valueOf((int) e.getFinalDamage());
                        if ((!dp.isDead())) {
                            storage.addBowHit(p);
                        }

                    }
                }
            }
        }





    @EventHandler
    public void onDmgMiss(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        Storage storage = new Storage();
        if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            if (player.getItemInHand().getType() == Material.WOOD_AXE || player.getItemInHand().getType() == Material.WOOD_SWORD) {
                storage.addSwing(player);
            }
            if (player.getItemInHand().getType() == Material.STONE_AXE || player.getItemInHand().getType() == Material.STONE_SWORD) {
                storage.addSwing(player);
            }
            if (player.getItemInHand().getType() == Material.IRON_AXE || player.getItemInHand().getType() == Material.IRON_SWORD) {
                storage.addSwing(player);
            }
            if (player.getItemInHand().getType() == Material.GOLD_AXE || player.getItemInHand().getType() == Material.GOLD_SWORD) {
                storage.addSwing(player);
            }
            if (player.getItemInHand().getType() == Material.DIAMOND_AXE || player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                storage.addSwing(player);
            }
        }
    }

    public static int getHealth(Player player) {
        return (int) StrictMath.ceil(damageable(player).getHealth());
    }
    public static Damageable damageable(Player player) {
        return player;
    }

}
