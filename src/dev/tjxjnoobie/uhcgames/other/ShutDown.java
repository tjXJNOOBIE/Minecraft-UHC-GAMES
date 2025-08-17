package dev.tjxjnoobie.uhcgames.other;

import dev.tjxjnoobie.uhcgames.Main;

import org.bukkit.Server;
import org.bukkit.World;

public class ShutDown {
	
	
	
	public static void run() {
		Main.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			public void run() {


				Server server = Main.getPlugin().getServer();
			for (World world : server.getWorlds()) {
					world.getEntities().clear();
					server.unloadWorld(world, false);
				}

			}
		});
	}
}

