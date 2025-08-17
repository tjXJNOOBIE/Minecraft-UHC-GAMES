package Events;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropEvent implements Listener{
	
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		for (Arena arena : Main.getInstance().arenas) {
			if (arena.watching.contains(player)) {
				event.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onDrop3(PlayerDropItemEvent event) {
		if (event.getPlayer().getWorld().getName().equals("MCSGLobby")) {
			event.setCancelled(true);
		}
		if(Main.getInstance().arenas.get(0).watching.contains(event.getPlayer())) {
			event.setCancelled(true);
			
		}
	}
	
}
