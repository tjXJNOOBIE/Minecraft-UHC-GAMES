package Events;

import dev.tjxjnoobie.uhcgames.Main;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class InventoryDrag implements Listener {


	@EventHandler
	public void onDrag(InventoryDragEvent e) {
		if (Main.getInstance().arenas.get(0).watching.contains(e.getWhoClicked())) {
			e.setCancelled(true);
		}
		if (e.getInventory().getName().equalsIgnoreCase("§9§lClass Editor")) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public  void onMove(InventoryMoveItemEvent e) {
		if(e.getInitiator().getName().equalsIgnoreCase("§9§lClass Editor")) {
			e.setCancelled(true);
		}
		}
}
