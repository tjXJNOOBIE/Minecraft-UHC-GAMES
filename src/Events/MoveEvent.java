package Events;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

public class MoveEvent implements Listener{
	
	@EventHandler
	public void onMove(final PlayerMoveEvent e) {
		final Player player = e.getPlayer();
			if(Main.getInstance().arenas.get(0).canMove == false && Main.getInstance().arenas.get(0).ingame.contains(player)) {
				final Location from = e.getFrom();
				final Location to = e.getTo();
				final double x = Math.floor(from.getX());
				final double z = Math.floor(from.getZ());

				if (((e.getTo().getX() != e.getFrom().getX()) || (e.getTo().getZ() != e.getFrom().getZ()))) {
					e.setTo(e.getFrom());
					return;
				}}









			
		for (Arena arenas : Main.getInstance().arenas) {
			if ((arenas.getIngame().contains(e.getPlayer())) && (!arenas.canMove)) {
				{
					return;
				}
			}
	if ((arenas.watching.contains(player) && (!arenas.canMove))) {
		player.setWalkSpeed(0.2F);
		player.removePotionEffect(PotionEffectType.JUMP);
		return;
	}
				}
	}

	@EventHandler
	public void onMove2(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(e.getPlayer().getLocation().getY() < -1 && e.getPlayer().getWorld().getName().equals("MCSGLobby")) {
			player.teleport(new Location(Bukkit.getWorld("MCSGLobby"), -17, 74, 3.30));
		}
	}
}
