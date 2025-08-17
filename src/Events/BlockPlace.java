package Events;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class BlockPlace implements Listener {

	public static HashMap<Block, Location> placed = new HashMap<>();



	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		for (Arena arena : Main.getInstance().arenas) {
			if (arena.watching.contains(event.getPlayer())) {
				event.setCancelled(true);
				return;
			}
			if(event.getBlockPlaced().getType() == Material.WOOD||
					event.getBlockPlaced().getType() == Material.COBBLESTONE ||event.getBlockPlaced()
					.getType() == Material.WORKBENCH){
				event.setCancelled(false);
				placed.put(event.getBlockPlaced(),event.getBlockPlaced().getLocation());
				return;
			}
			if ((!event.getPlayer().getItemInHand().getType().equals(Material.FLINT_AND_STEEL))
					&& (!Main.maintance.contains(event.getPlayer()))) {
				event.setCancelled(true);
			}
		}
		}


	@EventHandler
	public void onPlacee(BlockPlaceEvent e) {
		Block block = e.getBlock();
		Player player = e.getPlayer();
		if (e.getBlock().getType().equals(Material.FLINT_AND_STEEL)) {
			if (e.getBlock().getFace(block.getRelative(BlockFace.UP)) != null) {
				player.sendBlockChange(block.getLocation(), block.getType(), (byte) 0);
			}
			if (e.getPlayer().getItemInHand().getType().equals(Material.FLINT_AND_STEEL)) {
				ItemStack flintandsteal = new ItemStack(Material.FLINT_AND_STEEL, 1);
				if (flintandsteal.getDurability() == 64) {
					e.getBlock().setType(Material.FIRE);
				}
				if (e.getBlock().getType() == Material.AIR && player.getItemInHand().getType().equals(flintandsteal)) {
					e.getBlock().setType(Material.FIRE);
				}
			}
		}
	}
}