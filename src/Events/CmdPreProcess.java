package Events;

import dev.tjxjnoobie.uhcgames.Main;
import dev.tjxjnoobie.uhcgames.managers.Arena;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CmdPreProcess implements Listener{
	
	
	@EventHandler
	public void onPreprocess(PlayerCommandPreprocessEvent event) {
		if ((event.getMessage().equals("/d")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not disguise at this time."); 
			event.setCancelled(true);
			return;
		}	
		if ((event.getMessage().equals("/disguise")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not disguise at this time."); 
			event.setCancelled(true);
			return;
		}	
		if ((event.getMessage().equals("/dis")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not disguise at this time."); 
			event.setCancelled(true);
			return;
		}	
		if ((event.getMessage().equals("/ud")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not undisguise at this time."); 
			event.setCancelled(true);
			return;
		}	
		if ((event.getMessage().equals("/undisguise")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not undisguise at this time."); 
			event.setCancelled(true);
			return;
		}	
		if ((event.getMessage().equals("/undis")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not undisguise at this time."); 
			event.setCancelled(true);
			return;
		}
			if ((event.getMessage().equals("/und")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not undisguise at this time.");
			event.setCancelled(true);
			return;
		}
		if ((event.getMessage().equals("/fly")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not use this command at this time.");
			event.setCancelled(true);
			return;
		}
		if ((event.getMessage().equals("/gamemode")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not use this command at this time.");
			event.setCancelled(true);
			return;
		}
		if ((event.getMessage().equals("/effect")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not use this command at this time.");
			event.setCancelled(true);
			return;
		}
		if ((event.getMessage().equals("/give")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not use this command at this time.");
			event.setCancelled(true);
			return;
		}
		if ((event.getMessage().equals("/tp")) && Arena.joinable == false) {
			event.getPlayer().sendMessage(Main.prefix + "You may not use this command at this time.");
			event.setCancelled(true);
			return;
		}
		
	}

}
