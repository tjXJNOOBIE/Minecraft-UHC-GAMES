package Events;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by TJ on 4/29/2017.
 */
public class BlockIgnite implements Listener{

    @EventHandler
    public void onIgnite(final BlockIgniteEvent event) {
        for (final Arena arena : Main.getInstance().arenas) {
            if ((arena.getIngame().contains(event.getPlayer()))
                    && (event.getCause().equals(BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL))) {
                final Location loc = event.getBlock().getLocation();

                final ItemStack flint = new ItemStack(Material.FLINT_AND_STEEL, 1,
                        (short) (event.getPlayer().getItemInHand().getDurability() + 16));
                event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().getHeldItemSlot(), flint);
                if (flint.getDurability() >= 64) {
                    event.getBlock().setType(Material.FIRE);
                    new BukkitRunnable() {
                        public void run() {
                            event.getPlayer().getInventory().removeItem(new ItemStack[] { flint });


                        }
                    }.runTaskTimer(Main.getInstance(), 5L, 5L);



                    new BukkitRunnable() {
                        public void run() {
                            if (arena.cleanup) {
                                loc.getBlock().setType(Material.AIR);
                                cancel();
                            }
                        }
                    }.runTaskTimer(Main.getInstance(), 20L, 20L);
                }
            }
        }

    }
}


