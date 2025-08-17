package Events;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockBreak implements Listener{
	
	
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (!Main.maintance.contains(event.getPlayer())) {
			for (final Arena arena : Main.getInstance().arenas) {
				if (arena.getIngame().contains(event.getPlayer())) {
					if ((event.getBlock().getType().equals(Material.LEAVES))
							|| (event.getBlock().getType().equals(Material.LEAVES_2))
							|| (event.getBlock().getType().equals(Material.VINE))
							|| (event.getBlock().getType().equals(Material.LONG_GRASS))) {
						if (!event.getPlayer().getWorld().getName().equals("MCSGLobby")) {
							final BlockState state = event.getBlock().getState();
							Block b = event.getBlock();
							b.setType(Material.AIR);
							new BukkitRunnable() {
								public void run() {
									if (arena.cleanup) {
										state.update(true, false);
										cancel();
									}
								}
							}.runTaskTimer(Main.getInstance(), 20L, 20L);
						} else {
							event.setCancelled(true);
						}
					} else {
						event.setCancelled(true);
					}
				} else {
					event.setCancelled(true);
				}
			}
		}
	}


@EventHandler
public void onBreak2(BlockBreakEvent event) {
	if (event.getPlayer().getWorld().getName().equals("MCSGLobby")) {
		event.setCancelled(true);
	}
}
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent event) {
		int id = event.getBlock().getTypeId();
		if(id == 10 || id == 11) {
			event.setCancelled(true);
		}
	}
}
