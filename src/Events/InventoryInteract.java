package Events;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class InventoryInteract implements Listener{

	
	@EventHandler
	public void onInventory(InventoryInteractEvent event) {
		Player player = (Player) event.getWhoClicked();
		for (Arena a : Main.getInstance().arenas) {
			if (a.watching.contains(player)) {
				event.setCancelled(true);
			}
		}
	}
}
