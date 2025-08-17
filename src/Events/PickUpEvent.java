package Events;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickUpEvent implements Listener{

	
	@EventHandler
	public void onDrop(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		for (Arena arena : Main.getInstance().arenas) {
			if (arena.watching.contains(player)) {
				event.setCancelled(true);
			}
			if(Arena.canPickup == false) {
				event.setCancelled(true);
			}
		
		}
	}
}
