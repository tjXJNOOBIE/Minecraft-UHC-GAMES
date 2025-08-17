package Events;

import dev.tjxjnoobie.uhcgames.managers.Arena;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;

public class MiscEvents implements Listener{

	

		@SuppressWarnings("deprecation")
		@EventHandler
		public void onPing(final ServerListPingEvent  e) {
			if((Arena.joinable == true)) {
				e.setMotd("Recruiting");
				return;
		        }
		
			// ** Setting server's MOTD via gamestate to update via bungee **
			if((Arena.joinable == false)) {
				e.setMotd("IN-GAME");
				return;
			}
			}


		
		@EventHandler
		public void onSpawn(CreatureSpawnEvent event) {
			event.setCancelled(true);
		}
		

		@EventHandler
		public void onPhysics(BlockPhysicsEvent event) {
			if (event.getBlock().getType().equals(Material.VINE)) {
				event.setCancelled(true);
			}
		}

}
