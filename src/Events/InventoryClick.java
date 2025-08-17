package Events;

import dev.tjxjnoobie.uhcgames.Main;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClick implements Listener {

	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(Main.getInstance().arenas.get(0).watching.contains(e.getWhoClicked())) {
			e.setCancelled(true);
		}

		if  (e.getClickedInventory() != null && (e.getClickedInventory().getType() == InventoryType.ENCHANTING) &&
				(e.getCurrentItem().getType() == Material.INK_SACK) &&
				(e.getCurrentItem().getDurability() == 4)) {
			e.setCancelled(true);
		}
	}
}
